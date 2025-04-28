package Soal1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    private Perusahaan perusahaan = new Perusahaan();
    private JTextArea areaTampil;

    public Main() {
        setTitle("Sistem Manajemen Karyawan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // komponen tombol
        JButton btnTambah = new JButton("Tambah Karyawan");
        JButton btnHapus = new JButton("Hapus Karyawan");
        JButton btnUbahPosisi = new JButton("Ubah Posisi");
        JButton btnUbahGaji = new JButton("Ubah Gaji");
        JButton btnTampilkan = new JButton("Tampilkan Semua");
        JButton btnCari = new JButton("Cari Nama");
        JButton btnFilter = new JButton("Filter Posisi");
        JButton btnLaporanGaji = new JButton("Laporan Total Gaji");
        JButton btnSimpan = new JButton("Simpan ke File");
        JButton btnLoad = new JButton("Load dari File");

        areaTampil = new JTextArea();
        areaTampil.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTampil);

        // panel tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(5, 2));
        panelTombol.add(btnTambah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnUbahPosisi);
        panelTombol.add(btnUbahGaji);
        panelTombol.add(btnTampilkan);
        panelTombol.add(btnCari);
        panelTombol.add(btnFilter);
        panelTombol.add(btnLaporanGaji);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnLoad);

        add(panelTombol, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // event Button
        btnTambah.addActionListener(e -> tambahKaryawan());
        btnHapus.addActionListener(e -> hapusKaryawan());
        btnUbahPosisi.addActionListener(e -> ubahPosisi());
        btnUbahGaji.addActionListener(e -> ubahGaji());
        btnTampilkan.addActionListener(e -> tampilkanSemua());
        btnCari.addActionListener(e -> cariNama());
        btnFilter.addActionListener(e -> filterPosisi());
        btnLaporanGaji.addActionListener(e -> laporanGaji());
        btnSimpan.addActionListener(e -> simpanKeFile());
        btnLoad.addActionListener(e -> loadDariFile());
    }

    // method masing-masing tombol
    private void tambahKaryawan() {
        String id = JOptionPane.showInputDialog(this, "Masukkan ID:");
        String nama = JOptionPane.showInputDialog(this, "Masukkan Nama:");
        String posisi = JOptionPane.showInputDialog(this, "Masukkan Posisi:");
        double gaji = Double.parseDouble(JOptionPane.showInputDialog(this, "Masukkan Gaji:"));
        if (gaji >= 0) {
            if (perusahaan.tambahKaryawan(new Karyawan(id, nama, posisi, gaji))) {
                JOptionPane.showMessageDialog(this, "Karyawan ditambahkan!");
            } else {
                JOptionPane.showMessageDialog(this, "ID sudah ada!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif!");
        }
    }

    private void hapusKaryawan() {
        String id = JOptionPane.showInputDialog(this, "Masukkan ID yang dihapus:");
        if (perusahaan.hapusKaryawan(id)) {
            JOptionPane.showMessageDialog(this, "Karyawan dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "ID tidak ditemukan!");
        }
    }

    private void ubahPosisi() {
        String id = JOptionPane.showInputDialog(this, "Masukkan ID:");
        String posisiBaru = JOptionPane.showInputDialog(this, "Masukkan posisi baru:");
        if (perusahaan.ubahPosisi(id, posisiBaru)) {
            JOptionPane.showMessageDialog(this, "Posisi diubah!");
        } else {
            JOptionPane.showMessageDialog(this, "ID tidak ditemukan!");
        }
    }

    private void ubahGaji() {
        String id = JOptionPane.showInputDialog(this, "Masukkan ID:");
        double gajiBaru = Double.parseDouble(JOptionPane.showInputDialog(this, "Masukkan gaji baru:"));
        if (perusahaan.ubahGaji(id, gajiBaru)) {
            JOptionPane.showMessageDialog(this, "Gaji diubah!");
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengubah!");
        }
    }

    private void tampilkanSemua() {
        areaTampil.setText("");
        for (Karyawan k : perusahaan.getAllKaryawan()) {
            areaTampil.append(k.getId() + " - " + k.getNama() + " - " + k.getPosisi() + " - Rp" + k.getGaji() + "\n");
        }
    }

    private void cariNama() {
        String nama = JOptionPane.showInputDialog(this, "Masukkan nama yang dicari:");
        List<Karyawan> hasil = perusahaan.cariKaryawanByNama(nama);
        areaTampil.setText("");
        for (Karyawan k : hasil) {
            areaTampil.append(k.getId() + " - " + k.getNama() + " - " + k.getPosisi() + " - Rp" + k.getGaji() + "\n");
        }
    }

    private void filterPosisi() {
        String posisi = JOptionPane.showInputDialog(this, "Masukkan posisi:");
        List<Karyawan> hasil = perusahaan.filterByPosisi(posisi);
        areaTampil.setText("");
        for (Karyawan k : hasil) {
            areaTampil.append(k.getId() + " - " + k.getNama() + " - " + k.getPosisi() + " - Rp" + k.getGaji() + "\n");
        }
    }

    private void laporanGaji() {
        double total = perusahaan.totalGaji();
        JOptionPane.showMessageDialog(this, "Total seluruh gaji: Rp" + total);
    }

    private void simpanKeFile() {
        try {
            perusahaan.simpanKeFile("karyawan.txt");
            JOptionPane.showMessageDialog(this, "Data disimpan ke file!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file!");
        }
    }

    private void loadDariFile() {
        try {
            perusahaan.loadDariFile("karyawan.txt");
            JOptionPane.showMessageDialog(this, "Data dimuat dari file!");
            tampilkanSemua();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat file!");
        }
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
