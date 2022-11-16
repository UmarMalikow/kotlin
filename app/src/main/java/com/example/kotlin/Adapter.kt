package com.example.kotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class Adapter(onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<Adapter.CelebritiesViewHolder>(), View.OnClickListener {

    private var listCelebrity: MutableList<Model>? = null
    private val onItemClickListener: OnItemClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listCelebrity: MutableList<Model>?) {
        this.listCelebrity = listCelebrity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebritiesViewHolder {
        return CelebritiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_neme, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CelebritiesViewHolder, position: Int) {
        holder.itemView.setOnClickListener(this)
        holder.onBind(listCelebrity!![position])
    }

    override fun getItemCount(): Int {
        return listCelebrity!!.size
    }

    override fun onClick(view: View) {
        onItemClickListener.onClick(view.tag as Model)
        listCelebrity!!.clear()
    }

    class CelebritiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivImage: ImageView
        private val tvName: TextView

        fun onBind(celebrities: Model) {
            Glide.with(ivImage.context).load(celebrities.imageUrl).into(ivImage)
            tvName.text = celebrities.name
            itemView.tag = celebrities
        }

        init {
            ivImage = view.findViewById(R.id.iv_image)
            tvName = view.findViewById(R.id.tv_name)
        }
    }

    init {
        this.onItemClickListener = onItemClickListener
    }
}