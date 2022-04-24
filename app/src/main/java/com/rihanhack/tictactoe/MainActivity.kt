package com.rihanhack.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rihanhack.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resources.getString(R.string.win_text, 0).also { binding.player1Score.text = it
            binding.player2Score.text = it }
        binding.drawScore.text = resources.getString(R.string.draw_text, 0)
    }
}