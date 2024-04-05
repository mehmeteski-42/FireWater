import java.awt.*;
import java.util.ArrayList;

public class PasifObje extends Objeler{
    public boolean shootable;
    public int hp=10;
    public PasifObje(int x, int y, int sizex, int sizey){
        super(x,y,sizex,sizey,true,Color.DARK_GRAY);
        shootable=false;
    }
    public boolean isDead(ArrayList<Bullet> bullets){
        if (shootable) {
            for (int i = 0; i < bullets.size(); i++) {
                Bullet mermi = bullets.get(i);
                if (mermi.bulletx < x + sizex &&
                        mermi.bulletx + mermi.bulletSize > x &&
                        mermi.bullety < y + sizey &&
                        mermi.bullety + mermi.bulletSize > y) {
                    hp--;
                    bullets.remove(i);
                    if (hp <= 0)
                        return true;
                }
            }
            return false;
        }
        return false;
    }
}
