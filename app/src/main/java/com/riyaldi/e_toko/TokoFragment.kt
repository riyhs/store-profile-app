package com.riyaldi.e_toko

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_toko.*

class TokoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toko, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(requireActivity())
            .load("https://brannetmarket.com/wp-content/uploads/2019/01/BRALOGO136.jpg")
            .into(profile_image)

        button.setOnClickListener {
            startActivity(Intent(activity, MapActivity::class.java))
        }
    }

}