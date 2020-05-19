package io.todo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Todo extends PanacheEntity {

    public String title;
    public boolean completed;
    @ManyToOne
    public User user;

    public static List<User> listAllByUser(User user, Sort sort) {
        return list("user = ?1 ",sort,user);
    }

}