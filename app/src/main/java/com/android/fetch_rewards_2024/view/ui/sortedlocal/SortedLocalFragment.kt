package com.android.fetch_rewards_2024.view.ui.sortedlocal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.fetch_rewards_2024.R
import com.android.fetch_rewards_2024.view.adapter.FetchListAdapter
import com.android.fetch_rewards_2024.vm.SortedLocalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortedLocalFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var swipeToUpdateLocal: SwipeRefreshLayout
    private lateinit var progressBarLocal: ProgressBar
    private lateinit var recyclerViewLocal: RecyclerView
    private lateinit var emptyLocalRl: RelativeLayout
    private val sortedLocalViewModel: SortedLocalViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sortedLocalViewModel.getFetchListFromRemoteAndInsertToLocal()
        sortedLocalViewModel.getFetchListFromLocal()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_sorted_local, container, false)
        swipeToUpdateLocal = view.findViewById(R.id.swipeToUpdateLocal)
        progressBarLocal = view.findViewById(R.id.progressBarLocal)
        emptyLocalRl = view.findViewById(R.id.emptyLocalRl)
        recyclerViewLocal = view.findViewById(R.id.recyclerViewLocal)
        recyclerViewLocal.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeToUpdateLocal.setOnRefreshListener(this)
        sortedLocalViewModel.fetchListLiveDataLocal.observe(viewLifecycleOwner) {

            when {
                it == null -> {
                    recyclerViewLocal.visibility = View.GONE
                    progressBarLocal.visibility = View.VISIBLE
                    emptyLocalRl.visibility = View.GONE
                    Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT)
                        .show()
                }

                it.isEmpty() -> {
                    recyclerViewLocal.visibility = View.GONE
                    emptyLocalRl.visibility = View.VISIBLE
                    progressBarLocal.visibility = View.GONE
                }

                else -> {
                    recyclerViewLocal.visibility = View.VISIBLE
                    recyclerViewLocal.adapter = FetchListAdapter(it)
                    progressBarLocal.visibility = View.GONE
                    emptyLocalRl.visibility = View.GONE
                }
            }
        }
    }

    override fun onRefresh() {
        sortedLocalViewModel.getFetchListFromRemoteAndInsertToLocal()
        swipeToUpdateLocal.isRefreshing = false
        Toast.makeText(requireContext(), "Refreshing", Toast.LENGTH_SHORT).show()
    }
}