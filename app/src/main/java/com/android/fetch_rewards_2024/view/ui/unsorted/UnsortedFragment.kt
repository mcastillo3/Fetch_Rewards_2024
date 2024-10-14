package com.android.fetch_rewards_2024.view.ui.unsorted

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
import com.android.fetch_rewards_2024.vm.UnsortedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnsortedFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var recyclerViewUnsorted: RecyclerView
    private lateinit var swipeToUpdateUnsorted: SwipeRefreshLayout
    private lateinit var progressBarUnsorted: ProgressBar
    private val unsortedViewModel: UnsortedViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        unsortedViewModel.getFetchListFromRemote()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_unsorted, container, false)
        swipeToUpdateUnsorted = view.findViewById(R.id.swipeToUpdateUnsorted)
        progressBarUnsorted = view.findViewById(R.id.progressBarUnsorted)
        recyclerViewUnsorted = view.findViewById(R.id.recyclerViewUnsorted)
        recyclerViewUnsorted.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeToUpdateUnsorted.setOnRefreshListener(this)
        unsortedViewModel.fetchListLiveDataUnsorted.observe(viewLifecycleOwner) {

            when {
                it == null -> {
                    recyclerViewUnsorted.visibility = View.GONE
                    progressBarUnsorted.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT)
                        .show()
                }

                it.isEmpty() -> progressBarUnsorted.visibility = View.VISIBLE
                else -> {
                    recyclerViewUnsorted.visibility = View.VISIBLE
                    recyclerViewUnsorted.adapter = FetchListAdapter(it)
                    progressBarUnsorted.visibility = View.GONE
                }
            }
        }
    }

    override fun onRefresh() {
        unsortedViewModel.getFetchListFromRemote()
        swipeToUpdateUnsorted.isRefreshing = false
        Toast.makeText(requireContext(), "Refreshing", Toast.LENGTH_SHORT).show()
    }
}