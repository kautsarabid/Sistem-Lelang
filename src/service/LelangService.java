// service/LelangService.java
package service;

import model.*;
import java.text.SimpleDateFormat;

public class LelangService {
    
    public boolean lakukanBid(Barang barang, Peserta peserta, double hargaBid) {
        // Validasi harga bid
        double minBid = barang.getHargaTertinggi() + barang.getKelipatanBid();
        
        if (hargaBid < minBid) {
            System.out.println("[INFO] Harga bid minimal: Rp " + String.format("%,.0f", minBid));
            return false;
        }

        // Simpan bid sebelumnya
        Peserta pemenangSebelum = barang.getPemenang();
        double hargaSebelum = barang.getHargaTertinggi();
        
        // Update bid baru
        barang.setHargaTertinggi(hargaBid);
        barang.setPemenang(peserta);
        barang.setStatus(StatusBarang.DIBID);
        
        // Tambah ke history
        barang.tambahBidHistory(peserta, hargaBid);

        // Notify all observers
        String message = "Barang " + barang.getNamaBarang() + 
                        " telah di-bid dengan harga Rp " + String.format("%,.0f", hargaBid) +
                        " oleh " + peserta.getNama();
        barang.notifyObservers(message);

        // Notifikasi khusus untuk pemenang sebelumnya yang tersalip
        if (pemenangSebelum != null && !pemenangSebelum.equals(peserta)){
            System.out.println("[NOTIFIKASI] " + pemenangSebelum.getNama() + " telah disalip! Bid sebelumnya: Rp " + String.format("%,.0f",hargaSebelum));
        }
        
        return true;
    }
    
    public void buyItNow(Barang barang, Peserta peserta) {
        barang.setHargaTertinggi(barang.getHargaBIN());
        barang.setPemenang(peserta);
        barang.setStatus(StatusBarang.MENUNGGU_PEMBAYARAN);
        barang.tambahBidHistory(peserta, barang.getHargaBIN());
        
        String message = "Barang " + barang.getNamaBarang() + 
                        " telah dibeli dengan BIN seharga Rp " + 
                        String.format("%,.0f", barang.getHargaBIN()) +
                        " oleh " + peserta.getNama();
        barang.notifyObservers(message);
    }
    
    public void selesaikanLelang(Barang barang) {
        barang.setStatus(StatusBarang.MENUNGGU_PEMBAYARAN);
        
        String message = "Lelang untuk " + barang.getNamaBarang() + 
                        " telah selesai. Silakan lakukan pembayaran.";
        barang.notifyObservers(message);
    }

    public void cancelBid(Barang barang){
        barang.setStatus(StatusBarang.TERSEDIA);
        barang.setPemenang(null);
        barang.setHargaTertinggi(barang.getHargaAwal());
        barang.clearBidHistory();
        barang.clearObservers();

        String message = "Bid untuk barang " + barang.getNamaBarang() + " telah di cancel. Barang kembali tersedia";
        System.out.println("[INFO] " + message);
    }

    public void tampilkanHistoryBid(Barang barang){
        System.out.println("\n=== HISTORY BID - " +barang.getNamaBarang()+ " ===");

        if(barang.getHistoryBid().isEmpty()){
            System.out.println("Belum ada bid untuk barang ini.\n");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        int no =1;

        System.out.println("┌─────┬──────────────────┬──────────────────┬─────────────────────┐");
        System.out.println("│ No  │ Peserta          │ Harga Bid        │ Waktu               │");
        System.out.println("├─────┼──────────────────┼──────────────────┼─────────────────────┤");
        
        for (Barang.BidHistory history : barang.getHistoryBid()) {
            String nama = history.getPeserta().getNama();
            String harga = String.format("Rp %,.0f", history.getHargaBid());
            String waktu = sdf.format(history.getWaktu());
            
            System.out.printf("│ %-3d │ %-16s │ %-16s │ %-19s │\n", 
                            no++, 
                            nama.length() > 16 ? nama.substring(0, 16) : nama,
                            harga.length() > 16 ? harga.substring(0, 16) : harga,
                            waktu);
        }
        
        System.out.println("└─────┴──────────────────┴──────────────────┴─────────────────────┘");
        System.out.println("Total Bid: " + barang.getHistoryBid().size());
        System.out.println("Pemenang Sementara: " + barang.getPemenang().getNama());
        System.out.println("Harga Tertinggi: Rp " + String.format("%,.0f", barang.getHargaTertinggi()));
        System.out.println();
    }
}