import java.awt.*;
import java.io.Serializable;

public class Objeler implements Serializable {
    public int x;
    public int y;
    public int sizex;
    public int sizey;
    public boolean solid;
    public Color c;

    public Objeler(int x, int y, int sizex, int sizey, boolean solid, Color c) {
        this.x = x;
        this.y = y;
        this.sizex = sizex;
        this.sizey = sizey;
        this.solid = solid;
        this.c=c;
    }
    private void a(){}
}
