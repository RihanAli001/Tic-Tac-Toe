package com.rihanhack.tictactoe

class Game(private val player1: String, private val player2: String) {
    private val a = Array(9) { i -> i }
    private var score1 = 0
    private var score2 = 0
    private var draw = 0
    private var turn = 1
    private var currentPlayer = player1

    fun makeTurn(player: String, p: Int): Boolean {
        return if (a[p - 1] == 0) {
            if (player == player1) {
                a[p - 1] = 1
            } else {
                a[p - 1] = 2
            }
            true
        } else {
            println("Already filled...")
            false
        }
    }

    fun getPlayer1(): String {
        return player1
    }

    fun getPlayer2(): String {
        return player2
    }

    fun getCurrentPlayer(): String {
        return currentPlayer
    }

    fun setTurn(t: Int) {
        turn = if (t == 1) {
            currentPlayer = player1
            1
        } else {
            currentPlayer = player2
            2
        }
    }

    fun nextTurn() {
        turn = if (turn == 1) {
            currentPlayer = player2
            2
        } else {
            currentPlayer = player1
            1
        }
    }

    fun getTurn(): Int {
        return turn
    }

    fun getPlayer1Score(): Int {
        return score1
    }

    fun getPlayer2Score(): Int {
        return score2
    }

    fun getDrawScore(): Int {
        return draw
    }

    fun increaseDrawScore(v: Int) {
        draw += v
    }

    fun isDraw(): Boolean {
        for (i in a.indices){
            if (a[i] == 0){
                return false
            }
        }
        return true
    }

    fun isWin(player: String): Boolean {
        return if (winingStatus(player)) {
            if (player == player1) {
                score1++
            } else {
                score2++
            }
            true
        } else {
            false
        }
    }

    private fun winingStatus(player: String): Boolean {
        var p = 1
        if (player == player2) {
            p = 2
        }

        if (((a[0] == p) and (a[4] == p) and (a[8] == p)) or
            ((a[2] == p) and (a[4] == p) and (a[6] == p))
        ) {
            return true
        }

        for (i in 0..2) {
            if ((a[i] == p) and (a[3 + i] == p) and (a[6 + i] == p)) {
                return true
            }
        }

        for (i in 0..2) {
            if ((a[i * 3] == p) and (a[i * 3 + 1] == p) and (a[i * 3 + 2] == p)) {
                return true
            }
        }

        return false
    }

    fun reset() {
        for (i in a.indices) {
            a[i] = 0
        }
    }
}