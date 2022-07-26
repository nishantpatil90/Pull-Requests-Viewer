package com.nishant.pullrequestsviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishant.pullrequestsviewer.data.Repository
import com.nishant.pullrequestsviewer.model.PullRequest
import com.nishant.pullrequestsviewer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var fetchListJob: Job? = null
    private val _pullRequestList: MutableStateFlow<Resource<List<PullRequest>>> =
        MutableStateFlow(Resource.Loading(emptyList()))

    val pullRequestList = _pullRequestList.asStateFlow()

    fun fetchPullRequests() {
        fetchListJob?.cancel()
        fetchListJob = viewModelScope.launch {
            val list = repository.getClosedPullRequests().first()
            if (this.isActive) {
                _pullRequestList.value = list
            }
        }
    }
}
