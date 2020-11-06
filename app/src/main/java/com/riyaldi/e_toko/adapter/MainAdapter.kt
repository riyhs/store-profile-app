package com.riyaldi.e_toko.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riyaldi.e_toko.ProductDetailActivity
import com.riyaldi.e_toko.R
import com.riyaldi.e_toko.model.ProdukModel
import kotlinx.android.synthetic.main.product_card.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainAdapter (private val produk: List<ProdukModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @InternalCoroutinesApi
        fun bind(item: ProdukModel) {
            with(itemView) {
                tv_name.text = item.nama
                tv_desc.text = item.deskripsi
                tv_price.text = item.harga.toString()

                Glide
                    .with(itemView.context)
                    .load(item.foto)
                    .centerCrop()
                    .into(iv_card_product)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ProductDetailActivity::class.java)
                    intent.putExtra("product",
                        ProdukModel(
                            item.nama,
                            item.harga,
                            item.deskripsi,
                            item.jenis,
                            item.ukuran,
                            item.foto
                        )
                    )

                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(produk[position])
    }

    override fun getItemCount(): Int = produk.size
}