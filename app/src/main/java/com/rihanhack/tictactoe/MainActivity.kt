package com.rihanhack.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.rihanhack.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resources.getString(R.string.win_text, 0).also {
            binding.player1Score.text = it
            binding.player2Score.text = it
        }
        binding.drawScore.text = resources.getString(R.string.draw_text, 0)

        binding.one.setOnClickListener { onClick(1) }
        binding.two.setOnClickListener { onClick(2) }
        binding.three.setOnClickListener { onClick(3) }
        binding.four.setOnClickListener { onClick(4) }
        binding.five.setOnClickListener { onClick(5) }
        binding.six.setOnClickListener { onClick(6) }
        binding.seven.setOnClickListener { onClick(7) }
        binding.eight.setOnClickListener { onClick(8) }
        binding.nine.setOnClickListener { onClick(9) }

        game = Game("Parvej Ali", "Rihan Ali")
        shufflePlayer()
        binding.resetGame.setOnClickListener { reset() }
        reset()
    }

    private fun shufflePlayer() {
        if ((1..2).random() == 1) game.setTurn(1) else game.setTurn(2)
        binding.currentPlayer.text =
            resources.getString(R.string.player_turn, game.getCurrentPlayer())
    }

    private fun reset() {
        game.reset()
        binding.one.setImageDrawable(null)
        binding.two.setImageDrawable(null)
        binding.three.setImageDrawable(null)
        binding.four.setImageDrawable(null)
        binding.five.setImageDrawable(null)
        binding.six.setImageDrawable(null)
        binding.seven.setImageDrawable(null)
        binding.eight.setImageDrawable(null)
        binding.nine.setImageDrawable(null)
    }

    private fun onClick(v: Int) {
        println(game.getCurrentPlayer())
        val change = game.makeTurn(game.getCurrentPlayer(), v)

        println("Change: $change")
        if (!change) {
            return
        }

        val img = if (game.getTurn() == 1) {
            R.drawable.circle
        } else {
            R.drawable.cross
        }

        when (v) {
            1 -> binding.one.setImageResource(img)
            2 -> binding.two.setImageResource(img)
            3 -> binding.three.setImageResource(img)
            4 -> binding.four.setImageResource(img)
            5 -> binding.five.setImageResource(img)
            6 -> binding.six.setImageResource(img)
            7 -> binding.seven.setImageResource(img)
            8 -> binding.eight.setImageResource(img)
            9 -> binding.nine.setImageResource(img)
        }

        if (game.isWin(game.getCurrentPlayer())) {
            println("Winner: ${game.getCurrentPlayer()}")

            val builder = AlertDialog.Builder(this)
                .setTitle("Winner : ${game.getCurrentPlayer()}")
                .setMessage("Score 1: ${game.getPlayer1Score()} and Score 2 : ${game.getPlayer2Score()}")
                .setPositiveButton("Play More") { _, _ -> reset() }
                .setNegativeButton("No") { _, _ ->
                    Snackbar.make(
                        binding.root,
                        "Winner : ${game.getCurrentPlayer()}",
                        BaseTransientBottomBar.LENGTH_SHORT
                    ).show()
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

            if (game.getTurn() == 1) {
                binding.player1Score.text =
                    resources.getString(R.string.win_text, game.getPlayer1Score())
            } else {
                binding.player2Score.text =
                    resources.getString(R.string.win_text, game.getPlayer2Score())
            }
        }else if (game.isDraw()){
            game.increaseDrawScore(1)
            binding.drawScore.text = resources.getString(R.string.draw_text, game.getDrawScore())
            Snackbar.make(binding.root, "Game draw", BaseTransientBottomBar.LENGTH_SHORT).show()
            reset()
        } else {
            game.nextTurn()
            binding.currentPlayer.text =
                resources.getString(R.string.player_turn, game.getCurrentPlayer())
        }
    }
}