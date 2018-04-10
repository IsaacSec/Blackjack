package data.database.services.definition;

import data.database.model.User;

public interface AccountDao {

    boolean registerUser(User user);

    User getUserByNicknameAndPass(String nickname, String pass);

}
