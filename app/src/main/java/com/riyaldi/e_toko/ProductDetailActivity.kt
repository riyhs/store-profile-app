package com.riyaldi.e_toko

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.riyaldi.e_toko.model.ProdukModel
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductDetailActivity : AppCompatActivity() {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val carCollection = mFirestore.collection("cart")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.getParcelableExtra<ProdukModel>("product")

        setupLayout(product)
        if (product != null) {
            bt_beli.setOnClickListener {
                onBuySuccess(product)
            }
            bt_cart.setOnClickListener {
                val data = initData(product)
                saveData(data)
            }
        }
    }

    private fun setupLayout(product: ProdukModel?) {
        if (product != null) {
            tv_detail_name.text = product.nama
            tv_detail_desc.text = product.deskripsi
            tv_detail_price.text = product.harga.toString()

            Glide
                .with(this)
                .load(product.foto)
                .centerCrop()
                .into(iv_detail_photo)
        }
    }

    private fun onBuySuccess(product: ProdukModel) {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Berhasil membeli ${product.nama}")

        builder.setPositiveButton(
                "Ok") { _, _ ->
            finish()
        }

        builder.show()
    }

    private fun saveData(cart : MutableMap<String, Any>) = CoroutineScope(Dispatchers.IO).launch {
        carCollection.document(setId()).set(cart)
                .addOnCompleteListener {
                    if (it.isSuccessful) Toast.makeText(this@ProductDetailActivity, "Berhasil menambahkan", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this@ProductDetailActivity, "Gagal menambahkan", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this@ProductDetailActivity, "Error ${it.message}.", Toast.LENGTH_SHORT).show()
                }
    }

    private fun initData(product: ProdukModel) : MutableMap<String, Any> {
        val name = product.nama
        val price = product.harga
        val cart = mutableMapOf<String, Any>()

        cart["nama"] = name.toString()
        cart["harga"] = price

        return cart
    }

    private fun setId(): String {
        val sharedPref = this.getSharedPreferences("total", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val size = sharedPref.getInt("size", 0)

        editor.putInt("size", size + 1)
        editor.apply()
        return "$size"
    }

}