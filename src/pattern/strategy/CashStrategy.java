package pattern.strategy;

import model.Barang;

public class CashStrategy implements PembayaranStrategy{
    @Override
    public void bayar(Barang barang){
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     PEMBAYARAN SECARA TUNAI            ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Lokasi    : Kantor Toko Lelang         ║");
        System.out.println("║ Alamat    : Jl. Lelang No. 123         ║");
        System.out.println("║ Jumlah    : Rp " + String.format("%,.0f", barang.getHargaTertinggi()));
        System.out.println("╚════════════════════════════════════════╝");
    }
}
