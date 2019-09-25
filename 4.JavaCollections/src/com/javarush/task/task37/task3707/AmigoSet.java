package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

// 2. Этот класс должен работать с любыми типами, поэтому сделай его дженериком: добавь тип, например, E.
//Стандартные буквы, которые используют для дженериков - это E (element), T (type), K (key), V (value).
//Названия не принципиальны, но облегчают чтение кода.
public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
    final static private Object PRESENT = new Object(); // заглушка для Nap
    // Map: Список ключей будет нашим сэтом, а вместо значений будем пихать в мапу заглушку PRESENT.
    private transient HashMap<E,Object> map; // не должно сериализоваться
    private static final long serialVersionUID = 1L;

    @Override
    public Iterator iterator() {
        // вернуть итеоратор для множества ключей map
        return  map.keySet().iterator();
        //return null;
    }

    @Override
    public int size() {
        //return 0;
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        //return super.contains(o);
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        //super.clear();
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        //return super.remove(o);
        return map.remove(o)==null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException, InternalError {
        //Напиши свою реализацию метода Object clone(), сделай поверхностное клонирование.
        //
        //* Клонируй множество, клонируй map.
        //* В случае возникновения исключений выбрось InternalError.
        //* Убери лишнее пробрасывание исключения.
        // - нужно клонировать наш объект (this) с помощью одноимённого метода родителя
        //   (который возвращает объект который должен вернуть наш переопределённый метод);
        //- клонировать поле map нашего объекта в поле нашего клона (из пункта выше);
        //- любые исключения перехватить и вместо них выбросить throw new InternalError();.
        try {
            AmigoSet<E> obj = (AmigoSet<E>)super.clone();
            obj.map = (HashMap)map.clone();
            return obj;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    // конструкторы

    public AmigoSet() {
        this.map = new HashMap<>();
    }
// 4. Создай конструктор с одним параметром Collection<? extends E> collection.
////Для инициализации поля map воспользуйся конструктором, в который передается Capacity.
// Вычисли свою Capacity по такой формуле: максимальное из 16 и округленного в большую сторону значения (collection.size()/.75f)
// Добавь все элементы из collection в нашу коллекцию.
// Нужный метод добавления всех элементов у нас есть благодаря тому, что AbstractSet наследуется от AbstractCollection.
    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max((int) (collection.size() / .75f) + 1, 16); // т.к. ceil() не работает
        this.map = new HashMap<>(capacity);
        //this(Math.max((int) (collection.size() / .75f) + 1, 16), collection);
        //map.putAll((Map<? extends E, ?>) collection);
        for(E e : collection)
            map.put(e,PRESENT);

    }
/*
    // конструктор с capacity (емкость)
    public AmigoSet(int capacity, Collection<? extends E> collection) {
        this(); // инициализация map (вызов конструктора без параметров)
        map.putAll((Map<? extends E, ?>) collection);
    }
*/
    @Override
    public boolean add(Object o) {
        //return super.add(o);
        if(map.containsKey(o)) return false;
        map.put((E)o,PRESENT);
        return true;
    }
    // ------------- сериализация ----------------
    private void writeObject(ObjectOutputStream stream) throws IOException {
        // map у нас transient, поэтому используем HashMapReflectionHelper()
        stream.defaultWriteObject();
        int mapSize = map.size();
        stream.writeObject(mapSize);
        // сериализуем ключи map (значения всё равно = PRESENT)
        for(E e : map.keySet()) {
            stream.writeObject(e);
        }
        stream.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        stream.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject(); // получаем значения PRESENT и UID
        int mapSize = (int)stream.readObject(); // размер нашей мапы
        // промежуточный сет с ключами map. В него заносим содержимое сериализованного объекта
        Set<E> tmpSet = new HashSet<E>();
        for(int i=0; i<mapSize; i++) {
            tmpSet.add((E)stream.readObject());
        }
        // читаем переменные capacity и loadFactor
        int capacity = (int)stream.readObject();
        float loadfactor = (float)stream.readObject();
        // инициализируем конструктор как в строке 86
        this.map = new HashMap<>(capacity, loadfactor);
        // заполлняем нашу map
        for(E e : tmpSet) {
            map.put(e,PRESENT);
        }

    }

}
