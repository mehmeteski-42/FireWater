import java.awt.*;
import java.util.ArrayList;

public class Coin extends Objeler {
    public Coin(int x, int y) {
        super(x, y, 4, 4,false, Color.YELLOW);
    }

    public boolean isHanged(ArrayList<Objeler> objeler, ArrayList<Entity> entities) {

        Rectangle coinBounds = new Rectangle(this.x, this.y, this.sizex, this.sizey);


        for (Objeler obje : objeler) {
            Rectangle objBounds = new Rectangle(obje.x, obje.y, obje.sizex, obje.sizey);
            if (coinBounds.intersects(objBounds)) {
                return false;
            }
        }

        for (Entity entity : entities) {
            Rectangle entityBounds = new Rectangle(entity.x, entity.y, entity.sizex, entity.sizey);
            if (coinBounds.intersects(entityBounds)) {
                return false;
            }
        }
        ArrayList<Rectangle> asitZeminler = new ArrayList<>();
        for (Objeler obje : objeler) {
            if (obje instanceof AsitZemin) {
                asitZeminler.add(new Rectangle(obje.x, obje.y-30, obje.sizex, obje.sizey+30));
            }
        }
        for (Rectangle asit : asitZeminler) {
            if (coinBounds.intersects(asit)) {
                return false;
            }
        }


        double a=Math.random();
        return a > 0.5;
    }
    public boolean take(Karakter a,ArrayList<Objeler> objeler){
        if (!(a.x > x + sizex || a.x + a.sizex < x || a.y > y + sizey || a.y + a.sizey < y)) {
            objeler.remove(this);
            return true;
        }
        return false;
    }
}