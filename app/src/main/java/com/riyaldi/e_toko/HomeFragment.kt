package com.riyaldi.e_toko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.e_toko.adapter.MainAdapter
import com.riyaldi.e_toko.model.ProdukModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class HomeFragment : Fragment() {

    private var produk = listOf<ProdukModel>(
        ProdukModel("Tainted Hand Sanitizer", 30000, "Hand Sanitizer, membersihkan sampai dengan 99% Kuman di tangan, pastikan tangan kamu bersih", "Hand Sanitizer" , "400 ML","https://cdn.aarp.net/content/dam/aarp/health/conditions_treatments/2020/07/1140-hand-sanitizer-recall-methanol.imgcache.rev.web.875.499.jpg"),
        ProdukModel("Sophie Paris Hand Sanitizer", 56000, "Hand Sanitizer, membersihkan sampai dengan 99% Kuman di tangan, pastikan tangan kamu bersih, bau rasa apel", "Hand Sanitizer" , "400 ML","https://www.sophieparis.com/media/wysiwyg/Blog/blogmaret2020/blog_1_3_.jpg"),
        ProdukModel("Desinfektan", 56000, "Disinfektan adalah bahan kimia yang digunakan untuk mencegah terjadinya infeksi atau pencemaran oleh jasad renik atau obat untuk membasmi kuman penyakit [1] Pengertian lain dari disinfektan adalah senyawa kimia yang bersifat toksik dan memiliki kemampuan membunuh mikroorganisme yang terpapar secara langsung oleh disinfektan.", "Desinfektan" , "400 ML","https://blogpictures.99.co/desinfektan-header.jpg"),
        ProdukModel("Sabun Pel Lantai", 9000, "Deskripsi Sabun Pembersih lantai 600ml SABUN PEL Lantai (APEL-LAVENDER)\n" +
                "Deskripsi Pembersih lantai pel 600ml\n" +
                "Pembersih lantai pel murah 600ml", "sabun" ,  "600 ML","https://cdns.klimg.com/dream.co.id/resized/630x440/news/2018/11/12/96357/bikin-sendiri-cairan-pembersih-lantai-yang-ampuh-hilangkan-noda-181112q_3x2.jpg")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() {
        rv_main.setHasFixedSize(true)
        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.adapter = MainAdapter(produk)
    }
}