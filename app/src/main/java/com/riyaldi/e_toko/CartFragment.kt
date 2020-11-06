package com.riyaldi.e_toko

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.riyaldi.e_toko.model.ProdukModel
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.transaksi_card.view.*


class CartFragment : Fragment() {

    private lateinit var mAdapter: FirestoreRecyclerAdapter<ProdukModel, CartViewHolder>
    private val mFirestore = FirebaseFirestore.getInstance()
    private val carCollection = mFirestore.collection("cart")
    private var mQuery = carCollection.limit(500)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_beli_all.visibility = View.VISIBLE

        rv_transaction.setHasFixedSize(true)
        rv_transaction.layoutManager = LinearLayoutManager(context)

        setAdapter(mQuery)
        bt_beli_all.setOnClickListener {
            deleteAllData()
            bt_beli_all.visibility = View.INVISIBLE
        }
    }

    inner class CartViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(cart: ProdukModel) {
            view.apply {
                val name = cart.nama
                val price = cart.harga

                tv_transaksi_title.text = price.toString()
                tv_transaksi_nama_produk.text = name
            }
        }
    }

    private fun setAdapter(query: Query) {
        val options = FirestoreRecyclerOptions.Builder<ProdukModel>()
            .setQuery(query, ProdukModel::class.java)
            .build()

        mAdapter = object : FirestoreRecyclerAdapter<ProdukModel, CartViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
                return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.transaksi_card, parent, false))
            }

            override fun onBindViewHolder(
                holder: CartViewHolder,
                position: Int,
                model: ProdukModel
            ) {
                holder.bind(model)
                Log.d("MODEL", model.toString())
            }
        }
        mAdapter.notifyDataSetChanged()
        rv_transaction.adapter = mAdapter
    }

    private fun deleteAllData() {
        var isSuccess = true
        rv_transaction.visibility = View.VISIBLE

        val sharedPref = requireActivity().getSharedPreferences("total", Context.MODE_PRIVATE)
        val size = sharedPref.getInt("size", 0)

        for (i in 0..size) {
            carCollection.document("$i")
                    .delete()
                    .addOnSuccessListener {
                        isSuccess = true
                        Log.d("DELETE", "DocumentSnapshot successfully deleted!")
                    }
                    .addOnFailureListener {
                        isSuccess = false
                        Toast.makeText(context, "Pembelian Gagal ${it.message}", Toast.LENGTH_SHORT).show()
                        Log.w("DELETE", "Error deleting document")
                    }
        }
        if (isSuccess) {
            Toast.makeText(context, "Pembelian Berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }
}