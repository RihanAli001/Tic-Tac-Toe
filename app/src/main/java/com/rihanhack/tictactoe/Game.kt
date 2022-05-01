package com.rihanhack.tictactoe

class Game(private val player1: String, private val player2: String) {
    private val a = Array(9) { i -> i }

    fun makeTurn(player: String, p: Int): Boolean {
        return if (a[p] == 0) {
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

    fun winingStatus(player: String): Boolean {
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
        for (i in 0..8) {
            a[i] = 0
        }
    }
}