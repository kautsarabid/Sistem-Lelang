package pattern.strategy;

import model.Barang;

public class TransferStrategy implements PembayaranStrategy {
    @Override
    public void bayar(Barang barang){
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     PEMBAYARAN MELALUI TRANSFER        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Bank      : BCA                        ║");
        System.out.println("║ No. Rek   : 1234567890                 ║");
        System.out.println("║ Atas Nama : Toko Lelang                ║");
        System.out.println("║ Jumlah    : Rp " + String.format("%,.0f", barang.getHargaTertinggi()));
        System.out.println("╚════════════════════════════════════════╝");
    }
}
