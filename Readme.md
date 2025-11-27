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
### Registrasi User
1. **Registrasi Peserta**
    * Pilih menu **1** -> **1**
    * Masukkan: Nama, NIK, No HP, Alamat
    * Peserta berhasil didaftarkan

2. **Registrasi Penitip**
    * Pilih menu **1** -> **2**
    * Masukkan: Nama, NIK, No HP, No Rekening
    * Penitip berhasil didaftarkan

### Penitipan Barang
* Pilih menu **2** -> **1**
* Pilih penitip dari daftar (masukkan NIK)
* Masukkan detail barang:
    * Nama Barang
    * Jenis Barang
    * Harga Awal (Rp)
    * Kelipatan Bid (Rp)
    * Harga BIN/Buy It Now (Rp)
* Barang berhasil dititipkan dengan kode barang

### Proses Lelang
1. **Bid Barang**
    * Pilih menu **3** -> **2**
    * Lihar daftar barang, pilih kode barang
    * Pilih peserta (Masukkan NIK)
    * Masukkan harga bid
    * Sistem akan:
        * Memvalidasi harga
        * Notifikasi ke semua observer
        * Update pemenang sementara (Peserta bid tertinggi)
        * Simpan ke history bid

2. **Buy It Now (BIN)**
    * Pilih menu **3** -> **3**
    * Pilih barang dan peserta
    * Barang langsung menunggu pembayaran

3. **Lihat History Bid**
    * Pilih menu **3** -> **4**
    * Pilih kode barang
    * Tampil history lengkap dengan timestamp

4. **Cancel Bid**
    * Pilih Menu **3** -> **5**
    * Pilih barang yang sedang di bid
    * Konfirmasi cancel
    * Barang kembali tersedia

5. **Selesaikan Lelang**
    * Pilih menu **3** -> **6**
    * Pilih barang yang sedang di bid
    * Status berubah ke "Menunggu Pembayaran"
    * Pemenang final ditentukan

### Pembayaran
1. **Proses Pembayaran**
    * Pilih Menu **4** -> Pilih metode pembayaran:
        * **2**: Transfer Bank
        * **3**: E-Wallet
        * **4**: Cash/Tunai
    * Pilih barang yang menunggu pembayaran
    * Sistem menampilkan detai pembayaran
    * Perhitungan otomatis: 
        * Fee penyelenggara: 10%
        * Pembayaran ke penitip: 90%
    * Status barang menjadi TERJUAL
    
2. **Tandai Bid and Run**
    * Pilih menu **4** -> **5**
    * Pilih barang yang tidak dibayar
    * Peserta masuk Blacklist
    * Barang kembali tersedia

### Laporan
* Menu **5** -> **1**: Semua barang dengan status
* Menu **5** -> **2**: Daftar peserta terdaftar
* Menu **5** -> **3**: Daftar penitip terdaftar
* Menu **5** -> **4**: Blacklist peserta
* Menu **5** -> **5**: Total fee penyelenggara
