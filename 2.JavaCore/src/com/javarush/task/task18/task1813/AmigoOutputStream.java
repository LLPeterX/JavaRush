package com.javarush.task.task18.task1813;

import java.io.*;
import java.nio.channels.FileChannel;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {

    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream component;

    public AmigoOutputStream(FileOutputStream component) throws IOException{
        super(fileName);
        this.component = component;
    }
/*
    public AmigoOutputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public AmigoOutputStream(String name, boolean append) throws FileNotFoundException {
        super(name, append);
    }

    public AmigoOutputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public AmigoOutputStream(File file, boolean append) throws FileNotFoundException {
        super(file, append);
    }

    public AmigoOutputStream(FileDescriptor fdObj) {
        super(fdObj);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public FileChannel getChannel() {
        return super.getChannel();
    }

    @Override
    protected void finalize() throws IOException {
        super.finalize();
    }
*/
    @Override
    public void flush() throws IOException{
        component.flush();
    }

    @Override
    public void write(byte[] b) throws IOException{
        component.write(b);
    }

    @Override
    public void write(int b) throws IOException{
        component.write(b);
    }


    @Override
    public void write(byte[] b, int start, int len) throws IOException{
        component.write(b,start,len);
    }


    @Override
    public void close() throws IOException{
        component.flush();
        String str = "JavaRush © All rights reserved.";
        byte[] array = str.getBytes();
        //System.out.println("len="+array.length);
        component.write(array);
        component.close();
    }


    public static void main(String[] args) throws IOException {
            new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
