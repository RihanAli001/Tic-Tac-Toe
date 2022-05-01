package com.rihanhack.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rihanhack.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resources.getString(R.string.win_text, 0).also { binding.player1Score.text = it
            binding.player2Score.text = it }
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

        val player1 = "Parvej Ali"
        val player2 = "Rihan Ali"
        game = Game(player1, player2)

        if ((0..1).random() == 0) {
            binding.currentPlayer.text = player1
        }else{
            binding.currentPlayer.text = player2
        }

        binding.resetGame.setOnClickListener {
            game.reset()
            reset()
        }
    }

    private fun reset() {
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

    private fun onClick(v : Int){
        val player = binding.currentPlayer.text.toString()
        val turn = game.makeTurn(player, v)

        if (!turn){
            return
        }
        
    }
}