package com.sergey.artspace

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

data class Artwork(
    val imageResId: Int,
    val titleResId: Int,
    val authorResId: Int
)


class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_INDEX = "current_index"
    }

    private lateinit var artworks: List<Artwork>
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        artworks = listOf(
            Artwork(R.drawable.mone, R.string.art1_title, R.string.art1_author),
            Artwork(R.drawable.salvador_dali, R.string.art2_title, R.string.art2_author),
            Artwork(R.drawable.ayvazovsky, R.string.art3_title, R.string.art3_author),
            Artwork(R.drawable.van_gogh, R.string.art4_title, R.string.art4_author)
        )

        currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0

        val imageView = findViewById<ImageView>(R.id.artImage)
        val titleText = findViewById<TextView>(R.id.artTitle)
        val authorText = findViewById<TextView>(R.id.artAuthor)
        val prevButton = findViewById<Button>(R.id.buttonPrevious)
        val nextButton = findViewById<Button>(R.id.buttonNext)

        prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateArtwork(imageView, titleText, authorText, prevButton, nextButton)
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                updateArtwork(imageView, titleText, authorText, prevButton, nextButton)
            }
        }

        updateArtwork(imageView, titleText, authorText, prevButton, nextButton)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, currentIndex)
    }

    private fun updateArtwork(
        imageView: ImageView,
        titleText: TextView,
        authorText: TextView,
        prevButton: Button,
        nextButton: Button
    ) {
        val title = getString(artwork.titleResId)
        val author = getString(artwork.authorResId)
        imageView.contentDescription = getString(
            R.string.artwork_description,
            title,
            author
        )

        val artwork = artworks[currentIndex]

        imageView.setImageResource(artwork.imageResId)
        titleText.setText(artwork.titleResId)
        authorText.setText(artwork.authorResId)

        prevButton.isEnabled = currentIndex > 0
        nextButton.isEnabled = currentIndex < artworks.size - 1
    }
}