package pattern.factory;
import model.User;

public interface UserFactory {
    User createUser(String nama, Integer nik, Integer noHp, String extra);
}
