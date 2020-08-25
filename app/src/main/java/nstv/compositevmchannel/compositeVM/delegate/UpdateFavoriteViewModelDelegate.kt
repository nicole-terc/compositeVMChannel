package nstv.compositevmchannel.compositeVM.delegate

import nstv.compositevmchannel.compositeVM.BaseViewModelDelegate
import nstv.compositevmchannel.data.model.Event
import nstv.compositevmchannel.useCase.UpdateFavoriteStatusUseCase

class UpdateFavoriteViewModelDelegate(
    val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : BaseViewModelDelegate() {
    override suspend fun onEvent(event: Event): Boolean {
        if (event is Event.UpdateFavorite) {
            updateFavorite(event)
            return true
        }
        return false
    }

    private suspend fun updateFavorite(event: Event.UpdateFavorite) {
        updateFavoriteStatusUseCase(event.elephant, event.isFavorite)
    }
}
