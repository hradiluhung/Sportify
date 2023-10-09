package com.adiluhung.sportify.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adiluhung.sportify.core.R
import com.adiluhung.sportify.core.databinding.ItemListPlayerBinding
import com.adiluhung.sportify.core.domain.model.Player
import com.bumptech.glide.Glide


class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.ListViewHolder>() {

    private var listData = ArrayList<Player>()
    var onItemClick: ((Player) -> Unit)? = null

    fun setData(newListData: List<Player>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_player, parent, false)
        )


    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListPlayerBinding.bind(itemView)
        fun bind(data: Player) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.strThumb)
                    .into(ivPlayer)
                tvPlayerName.text = data.strPlayer
                tvBornDate.text = data.dateBorn
                tvDescription.text = data.strDescriptionEN
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}