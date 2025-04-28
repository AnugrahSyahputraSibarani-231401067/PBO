package Soal1;

import java.util.*;
import java.io.*;

public class Perusahaan {
    private List<Karyawan> daftarKaryawan;

    public Perusahaan() {
        daftarKaryawan = new ArrayList<>();
    }

    // menambahkan karyawan baru
    public boolean tambahKaryawan(Karyawan karyawan) {
        if (cariKaryawanById(karyawan.getId()) == null) {
            daftarKaryawan.add(karyawan);
            return true;
        } else {
            return false; // ID sudah ada
        }
    }

    // hapus karyawan dari ID
    public boolean hapusKaryawan(String id) {
        Karyawan karyawan = cariKaryawanById(id);
        if (karyawan != null) {
            daftarKaryawan.remove(karyawan);
            return true;
        }
        return false;
    }

    // mengubah posisi
    public boolean ubahPosisi(String id, String posisiBaru) {
        Karyawan karyawan = cariKaryawanById(id);
        if (karyawan != null) {
            karyawan.setPosisi(posisiBaru);
            return true;
        }
        return false;
    }

    // ubah gaji
    public boolean ubahGaji(String id, double gajiBaru) {
        if (gajiBaru < 0) return false;
        Karyawan karyawan = cariKaryawanById(id);
        if (karyawan != null) {
            karyawan.setGaji(gajiBaru);
            return true;
        }
        return false;
    }

    // cari dari ID
    public Karyawan cariKaryawanById(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                return k;
            }
        }
        return null;
    }

    // cari dengan nama
    public List<Karyawan> cariKaryawanByNama(String nama) {
        List<Karyawan> hasil = new ArrayList<>();
        for (Karyawan k : daftarKaryawan) {
            if (k.getNama().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    // filter dari posisi
    public List<Karyawan> filterByPosisi(String posisi) {
        List<Karyawan> hasil = new ArrayList<>();
        for (Karyawan k : daftarKaryawan) {
            if (k.getPosisi().equalsIgnoreCase(posisi)) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    // total gaji karyawan
    public double totalGaji() {
        double total = 0;
        for (Karyawan k : daftarKaryawan) {
            total += k.getGaji();
        }
        return total;
    }

    public List<Karyawan> getAllKaryawan() {
        return daftarKaryawan;
    }

    // simpan data ke file
    public void simpanKeFile(String namaFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile));
        for (Karyawan k : daftarKaryawan) {
            writer.write(k.toCSV());
            writer.newLine();
        }
        writer.close();
    }

    // Load data dari file
    public void loadDariFile(String namaFile) throws IOException {
        daftarKaryawan.clear();
        BufferedReader reader = new BufferedReader(new FileReader(namaFile));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                Karyawan k = new Karyawan(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                daftarKaryawan.add(k);
            }
        }
        reader.close();
    }
}
