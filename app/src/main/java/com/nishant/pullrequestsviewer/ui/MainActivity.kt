package com.nishant.pullrequestsviewer.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.nishant.pullrequestsviewer.R
import com.nishant.pullrequestsviewer.databinding.ActivityMainBinding
import com.nishant.pullrequestsviewer.ui.adapter.PullRequestAdapter
import com.nishant.pullrequestsviewer.utils.STATUS
import com.nishant.pullrequestsviewer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { PullRequestAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.pullRequestList.adapter = adapter

        viewModel.fetchPullRequests()

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing = false
            viewModel.fetchPullRequests()
        }

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.pullRequestList.collectLatest {
                when (it.status) {
                    STATUS.LOADING -> {
                        updateView(ViewType.LOADING)
                    }

                    STATUS.ERROR -> {
                        updateView(ViewType.ERROR)
                    }

                    STATUS.SUCCESS -> {
                        val data = it.data

                        if (!data.isNullOrEmpty()) {
                            updateView(ViewType.LIST)
                            adapter.submitList(data)
                        } else {
                            updateView(ViewType.EMPTY)
                        }
                    }
                }
            }
        }
    }

    private fun updateView(type: ViewType) {
        binding.pullRequestList.isVisible = type == ViewType.LIST
        binding.progressView.isVisible = type == ViewType.LOADING
        binding.errorView.isVisible = type == ViewType.ERROR || type == ViewType.EMPTY

        val errorString = when (type) {
            ViewType.EMPTY -> getString(R.string.empty_list_message)
            ViewType.ERROR -> getString(R.string.error_message)
            else -> null
        }

        errorString?.let {
            binding.errorView.text = it
        }
    }

    enum class ViewType {
        LIST,
        ERROR,
        EMPTY,
        LOADING
    }
}