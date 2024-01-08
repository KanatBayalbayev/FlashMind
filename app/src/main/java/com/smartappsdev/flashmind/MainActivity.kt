package com.smartappsdev.flashmind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.smartappsdev.flashmind.databinding.ActivityMainBinding
import com.smartappsdev.flashmind.models.BoardSize
import com.smartappsdev.flashmind.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var boardSize: BoardSize = BoardSize.HARD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()

        with(binding){
            rvBoard.adapter = MemoryBoardAdapter(this@MainActivity,boardSize, randomizedImages)
            rvBoard.setHasFixedSize(true)
            rvBoard.layoutManager = GridLayoutManager(this@MainActivity, boardSize.getWidth())
        }
    }
}