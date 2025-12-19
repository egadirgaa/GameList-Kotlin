package com.example.gamelistdicoding.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide  // <-- Tambah ini
import com.example.gamelistdicoding.R
import com.google.android.material.imageview.ShapeableImageView
import android.widget.ImageButton
import androidx.core.widget.NestedScrollView

class AboutActivity : AppCompatActivity() {

    private var isLoading = false
    private var page = 1
    private lateinit var carouselHandler: Handler
    private val carouselRunnable = object : Runnable {
        override fun run() {
            vpTechCarousel.currentItem += 1
            carouselHandler.postDelayed(this, 2500)
        }
    }

    private lateinit var vpTechCarousel: ViewPager2

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener { finish() }

        val imgProfile: ShapeableImageView = findViewById(R.id.img_profile)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDescription: TextView = findViewById(R.id.tv_description)

        imgProfile.setImageResource(R.drawable.foto_profil)
        tvName.text = "Game List"
        tvDescription.text =
            "Aplikasi ini dibuat untuk Submission Akhil di kelas Aplikasi Android Sederhana di dicoding.com. " +
                    "Menampilkan 10 daftar game populer dengan detail lengkap saat item diklik."

        setupInfiniteLoading()

        setupTechCarousel()
    }

    private fun setupInfiniteLoading() {
        val containerContent: LinearLayout = findViewById(R.id.container_content)
        val nestedScrollView: NestedScrollView = findViewById(R.id.nestedScrollView)
        val layoutLoadingMore: LinearLayout = findViewById(R.id.layout_loading_more)
        val imgLoadingLogo: ImageView = findViewById(R.id.img_loading_logo)

        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, oldScrollY ->
            if (!v.canScrollVertically(1) && scrollY > oldScrollY && !isLoading) {
                loadMoreContent(containerContent, layoutLoadingMore, imgLoadingLogo)
            }
        }
    }

    private fun loadMoreContent(
        container: LinearLayout,
        loadingLayout: LinearLayout,
        loadingLogo: ImageView
    ) {
        if (isLoading) return
        isLoading = true
        loadingLayout.visibility = View.VISIBLE

        startInfiniteRotation(loadingLogo)

        Handler(Looper.getMainLooper()).postDelayed({
            addNewContentCard(container, page)
            page++

            loadingLayout.visibility = View.GONE
            loadingLogo.clearAnimation()
            loadingLogo.rotation = 0f
            isLoading = false

            if (page > 3) {
                loadingLayout.visibility = View.GONE
            }
        }, 2000)
    }

    private fun startInfiniteRotation(loadingLogo: ImageView) {
        if (!isLoading) {
            return
        }

        loadingLogo.animate()
            .rotationBy(360f)
            .setDuration(1000)
            .setInterpolator(LinearInterpolator())
            .withEndAction { startInfiniteRotation(loadingLogo) }
            .start()
    }

    private fun addNewContentCard(container: LinearLayout, page: Int) {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundResource(R.drawable.shape_retro_tag)
            setPadding(32, 24, 32, 24)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        val title = TextView(this).apply {
            text = when (page) {
                1 -> "Fitur Tambahan"
                2 -> "Credit & Thanks"
                3 -> "Fun Fact"
                else -> "Easter Egg!"
            }
            textSize = 18f
            setTypeface(typeface, android.graphics.Typeface.BOLD)
        }

        val desc = TextView(this).apply {
            text = when (page) {
                1 -> "• Dark mode support\n• Share game ke teman\n• About page retro style (yang ini!)"
                2 -> "Terima kasih kepada:\n- Dicoding Indonesia\n- API game yang digunakan\n- Retro aesthetic inspiration"
                3 -> "Tahukah kamu? Aplikasi ini dibuat sambil dengerin chiptune music lo-fi! \uD83C\uDFAE\uD83C\uDFB6"
                else -> "Kamu menemukan easter egg! Selamat! \uD83C\uDFC6"
            }
            textSize = 14f
            setLineSpacing(8f, 1f)
        }

        card.addView(title)
        card.addView(desc)
        container.addView(card, container.childCount - 1)
    }

    private fun setupTechCarousel() {
        vpTechCarousel = findViewById(R.id.vp_tech_carousel)

        vpTechCarousel.adapter = TechCarouselAdapter()

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
            page.alpha = 0.8f + r * 0.2f
        }
        vpTechCarousel.setPageTransformer(transformer)

        vpTechCarousel.setCurrentItem(1000, false)

        carouselHandler = Handler(Looper.getMainLooper())
        carouselHandler.postDelayed(carouselRunnable, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::carouselHandler.isInitialized) {
            carouselHandler.removeCallbacks(carouselRunnable)
        }
    }
}

// Adapter untuk carousel (pakai URL + Glide)
class TechCarouselAdapter : RecyclerView.Adapter<TechCarouselAdapter.TechViewHolder>() {
    private val logoUrls = listOf(
        "https://img.icons8.com/?size=100&id=rY6agKizO9eb&format=png&color=000000", // vue
        "https://img.icons8.com/?size=100&id=54087&format=png&color=000000",        // node
        "https://img.icons8.com/?size=100&id=7vdHawe2VPlT&format=png&color=000000", // laravel
        "https://img.icons8.com/?size=100&id=t4YbEbA834uH&format=png&color=000000", // react
        "https://img.icons8.com/?size=100&id=RMPOPCHeTMeF&format=png&color=000000", // nuxt
    )

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tech_logo, parent, false)
        return TechViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechViewHolder, position: Int) {
        holder.bind(logoUrls[position % logoUrls.size])
    }

    class TechViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivLogo: ImageView = itemView.findViewById(R.id.iv_tech_logo)

        fun bind(url: String) {
            Glide.with(itemView.context)
                .load(url)
                .placeholder(R.drawable.bg_retro_button)  // Tampilan saat loading
                .error(R.drawable.ic_code)                // Jika gagal load
                .fitCenter()
                .into(ivLogo)
        }
    }
}