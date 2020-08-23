package nstv.compositevmchannel.compositeVM

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

abstract class ViewModelDelegate<S, E> {

    protected val state: Channel<S> = Channel()

    abstract suspend fun onEvent(event: E): Boolean

    fun subscribe(): ReceiveChannel<S> = state

    fun clear() {
        state.close()
    }
}
