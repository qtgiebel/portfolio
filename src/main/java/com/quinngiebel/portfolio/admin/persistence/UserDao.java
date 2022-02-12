package com.quinngiebel.portfolio.admin.persistence;

import com.quinngiebel.portfolio.admin.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    List<User> users;

    public List<User> getAllUsers(){
        return null;
    }

    public User addUser(User user) {
        return null;
    }

    public User getUser(int rollNo) {
        return users.get(rollNo);
    }

    public void updateUser(User user) {

    }

    public void deleteStudent(User user) {

    }
}
