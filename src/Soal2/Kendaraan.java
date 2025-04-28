package Soal2;

public class Kendaraan {
    private String jenis;
    private int lamaParkir;
    private double biaya;

    public Kendaraan(String jenis) {
        this.jenis = jenis;
        this.lamaParkir = 0;
        this.biaya = 0;
    }

    // Hitung biaya parkir berdasarkan input lama jam parkir langsung
    public void hitungBiayaParkir(int jam) {
        this.lamaParkir = jam;
        this.biaya = kalkulasiBiaya(jam);
    }

    // Hitung biaya parkir berdasarkan jam masuk dan keluar
    public void hitungBiayaParkir(int jamMasuk, int jamKeluar) {
        int jam = jamKeluar - jamMasuk;
        if (jam <= 0) {
            jam = 1; // Minimal 1 jam
        }
        this.lamaParkir = jam;
        this.biaya = kalkulasiBiaya(jam);
    }

    // Kalkulasi biaya berdasarkan jenis kendaraan
    private double kalkulasiBiaya(int jam) {
        double tarifPerJam = 0;
        switch (jenis.toLowerCase()) {
            case "motor":
                tarifPerJam = 2000;
                break;
            case "mobil":
                tarifPerJam = 5000;
                break;
            case "truk":
                tarifPerJam = 8000;
                break;
            default:
                tarifPerJam = 3000; // Default jika jenis tidak dikenali
        }
        double total = tarifPerJam * jam;

        // Diskon jika lebih dari 5 jam
        if (jam > 5) {
            total *= 0.9; // Diskon 10%
        }
        return total;
    }

    // Menampilkan ringkasan
    public void tampilkanRingkasan() {
        System.out.println("Jenis Kendaraan: " + jenis);
        System.out.println("Lama Parkir: " + lamaParkir + " jam");
        System.out.println("Total Biaya: Rp" + biaya);
        System.out.println("----------------------------------");
    }

    public double getBiaya() {
        return biaya;
    }
}
