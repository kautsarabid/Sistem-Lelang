package model;

public abstract class User {
    protected String nama;
    protected Integer nik;
    protected Integer noHp;

    public User(String nama, Integer nik, Integer noHp){
        this.nama = nama;
        this.nik = nik;
        this.noHp = noHp;
    }

    public String getNama() { return nama;}
    public void setNama(String nama) { this.nama = nama;}
    public Integer getNik() { return nik;}
    public Integer getNoHp() { return noHp;}

    public abstract void tampilkanInfo();
}
