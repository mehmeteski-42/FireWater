import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TNT extends Entity{
    boolean patladi=false;
    int patlamaCount=0;
    public TNT(int cubeSize, int x, int y, boolean pushable) {
        super(cubeSize, x, y, pushable, Color.pink);
        durum=new ImageIcon("assets\\Tnt.png");
    }
    public void patlat(ArrayList<Objeler> objeler,ArrayList<Entity> entities) {
        if (!patladi){
            patladi = true;
            durum = new ImageIcon("assets\\patlama.png");
            x -= sizex / 2;
            y -= sizey / 2;
            sizex = sizex * 2;
            sizey = sizey * 2;
            spidey = true;
            for (int i = 0; i < objeler.size(); i++) {
                if (objeler.get(i) instanceof Duvar) {
                    Duvar d = (Duvar) objeler.get(i);
                    if ((d.x + d.sizex > x && d.x < x + sizex) && (y + sizey > d.y && d.y + d.sizey > y))
                        objeler.remove(i);
                }
            }
            for (int i = 0; i < entities.size(); i++) {
                Entity d = entities.get(i);
                if ((d.x + d.sizex > x && d.x < x + sizex) && (y + sizey > d.y && d.y + d.sizey > y)) {
                    if (d instanceof Karakter) {
                        ((Karakter) d).dead = true;
                    }
                    else
                        entities.remove(i);
                }
            }
        }
    }
}
