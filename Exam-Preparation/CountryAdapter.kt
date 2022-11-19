package net.gostartups.myapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.gostartups.myapplication.R
import net.gostartups.myapplication.activity.MainActivity
import net.gostartups.myapplication.databinding.CountryListItemBinding
import net.gostartups.myapplication.db.entity.CountryDetails
import net.gostartups.myapplication.fragment.CountryDetailsFragment

class CountryAdapter(private val countries: List<CountryDetails>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            ivLiked.visibility = if (currentCountry.favourite) View.VISIBLE else View.GONE

            Glide
                // context to use for requesting the image
                .with(root.context)
                // URL to load the asset from
                .load(currentCountry.flag)
                .centerCrop()
                // image to be shown until online asset is downloaded
                .placeholder(R.drawable.ic_launcher_foreground)
                // ImageView to load the online asset into when ready
                .into(ivFlag)

            holder.binding.root.setOnClickListener {
                (it.context as MainActivity).supportFragmentManager.commit {
                    val bundle = Bundle()
                    bundle.putString("country_name", currentCountry.name)
                    replace(R.id.container_view, CountryDetailsFragment::class.java, bundle)
                    addToBackStack("countries_list_to_details")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}