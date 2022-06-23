package org.d3if2134.luas_segitiga.ui.objek_segitiga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2134.luas_segitiga.databinding.FragmentObjekSegitigaBinding
import org.d3if2134.luas_segitiga.network.ApiStatus

class ObjekSegitigaFragment: Fragment() {
    private val viewModel: ObjekSegitigaViewModel by lazy {
        ViewModelProvider(this).get(ObjekSegitigaViewModel::class.java)
    }
    private lateinit var binding: FragmentObjekSegitigaBinding
    private lateinit var myAdapter: ObjekSegitigaAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentObjekSegitigaBinding.inflate(layoutInflater, container, false)
        myAdapter = ObjekSegitigaAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }ApiStatus.SUCCESS -> {
            binding.progressBar.visibility = View.GONE
        }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}