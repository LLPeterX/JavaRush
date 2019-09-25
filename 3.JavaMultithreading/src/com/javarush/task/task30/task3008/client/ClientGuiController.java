package com.javarush.task.task30.task3008.client;

public class ClientGuiController extends Client {
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    // вн.класс
    public class GuiSocketThread extends SocketThread {

        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        protected  void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }


    } // end GuiSocketThread

    @Override
    protected  SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    @Override
    public String getServerAddress() {
        return view.getServerAddress();
    }

    @Override
    public int getServerPort() {
        return view.getServerPort();
    }

    @Override
    public String getUserName() {
        return view.getUserName();
    }

    @Override
    public void run() {
        //new SocketThread().run();
        // Разберись, почему нет необходимости вызывать метод run() в отдельном потоке, как мы это делали для консольного клиента.
        // нихера не понятно!
        SocketThread thread = getSocketThread();
        thread.run();
    }

    public ClientGuiModel getModel() {
        return this.model;
    }

    public static void main(String[] args) {
        ClientGuiController controller = new ClientGuiController();
        controller.run();
    }

}

/*
 Ты написал сервер для обмена текстовыми сообщениями.
• Ты написал консольный клиент, который умеет подключаться к серверу и обмениваться сообщениями с другими участниками.
• Ты написал бот клиента, который может принимать запросы и отправлять данные о текущей дате и времени.
• Ты написал клиента для чата с графическим интерфейсом.

Что можно добавить или улучшить:
• Можно добавить поддержку приватных сообщений (когда сообщение отправляется не всем, а какому-то конкретному участнику).
• Можно расширить возможности бота, попробовать научить его отвечать на простейшие вопросы или время от времени отправлять шутки.
• Добавить возможность пересылки файлов между пользователями.
• Добавить контекстное меню в графический клиент, например, для отправки приватного сообщения кому-то из списка участников.
• Добавить раскраску сообщений в графическом клиенте в зависимости от отправителя.
• Добавить блокировку сервером участников за что-либо, например, ненормативную лексику в сообщениях.
• Добавить еще миллион фич и полезностей!

Ты научился:
• Работать с сокетами.
• Пользоваться сериализацией и десериализацией.
• Создавать многопоточные приложения, синхронизировать их, применять модификатор volatile, пользоваться классами из библиотеки java.util.concurrent.
• Применять паттерн MVC.
• Использовать внутренние и вложенные классы.
• Работать с библиотекой Swing.
• Применять классы Calendar и SimpleDateFormat.

Так держать!


 */