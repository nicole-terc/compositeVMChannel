package nstv.compositevmchannel.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.image
import kotlinx.android.synthetic.main.item_list.view.name
import nstv.compositevmchannel.R
import nstv.compositevmchannel.data.model.Elephant

class ListAdapter(private val itemClickListener: (Elephant) -> Unit = {}) : ListAdapter<Elephant, ItemViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.image
    private val name = itemView.name

    fun bind(item: Elephant, itemClickListener: (Elephant) -> Unit = {}) {
        name.text = item.name

        Glide.with(itemView.context)
            .load(item.imageUrl)
            .placeholder(R.color.backgroundTransparent)
            .into(image)

        itemView.setOnClickListener {
            itemClickListener(item)
        }
    }
}

internal class ItemDiffCallback : DiffUtil.ItemCallback<Elephant>() {
    override fun areItemsTheSame(oldItem: Elephant, newItem: Elephant): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Elephant, newItem: Elephant): Boolean = oldItem == newItem

}
