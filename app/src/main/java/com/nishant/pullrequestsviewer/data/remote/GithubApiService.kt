package com.nishant.pullrequestsviewer.data.remote

import com.nishant.pullrequestsviewer.model.PullRequest
import retrofit2.Response
import retrofit2.http.GET

interface GithubApiService {

    @GET("repos/nishantpatil90/Pull-Requests-Viewer/pulls?state=closed")
    suspend fun getClosedPullRequests(): Response<List<PullRequest>>
}
