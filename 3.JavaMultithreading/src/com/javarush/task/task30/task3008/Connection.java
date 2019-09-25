package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

//Класс Connection будет выполнять роль обертки над классом java.net.Socket,
//которая должна будет уметь сериализовать и десериализовать объекты типа Message в сокет.
//Методы этого класса должны быть готовы к вызову из разных потоков.
public class Connection implements Closeable {
    final private Socket socket;
    final private ObjectInputStream in;
    final private ObjectOutputStream out;

    public Connection(Socket socket) throws IOException  {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized (out) {
            out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        Message message;
        synchronized (in) {
            message = (Message)in.readObject();
        }
        return message;
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
}
