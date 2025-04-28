package Soal1;

public class Karyawan {
    private String id;
    private String nama;
    private String posisi;
    private double gaji;

    // constructor inisialisasi karyawan baru
    public Karyawan(String id, String nama, String posisi, double gaji) {
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
        this.setGaji(gaji); // Validasi gaji di setter
    }

    // getter setter
    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getPosisi() { return posisi; }
    public double getGaji() { return gaji; }

    public void setPosisi(String posisi) { this.posisi = posisi; }
    public void setGaji(double gaji) {
        if (gaji >= 0) {
            this.gaji = gaji;
        } else {
            throw new IllegalArgumentException("Gaji tidak boleh negatif.");
        }
    }

    // menyimpan data ke csv
    public String toCSV() {
        return id + "," + nama + "," + posisi + "," + gaji;
    }
}
