package com.nishant.pullrequestsviewer.data

import com.nishant.pullrequestsviewer.data.remote.RemoteDataSource
import com.nishant.pullrequestsviewer.model.PullRequest
import com.nishant.pullrequestsviewer.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource): BaseApiResponse() {

    fun getClosedPullRequests(): Flow<Resource<List<PullRequest>>> {

        return flow {
            emit(safeApiCall { remoteDataSource.getClosedPullRequestList() })
        }.flowOn(Dispatchers.IO)
    }
}
