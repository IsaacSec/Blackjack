package data.database.services.definition;

import data.database.model.User;

public interface Accountable {

    boolean registerUser(User user);

    User getUserByNicknameAndPass(String nickname, String pass);
}
