package com.android.fetch_rewards_2024.view.ui.sortedrest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.fetch_rewards_2024.R
import com.android.fetch_rewards_2024.view.adapter.FetchListAdapter
import com.android.fetch_rewards_2024.vm.SortedRestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortedRestFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var recyclerViewRest: RecyclerView
    private lateinit var swipeToUpdateRest: SwipeRefreshLayout
    private lateinit var progressBarRest: ProgressBar
    private val sortedRestViewModel: SortedRestViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sortedRestViewModel.getFetchListFromRemoteAndSort()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_sorted_rest, container, false)
        swipeToUpdateRest = view.findViewById(R.id.swipeToUpdateRest)
        progressBarRest = view.findViewById(R.id.progressBarRest)
        recyclerViewRest = view.findViewById(R.id.recyclerViewRest)
        recyclerViewRest.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeToUpdateRest.setOnRefreshListener(this)
        sortedRestViewModel.fetchListLiveDataRemote.observe(viewLifecycleOwner) {

            when {
                it == null -> {
                    recyclerViewRest.visibility = View.GONE
                    progressBarRest.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT)
                        .show()
                }

                it.isEmpty() -> progressBarRest.visibility = View.VISIBLE
                else -> {
                    recyclerViewRest.visibility = View.VISIBLE
                    recyclerViewRest.adapter = FetchListAdapter(it)
                    progressBarRest.visibility = View.GONE
                }
            }
        }
    }

    override fun onRefresh() {
        sortedRestViewModel.getFetchListFromRemoteAndSort()
        swipeToUpdateRest.isRefreshing = false
        Toast.makeText(requireContext(), "Refreshing", Toast.LENGTH_SHORT).show()
    }
}