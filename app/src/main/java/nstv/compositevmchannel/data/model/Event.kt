package nstv.compositevmchannel.data.model

sealed class Event {
    object Load : Event()
    object Clear : Event()
    class UpdateFavorite(elephant: Elephant) : Event()
}
