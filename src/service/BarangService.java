// service/BarangService.java
package service;

import model.*;
import java.util.*;

public class BarangService {
    private List<Barang> daftarBarang;
    
    public BarangService() {
        this.daftarBarang = new ArrayList<>();
    }
    
    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
    }
    
    public Barang cariBarang(String kodeBarang) {
        for (Barang b : daftarBarang) {
            if (b.getKodeBarang().equals(kodeBarang)) {
                return b;
            }
        }
        return null;
    }
    
    public void tampilkanSemuaBarang() {
        System.out.println("\n=== SEMUA BARANG ===");
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang.\n");
            return;
        }
        for (Barang b : daftarBarang) {
            b.tampilkanInfo();
        }
    }
    
    public void tampilkanBarangTersedia() {
        System.out.println("\n=== BARANG TERSEDIA UNTUK LELANG ===");
        boolean ada = false;
        for (Barang b : daftarBarang) {
            if (b.getStatus() == StatusBarang.TERSEDIA) {
                b.tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Tidak ada barang tersedia.\n");
        }
    }
    
    public void tampilkanBarangDibid() {
        System.out.println("\n=== BARANG SEDANG DI-BID ===");
        boolean ada = false;
        for (Barang b : daftarBarang) {
            if (b.getStatus() == StatusBarang.DIBID) {
                b.tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Tidak ada barang sedang di-bid.\n");
        }
    }
    
    public void tampilkanBarangMenungguPembayaran() {
        System.out.println("\n=== BARANG MENUNGGU PEMBAYARAN ===");
        boolean ada = false;
        for (Barang b : daftarBarang) {
            if (b.getStatus() == StatusBarang.MENUNGGU_PEMBAYARAN) {
                b.tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Tidak ada barang menunggu pembayaran.\n");
        }
    }
}