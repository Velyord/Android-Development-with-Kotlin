package com.example.project9

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project9.databinding.CountryListItemBinding

class CountryAdapter(val countries: List<CountryData>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(val binding: CountryListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryListItemBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = countries[position]
        holder.binding.apply {
            country = currentCountry.name
            capital = currentCountry.capital

            Glide
                // context to use for requesting the image
                .with(root.context)
                // URL to load the asset from
                .load(currentCountry.flags.png)
                .centerCrop()
                // image to be shown until online asset is downloaded
                .placeholder(R.drawable.ic_launcher_foreground)
                // ImageView to load the online asset into when ready
                .into(ivFlag)

        // CountryAdapter | click on country -> open CountryDetailActivity -> pass name of country
            holder.binding.root.setOnClickListener {
                val intent = Intent(holder.binding.root.context, CountryDetailActivity::class.java)
                intent.putExtra("name", country)
                holder.binding.root.context.startActivity(intent)
            }
        }
    }
    override fun getItemCount(): Int {
        return countries.size
    }
}