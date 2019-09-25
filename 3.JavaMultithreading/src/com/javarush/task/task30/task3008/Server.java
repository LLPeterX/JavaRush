package com.javarush.task.task30.task3008;

// Сервер создает серверное сокетное соединение.
//     - В цикле ожидает, когда какой-то клиент подключится к сокету.
//     - Создает новый поток обработчик Handler, в котором будет происходить обмен сообщениями с клиентом.
//     - Ожидает следующее соединение.


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for(Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение пользователю "+entry.getKey());
            }
        }
    }

    // внутр.класс Handler
    private static class Handler extends Thread {
        private Socket socket;

        // конструктор
        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message messageAskUser = new Message(MessageType.NAME_REQUEST);
            while (true) {
                connection.send(messageAskUser);
                Message answer = connection.receive();
                if(answer.getType() == MessageType.USER_NAME) {
                    String userName = answer.getData();
                    if (userName != null && userName.length() > 0 && !connectionMap.containsKey(userName)) {
                        connectionMap.put(answer.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED)); // отправить сообщение, что имя принято
                        return userName;
                    }
                }
            }
        } // serverHandshake

/* notifyUsers() :
//1) Пройтись по connectionMap.
//2) У каждого элемента из п.2 получить имя клиента, сформировать команду с типом USER_ADDED и полученным именем.
//3) Отправить сформированную команду через connection.
//4) Команду с типом USER_ADDED и именем равным userName отправлять не нужно, пользователь и так имеет информацию о себе.
 */
        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if(!name.equalsIgnoreCase(userName))
                    connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

/*
// Этап третий - главный цикл обработки сообщений сервером.
//Он должен:
//            1. Принимать сообщение клиента
//2. Если принятое сообщение - это текст (тип TEXT), то формировать новое текстовое сообщение путем конкатенации: имени клиента, двоеточия, пробела и текста сообщения.
// Например, если мы получили сообщение с текстом "привет чат" от пользователя "Боб", то нужно сформировать сообщение "Боб: привет чат".
// 3. Отправлять сформированное сообщение всем клиентам с помощью метода sendBroadcastMessage().
// 4. Если принятое сообщение не является текстом, вывести сообщение об ошибке
//5. Организовать бесконечный цикл, внутрь которого перенести функционал пунктов 10.1-10.4.
 */
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while(true) {
                Message clientMessage = connection.receive();
                if(clientMessage.getType() == MessageType.TEXT) {
                    Message answer = new Message(MessageType.TEXT, String.format("%s: %s",userName, clientMessage.getData()));
                    sendBroadcastMessage(answer);
                } else {
                    ConsoleHelper.writeMessage("Invalid text from "+userName);
                }
            }
        }
        // основной метод
/*
1. Выводить сообщение, что установлено новое соединение с удаленным адресом, который можно получить с помощью метода getRemoteSocketAddress().
2. Создавать Connection, используя поле socket.
3. Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента.
4. Рассылать всем участникам чата информацию об имени присоединившегося участника (сообщение с типом USER_ADDED).
Подумай, какой метод подойдет для этого лучше всего.
5. Сообщать новому участнику о существующих участниках.
6. Запускать главный цикл обработки сообщений сервером.
7. Обеспечить закрытие соединения при возникновении исключения.
8. Отловить все исключения типа IOException и ClassNotFoundException, вывести в консоль информацию, что произошла ошибка при обмене данными с удаленным адресом.
9. После того как все исключения обработаны, если п.11.3 отработал и возвратил нам имя, мы должны удалить запись для этого имени из connectionMap и разослать всем остальным участникам сообщение с типом USER_REMOVED и сохраненным именем.
10. Последнее, что нужно сделать в методе run() - вывести сообщение, информирующее что соединение с удаленным адресом закрыто.

 */
        @Override
        public void run() {
            String newUserName = null;
            ConsoleHelper.writeMessage("Установлено новое соедние с удалённым адресом "+socket.getRemoteSocketAddress());
            try(Connection connection = new Connection(socket)) { // п.2 - создали соединение
                newUserName = serverHandshake(connection); // п.3 - рукопожатие
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, newUserName)); // п.4 - разослали всем сообщение
                //sendListOfUsers(connection, newUserName);
                notifyUsers(connection,newUserName); // п.5 кривой - список участников
                serverMainLoop(connection, newUserName); // п.6 - основной цикл
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            } finally {
                if (newUserName != null) {
                    connectionMap.remove(newUserName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, newUserName));
                }
            }
            ConsoleHelper.writeMessage("Соединение с удалённым адресом закрыто");
        }
    } // end Handler


    public static void main(String[] args) {
        int serverPort;
        ConsoleHelper.writeMessage("Enter server port: ");
        serverPort = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            ConsoleHelper.writeMessage("Сервер запущен");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Handler handler = new Handler(clientSocket);
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end main
} // end Server
