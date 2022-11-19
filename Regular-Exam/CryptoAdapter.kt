package com.example.crypto.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto.R
import com.example.crypto.activity.MainActivity
import com.example.crypto.databinding.CryptoListItemBinding
import com.example.crypto.db.entity.CryptoDetails
import com.example.crypto.fragment.CryptoDetailsFragment

class CryptoAdapter(private val cryptoes: List<CryptoDetails>) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    class CryptoViewHolder(val binding: CryptoListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CryptoListItemBinding.inflate(layoutInflater, parent, false)

        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val currentCrypto = cryptoes[position]
        holder.binding.apply {
            crypto = currentCrypto.name
            symbol = currentCrypto.symbol
            //price = currentCrypto.price
            ivLiked.visibility = if (currentCrypto.favourite) View.VISIBLE else View.GONE

            Glide
                // context to use for requesting the image
                .with(root.context)
                // URL to load the asset from
                .load(currentCrypto.image)
                .centerCrop()
                // image to be shown until online asset is downloaded
                .placeholder(R.drawable.ic_launcher_foreground)
                // ImageView to load the online asset into when ready
                .into(ivImage)

            holder.binding.root.setOnClickListener {
                (it.context as MainActivity).supportFragmentManager.commit {
                    val bundle = Bundle()
                    bundle.putString("crypto_name", currentCrypto.name)
                    replace(R.id.container_view, CryptoDetailsFragment::class.java, bundle)
                    addToBackStack("cryptoes_list_to_details")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cryptoes.size
    }
}