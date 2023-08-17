package com.epam.rd.autocode.concurrenttictactoe;


public class PlayerImpl implements Player {
    private final TicTacToe ticTacToe;
    private final char mark;
    private final PlayerStrategy strategy;
    private boolean stop = false;

    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    @Override
    public void run() {

        while (!checkGameOver(ticTacToe.table())) {
            synchronized (ticTacToe) {
                if(checkGameOver(ticTacToe.table())) break;

                if (ticTacToe.lastMark() != mark) {
                    Move move = strategy.computeMove(mark, ticTacToe);
                    if(move != null && mark != ' '){
                        ticTacToe.setMark(move.row, move.column, mark);
                    }
                }

            }
        }
    }

    private boolean checkGameOver(char[][] board) {
        boolean gameOver = false;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                gameOver = true;
                break;
            }

            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                gameOver = true;
                break;
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            gameOver = true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            gameOver = true;
        }

        // Check for draw
        boolean hasEmptyCell = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    hasEmptyCell = true;
                    break;
                }
            }
            if (hasEmptyCell) {
                break;
            }
        }

        return gameOver || !hasEmptyCell;
    }

}
