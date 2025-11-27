package model;

import pattern.observer.Observer;

public class Peserta extends User implements Observer{
    private String alamat;

    public Peserta(String nama, Integer nik, Integer noHp, String alamat){
        super(nama, nik, noHp);
        this.alamat = alamat;
    }

    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) { this.alamat = alamat;}

    @Override
    public void tampilkanInfo(){
        System.out.println("Peserta: " +nama+ " | NIK: " +nik+ " | HP: " +noHp+ " | Alamat: " + alamat);
    }

    @Override
    public void update(String message){
        System.out.println("[NOTIFIKASI untuk " +nama+ "] " + message);
    }
}
