import java.awt.*;
import java.util.ArrayList;

public class Kapi extends FonksiyonelObjeler{
    public int border;
    public int border1;
    public int speed;
    public Kapi(int x, int y, int sizex, int sizey, Color c,int border) {
        super(x, y, sizex, sizey, true, c);
        this.border = border;
        border1 = y;
        speed = -1;
    }

    @Override
    public void start(boolean tus, ArrayList<Entity> entityler,ArrayList<Objeler> objeler) {
        if(tus){
            if (y != border)
                y = y + speed;
            for (Entity kup : entityler) {
                if (kup.x + kup.sizex > x && kup.x < x + sizex &&
                        kup.y + kup.sizey + 5 >= y && kup.y + kup.sizey - 5 <= y) {
                    kup.y = y - kup.sizey;
                }
            }
        }else {
            if (y != border1)
                y = y - speed;
            for (Entity kup : entityler) {
                if (kup.x + kup.sizex > x && kup.x < x + sizex &&
                        kup.y + kup.sizey + 5 >= y && kup.y + kup.sizey - 5 <= y) {
                    kup.y = y - kup.sizey;
                }
            }
        }
    }
}
