package com.smartappsdev.flashmind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.smartappsdev.flashmind.databinding.ActivityMainBinding
import com.smartappsdev.flashmind.models.BoardSize
import com.smartappsdev.flashmind.models.MemoryCard
import com.smartappsdev.flashmind.models.MemoryGame
import com.smartappsdev.flashmind.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    private lateinit var gameAdapter: MemoryBoardAdapter
    private lateinit var memoryGame: MemoryGame

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var boardSize: BoardSize = BoardSize.HARD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        memoryGame = MemoryGame(boardSize)

        with(binding) {
            gameAdapter = MemoryBoardAdapter(
                this@MainActivity,
                boardSize,
                memoryGame.cards,
                object : MemoryBoardAdapter.CardClickListener {
                    override fun onCardClicked(position: Int) {
                        updateGameWithFlip(position)
                    }

                }
            )
            rvBoard.adapter = gameAdapter
            rvBoard.setHasFixedSize(true)
            rvBoard.layoutManager = GridLayoutManager(this@MainActivity, boardSize.getWidth())
        }
    }

    private fun updateGameWithFlip(position: Int) {

        Log.d("TAG", "updateGameWithFlip: A new change here")
        Log.d("TAG", "updateGameWithFlip: Added Some new changes here")
        Log.d("TAG", "updateGameWithFlip: Added Some new changes here repeat")

        if (memoryGame.haveWonGame()) {
            Snackbar.make(binding.clRoot, "You already won! Use the menu to play again.", Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            Snackbar.make(binding.clRoot, "Invalid move!", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (memoryGame.flipCard(position)) {
            Log.d("MainActivity", "Found a match! Num pairs found: ${memoryGame.numPairsFound}")
        }

        memoryGame.flipCard(position)
        gameAdapter.notifyDataSetChanged()

    }
}