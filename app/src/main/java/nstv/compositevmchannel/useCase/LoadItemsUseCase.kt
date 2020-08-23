package nstv.compositevmchannel.useCase

import nstv.compositevmchannel.data.DataApi
import nstv.compositevmchannel.data.model.Elephant

const val EMPTY_IMG_URL = "https://elephant-api.herokuapp.com/pictures/missing.jpg"

class LoadItemsUseCase(private val api: DataApi) {
    suspend operator fun invoke(): List<Elephant> = api.getAll().filter { !it.name.isNullOrEmpty() && it.imageUrl != EMPTY_IMG_URL }
}
