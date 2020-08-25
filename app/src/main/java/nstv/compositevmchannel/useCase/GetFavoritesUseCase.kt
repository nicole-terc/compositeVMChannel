package nstv.compositevmchannel.useCase

import nstv.compositevmchannel.data.favoriteDB.FavoriteDAO
import nstv.compositevmchannel.data.model.Favorite

class GetFavoritesUseCase(private val favoriteDao: FavoriteDAO) {
    suspend operator fun invoke(): List<Favorite> = favoriteDao.getAll()
}
