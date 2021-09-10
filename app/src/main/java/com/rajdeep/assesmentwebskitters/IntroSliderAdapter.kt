package com.rajdeep.assesmentwebskitters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSlide: List<IntroSlide>):
    RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>() {
    inner class IntroSliderViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageSlideicon = view.findViewById<ImageView>(R.id.imageSlideicon)

        fun bind(introSlide: IntroSlide){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }
}