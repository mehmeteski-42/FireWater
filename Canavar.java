import java.awt.*;
import java.util.ArrayList;

public class Canavar extends Entity{
    public int hp=3;
    public Canavar(int x, int y,boolean a,Color c) {
        super(25,x,y,false,c);
        speed = 2;
        sizey = 25;
        sizex=25;
        this.x = x;
        this.y = y;
        this.a=a;
    }

    public void move(){
        if (a)
            x+=speed;
        else
            x-=speed;
    }
    public boolean hasarAldiMi(ArrayList<Bullet> bullets){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet mermi = bullets.get(i);
            if (mermi.bulletx < x + sizex &&
                    mermi.bulletx + mermi.bulletSize > x &&
                    mermi.bullety < y + sizey &&
                    mermi.bullety + mermi.bulletSize > y&&(!mermi.c.equals(c))) {
                hp--;
                bullets.remove(i);
                if (hp<=0&&!(this instanceof OrangeCanavar)||mermi.c.equals(Color.WHITE))
                    return true;
            }if (mermi.bulletx < x + sizex &&
                    mermi.bulletx + mermi.bulletSize > x &&
                    mermi.bullety < y + sizey &&
                    mermi.bullety + mermi.bulletSize > y&&mermi.c.equals(c)) {
                hp++;
                bullets.remove(i);
                return false;
            }
        }
        return false;
    }
}
