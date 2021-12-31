package com.kok.jpasource.entity;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author jay
 * @date 20 Dec 2021
 */
@SQLDelete(sql = "UPDATE t_user SET deleted = true WHERE id=?")
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserAddress userAddress;



    private String name;

    private boolean deleted = false;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
