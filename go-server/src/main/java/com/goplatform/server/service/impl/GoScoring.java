package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.domain.ChessBoard;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.*;



public  class GoScoring {

    public  static  int[] score1(int[][] board){
        //共同区域不平分
        return GoScoring1.scoreBoard(board);
    }

    public static int[] score2(int[][] board){
        //共同区域平分
        return GoScoring2.scoreBoard(board);
    }
}
 class GoScoring1 {
    /*
        点目的子函数
     */
    public static int[] scoreBoard(int[][] board) {
        int[] scores = new int[2]; // scores[0] for white, scores[1] for black
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (!visited[row][col] && board[row][col] != ChessBoard.EMPTY) {
                    ScoreArea area = findConnectedGroup(board, visited, row, col, board[row][col]);
                    scores[board[row][col]] += area.score;
                }
            }
        }

        return scores;
    }

    private static ScoreArea findConnectedGroup(int[][] board, boolean[][] visited, int row, int col, int color) {
        Set<Point> group = new HashSet<>();
        Set<Point> territory = new HashSet<>();
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(row, col));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            if (visited[p.row][p.col]) continue;

            visited[p.row][p.col] = true;
            group.add(p);

            // Check all four directions
            for (int[] d : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newRow = p.row + d[0], newCol = p.col + d[1];
                if (isValid(board, newRow, newCol)) {
                    if (board[newRow][newCol] == ChessBoard.EMPTY) {
                        territory.add(new Point(newRow, newCol));
                    } else if (board[newRow][newCol] == color && !visited[newRow][newCol]) {
                        stack.push(new Point(newRow, newCol));
                    }
                }
            }
        }

        // Now, check if the territory is only surrounded by the same color or empty spaces
        int score = 0;
        for (Point t : territory) {
            boolean isTerritory = true;
            for (int[] d : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}}) {
                int adjacentRow = t.row + d[0], adjacentCol = t.col + d[1];
                if (isValid(board, adjacentRow, adjacentCol)) {
                    if (board[adjacentRow][adjacentCol] != color && board[adjacentRow][adjacentCol] != ChessBoard.EMPTY) {
                        isTerritory = false;
                        break;
                    }
                }
            }
            if (isTerritory) score++;
        }

        return new ScoreArea(group, score);
    }

    private static boolean isValid(int[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    private static class Point {
        int row, col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class ScoreArea {
        Set<Point> group;
        int score;

        ScoreArea(Set<Point> group, int score) {
            this.group = group;
            this.score = score;
        }
    }
}


class GoScoring2 {

    public static int[] scoreBoard(int[][] board) {
        int[] scores = new int[2]; // scores[0] for white, scores[1] for black
        boolean[][] visited = new boolean[board.length][board[0].length];

        // Iterate over the board to find enclosed territories for each color
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (!visited[row][col] && board[row][col] == ChessBoard.EMPTY) {
                    Territory territory = findTerritory(board, visited, row, col);
                    if (territory.owner != ChessBoard.EMPTY) {
                        scores[territory.owner] += territory.size;
                    }
                }
            }
        }

        return scores;
    }

    private static Territory findTerritory(int[][] board, boolean[][] visited, int row, int col) {
        int owner = ChessBoard.EMPTY;
        int size = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (visited[p.row][p.col]) continue;

            visited[p.row][p.col] = true;
            size++;

            List<Point> neighbours = getNeighbours(p, board.length, board[0].length);
            for (Point neighbour : neighbours) {
                if (board[neighbour.row][neighbour.col] == ChessBoard.EMPTY && !visited[neighbour.row][neighbour.col]) {
                    queue.add(neighbour);
                } else if (board[neighbour.row][neighbour.col] != ChessBoard.EMPTY) {
                    if (owner == ChessBoard.EMPTY) {
                        owner = board[neighbour.row][neighbour.col];
                    } else if (owner != board[neighbour.row][neighbour.col]) {
                        // Territory touches more than one color, hence it's neutral
                        owner = ChessBoard.EMPTY;
                        break;
                    }
                }
            }
        }

        return new Territory(owner, size);
    }

    private static List<Point> getNeighbours(Point p, int rows, int cols) {
        List<Point> neighbours = new ArrayList<>();
        if (p.row > 0) neighbours.add(new Point(p.row - 1, p.col));
        if (p.col > 0) neighbours.add(new Point(p.row, p.col - 1));
        if (p.row < rows - 1) neighbours.add(new Point(p.row + 1, p.col));
        if (p.col < cols - 1) neighbours.add(new Point(p.row, p.col + 1));
        return neighbours;
    }

    private static class Point {
        int row, col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Territory {
        int owner, size;

        Territory(int owner, int size) {
            this.owner = owner;
            this.size = size;
        }
    }
}

