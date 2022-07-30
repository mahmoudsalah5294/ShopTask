package com.mahmoudsalah.shoptask.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudsalah.shoptask.databinding.FragmentHomeBinding
import com.mahmoudsalah.shoptask.model.Result
import com.mahmoudsalah.shoptask.repo.Repo
import com.mahmoudsalah.shoptask.repo.RepoInterface
import com.mahmoudsalah.shoptask.utility.URLSClass

class HomeFragment : Fragment(),ItemClickInterface {
    private lateinit var viewModel: HomeViewModel
    private lateinit var repo:RepoInterface
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        observeViewModel(viewModel)
        return homeBinding.root
    }

    private fun observeViewModel(viewModel: HomeViewModel) {
        viewModel.mutableItemData.observe(viewLifecycleOwner, Observer {
            updateUI(it.results,null)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            updateUI(null,it)
        })
    }

    private fun updateUI(results: List<Result>?, error: String?) {
        if (error.isNullOrBlank()){
            val recyclerView = homeBinding.recyclerView
            results?.let {
                setAdapter(recyclerView,it,this@HomeFragment)
            }
        }else {
            error?.let {
                Toast.makeText(context, "$it",Toast.LENGTH_LONG).show()
            }
        }
    }

    //    private fun updateUI(results: List<Result>) {
//        val recyclerView = homeBinding.recyclerView
//        setAdapter(recyclerView,results,this@HomeFragment)
////        homeBinding.swipeTpRefresh.isRefreshing = false
//    }
    private fun setAdapter(recyclerView: RecyclerView, data:List<Result>, itemClickInterface: ItemClickInterface){
        recyclerView.apply {
            this.layoutManager = GridLayoutManager(
                requireContext(), 2, RecyclerView.VERTICAL, false
            )
            adapter = HomeRecyclerViewAdapter(data, context, itemClickInterface)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = Repo()
        val factory = ViewModelFactory(repo,URLSClass.mainItems)
        viewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        viewModel.getItems()

    }

    override fun onClickItem(result: Result) {
          val action:NavDirections = HomeFragmentDirections.actionHomeFragmentToItemDetailsFragment(result)
          findNavController().navigate(action)
    }

}