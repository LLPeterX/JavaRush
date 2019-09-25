package com.javarush.task.task17.task1713;

import java.util.*;

/* 
Общий список
*/

public class Solution implements java.util.List<Long> {
    private ArrayList<Long> original = new ArrayList<Long>();

    public static void main(String[] args) {

    }

    @Override
    synchronized  public int size() {
        return original.size();
    }

    @Override
    synchronized public boolean isEmpty() {
        return original.isEmpty();
    }

    @Override
    synchronized public boolean contains(Object o) {
        //return false;
        return original.contains(o);
    }

    @Override
    synchronized public Iterator<Long> iterator() {
        //return null;
        return original.iterator();
    }

    @Override
    synchronized public Object[] toArray() {
        //return new Object[ 0 ];
        return original.toArray();
    }

    @Override
    synchronized public <T> T[] toArray(T[] a) {
        //return null;
        return original.toArray(a);

    }

    @Override
    synchronized public boolean add(Long aLong) {
        //return false;
        return original.add(aLong);
    }

    @Override
    synchronized public boolean remove(Object o) {
        //return false;
        return original.remove(o);
    }

    @Override
    synchronized public boolean containsAll(Collection<?> c) {
        //return false;
        return original.containsAll(c);
    }

    @Override
    synchronized public boolean addAll(Collection<? extends Long> c) {
        //return false;
        return original.addAll(c);
    }

    @Override
    synchronized public boolean addAll(int index, Collection<? extends Long> c) {
        //return false;
        return original.addAll(index,c);
    }

    @Override
    synchronized public boolean removeAll(Collection<?> c) {
        //return false;
        return original.removeAll(c);
    }

    @Override
    synchronized public boolean retainAll(Collection<?> c) {
        //return false;
        return original.retainAll(c);
    }

    @Override
    synchronized public void clear() {
        original.clear();
    }

    @Override
    synchronized public Long get(int index) {
        //return null;
        return original.get(index);
    }

    @Override
    synchronized public Long set(int index, Long element) {
        //return null;
        return original.set(index,element);
    }

    @Override
    synchronized public void add(int index, Long element) {
        original.add(index,element);
    }

    @Override
    synchronized public Long remove(int index) {
        //return null;
        return original.remove(index);
    }

    @Override
    synchronized public int indexOf(Object o) {
        return original.indexOf(o);
    }

    @Override
    synchronized public int lastIndexOf(Object o) {
        return original.lastIndexOf(o);
    }

    @Override
    synchronized public ListIterator<Long> listIterator() {
        //return null;
        return original.listIterator();
    }

    @Override
    synchronized public ListIterator<Long> listIterator(int index) {
        //return null;
        return original.listIterator(index);
    }

    @Override
    synchronized public List<Long> subList(int fromIndex, int toIndex) {
        //return null;
        return original.subList(fromIndex,toIndex);
    }
}
