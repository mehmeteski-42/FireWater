import java.awt.*;
import java.util.ArrayList;

public abstract class FonksiyonelObjeler extends Objeler{
    public FonksiyonelObjeler(int x, int y, int sizex, int sizey, boolean solid, Color c) {
        super(x, y, sizex, sizey, solid, c);
    }
    public abstract void start(boolean tus, ArrayList<Entity> entities,ArrayList<Objeler> objeler);
}
