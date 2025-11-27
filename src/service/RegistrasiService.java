package service;

import model.*;
import java.util.*;

public class RegistrasiService {
    private List<Peserta> daftarPeserta;
    private List<Penitip> daftarPenitip;
    private Set<Integer> blackList;

    public RegistrasiService(){
        this.daftarPeserta = new ArrayList<>();
        this.daftarPenitip = new ArrayList<>();
        this.blackList = new HashSet<>();
    }

    public void registrasiPeserta(Peserta peserta){
        daftarPeserta.add(peserta);
    }

    public void registrasiPenitip(Penitip penitip){
        daftarPenitip.add(penitip);
    }

    public Peserta cariPeserta(Integer nik){
        for (Peserta p : daftarPeserta){
            if (p.getNik().equals(nik)){
                return p;
            }
        }
        return null;
    }

    public Penitip cariPenitip(Integer nik){
        for(Penitip p : daftarPenitip){
            if(p.getNik().equals(nik)){
                return p;
            }
        }
        return null;
    }

    public void tambahKeBlacklist(Integer nik){
        blackList.add(nik);
    }

    public boolean isBlackListed(Integer nik){
        return blackList.contains(nik);
    }

    public void tampilkanSemuaPeserta(){
        System.out.println("\n=== DAFTAR PESERTA ===");
        if (daftarPeserta.isEmpty()){
            System.out.println("Belum ada peserta terdaftar.\n");
        }
        for (Peserta p : daftarPeserta){
            p.tampilkanInfo();
        }
        System.out.println();
    }

    public void tampilkanSemuaPenitip(){
        System.out.println("\n=== DAFTAR PENITIP ===");
        if(daftarPenitip.isEmpty()){
            System.out.println("Belum ada penitip terdaftar.\n");
        }

        for(Penitip p : daftarPenitip){
            p.tampilkanInfo();
        }
        System.out.println();
    }

    public void tampilkanBlacklist(){
        System.out.println("\n===  DAFTAR BLACKLIST ===");
        if (blackList.isEmpty()){
            System.out.println("Tidak ada peserta dalam blacklist.\n");
        }
        for (Integer nik : blackList){
            Peserta p = cariPeserta(nik);
            if (p != null){ 
                System.out.println("[BLACLIST] " +p.getNama()+ " (NIK: " +nik+ ")");
            }
        }
        System.out.println();
    }
}
