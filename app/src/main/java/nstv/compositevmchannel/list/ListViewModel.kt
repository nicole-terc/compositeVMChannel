package nstv.compositevmchannel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import nstv.compositevmchannel.data.model.Event
import nstv.compositevmchannel.data.model.State
import nstv.compositevmchannel.useCase.LoadItemsUseCase

class ListViewModel(
    val loadItemsUseCase: LoadItemsUseCase
) : ViewModel() {
    val state: Channel<State> = Channel()

    fun send(event: Event) {
        when (event) {
            is Event.Load -> loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            state.offer(State.Loading)

            val items = loadItemsUseCase()

            if (items.isEmpty()) {
                state.offer(State.Error("Error fetching items"))
            } else {
                state.offer(State.ShowData(items))
            }
        }
    }
}
