package com.javarush.task.task30.task3008.client;
import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/*
Клиент, в начале своей работы, должен запросить у пользователя адрес и порт сервера, подсоединиться к указанному адресу, получить запрос имени от сервера, спросить имя у пользователя, отправить имя пользователя серверу, дождаться принятия имени сервером.
После этого клиент может обмениваться текстовыми сообщениями с сервером.
Обмен сообщениями будет происходить в двух параллельно работающих потоках.
Один будет заниматься чтением из консоли и отправкой прочитанного серверу, а второй поток будет получать данные от сервера и выводить их в консоль.
 */
public class Client {
    protected Connection connection;
    volatile private boolean clientConnected = false; // 5) Добавь приватное поле-флаг boolean clientConnected в класс Client. Проинициализируй его значением false.

// ------   SocketThread будет отвечать за поток, устанавливающий сокетное соединение и читающий сообщения сервера.
    public class SocketThread extends Thread {
        //должен выводить текст message в консоль
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        // void informAboutAddingNewUser(String userName) - должен выводить в консоль информацию о том,
        // что участник с именем userName присоединился к чату.
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName+" присоединился к чату");
        }

        //informAboutDeletingNewUser(String userName) - должен выводить в консоль, что участник с именем userName покинул чат.
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName+" покинул чат");
        }

//        void notifyConnectionStatusChanged(boolean clientConnected) - этот метод должен:
//        а) Устанавливать значение поля clientConnected внешнего объекта Client в соответствии с переданным параметром.
//        б) Оповещать (пробуждать ожидающий) основной поток класса Client.
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {

                // В цикле получать сообщения, используя соединение connection
                Message message = connection.receive();

                MessageType type = message.getType();
                if(type!=MessageType.NAME_REQUEST && type!=MessageType.NAME_ACCEPTED) {
                    throw new IOException("Unexpected MessageType");

                } else {
                    if(type == MessageType.NAME_REQUEST) {
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                    } else if(type == MessageType.NAME_ACCEPTED) {
                        notifyConnectionStatusChanged(true);
                        break;
                    }
                }
            }
        } // end clientHandshake

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if(message == null || message.getType()==null)
                    throw new IOException("Unexpected MessageType");
                switch (message.getType()) {
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }

        @Override
        public void run() {
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try {
                Socket socket = new Socket(serverAddress, serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);

            }
        } // end run
    } // end SocketThread



    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Server address:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Server port:");
        return ConsoleHelper.readInt();
    }
    protected String getUserName() {
        ConsoleHelper.writeMessage("User name:");
        return ConsoleHelper.readString();
    }
//    в данной реализации клиента всегда должен возвращать true (мы всегда отправляем текст введенный в консоль).
//    Этот метод может быть переопределен, если мы будем писать какой-нибудь другой клиент, унаследованный от нашего,
//    который не должен отправлять введенный в консоль текст.
    protected boolean shouldSendTextFromConsole() {
        return true;
    }
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

//    6. void sendTextMessage(String text) - создает новое текстовое сообщение, используя переданный текст и отправляет его серверу через соединение connection.
//    Если во время отправки произошло исключение IOException, то необходимо вывести информацию об этом пользователю и присвоить false полю clientConnect
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
            clientConnected = false;
        }

    }
/*
    1. Добавь метод public void run().
    Он должен создавать вспомогательный поток SocketThread, ожидать пока тот установит соединение с сервером, а после этого в цикле считывать сообщения с консоли и отправлять их серверу.
    Условием выхода из цикла будет отключение клиента или ввод пользователем команды 'exit'.
    Для информирования главного потока, что соединение установлено во вспомогательном потоке, используй методы wait() и notify() объекта класса Client.
*/
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
                return;
            }
        }
/*
д) После того, как поток дождался нотификации, проверь значение clientConnected.
Если оно true - выведи "Соединение установлено.
Для выхода наберите команду 'exit'.".
Если оно false - выведи "Произошла ошибка во время работы клиента.".
е) Считывай сообщения с консоли пока клиент подключен.
Если будет введена команда 'exit', то выйди из цикла.
ж) После каждого считывания, если метод shouldSendTextFromConsole() возвращает true, отправь считанный текст с помощью метода sendTextMessage().
 */
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            String input;
            while (clientConnected) {
                input = ConsoleHelper.readString();
                if ("exit".equals(input)) {
                    break;
                }
                if (shouldSendTextFromConsole()) {
                    sendTextMessage(input);
                }
            }
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
    } // end run

    //----------------- main ---------------
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
