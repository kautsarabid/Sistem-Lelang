// Main.java
import model.*;
import service.*;
import pattern.factory.*;
import pattern.observer.*;
import pattern.strategy.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static RegistrasiService registrasiService = new RegistrasiService();
    private static BarangService barangService = new BarangService();
    private static LelangService lelangService = new LelangService();
    private static PembayaranService pembayaranService = new PembayaranService();
    
    public static void main(String[] args) {
        // Load data default
        loadDefaultData();
        
        System.out.println("==============================================");
        System.out.println("    SISTEM TOKO LELANG BARANG");
        System.out.println("==============================================");
        System.out.println("[INFO] Data default telah dimuat:");
        System.out.println("  - 2 Peserta: Ahmad Rizki & Siti Nurhaliza");
        System.out.println("  - 1 Penitip: Budi Santoso");
        System.out.println("  - 3 Barang: Laptop, Smartphone, Kamera");
        System.out.println("==============================================\n");
        
        boolean running = true;
        while (running) {
            tampilkanMenuUtama();
            int pilihan = inputInteger("Pilih menu: ");
            
            switch (pilihan) {
                case 1:
                    menuRegistrasi();
                    break;
                case 2:
                    menuPenitipanBarang();
                    break;
                case 3:
                    menuLelang();
                    break;
                case 4:
                    menuPembayaran();
                    break;
                case 5:
                    menuLaporan();
                    break;
                case 0:
                    System.out.println("\nTerima kasih telah menggunakan sistem lelang!");
                    running = false;
                    break;
                default:
                    System.out.println("\n[ERROR] Pilihan tidak valid!\n");
            }
        }
        scanner.close();
    }
    
    private static void tampilkanMenuUtama() {
        System.out.println("==============================================");
        System.out.println("MENU UTAMA");
        System.out.println("==============================================");
        System.out.println("1. Registrasi (Peserta/Penitip)");
        System.out.println("2. Penitipan Barang");
        System.out.println("3. Lelang Barang");
        System.out.println("4. Pembayaran");
        System.out.println("5. Laporan");
        System.out.println("0. Keluar");
        System.out.println("==============================================");
    }
    
    private static void menuRegistrasi() {
        System.out.println("\n=== MENU REGISTRASI ===");
        System.out.println("1. Registrasi Peserta");
        System.out.println("2. Registrasi Penitip");
        System.out.println("3. Lihat Daftar Peserta");
        System.out.println("4. Lihat Daftar Penitip");
        System.out.println("0. Kembali");
        
        int pilihan = inputInteger("Pilih: ");
        
        switch (pilihan) {
            case 1:
                registrasiPeserta();
                break;
            case 2:
                registrasiPenitip();
                break;
            case 3:
                registrasiService.tampilkanSemuaPeserta();
                break;
            case 4:
                registrasiService.tampilkanSemuaPenitip();
                break;
        }
    }
    
    private static void registrasiPeserta() {
        System.out.println("\n--- Registrasi Peserta ---");
        
        String nama = inputString("Nama: ");
        Integer nik = inputInteger("NIK: ");
        Integer noHP = inputInteger("No HP: ");
        String alamat = inputString("Alamat: ");
        
        // Factory Pattern untuk membuat user
        UserFactory factory = new PesertaFactory();
        User peserta = factory.createUser(nama, nik, noHP, alamat);
        
        registrasiService.registrasiPeserta((Peserta) peserta);
        System.out.println("\n[SUCCESS] Peserta berhasil diregistrasi!\n");
    }
    
    private static void registrasiPenitip() {
        System.out.println("\n--- Registrasi Penitip ---");
        
        String nama = inputString("Nama: ");
        Integer nik = inputInteger("NIK: ");
        Integer noHP = inputInteger("No HP: ");
        String noRekening = inputString("No Rekening: ");
        
        // Factory Pattern untuk membuat user
        UserFactory factory = new PenitipFactory();
        User penitip = factory.createUser(nama, nik, noHP, noRekening);
        
        registrasiService.registrasiPenitip((Penitip) penitip);
        System.out.println("\n[SUCCESS] Penitip berhasil diregistrasi!\n");
    }
    
    private static void menuPenitipanBarang() {
        System.out.println("\n=== MENU PENITIPAN BARANG ===");
        System.out.println("1. Titip Barang");
        System.out.println("2. Lihat Barang Tersedia");
        System.out.println("0. Kembali");
        
        int pilihan = inputInteger("Pilih: ");
        
        switch (pilihan) {
            case 1:
                titipBarang();
                break;
            case 2:
                barangService.tampilkanSemuaBarang();
                break;
        }
    }
    
    private static void titipBarang() {
        System.out.println("\n--- Penitipan Barang ---");
        
        registrasiService.tampilkanSemuaPenitip();
        Integer nikPenitip = inputInteger("\nMasukkan NIK Penitip: ");
        
        Penitip penitip = registrasiService.cariPenitip(nikPenitip);
        if (penitip == null) {
            System.out.println("\n[ERROR] Penitip tidak ditemukan!\n");
            return;
        }
        
        String namaBarang = inputString("Nama Barang: ");
        String jenisBarang = inputString("Jenis Barang: ");
        double hargaAwal = inputDouble("Harga Awal: ");
        double kelipatanBid = inputDouble("Kelipatan Bid: ");
        double hargaBIN = inputDouble("Harga BIN (Buy It Now): ");
        
        Barang barang = new Barang(namaBarang, jenisBarang, hargaAwal, 
                                   kelipatanBid, hargaBIN, penitip);
        barangService.tambahBarang(barang);
        
        System.out.println("\n[SUCCESS] Barang berhasil dititipkan!");
        System.out.println("Kode Barang: " + barang.getKodeBarang() + "\n");
    }
    
    private static void menuLelang() {
        System.out.println("\n=== MENU LELANG ===");
        System.out.println("1. Lihat Barang Lelang");
        System.out.println("2. Bid Barang");
        System.out.println("3. Buy It Now (BIN)");
        System.out.println("4. Lihat History Bid Barang");
        System.out.println("5. Cancel Bid Barang");
        System.out.println("6. Selesaikan Lelang");
        System.out.println("0. Kembali");
        
        int pilihan = inputInteger("Pilih: ");
        
        switch (pilihan) {
            case 1:
                barangService.tampilkanBarangTersedia();
                break;
            case 2:
                bidBarang();
                break;
            case 3:
                buyItNow();
                break;
            case 4:
                lihatHistoryBid();
                break;
            case 5:
                cancelBid();
                break;
            case 6:
                selesaikanLelang();
                break;
        }
    }
    
    private static void bidBarang() {
        System.out.println("\n--- Bid Barang ---");
        
        barangService.tampilkanSemuaBarang();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null) {
            System.out.println("\n[ERROR] Barang tidak ditemukan!\n");
            return;
        }
        
        if (barang.getStatus() != StatusBarang.TERSEDIA && 
            barang.getStatus() != StatusBarang.DIBID) {
            System.out.println("\n[ERROR] Barang tidak tersedia untuk di-bid!\n");
            return;
        }
        
        // Tampilkan detail barang yang dipilih beserta info bid saat ini
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            DETAIL BARANG YANG AKAN DI-BID                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        barang.tampilkanInfo();
        
        // Jika barang sudah pernah di-bid, tampilkan info persaingan
        if (barang.getStatus() == StatusBarang.DIBID && !barang.getHistoryBid().isEmpty()) {
            System.out.println("\n┌──────────────────────────────────────────────────────────┐");
            System.out.println("│               ⚠️  INFORMASI PERSAINGAN BID                │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│ 🏆 Pemenang Saat Ini: " + barang.getPemenang().getNama());
            System.out.println("│ 💰 Harga Tertinggi   : Rp " + formatAngka(barang.getHargaTertinggi()));
            System.out.println("│ 📊 Total Bid         : " + barang.getHistoryBid().size() + " bid");
            System.out.println("│ 💵 Bid Minimal       : Rp " + formatAngka(barang.getHargaTertinggi() + barang.getKelipatanBid()));
            System.out.println("└──────────────────────────────────────────────────────────┘");
            
            // Tampilkan 3 bid terakhir jika ada
            if (barang.getHistoryBid().size() > 0) {
                System.out.println("\n📜 3 Bid Terakhir:");
                int start = Math.max(0, barang.getHistoryBid().size() - 3);
                for (int i = barang.getHistoryBid().size() - 1; i >= start; i--) {
                    Barang.BidHistory history = barang.getHistoryBid().get(i);
                    System.out.println("   " + (barang.getHistoryBid().size() - i) + ". " + 
                                     history.getPeserta().getNama() + 
                                     " → Rp " + formatAngka(history.getHargaBid()));
                }
            }
            System.out.println();
        }
        
        registrasiService.tampilkanSemuaPeserta();
        Integer nikPeserta = inputInteger("\nMasukkan NIK Peserta: ");
        
        Peserta peserta = registrasiService.cariPeserta(nikPeserta);
        if (peserta == null) {
            System.out.println("\n[ERROR] Peserta tidak ditemukan!\n");
            return;
        }
        
        if (registrasiService.isBlackListed(nikPeserta)) {
            System.out.println("\n[ERROR] Peserta ada dalam blacklist!\n");
            return;
        }
        
        // Cek apakah peserta ini adalah pemenang saat ini
        if (barang.getPemenang() != null && barang.getPemenang().equals(peserta)) {
            System.out.println("\n[INFO] Anda adalah pemenang saat ini dengan bid: Rp " + 
                             formatAngka(barang.getHargaTertinggi()));
            String konfirmasi = inputString("Apakah ingin menaikkan bid Anda? (ya/tidak): ");
            if (!konfirmasi.equalsIgnoreCase("ya")) {
                System.out.println("\n[INFO] Bid dibatalkan.\n");
                return;
            }
        }
        
        double hargaBid = inputDouble("Harga Bid: ");
        
        // Observer Pattern - barang akan notify observers
        barang.tambahObserver(peserta);
        
        boolean berhasil = lelangService.lakukanBid(barang, peserta, hargaBid);
        
        if (berhasil) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                    ✅ BID BERHASIL!                        ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║ Peserta      : " + peserta.getNama());
            System.out.println("║ Harga Bid    : Rp " + formatAngka(hargaBid));
            System.out.println("║ Status       : Pemenang Sementara");
            System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        } else {
            System.out.println("\n[ERROR] Bid gagal! Harga bid tidak sesuai.\n");
        }
    }
    
    /**
     * format angka dengan pemisah ribuan
     */
    private static String formatAngka(double angka) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
        return df.format(angka);
    }
    
    private static void buyItNow() {
        System.out.println("\n--- Buy It Now ---");
        
        barangService.tampilkanBarangTersedia();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null) {
            System.out.println("\n[ERROR] Barang tidak ditemukan!\n");
            return;
        }
        
        if (barang.getStatus() != StatusBarang.TERSEDIA && 
            barang.getStatus() != StatusBarang.DIBID) {
            System.out.println("\n[ERROR] Barang tidak tersedia untuk dibeli!\n");
            return;
        }
        
        registrasiService.tampilkanSemuaPeserta();
        Integer nikPeserta = inputInteger("\nMasukkan NIK Peserta: ");
        
        Peserta peserta = registrasiService.cariPeserta(nikPeserta);
        if (peserta == null) {
            System.out.println("\n[ERROR] Peserta tidak ditemukan!\n");
            return;
        }
        
        if (registrasiService.isBlackListed(nikPeserta)) {
            System.out.println("\n[ERROR] Peserta ada dalam blacklist!\n");
            return;
        }
        
        barang.tambahObserver(peserta);
        lelangService.buyItNow(barang, peserta);
        
        System.out.println("\n[SUCCESS] Barang berhasil dibeli dengan BIN!\n");
    }
    
    private static void lihatHistoryBid() {
        System.out.println("\n--- History Bid Barang ---");
        
        barangService.tampilkanBarangDibid();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null) {
            System.out.println("\n[ERROR] Barang tidak ditemukan!\n");
            return;
        }
        
        lelangService.tampilkanHistoryBid(barang);
    }
    
    private static void cancelBid() {
        System.out.println("\n--- Cancel Bid Barang ---");
        
        barangService.tampilkanBarangDibid();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null || barang.getStatus() != StatusBarang.DIBID) {
            System.out.println("\n[ERROR] Barang tidak dalam status bid!\n");
            return;
        }
        
        String konfirmasi = inputString("Apakah yakin ingin cancel bid barang ini? (ya/tidak): ");
        if (!konfirmasi.equalsIgnoreCase("ya")) {
            System.out.println("\n[INFO] Cancel dibatalkan.\n");
            return;
        }
        
        lelangService.cancelBid(barang);
        System.out.println("\n[SUCCESS] Bid barang berhasil di-cancel! Barang kembali tersedia.\n");
    }
    
    private static void selesaikanLelang() {
        System.out.println("\n--- Selesaikan Lelang ---");
        
        barangService.tampilkanBarangDibid();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null || barang.getStatus() != StatusBarang.DIBID) {
            System.out.println("\n[ERROR] Barang tidak dalam status bid!\n");
            return;
        }
        
        lelangService.selesaikanLelang(barang);
        System.out.println("\n[SUCCESS] Lelang selesai! Barang menunggu pembayaran.\n");
    }
    
    private static void menuPembayaran() {
        System.out.println("\n=== MENU PEMBAYARAN ===");
        System.out.println("1. Lihat Barang Menunggu Pembayaran");
        System.out.println("2. Bayar dengan Transfer");
        System.out.println("3. Bayar dengan E-Wallet");
        System.out.println("4. Bayar dengan Cash");
        System.out.println("5. Tandai Bid and Run");
        System.out.println("0. Kembali");
        
        int pilihan = inputInteger("Pilih: ");
        
        switch (pilihan) {
            case 1:
                barangService.tampilkanBarangMenungguPembayaran();
                break;
            case 2:
                prosesPembayaran(new TransferStrategy());
                break;
            case 3:
                prosesPembayaran(new EWalletStrategy());
                break;
            case 4:
                prosesPembayaran(new CashStrategy());
                break;
            case 5:
                tandaiBidAndRun();
                break;
        }
    }
    
    private static void prosesPembayaran(PembayaranStrategy strategy) {
        System.out.println("\n--- Pembayaran ---");
        
        barangService.tampilkanBarangMenungguPembayaran();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null || barang.getStatus() != StatusBarang.MENUNGGU_PEMBAYARAN) {
            System.out.println("\n[ERROR] Barang tidak menunggu pembayaran!\n");
            return;
        }
        
        // Strategy Pattern untuk metode pembayaran
        pembayaranService.setPembayaranStrategy(strategy);
        pembayaranService.prosesPembayaran(barang);
        
        barang.setStatus(StatusBarang.TERJUAL);
        System.out.println("\n[SUCCESS] Pembayaran berhasil! Barang akan dikirim.\n");
    }
    
    private static void tandaiBidAndRun() {
        System.out.println("\n--- Tandai Bid and Run ---");
        
        barangService.tampilkanBarangMenungguPembayaran();
        String kodeBarang = inputString("\nMasukkan Kode Barang: ");
        
        Barang barang = barangService.cariBarang(kodeBarang);
        if (barang == null || barang.getStatus() != StatusBarang.MENUNGGU_PEMBAYARAN) {
            System.out.println("\n[ERROR] Barang tidak menunggu pembayaran!\n");
            return;
        }
        
        Peserta pemenang = barang.getPemenang();
        if (pemenang != null) {
            registrasiService.tambahKeBlacklist(pemenang.getNik());
            lelangService.cancelBid(barang);
            
            System.out.println("\n[SUCCESS] Peserta ditandai Bid and Run dan dimasukkan ke blacklist!\n");
            System.out.println("[INFO] Barang kembali tersedia untuk di-bid.\n");
        }
    }
    
    private static void menuLaporan() {
        System.out.println("\n=== LAPORAN ===");
        System.out.println("1. Laporan Barang");
        System.out.println("2. Laporan Peserta");
        System.out.println("3. Laporan Penitip");
        System.out.println("4. Laporan Blacklist");
        System.out.println("5. Laporan Fee");
        System.out.println("0. Kembali");
        
        int pilihan = inputInteger("Pilih: ");
        
        switch (pilihan) {
            case 1:
                barangService.tampilkanSemuaBarang();
                break;
            case 2:
                registrasiService.tampilkanSemuaPeserta();
                break;
            case 3:
                registrasiService.tampilkanSemuaPenitip();
                break;
            case 4:
                registrasiService.tampilkanBlacklist();
                break;
            case 5:
                pembayaranService.tampilkanLaporanFee();
                break;
        }
    }
    
    private static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private static int inputInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Masukkan angka yang valid!");
            }
        }
    }
    
    private static double inputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Masukkan angka yang valid!");
            }
        }
    }

    private static void loadDefaultData() {
        // ===== PESERTA =====
        UserFactory pesertaFactory = new PesertaFactory();
        Peserta peserta1 = (Peserta) pesertaFactory.createUser(
            "Kautsar",
            111,
            111,
            "Limpok"
        );
        registrasiService.registrasiPeserta(peserta1);

        Peserta peserta2 = (Peserta) pesertaFactory.createUser(
            "Abid",
            222,
            222,
            "Limpok"
        );
        registrasiService.registrasiPeserta(peserta2);
        
        // ===== PENITIP =====
        UserFactory penitipFactory = new PenitipFactory();
        Penitip penitip1 = (Penitip) penitipFactory.createUser(
            "Muttaqin",
            333,
            333,
            "1234567890" // No Rekening
        );
        registrasiService.registrasiPenitip(penitip1);
        
        // ===== BARANG =====
        Barang barang1 = new Barang(
            "Laptop Gaming ASUS ROG",
            "Elektronik",
            10000000,  // Harga Awal: 10 juta
            500000,    // Kelipatan Bid: 500 ribu
            15000000,  // Harga BIN: 15 juta
            penitip1
        );
        barangService.tambahBarang(barang1);
        
        Barang barang2 = new Barang(
            "iPhone 15 Pro Max 256GB",
            "Elektronik",
            15000000,  // Harga Awal: 15 juta
            1000000,   // Kelipatan Bid: 1 juta
            20000000,  // Harga BIN: 20 juta
            penitip1
        );
        barangService.tambahBarang(barang2);
        
    }
}