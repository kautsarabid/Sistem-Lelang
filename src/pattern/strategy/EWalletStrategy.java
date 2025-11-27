package pattern.strategy;

import model.Barang;

public class EWalletStrategy implements PembayaranStrategy{
    @Override
    public void bayar(Barang barang){
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     PEMBAYARAN MELALUI E-WALLET        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ E-Wallet  : OVO / GoPay / DANA         ║");
        System.out.println("║ No. HP    : 081234567890               ║");
        System.out.println("║ Atas Nama : Toko Lelang                ║");
        System.out.println("║ Jumlah    : Rp " + String.format("%,.0f", barang.getHargaTertinggi()));
        System.out.println("╚════════════════════════════════════════╝");
    }
}
