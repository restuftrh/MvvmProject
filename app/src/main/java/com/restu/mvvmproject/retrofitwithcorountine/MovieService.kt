package com.restu.mvvmproject.retrofitwithcorountine

import retrofit2.Response
import retrofit2.http.*


interface MovieService {

    @GET("/movies")
    suspend fun getMovie(): Response<Movie>

//    @GET("/albums")
//    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>
//
//    @GET("/albums/{id}")
//    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>
//
//    @POST("/albums")
//    suspend fun uploadAlbum(@Body album: AlbumsItem): Response<AlbumsItem>


}