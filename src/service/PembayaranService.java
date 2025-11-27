// service/PembayaranService.java
package service;

import model.Barang;
import pattern.strategy.PembayaranStrategy;
import java.util.*;

public class PembayaranService {
    private PembayaranStrategy strategy;
    private static final double PERSENTASE_FEE = 0.10; // 10% fee
    private List<Double> daftarFee;
    
    public PembayaranService() {
        this.daftarFee = new ArrayList<>();
    }
    
    public void setPembayaranStrategy(PembayaranStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void prosesPembayaran(Barang barang) {
        if (strategy == null) {
            System.out.println("[ERROR] Metode pembayaran belum dipilih!");
            return;
        }
        
        strategy.bayar(barang);
        
        // Hitung fee
        double fee = barang.getHargaTertinggi() * PERSENTASE_FEE;
        daftarFee.add(fee);
        
        System.out.println("\n[INFO] Fee penyelenggara (10%): Rp " + String.format("%,.0f", fee));
        System.out.println("[INFO] Pembayaran ke penitip: Rp " + 
                         String.format("%,.0f", barang.getHargaTertinggi() - fee));
    }
    
    public void tampilkanLaporanFee() {
        System.out.println("\n=== LAPORAN FEE PENYELENGGARA ===");
        if (daftarFee.isEmpty()) {
            System.out.println("Belum ada transaksi.\n");
            return;
        }
        
        double totalFee = 0;
        for (int i = 0; i < daftarFee.size(); i++) {
            System.out.println("Transaksi " + (i+1) + ": Rp " + 
                             String.format("%,.0f", daftarFee.get(i)));
            totalFee += daftarFee.get(i);
        }
        System.out.println("─────────────────────────────────");
        System.out.println("TOTAL FEE: Rp " + String.format("%,.0f", totalFee));
        System.out.println();
    }
}