package pattern.factory;

import model.User;
import model.Peserta;

public class PesertaFactory implements UserFactory{
    @Override
    public User createUser(String nama, Integer nik, Integer noHp, String alamat){
        return new Peserta(nama, nik, noHp, alamat);
    }
}
