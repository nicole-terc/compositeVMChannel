package nstv.compositevmchannel.useCase

import nstv.compositevmchannel.data.favoriteDB.FavoriteDAO
import nstv.compositevmchannel.data.model.Elephant
import nstv.compositevmchannel.data.model.Favorite

class UpdateFavoriteStatusUseCase(private val favoriteDao: FavoriteDAO) {
    suspend operator fun invoke(elephant: Elephant, favorite: Boolean) {
        val favoriteItem = Favorite(elephant.id)

        if (favorite) {
            favoriteDao.save(favoriteItem)
        } else {
            favoriteDao.delete(favoriteItem)
        }
    }
}
