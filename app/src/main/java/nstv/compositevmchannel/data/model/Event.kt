package nstv.compositevmchannel.data.model

sealed class Event {
    object Load : Event()
    object Clear : Event()
    class UpdateFavorite(val elephant: Elephant, val isFavorite: Boolean) : Event()
}
