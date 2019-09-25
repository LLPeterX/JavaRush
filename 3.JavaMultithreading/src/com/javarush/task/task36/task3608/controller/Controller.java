package com.javarush.task.task36.task3608.controller;

//MVC (2)
//        1. Создай пакет controller, в котором создай класс Controller.
//        Этот класс будет получать запрос от клиента, оповещать Модель об этом, а Модель, в свою очередь, будет обновлять ModelData.
//
//        2. Добавь в контроллер поле Model model вместе с сеттером.
//
//        3. В контроллере создай публичный метод void onShowAllUsers(), который должен обратиться к модели и инициировать загрузку пользователей.
//
//        4. Создай пакет view. В нем создай интерфейс View.
//
//        5. В интерфейс View добавь два метода: void refresh(ModelData modelData) и void setController(Controller controller)
//


import com.javarush.task.task36.task3608.model.MainModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

// контроллер - промежуток между моделью (MainModel) и визуализацией (UserView)
public class Controller {
    private Model model;
    private UsersView usersView; // все пользователи
    private EditUserView editUserView; // конкретный пользователь

    public void setModel(Model model) {
        this.model = model;

    }

    public void onShowAllUsers() {
        // загружаем из модели всех польователей и передаем их в usersView
        model.loadUsers();
        usersView.refresh(model.getModelData()); // null pointer
    }

    public void onShowAllDeletedUsers() {
        model.loadDeletedUsers();
        usersView.refresh(model.getModelData()); // null pointer

    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
   }

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }

    public void onOpenUserEditForm(long userId) {
        model.loadUserById(userId);
        //usersView.refresh(model.getModelData());
        editUserView.refresh(model.getModelData());
    }

    public void onUserDelete(long id) {
        model.deleteUserById(id);
        usersView.refresh(model.getModelData());
    }


    public void onUserChange(String name, long id, int level) {
        model.changeUserData(name,id,level);
        usersView.refresh(model.getModelData());
    }


}
