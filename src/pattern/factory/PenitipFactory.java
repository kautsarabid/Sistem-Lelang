package pattern.factory;
import model.User;
import model.Penitip;

public class PenitipFactory implements UserFactory{
    @Override
    public User createUser(String nama, Integer nik, Integer noHp, String noRekening){
        return new Penitip(nama, nik, noHp, noRekening);
    }
}
