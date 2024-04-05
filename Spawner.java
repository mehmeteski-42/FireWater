import java.awt.*;
import java.util.ArrayList;

public class Spawner extends FonksiyonelObjeler{
    public int x;
    public int y;
    public int size;
    public int count2=0;
    public Spawner(int x, int y, int size,Color c) {
        super(x, y, size,size,true,c);
        this.x=x;
        this.y=y;
        this.size=size;
    }
    @Override
    public void start(boolean tus, ArrayList<Entity> entities,ArrayList<Objeler> objeler) {
        if (!tus) {
            int count = 0;
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof Canavar)
                    count++;
            }
            if (count2 >= 100 && count < 10) {
                int secici = (int) (5 * Math.random());
                if (secici == 0 || secici == 1)
                    entities.add(new BlueCanavar(x, y - size, false));
                if (secici == 2 || secici == 3)
                    entities.add(new RedCanavar(x, y - size, false));
                if (secici == 4)
                    entities.add(new OrangeCanavar(x, y - size, false));
                count2 = 0;
            }
            count2++;
        }
    }
}
