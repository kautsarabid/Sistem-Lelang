package model;

import pattern.observer.Observer;
import pattern.observer.Subject;
import java.util.*;

public class Barang implements Subject {
    private static int counter = 1000;
    private String kodeBarang;
    private String namaBarang;
    private String jenisBarang;
    private double hargaAwal;
    private double kelipatanBid;
    private double hargaBIN;
    private double hargaTertinggi;
    private StatusBarang status;
    private Penitip penitip;
    private Peserta  pemenang;
    private List<Observer> observers;
    private List<BidHistory> historyBid;

    public Barang(String namaBarang, String jenisBarang, double hargaAwal, double kelipatanBid, double hargaBIN, Penitip penitip){
        this.kodeBarang = "BRG" + (counter++);
        this.namaBarang = namaBarang;
        this.jenisBarang = jenisBarang;
        this.hargaAwal = hargaAwal;
        this.kelipatanBid = kelipatanBid;
        this.hargaBIN = hargaBIN;
        this.hargaTertinggi = hargaAwal;
        this.status = StatusBarang.TERSEDIA;
        this.penitip = penitip;
        this.observers = new ArrayList<>();
        this.historyBid = new ArrayList<>();
    }

    // Inner class untuk history bid
    public static class BidHistory{
        private Peserta peserta;
        private double hargaBid;
        private Date waktu;

        public BidHistory(Peserta peserta, double hargaBid){
            this.peserta = peserta;
            this.hargaBid = hargaBid;
            this.waktu = new Date();
        }

        public Peserta getPeserta(){return peserta;}
        public double getHargaBid(){return  hargaBid;}
        public Date getWaktu(){return waktu;}
    }

    // Getters dan Setters
    public String getKodeBarang(){return kodeBarang;}
    public String getNamaBarang(){ return namaBarang;}
    public String getJenisBarang(){return jenisBarang;}
    public double getHargaAwal(){ return hargaAwal;}
    public double getKelipatanBid() { return kelipatanBid;}
    public double getHargaBIN(){return hargaBIN;}
    public double getHargaTertinggi(){return hargaTertinggi;}
    public StatusBarang getStatus() {return status;}
    public Penitip getPenitip(){return penitip;}
    public Peserta getPemenang(){return pemenang;}
    public List<BidHistory> getHistoryBid(){return historyBid;}

    public void setHargaTertinggi(double harga){this.hargaTertinggi = harga;}
    public void setStatus(StatusBarang status){ this.status = status;}
    public void setPemenang(Peserta pemenang) {this.pemenang = pemenang;}
    
    public void tambahBidHistory(Peserta peserta, double hargaBid){
        historyBid.add(new BidHistory(peserta, hargaBid));
    }

    public void clearBidHistory(){
        historyBid.clear();
    }

    // Observer Pattern
    @Override
    public void tambahObserver(Observer observer){
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void hapusObserver(Observer observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message){
        for(Observer observer : observers){
            observer.update(message);
        }
    }

    public void clearObservers(){
        observers.clear();
    }

    public void tampilkanInfo(){
          System.out.println("┌─────────────────────────────────────────");
        System.out.println("│ Kode: " + kodeBarang);
        System.out.println("│ Nama: " + namaBarang);
        System.out.println("│ Jenis: " + jenisBarang);
        System.out.println("│ Harga Awal: Rp " + String.format("%,.0f", hargaAwal));
        System.out.println("│ Kelipatan Bid: Rp " + String.format("%,.0f", kelipatanBid));
        System.out.println("│ Harga BIN: Rp " + String.format("%,.0f", hargaBIN));
        System.out.println("│ Harga Tertinggi: Rp " + String.format("%,.0f", hargaTertinggi));
        System.out.println("│ Status: " + status);
        System.out.println("│ Penitip: " + penitip.getNama());
        if (pemenang != null) {
            System.out.println("│ Pemenang Sementara: " + pemenang.getNama());
        }
        if(!historyBid.isEmpty()){
            System.out.println("| Total Bid: " + historyBid.size());
        }
        System.out.println("└─────────────────────────────────────────");
    }
}
