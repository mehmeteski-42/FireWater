import java.awt.*;
import java.util.ArrayList;

public class Asansor extends FonksiyonelObjeler {
    public int border;
    public int border1;
    public int speed;

    public Asansor(int x, int y, int sizex, int sizey,Color c, int border) {
        super(x, y, sizex, sizey, true, c);
        this.border = border;
        border1 = y;
        speed = -1;
    }
    public void start(boolean tus, ArrayList<Entity> entityler,ArrayList<Objeler> objeler) {
        if (tus) {
            y = y + speed;
            if (y == border)
                speed = -speed;
            if (y == border1)
                speed = -speed;
            for (Entity kup : entityler) {
                if (kup.x + kup.sizex > x && kup.x < x + sizex &&
                        kup.y + kup.sizey + 5 >= y && kup.y + kup.sizey - 5 <= y) {
                    if (!(kup instanceof Karakter))
                        kup.spidey=true;
                    kup.y = y - kup.sizey;
                }else if (!(kup instanceof Karakter))
                    kup.spidey=false;
            }
        }
    }
}