package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    private char[][] board;

    private char lastMark;

    public TicTacToeImpl() {
        board = new char[][]{{' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
    }

    @Override
    public void setMark(int x, int y, char mark) {
        if(x>2 || y>2 || (mark!='X' && mark!='O')) // if coordinates get outside the table or the mark is not X or O throw exception
            throw new IllegalArgumentException();
        if (board[x][y]==' ') { // if given place in the table is blank you can place the mark
            board[x][y] = mark;
            lastMark=mark;
        }
        else
            throw new IllegalArgumentException();

    }


//    @Override
//    public char[][] table() {
//        return Arrays.stream(board).map(char[]::clone).toArray(char[][]::new);
//    }

    public char[][] table() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    @Override
    public char lastMark() {
        return lastMark;
    }
}


