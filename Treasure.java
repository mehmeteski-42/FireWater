import javax.swing.*;
import java.awt.*;

public class Treasure extends Objeler{
    public Treasure(int x, int y, int sizex, int sizey) {
        super(x, y, sizex, sizey, false, null);
    }
    public boolean open(Karakter a) {
        Rectangle treasureBounds = new Rectangle(this.x, this.y, this.sizex, this.sizey);
        Rectangle karakterBounds = new Rectangle(a.x, a.y, a.sizex, a.sizey);
        return treasureBounds.intersects(karakterBounds);
    }
}
