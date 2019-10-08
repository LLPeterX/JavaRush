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
//        int newX = this.getX();
//        int newY = this.getY();
        switch (direction) {
            case UP:
                return this.getX() == gameObject.getX() && this.getY()-Model.FIELD_CELL_SIZE==gameObject.getY();
            case DOWN:
                return this.getX() == gameObject.getX() && this.getY()+Model.FIELD_CELL_SIZE==gameObject.getY();
            case LEFT:
                return this.getX()-Model.FIELD_CELL_SIZE == gameObject.getX() && this.getY()==gameObject.getY();
            case RIGHT:
                return this.getX()+Model.FIELD_CELL_SIZE == gameObject.getX() && this.getY()==gameObject.getY();
        }
        return false;
        //return ( newX==gameObject.getX() && newY==gameObject.getY() );
    }
}
