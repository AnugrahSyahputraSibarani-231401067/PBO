package Soal3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PapanLotre lotre = new PapanLotre();
        boolean bermain = true;

        while (bermain) {
            lotre.displayBoard();
            System.out.print("Masukkan baris (0-3): ");
            int row = sc.nextInt();
            System.out.print("Masukkan kolom (0-4): ");
            int col = sc.nextInt();

            boolean hasilTebak = lotre.guess(row, col);

            if (!hasilTebak) {
                lotre.displayBoard();
                System.out.println("Kamu kena bom. Game over.");
                bermain = false;
            } else if (lotre.isGameOver()) {
                lotre.displayBoard();
                System.out.println("Kamu berhasil.");
                bermain = false;
            }
        }

        System.out.println("Game Selesai");
    }
}
