package nstv.compositevmchannel.data.model

sealed class State {
    object Loading : State()
    data class ShowData(val items: List<Elephant>) : State()
    data class Error(val error: String) : State()
}
