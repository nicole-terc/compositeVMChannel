package nstv.compositevmchannel.compositeVM.delegate

import nstv.compositevmchannel.compositeVM.BaseViewModelDelegate
import nstv.compositevmchannel.data.model.Event
import nstv.compositevmchannel.data.model.Event.Load
import nstv.compositevmchannel.data.model.State
import nstv.compositevmchannel.useCase.LoadItemsUseCase

class LoadViewModelDelegate(
    val loadItemsUseCase: LoadItemsUseCase
) : BaseViewModelDelegate() {

    override suspend fun onEvent(event: Event): Boolean {
        if (event is Load) {
            loadItems()
            return true
        }
        return false
    }

    private suspend fun loadItems() {
        state.offer(State.Loading)

        val items = loadItemsUseCase()
        if (items.isEmpty()) {
            state.offer(State.Error("Error fetching items"))
        } else {
            state.offer(State.ShowData(items))
        }
    }
}
