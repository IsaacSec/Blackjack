package data.database.services.implementation;

import data.database.model.User;
import data.database.services.dao.AbstractDao;
import data.database.services.definition.AccountDao;
import data.database.services.definition.Accountable;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SQLCriterion;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImplementer extends AbstractDao<Integer, User> implements AccountDao{

    @Override
    public boolean registerUser(User user) {
        System.out.println("Trying to register user: "+user.getUsername());
        persist(user);
        return true;
    }

    @Override
    public User getUserByNicknameAndPass(String nickname, String pass) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username", nickname));
        criteria.add(Restrictions.eq("password", pass));

        if (criteria.list().isEmpty()) {
            return null;
        } else {
            return (User)criteria.list().get(0);
        }

    }
}
