# Sistem Lelang Barang
Sistem toko lelang barang berbasis Console Application yang dibangun menggunakan bahasa pemrograman Java dengan mengimplementasi 3 Design Pattern untuk Project UAS Arsitektur Perangkat Lunak.

## Fitur Utama
1. **Manajemen Penguna**
* Registrasi Peserta Lelang dengan data lengkap (NIK, No HP, Alamat).
* Registrasi Penitip Barang dengan informasi rekening.
* Sistem Blacklist untuk peserta yang melakukan Bid and Run.

2. **Manajemen Barang**
* Penitipan Barang tanpa batasan jumlah
* Informasi Barang Lengkap (Harga Awal, Kelipatan Bid, Harga BIN)
* Status Tracking barang (Tersedia, Di-Bid, Menunggu Pembayaran, Terjual)

3. **Sistem Lelang**
* Bidding System dengan kelipatan harga
* Real-time Competiotion - peserta saling ber-adu bid
* History Bid - melihat semua bid yang masuk dengan timestamp
* Buy it Now (BIN) - pembelian langsung tanpa ber adu bid
* Cancel Bid - membatalkan bid dan mengembalikan status barang
* Notifikasi Real-Time untuk semua peserta (Observer Pattern)

4. **Sistem Pembayaran**
* Terdapat 3 sistem pembayaran (Strategy Pattern):
    * Transfer Bank
    * E-Wallet (OVO/GoPay/Dana)
    * Cash/Tunai
* Perhitungan Fee Otomatis (10% untuk penyelenggara)
* Tracking Pembayaran ke penitip

5. **Sistem Pelaporan**
* Laporan Barang (Semua status)
* Laporan Peserta Terdaftar
* Laporan Penitip Terdaftar
* Laporan Blacklist
* Laporan Fee Penyelenggara

## Design Pattern
Sistem ini mengimplementasikan 3 Design Pattern:

1. **Factory Method Pattern**
Digunakan untuk menciptakan objek User (Peserta dan Penitip) secara dinamis.

2. **Observer Pattern**
Digunakan untuk sistem notifikasi real-time kepada peserta lelang

3. **Strategy Pattern**
Digunakan untuk memilih metode pembayaran secara dinamis.

## Teknologi yang digunakan
* Bahasa: Java 20
* Console UI: Scanner & System.out
* Format: DecimalFormat untuk format rupiah

## Cara Instalasi
1. **Clone Repository**
```
git clone https://github.com/username/sistem-lelang-barang.git
cd  sistem-lelang-barang
cd src
```

2. **Compile Semua File**
```
javac -d bin **/*.java *.java
```

3. **Jalankan Aplikasi**
```
java -cp bin Main
```

## Cara Penggunaan
1. **Registrasi User**
1. **Registrasi Peserta**
    1. Pilih menu **1** -> **1**
    2. Masukkan: Nama, NIK, No HP, Alamat
    3. Peserta berhasil didaftarkan

2. **Registrasi Penitip**
    1. Pilih menu **1** -> **2**
    2. Masukkan: Nama, NIK, No HP, No Rekening
    3. Penitip berhasil didaftarkan

2. **Penitipan Barang**
    1. Pilih menu **2** -> **1**
    2. Pilih penitip dari daftar (masukkan NIK)
    3. Masukkan detail barang:
        * Nama Barang
        * Jenis Barang
        * Harga Awal (Rp)
        * Kelipatan Bid (Rp)
        * Harga BIN/Buy It Now (Rp)
    4. Barang berhasil dititipkan dengan kode barang

3. **Proses Lelang**
    1. **Bid Barang**
        1. Pilih menu **3** -> **2**
        2. Lihar daftar barang, pilih kode barang
        3. Pilih peserta (Masukkan NIK)
        4. Masukkan harga bid
        5. Sistem akan:
            * Memvalidasi harga
            * Notifikasi ke semua observer
            * Update pemenang sementara (Peserta bid tertinggi)
            * Simpan ke history bid

    2. **Buy It Now (BIN)**
        1. Pilih menu **3** -> **3**
        2. Pilih barang dan peserta
        3. Barang langsung menunggu pembayaran

    3. **Lihat History Bid**
        1. Pilih menu **3** -> **4**
        2. Pilih kode barang
        3. Tampil history lengkap dengan timestamp

    4. **Cancel Bid**
        1. Pilih Menu **3** -> **5**
        2. Pilih barang yang sedang di bid
        3. Konfirmasi cancel
        4. Barang kembali tersedia

    5. **Selesaikan Lelang**
        1. Pilih menu **3** -> **6**
        2. Pilih barang yang sedang di bid
        3. Status berubah ke "Menunggu Pembayaran"
        4. Pemenang final ditentukan

4. **Pembayaran**
    1. **Proses Pembayaran**
        1. Pilih Menu **4** -> Pilih metode pembayaran:
            * **2**: Transfer Bank
            * **3**: E-Wallet
            * **4**: Cash/Tunai
        2. Pilih barang yang menunggu pembayaran
        3. Sistem menampilkan detai pembayaran
        4. Perhitungan otomatis: 
            * Fee penyelenggara: 10%
            * Pembayaran ke penitip: 90%
        5. Status barang menjadi TERJUAL
    
    2. **Tandai Bid and Run**
        1. Pilih menu **4** -> **5**
        2. Pilih barang yang tidak dibayar
        3. Peserta masuk Blacklist
        4. Barang kembali tersedia

5. **Laporan**
    * Menu **5** -> **1**: Semua barang dengan status
    * Menu **5** -> **2**: Daftar peserta terdaftar
    * Menu **5** -> **3**: Daftar penitip terdaftar
    * Menu **5** -> **4**: Blacklist peserta
    * Menu **5** -> **5**: Total fee penyelenggara
