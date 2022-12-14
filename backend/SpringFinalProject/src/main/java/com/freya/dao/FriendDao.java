package com.freya.dao;

import com.freya.pojo.Friend;
import com.freya.pojo.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author xzq
 * @Description 好友部分数据层
 */
@Repository
@Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED,noRollbackFor=ArithmeticException.class,rollbackFor=NullPointerException.class, readOnly=false, timeout=-1)
public class FriendDao extends HibernateDaoSupport {
    public void addFriend(Friend friend) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().save(friend);
    }

    public String deleteFriend(Friend friend) {
        try {
            assert this.getHibernateTemplate() != null;
            this.getHibernateTemplate().delete(friend);
            return "200";
        }
        catch (Exception e) {
            return "500";
        }
    }

    public Friend modifyFriend(Friend friend) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().update(friend);
        return friend;
    }

    public List<Friend> getSingleFriend(Friend friend) {
        assert this.getHibernateTemplate() != null;
        return (List<Friend>) this.getHibernateTemplate().find("from Friend f where f.part1=? and f.part2=?", friend.getPart1(), friend.getPart2());
    }

    public List<User> getConditionalUser(User user) {
        assert this.getHibernateTemplate() != null;
        return (List<User>) this.getHibernateTemplate().find("from User u where u.username like ?", user.getUsername());
    }

    public List<Friend> getFriends() {
        assert this.getHibernateTemplate() != null;
        return (List<Friend>) this.getHibernateTemplate().find("from Friend f");
    }

    public List<Friend> getSingleUserFriend(User user) {
        assert this.getHibernateTemplate() != null;

        List<Friend> list1 = (List<Friend>) this.getHibernateTemplate().find("from Friend f where f.part1.username=?", user.getUsername());
        List<Friend> list2 = (List<Friend>) this.getHibernateTemplate().find("from Friend f where f.part2.username=?", user.getUsername());
        List<Friend> list = new ArrayList<>();
        Stream.of(list1, list2).forEach(list::addAll);
        System.out.println("list:" + list1.size());
        System.out.println("list:" + list2.size());
        return list;
    }
}
