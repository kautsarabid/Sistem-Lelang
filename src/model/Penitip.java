package model;

public class Penitip extends User{
    private String noRekening;

    public Penitip(String nama, Integer nik, Integer noHp, String noRekening){
        super(nama, nik, noHp);
        this.noRekening = noRekening;
    }

    public String getNoRekening(){return noRekening;}
    public void setNoRekening(String noRekening) { this.noRekening = noRekening; }

    @Override
    public void tampilkanInfo(){
        System.out.println("Penitip: " +nama+ " | NIK: " +nik+ " | HP: " +noHp+ " | Rekening: " +noRekening);
    }
}
