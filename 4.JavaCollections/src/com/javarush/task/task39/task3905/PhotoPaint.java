package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if(image == null || image.length==0 ) return false;
        if(x<0 || x>=image.length || y<0 || y>=image[0].length)
            return false;
        if(image[y][x] == desiredColor)
            return false;
        // заливка массива в 4 стороны от тек.точки
        Color currentColor = image[y][x];
        fill(image, y, x, desiredColor, currentColor);
        return true;
    }

    // рекрсивно заливаем массив от точки (x,y)
    private void fill(Color[][] image, int y, int x, Color desiredColor, Color currentColor) {
        if (y < 0 || x < 0 || y >= image.length || x >= image[0].length)
            return;
        if (image[y][x] != currentColor)
            return;
        else
            image[y][x] = desiredColor;
        this.fill(image, y - 1, x, desiredColor, currentColor);
        this.fill(image, y + 1 , x, desiredColor, currentColor);
        this.fill(image, y, x - 1, desiredColor, currentColor);
        this.fill(image, y, x + 1, desiredColor, currentColor);
    }
}
