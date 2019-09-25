package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.List;

public interface Model {
    public ModelData getModelData();
    void loadUsers();
    void loadDeletedUsers();
    void loadUserById(long userId);
    //List<User> getAllUsers();
    void deleteUserById(long userId);
    void changeUserData(String name, long id, int level);
}
