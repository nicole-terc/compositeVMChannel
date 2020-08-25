package nstv.compositevmchannel.compositeVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


open class CompositeViewModel<S, E>(
    private val delegates: List<ViewModelDelegate<S, E>>
) : ViewModel() {
    val state: Channel<S> = Channel()

    init {
        registerDelegates()
    }

    private fun registerDelegates() {
        viewModelScope.launch {
            delegates.forEach { delegate ->
                delegate.subscribe()
                    .consumeAsFlow()
                    .collect {
                        state.send(it)
                    }
            }
        }
    }

    fun send(event: E) {
        viewModelScope.launch {
            delegates.any {
                it.onEvent(event)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        delegates.forEach {
            it.clear()
        }
        state.close()
    }
}
