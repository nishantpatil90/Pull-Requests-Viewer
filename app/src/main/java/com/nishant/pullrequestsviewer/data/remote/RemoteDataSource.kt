package com.nishant.pullrequestsviewer.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: GithubApiService) {

    suspend fun getClosedPullRequestList() = apiService.getClosedPullRequests()
}
