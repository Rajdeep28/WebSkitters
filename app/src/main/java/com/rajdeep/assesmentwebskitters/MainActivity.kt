package com.rajdeep.assesmentwebskitters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide("Image1",
                "Image1",
                R.drawable.slide1),
            IntroSlide("Image2",
                "Image2",
                R.drawable.slide1),
            IntroSlide("Image3",
                "Image3",
                R.drawable.slide1)
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        introSlideViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSlideViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        next.setOnClickListener {
            if (introSlideViewPager.currentItem +1 < introSliderAdapter.itemCount){
                introSlideViewPager.currentItem += 1
            }else{
                Intent(applicationContext,AnotherActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

        }
        skip_intro.setOnClickListener {
            Intent(applicationContext,AnotherActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorContainers.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childeCount = indicatorContainers.childCount
        for (i in 0 until childeCount){
            val imageView = indicatorContainers.get(i) as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}