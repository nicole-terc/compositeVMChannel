package nstv.compositevmchannel.data

import nstv.compositevmchannel.data.model.Elephant
import retrofit2.http.GET

interface DataApi {
    @GET("/elephants")
    suspend fun getAll(): List<Elephant>
}
