package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.List;

public class PlayerShip extends Ship {
    private Direction direction = Direction.UP; // UP = стоим на месте

    public PlayerShip() {
        super(SpaceInvadersGame.WIDTH / 2.0, SpaceInvadersGame.HEIGHT - ShapeMatrix.PLAYER.length - 1);
        setStaticView(ShapeMatrix.PLAYER);
    }

    public void verifyHit(List<Bullet> bullets) {
        if(bullets.size()==0) return;
        if(isAlive) {
            for(Bullet bullet : bullets) {
                if(bullet.isAlive && isCollision(bullet)) {
                    kill();
                    bullet.kill();
                    //break;
                }
            }
        }
    }

    @Override
    public void kill() {
        if(!isAlive) return;
        isAlive = false;
        setAnimatedView(false, ShapeMatrix.KILL_PLAYER_ANIMATION_FIRST,
                ShapeMatrix.KILL_PLAYER_ANIMATION_SECOND,
                ShapeMatrix.KILL_PLAYER_ANIMATION_THIRD,
                ShapeMatrix.DEAD_PLAYER);
    }

    public void setDirection(Direction newDirection) {
        if(newDirection!=Direction.DOWN)
            this.direction = newDirection;
    }

    public void move() {
        if(!isAlive) return;
        switch (direction) {
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        if(x<0)
            x=0;
        else if(x+width > SpaceInvadersGame.WIDTH)
            x = SpaceInvadersGame.WIDTH - width;

    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public Bullet fire() {
        if(!isAlive) return null;
        return new Bullet(x+2, y - ShapeMatrix.BULLET.length, Direction.UP);
    }
}
