package com.javarush.task.task20.task2001;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            //Human ivanov = new Human("Ivanov", null);
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if(ivanov.equals(somePerson)) {
                System.out.println("Objects are equal");
            } else {
                System.out.println("Objects NOT equal");
                //System.out.println("Ivanov:"+ivanov.assets+", some:"+somePerson.assets);
            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            outputStream.write(this.name.getBytes());
            outputStream.write('\n');
            if(assets == null || assets.size()==0) {
                outputStream.write('0');
                outputStream.write('\n');
            } else {
                outputStream.write(String.valueOf(assets.size()).getBytes());
                outputStream.write('\n');
                for(Asset a : this.assets) {
                    outputStream.write(a.getName().getBytes());
                    outputStream.write('\n');
                    outputStream.write(String.valueOf(a.getPrice()).getBytes());
                    outputStream.write('\n');
                }
            }
            //outputStream.flush(); - лишнее
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            StringBuilder sb = new StringBuilder();
            while(inputStream.available()>0)
                sb.append((char)inputStream.read());
            String[] content = sb.toString().split("\n");
            this.name = content[0];
            //System.out.println("Name="+this.name);
//            for(int i=0; i<content.length; i++) {
//                System.out.println(i+" "+content[i]);
//            }
            this.assets=null;
            this.assets = new ArrayList<Asset>();
            int num_assets = Integer.parseInt(content[1]);
            if(num_assets>0) {
                int j=2;
                for(int i=0; i<num_assets;i++) {
                    String assetName =content[j++];
                    String strPrice = content[j++];
                    this.assets.add(new Asset(assetName, Double.parseDouble(strPrice)));
                    //System.out.println("ASSET "+i+",name="+assetName+", price="+strPrice);
                }
            }
        }
    }
}
