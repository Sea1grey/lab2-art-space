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

    private lateinit var artworks: List<Artwork>
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация коллекции
        artworks = listOf(
            Artwork(R.drawable.mone, R.string.art1_title, R.string.art1_author),
            Artwork(R.drawable.salvador_dali, R.string.art2_title, R.string.art2_author),
            Artwork(R.drawable.ayvazovsky, R.string.art3_title, R.string.art3_author),
            Artwork(R.drawable.van_gogh, R.string.art4_title, R.string.art4_author)
        )

        // Связываем элементы
        val imageView = findViewById<ImageView>(R.id.artImage)
        val titleText = findViewById<TextView>(R.id.artTitle)
        val authorText = findViewById<TextView>(R.id.artAuthor)
        val prevButton = findViewById<Button>(R.id.buttonPrevious)
        val nextButton = findViewById<Button>(R.id.buttonNext)

        // Кнопка "Назад"
        prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateArtwork(imageView, titleText, authorText, prevButton, nextButton)
            }
        }

        // Кнопка "Вперёд"
        nextButton.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                updateArtwork(imageView, titleText, authorText, prevButton, nextButton)
            }
        }

        // Первичное отображение
        updateArtwork(imageView, titleText, authorText, prevButton, nextButton)
    }

    private fun updateArtwork(
        imageView: ImageView,
        titleText: TextView,
        authorText: TextView,
        prevButton: Button,
        nextButton: Button
    ) {
        val artwork = artworks[currentIndex]

        imageView.setImageResource(artwork.imageResId)
        titleText.setText(artwork.titleResId)
        authorText.setText(artwork.authorResId)

        prevButton.isEnabled = currentIndex > 0
        nextButton.isEnabled = currentIndex < artworks.size - 1
    }
}