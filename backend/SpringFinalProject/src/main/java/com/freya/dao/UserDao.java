package com.freya.dao;

import com.freya.pojo.Friend;
import com.freya.pojo.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED,noRollbackFor=ArithmeticException.class,rollbackFor=NullPointerException.class, readOnly=false, timeout=-1)
public class UserDao extends HibernateDaoSupport {
    public void addUser(User user) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().save(user);
    }

    public String deleteUser(User user) {
        try {
            assert this.getHibernateTemplate() != null;
            this.getHibernateTemplate().delete(user);
            return "200";
        }
        catch (Exception e) {
            return "500";
        }
    }

    public User modifyUser(User user) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().update(user);
        return user;
    }

    public User getSingleUser(User user) {
        assert this.getHibernateTemplate() != null;
        return (User) this.getHibernateTemplate().find("from User u where u.username=?", user.getUsername()).get(0);
    }

    public List<User> getUser() {
        assert this.getHibernateTemplate() != null;
        return (List<User>) this.getHibernateTemplate().find("from User u");
    }
}
