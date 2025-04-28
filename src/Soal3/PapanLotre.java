package Soal3;

import java.util.Random;

public class PapanLotre {
    private char[][] board;
    private boolean[][] revealed;
    private int[][] data;
    private int totalSafeRevealed;

    public PapanLotre() {
        board = new char[4][5];
        revealed = new boolean[4][5];
        data = new int[4][5];
        totalSafeRevealed = 0;
        generateBoard();
    }

    // Menghasilkan papan dengan 2 bom secara acak
    public void generateBoard() {
        Random rand = new Random();

        // Inisialisasi semua kotak aman
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = 0; // 0 = aman
                board[i][j] = '*'; // '*' untuk kotak tertutup
                revealed[i][j] = false;
            }
        }

        // Menempatkan 2 bom di posisi acak
        int bomDitempatkan = 0;
        while (bomDitempatkan < 2) {
            int row = rand.nextInt(4);
            int col = rand.nextInt(5);
            if (data[row][col] == 0) {
                data[row][col] = 1; // 1 = bom
                bomDitempatkan++;
            }
        }
    }

    // Menampilkan papan saat ini
    public void displayBoard() {
        System.out.println("Papan Lotre:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (revealed[i][j]) {
                    if (data[i][j] == 1) {
                        System.out.print("X "); // Bom
                    } else {
                        System.out.print("O "); // Aman
                    }
                } else {
                    System.out.print("* "); // Belum dibuka
                }
            }
            System.out.println();
        }
    }

    // Proses tebakan pemain
    public boolean guess(int row, int col) {
        if (row < 0 || row >= 4 || col < 0 || col >= 5) {
            System.out.println("Posisi di luar papan! Coba lagi.");
            return true; // Tetap lanjut
        }

        if (revealed[row][col]) {
            System.out.println("Kotak sudah dibuka! Pilih yang lain.");
            return true;
        }

        revealed[row][col] = true;

        if (data[row][col] == 1) {
            board[row][col] = 'X';
            return false;
        } else {
            board[row][col] = 'O';
            totalSafeRevealed++;
            return true;
        }
    }

    // Cek kalau sudah selesai
    public boolean isGameOver() {
        return totalSafeRevealed == 18;
    }
}
