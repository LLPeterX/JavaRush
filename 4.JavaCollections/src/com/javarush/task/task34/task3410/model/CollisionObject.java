package com.javarush.task.task34.task3410.model;

// 4.3. Игровые объекты типа "дом" не поддерживают логики столкновений
// (игрок или ящики могут свободно передвигаться по ним).
// Что касается остальных объектов, то они не должны проходить сквозь друг друга,
// они должны сталкиваться. Например, ящик нельзя протолкнуть сквозь стену.
public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    // Этот метод должен возвращаться true, если при перемещении текущего объекта
    // в направлении direction на FIELD_CELL_SIZE произойдет столкновение
    // с объектом gameObject, переданным в качестве параметра.
    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction){
            case UP: return getY() - Model.FIELD_CELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case DOWN: return getY() + Model.FIELD_CELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case LEFT: return getX() - Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
            case RIGHT: return getX() + Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
        }
        return false;
    }
}
