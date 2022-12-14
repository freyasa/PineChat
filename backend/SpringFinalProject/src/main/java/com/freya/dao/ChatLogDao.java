package com.freya.dao;


import com.freya.pojo.ChatLog;
import com.freya.pojo.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xzq
 * @Description 聊天记录数据层
 */
@Repository
@Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED,noRollbackFor=ArithmeticException.class,rollbackFor=NullPointerException.class, readOnly=false, timeout=-1)
public class ChatLogDao extends HibernateDaoSupport {
    public void addChatLog(ChatLog chatLog) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().save(chatLog);
    }

    public String deleteChatLog(ChatLog chatLog) {
        try {
            assert this.getHibernateTemplate() != null;
            this.getHibernateTemplate().delete(chatLog);
            return "200";
        }
        catch (Exception e) {
            return "500";
        }
    }

    public ChatLog modifyChatLog(ChatLog chatLog) {
        assert this.getHibernateTemplate() != null;
        this.getHibernateTemplate().update(chatLog);
        return chatLog;
    }

    public ChatLog getSingleChatLog(ChatLog chatLog) {
        assert this.getHibernateTemplate() != null;
        return (ChatLog) this.getHibernateTemplate().find("from ChatLog c where c.id=?", chatLog.getId()).get(0);
    }

    public List<ChatLog> getSingleUserChatLog(User user, User user1) {
        assert this.getHibernateTemplate() != null;
        return (List<ChatLog>) this.getHibernateTemplate().find("from ChatLog c where (c.sender=? and c.receiver=?) or (c.receiver=? and c.sender=?)", user, user1, user, user1);
    }

    public List<ChatLog> getSingleUserRecent(User user) {
        assert this.getHibernateTemplate() != null;
        return (List<ChatLog>) this.getHibernateTemplate().find("from ChatLog c where c.sender=? or c.receiver=?", user, user);
    }

    public List<ChatLog> getChatLogs() {
        assert this.getHibernateTemplate() != null;
        return (List<ChatLog>) this.getHibernateTemplate().find("from ChatLog c");
    }
}
