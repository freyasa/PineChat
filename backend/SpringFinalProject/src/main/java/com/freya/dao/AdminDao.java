package com.freya.dao;

import com.freya.pojo.Admin;
import com.freya.pojo.ChatLog;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xzq
 * @Description 管理员数据层
 */
@Repository
@Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED,noRollbackFor=ArithmeticException.class,rollbackFor=NullPointerException.class, readOnly=false, timeout=-1)
public class AdminDao extends HibernateDaoSupport {
    public void addAdmin(Admin admin) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().save(admin);
    }

    public Admin getAdmin(Admin admin) {
        assert this.getHibernateTemplate() != null;
        return (Admin) this.getHibernateTemplate().find("from Admin a where a.username=?", admin.getUsername()).get(0);
    }
}
