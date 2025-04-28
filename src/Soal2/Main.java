package Soal2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Kendaraan> daftarKendaraan = new ArrayList<>();
        boolean lanjut = true;

        System.out.println("=== Selamat datang di ParkirChan ===");

        while (lanjut) {
            System.out.print("\nMasukkan jenis kendaraan (Motor/Mobil/Truk): ");
            String jenis = sc.nextLine();

            Kendaraan kendaraan = new Kendaraan(jenis);

            System.out.println("Pilih metode input durasi:");
            System.out.println("1. Input langsung jumlah jam parkir");
            System.out.println("2. Input jam masuk dan jam keluar");
            System.out.print("Pilihan: ");
            int pilihan = Integer.parseInt(sc.nextLine());

            if (pilihan == 1) {
                System.out.print("Masukkan jumlah jam parkir: ");
                int jam = Integer.parseInt(sc.nextLine());
                kendaraan.hitungBiayaParkir(jam);
            } else if (pilihan == 2) {
                System.out.print("Masukkan jam masuk (0-23): ");
                int jamMasuk = Integer.parseInt(sc.nextLine());
                System.out.print("Masukkan jam keluar (0-23): ");
                int jamKeluar = Integer.parseInt(sc.nextLine());
                kendaraan.hitungBiayaParkir(jamMasuk, jamKeluar);
            } else {
                System.out.println("Pilihan tidak valid, data tidak diproses.");
                continue;
            }

            kendaraan.tampilkanRingkasan();
            daftarKendaraan.add(kendaraan);

            System.out.print("Tambah kendaraan lagi? (y/n): ");
            String jawaban = sc.nextLine();
            if (jawaban.equalsIgnoreCase("n")) {
                lanjut = false;
            }
        }

        // Menampilkan ringkasan akhir
        double totalBiayaSemua = 0;
        System.out.println("\n=== Ringkasan Akhir Parkir ===");
        for (Kendaraan k : daftarKendaraan) {
            totalBiayaSemua += k.getBiaya();
        }
        System.out.println("Jumlah kendaraan: " + daftarKendaraan.size());
        System.out.println("Total semua biaya parkir: Rp" + totalBiayaSemua);
        System.out.println("=== Terima Kasih Telah Menggunakan ParkirChan! ===");
    }
}