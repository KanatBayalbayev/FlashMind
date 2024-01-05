package com.smartappsdev.flashmind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.smartappsdev.flashmind.databinding.ActivityMainBinding
import com.smartappsdev.flashmind.models.BoardSize
import com.smartappsdev.flashmind.models.MemoryCard
import com.smartappsdev.flashmind.models.MemoryGame
import com.smartappsdev.flashmind.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var boardSize: BoardSize = BoardSize.HARD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val memoryGame = MemoryGame(boardSize)

        with(binding) {
            rvBoard.adapter = MemoryBoardAdapter(
                this@MainActivity,
                boardSize,
                memoryGame.cards,
                object : MemoryBoardAdapter.CardClickListener{
                    override fun onCardClicked(position: Int) {
                        Log.d("MainActivity", position.toString())
                    }

                }
            )
            rvBoard.setHasFixedSize(true)
            rvBoard.layoutManager = GridLayoutManager(this@MainActivity, boardSize.getWidth())
        }
    }
}