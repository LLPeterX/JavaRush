package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.dao.mock.DataSource;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    // ниже сомнительно
    @Override
    public void loadUsers() {
//        DataSource ds = DataSource.getInstance();
//        modelData.setUsers(ds.getUsers());
        List<User> u = new ArrayList<>();
        u.add(new User("Abbat",1,1));
        u.add(new User("Bobby",2,1));
        modelData.setUsers(u);
    }

    @Override
    public void loadDeletedUsers() {
        //List<User> u = new ArrayList<>();
        throw new UnsupportedOperationException();

    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }


    public List<User> getAllUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw new UnsupportedOperationException();
    }

}
