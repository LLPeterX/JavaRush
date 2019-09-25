package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream; // for file data (not object!)
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.flush();
        //out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(this.fileName,true);
        //in.defaultReadObject();
        //in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Solution sol = new Solution("c:/temp/test2.txt");
        sol.writeObject("Test data");
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("c:/temp/test2.bin"));
        output.writeObject(sol);

        //Solution sol2 = new Solution("c:/temp/test2.bin");
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("c:/temp/test2.bin"));
        try {
            Solution sol2 = (Solution) input.readObject();
            sol2.writeObject("New data");
            //output.writeObject(sol2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
