package data.database.services.implementation;


import data.database.model.User;
import data.database.services.definition.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class AccountServiceImplementer implements AccountService{

    @Autowired
    private AccountDaoImplementer dao;


    @Override
    public boolean registerUser(User user) {
        return dao.registerUser(user);
    }

    @Override
    public User getUserByNicknameAndPass(String nickname, String pass) {
        return dao.getUserByNicknameAndPass(nickname, pass);
    }
}
