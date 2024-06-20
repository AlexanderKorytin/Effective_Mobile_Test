package com.example.tickets.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tickets.R
import com.example.tickets.databinding.OfferItemBinding
import com.example.tickets.domain.models.OfferData
import com.example.util.dpToPixels

class OfferViewHolder(private val parentViewBinding: OfferItemBinding) :
    RecyclerView.ViewHolder(parentViewBinding.root) {

    private val radiusIconTrackPx = dpToPixels(RADIUS_DP, parentViewBinding.root.context)
    fun bind(offerData: OfferData) {
        val price = "от ${offerData.price}"
        parentViewBinding.offerItemPrice.text = price
        parentViewBinding.offerItemTitle.text = offerData.title
        parentViewBinding.offerItemTown.text = offerData.town
        Glide
            .with(parentViewBinding.root.context)
            .load(getImage(offerData.id))
            .transform(CenterCrop(), RoundedCorners(radiusIconTrackPx))
            .into(parentViewBinding.icImageOffer)
    }

    private fun getImage(id: Int): Int {
        return when {
            id == 1 -> {
                R.drawable.image1
            }

            id == 2 -> {
                R.drawable.image2
            }

            id == 3 -> {
                R.drawable.image3
            }

            else -> {
                R.drawable.image1
            }
        }
    }
}

private const val RADIUS_DP = 16.0f