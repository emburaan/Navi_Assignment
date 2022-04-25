package com.sumit.navi.ui.allpulls

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sumit.navi.R
import com.sumit.navi.databinding.FragmentAllpullsBinding
import com.sumit.navi.other.Status
import com.sumit.navi.ui.allpulls.adapter.AllPullsAdapter
import com.sumit.navi.utils.hiltNavGraphViewModels
import com.sumit.navi.utils.isInternetConnected
import com.sumit.navi.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllPullsFragment : Fragment(R.layout.fragment_allpulls) {
    private val mainViewModel by hiltNavGraphViewModels<AllPullsViewModel>(R.id.nav_graph)
    private val binding by viewBinding(FragmentAllpullsBinding::bind)
    private lateinit var adapter: AllPullsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().isInternetConnected()) {
            mainViewModel.getPulls()
        } else {
            showDialog(
                requireContext(),
                getString(R.string.no_internet),
                getString(R.string.no_internet_errormsg),
                getString(R.string.ok)
            ) { _, _ ->
                activity?.finish()
            }
        }
        setupUi()
        setupObserver()
    }

    private fun setupObserver() {
        mainViewModel.pulls.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rvPulls.visibility = View.VISIBLE
                    it.data.let { res ->
                        res?.let { pulls -> adapter.submitList(pulls) }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvPulls.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rvPulls.visibility = View.VISIBLE
                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showDialog(
        context: Context, title: String, msg: String,
        positiveBtnText: String,
        positiveBtnClickListener: DialogInterface.OnClickListener
    ): AlertDialog {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(msg)
            .setCancelable(true)
            .setPositiveButton(positiveBtnText, positiveBtnClickListener)
        val alert = builder.create()
        alert.show()
        return alert
    }

    private fun setupUi() {
        adapter = AllPullsAdapter(requireContext())
        binding.rvPulls.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPulls.adapter = adapter
    }
}