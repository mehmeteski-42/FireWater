import java.awt.*;
import java.io.Serializable;

public class Bullet implements Serializable {
    public double bulletx;
    public double bullety;
    public int bulletSize;
    public Color c;
    public int speed=8;

    public Bullet(double bulletx, double bullety, Color c,boolean bulletDirection) {
        this.bulletx = bulletx;
        this.bullety = bullety;
        this.c=c;
        bulletSize=5;
        if(!bulletDirection)
            speed=-speed;
    }
}
