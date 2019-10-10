package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20; // размер ячейки игрового поля
    private EventListener eventListener;
    private GameObjects gameObjects; // все игровые объекты
    private int currentLevel = 1; // тек.уровень
    // проинициализировать
    private LevelLoader levelLoader = new LevelLoader(Paths.get("../res/levels.txt"));


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return this.gameObjects;
    }

    // 1.5. Метод restartLevel(int level), он должен получать новые игровые объекты
    // для указанного уровня у загрузчика уровня levelLoader и сохранять их в поле gameObjects.
    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        this.currentLevel++;
        restartLevel(this.currentLevel);
    }

    // 5.4. void move(Direction direction). Метод должен:
    //15.4.1. Проверить столкновение со стеной (метод checkWallCollision()), если есть столкновение - выйти из метода.
    //15.4.2. Проверить столкновение с ящиками (метод checkBoxCollisionAndMoveIfAvaliable()),
    //         если есть столкновение - выйти из метода.
    //15.4.3. Передвинуть игрока в направлении direction.
    //15.4.4. Проверить завершен ли уровень.
    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) { // столкнулся со стеной
            return;
        }
        if (checkBoxCollisionAndMoveIfAvaliable(direction)){ // двигаем или не двигаем ящик
            return;
        }
        // иначе перемещаем игрока (ящик, если это была он, уже свинули в checkBoxCollisionAndMoveIfAvailable()
        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
        }
        checkCompletion();
    }

    private int getDX(Direction direction) {
        int diff = Model.FIELD_CELL_SIZE;
        switch (direction) {
            case LEFT: return -diff;
            case RIGHT: return diff;
            default: return 0;
        }
    }
    private int getDY(Direction direction) {
        int diff = Model.FIELD_CELL_SIZE;
        switch (direction) {
            case UP: return -diff;
            case DOWN: return diff;
            default: return 0;
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        // Этот метод проверяет столкновение объекта gameObject со какой-нибудь стеной.
        // Он должен вернуть true, если при движении объекта gameObject в направлении direction произойдет
        // столкновение со стеной, иначе false. Для определения столкновения используй метод isCollision() у игрового объекта.
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    // Этот метод проверяет столкновение с ящиками. Метод должен:
    //15.2.1. Вернуть true, если игрок не может быть сдвинут в направлении direction
    // (там находится: или ящик, за которым стена; или ящик за которым еще один ящик).
    //15.2.2. Вернуть false, если игрок может быть сдвинут в направлении direction
    // (там находится: или свободная ячейка; или дом; или ящик, за которым свободная ячейка или дом).
    // При этом, если на пути есть ящик, который может быть сдвинут, то необходимо переместить этот ящик на новые координаты.
    // Обрати внимание, что все объекты перемещаются на фиксированное значение FIELD_CELL_SIZE,
    // независящее от размеров объекта, которые используются для его отрисовки.
    //Подсказка: для определения столкновений используй методы isCollision() игровых объектов и метод checkWallCollision() модели.
    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer(); // игрок
        // проверяем все ящики, в которые может упереться игрок при движении в направлении Direction
        for(Box box : getGameObjects().getBoxes()) {
            if(player.isCollision(box,direction)) { // уперся в ящик
                // проверяем, с чем столкнется этот ящик - если там стена или другой ящик, то выход с true (т.е. блок)
                for(GameObject o : getGameObjects().getAll()) {
                    if(!o.equals(box) && !o.equals(player) && box.isCollision(o,direction)) {
                        if(o instanceof Wall || o instanceof Box)
                            return true;
                    }
                }
                // если наш ящик ни с чем не столкнулся, двигаем его
                switch (direction) {
                    case LEFT:
                        box.move(-FIELD_CELL_SIZE,0);
                        break;
                    case RIGHT:
                        box.move(FIELD_CELL_SIZE,0);
                        break;
                    case UP:
                        box.move(0,-FIELD_CELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0,FIELD_CELL_SIZE);
                        break;
                }
                break; // коллизия только одна, посему сразу выходим из цикла
            }
        }
        return false;
    }

    // 15.3. void checkCompletion(). Этот метод должен проверить пройден ли уровень
    // (на всех ли домах стоят ящики, их координаты должны совпадать).
    // Если условие выполнено, то проинформировать слушателя событий, что текущий уровень завершен.
    public void checkCompletion() {
        int countBoxesInHome=0;
        for(Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY())
                    countBoxesInHome++;
            }
        }
        if(countBoxesInHome == gameObjects.getBoxes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

}
