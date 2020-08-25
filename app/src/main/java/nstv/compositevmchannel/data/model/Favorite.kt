package nstv.compositevmchannel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey val itemId: String
)
