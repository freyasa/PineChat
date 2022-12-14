package com.freya.pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Proxy(lazy = false)
@Table(name = "friend")
public class Friend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = User.class)
    private User part1;

    @OneToOne(targetEntity = User.class)
    private User part2;

    private String property;

    public Friend() {
    }

    public Friend(int id, User part1, User part2, String property) {
        this.id = id;
        this.part1 = part1;
        this.part2 = part2;
        this.property = property;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPart1() {
        return part1;
    }

    public void setPart1(User part1) {
        this.part1 = part1;
    }

    public User getPart2() {
        return part2;
    }

    public void setPart2(User part2) {
        this.part2 = part2;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", part1=" + part1 +
                ", part2=" + part2 +
                ", property='" + property + '\'' +
                '}';
    }
}
