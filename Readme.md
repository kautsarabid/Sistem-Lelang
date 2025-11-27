# Sistem Lelang Barang
Sistem toko lelang barang berbasis Console Application yang dibangun menggunakan bahasa pemrograman Java dengan mengimplementasi 3 Design Pattern untuk Project UAS Arsitektur Perangkat Lunak.

## Fitur Utama
1. Manajemen Penguna
* Registrasi Peserta Lelang dengan data lengkap (NIK, No HP, Alamat).
* Registrasi Penitip Barang dengan informasi rekening.
* Sistem Blacklist untuk peserta yang melakukan Bid and Run.

2. Manajemen Barang
* Penitipan Barang tanpa batasan jumlah
* Informasi Barang Lengkap (Harga Awal, Kelipatan Bid, Harga BIN)
* Status Tracking barang (Tersedia, Di-Bid, Menunggu Pembayaran, Terjual)

3. Sistem Lelang
* Bidding System dengan kelipatan harga
* Real-time Competiotion - peserta saling ber-adu bid
* History Bid - melihat semua bid yang masuk dengan timestamp
* Buy it Now (BIN) - pembelian langsung tanpa ber adu bid
* Cancel Bid - membatalkan bid dan mengembalikan status barang
* Notifikasi Real-Time untuk semua peserta (Observer Pattern)

4. Sistem Pembayaran
* Terdapat 3 sistem pembayaran (Strategy Pattern):
    * Transfer Bank
    * E-Wallet (OVO/GoPay/Dana)
    * Cash/Tunai
* Perhitungan Fee Otomatis (10% untuk penyelenggara)
* Tracking Pembayaran ke penitip

5. Sistem Pelaporan
* Laporan Barang (Semua status)
* Laporan Peserta Terdaftar
* Laporan Penitip Terdaftar
* Laporan Blacklist
* Laporan Fee Penyelenggara

## Design Pattern
Sistem ini mengimplementasikan 3 Design Pattern:

1. Factory Method Pattern
Digunakan untuk menciptakan objek User (Peserta dan Penitip) secara dinamis.

2. Observer Pattern
Digunakan untuk sistem notifikasi real-time kepada peserta lelang

3. Strategy Pattern
Digunakan untuk memilih metode pembayaran secara dinamis.

## Teknologi yang digunakan
* Bahasa: Java 20
* Console UI: Scanner & System.out
* Format: DecimalFormat untuk format rupiah