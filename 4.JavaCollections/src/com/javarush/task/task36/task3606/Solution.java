package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        // здесь надо добавитть в hiddenClasses все *.class из каталога packageName
        //System.out.println("DIR: "+packageName);
        File dir = new File(this.packageName);
        int ix = packageName.indexOf("com/javarush/");
        String simplePackageName  = packageName.substring(ix).replaceAll("/",".");
        //System.out.println("Simple: "+simplePackageName);
        for(File f: dir.listFiles()) {
            if(f.getName().endsWith(".class")) {
                // заносим классы в List
                // сначала читаем содержмое каждого файлв в byte[]
                Class c = new ClassFromPath().load(f.toPath(), simplePackageName);
                hiddenClasses.add(c);
            }
        }
    }

    // должен создавать объект указанного класса
    public HiddenClass getHiddenClassObjectByKey(String key) {
        //return null;
        for (Class c : hiddenClasses) {
            if (c.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor[] constructors = c.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance(null);
                        }
                    }
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }
// ----------------------------------- из task3507  -------------------------------
    public static class ClassFromPath extends ClassLoader {
        public Class<?> load(Path path, String packageName) {
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] bytes = Files.readAllBytes(path);
                return defineClass(className, bytes, 0, bytes.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

