package com.freya.dao;

import com.freya.pojo.Admin;
import com.freya.pojo.LoginLog;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED,noRollbackFor=ArithmeticException.class,rollbackFor=NullPointerException.class, readOnly=false, timeout=-1)
public class LoginLogDao extends HibernateDaoSupport {
    public void addLoginLog(LoginLog loginLog) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().save(loginLog);
    }

    public List<LoginLog> getLoginLogs() {
        assert this.getHibernateTemplate() != null;
        return (List<LoginLog>) this.getHibernateTemplate().find("from LoginLog l");
    }
}
