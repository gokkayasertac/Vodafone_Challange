package com.sertac.core.domain.api

import com.sertac.core.domain.repository.Repository
import com.sertac.core.domain.userprofile.UserProfileResponse
import com.sertac.core.domain.userrepository.UserRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("repositories")
    suspend fun getRepositories(): Response<List<Repository?>?>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") userName: String): Response<UserProfileResponse>

    @GET("users/{user}/repos")
    suspend fun getUserRepositories(@Path("user") userName: String): Response<List<UserRepository?>?>

    /*
    https://api.github.com/repositories
    https://api.github.com/users/traex
    https://api.github.com/users/traex/repos
     */
}