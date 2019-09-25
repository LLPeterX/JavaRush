package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)

*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    int entryCount=0; // не включая root

    public CustomTree() {
        this.root = new Entry<String>("0");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
        //return null;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        // переопределить метод add(String s) - добавляет элементы дерева,
        // в качестве параметра принимает имя элемента (elementName),
        // искать место для вставки начинаем слева направо.
        // найти первое попавшееся место
/* мой старый код - не работает --
        Entry<String> currentNode = root;
        while(true) {
            if(currentNode.isAvailableToAddChildren()) {
                Entry<String> newNode = new Entry<String>(s);
                newNode.parent = currentNode;
                if (currentNode.leftChild == null) {
                    currentNode.leftChild = newNode;
                    currentNode.availableToAddLeftChildren=false;
                } else {
                    currentNode.rightChild = newNode;
                    currentNode.availableToAddRightChildren = false;
                }
                entryCount++;
                break;
            }
            // не нашли (т.е. оба узла заняты) - топаем по левому узлу
            currentNode = currentNode.leftChild;
        }
        return true;

 */
        Queue<Entry<String>> nodes = new LinkedList<>(Collections.singletonList(root));
        while (!nodes.isEmpty()) {
            Entry<String> currentNode = nodes.poll();

            if (currentNode.isAvailableToAddChildren()) {
                Entry<String> newEntry = new Entry<String>(s);
                newEntry.parent = currentNode;
                if (currentNode.availableToAddLeftChildren) {
                    currentNode.leftChild = newEntry;
                    currentNode.availableToAddLeftChildren = false;
//                    entryCount++;
//                    return true;
                } else {
                 //return appendChild(s, currentNode, LEFT);
                    //return appendChild(s, currentNode, RIGHT);
                    currentNode.rightChild = newEntry;
                    currentNode.availableToAddRightChildren = false;
                }
                entryCount++;
                return true;
            } else {
                if (currentNode.leftChild != null)
                    nodes.offer(currentNode.leftChild);
                if (currentNode.rightChild != null)
                    nodes.offer(currentNode.rightChild);
            }
        }

        return false;
    }

    // реализовать метод getParent(String s) - возвращает имя родителя элемента дерева,
    // имя которого было полученного в качестве параметра.
    public String getParent(String s) {
        Queue<Entry<String>> nodes = new LinkedList<>(Collections.singletonList(root));

        while (!nodes.isEmpty()) {
            Entry<String> node = nodes.poll();

            if (node.elementName.equals(s))
                return node.parent.elementName;

            if (node.leftChild != null)
                nodes.offer(node.leftChild);
            if (node.rightChild != null)
                nodes.offer(node.rightChild);
        }

        return null;

    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof String) {
            // тут вся логика
            Queue<Entry<String>> nodes = new LinkedList<>(Collections.singletonList(root)); // очереди 1 root
            // идем по всем узлам
            while (!nodes.isEmpty()) {
                Entry<String> node = nodes.poll(); // выбираем из очереди узел
                if (node.elementName.equals(o)) { // нашли удаляемый узел
                    // удаляем у родителя ссылку на левый или правый (ссылки просто проверяем)
                    if (node.parent.leftChild == node) {
                        node.parent.leftChild = null;
                        node.parent.availableToAddLeftChildren = true;
                    }
                    if (node.parent.rightChild == node) {
                        node.parent.rightChild = null;
                        node.parent.availableToAddRightChildren = true;
                    }
                    return true; // сразу выходим - надо удалить только первый найденный
                }

                if (node.leftChild != null)
                    nodes.offer(node.leftChild);
                if (node.rightChild != null)
                    nodes.offer(node.rightChild);
            }
            return true;
        } else {
            throw new UnsupportedOperationException();
        }
    }



    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        //return entryCount; // так нельзя - не работает с удалением

        int count = -1;

        Queue<Entry<String>> nodes = new LinkedList<>(Collections.singletonList(root)); // помещаем в очередь один объект
        while (!nodes.isEmpty()) {
            Entry<String> node = nodes.poll(); // вытащить и удалить эл-т из очереди.
            count++;

            if (node.leftChild != null)
                nodes.offer(node.leftChild); // добавить лквый узел в конец очереди
            if (node.rightChild != null)
                nodes.offer(node.rightChild); // добавить правый узел в конец очереди
        }

        return count;
    }


    // Класс, описывающий тип элементов дерева:
    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;
        int level;

        // конструктор
        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren=true;
            this.level = (parent!=null) ? parent.level+1 : 0;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren||availableToAddRightChildren;
        }

    } // end class Entry
}
