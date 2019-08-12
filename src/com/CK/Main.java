package com.CK;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 4;
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(n).toString());
    }
}

class Solution {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) return res;
        boolean[][] board = new boolean[n][n];
        List<Integer> dfsList = new ArrayList<>();
        dfs(board, 0, dfsList);
        return res;
    }

    private void dfs(boolean[][] board, int r, List<Integer> dfsList){
        int n = board.length;
        if (r >= n) {
            drawResult(dfsList, n);
            return;
        }

        for (int c = 0; c < n; c++) {
            if (isValid(board, r, c, n)) {
                board[r][c] = true;
                dfsList.add(c);
                dfs(board, r + 1, dfsList);
                board[r][c] = false;
                dfsList.remove(dfsList.size() - 1);
            }
        }
    }

    private boolean isValid(boolean[][] board, int r, int c, int n){
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < n; j++){
                if (board[i][c])
                    return false;
                if (board[r][j])
                    return false;
                if (r - c == i - j && board[i][j])
                    return false;
                if (r + c == i + j && board[i][j])
                    return false;
            }
        }
        return true;
    }

    private void drawResult(List<Integer> dfsList, int n){
        List<String> singleRes = new ArrayList<>();
        for (int r = 0; r < n; r++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ( j == dfsList.get(r)) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            singleRes.add(sb.toString());
        }
        res.add(singleRes);
    }
}

// Directly draw on Board
class Solution2 {
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<List<String>>();

        solve(res, chess, 0);
        return res;
    }
    private void solve(List<List<String>> res, char[][] chess, int row) {
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            if (valid(chess, row, col)) {
                chess[row][col] = 'Q';
                solve(res, chess, row + 1);
                chess[row][col] = '.';
            }
        }
    }
    private boolean valid(char[][] chess, int row, int col) {
        // check all cols
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        //check 45 degree
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //check 135
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }
}