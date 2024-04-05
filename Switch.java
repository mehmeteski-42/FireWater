import java.awt.*;
import java.util.ArrayList;

public class Switch extends Objeler {
    public boolean tus;
    public ArrayList<FonksiyonelObjeler> objeler;
    public Switch(int x, int y, int sizex, int sizey,Color c) {
        super(x, y, sizex, sizey, true, c);
        tus=false;
        objeler=new ArrayList<>();
    }

    public void tusAyar(Karakter a) {
        if (a.y + a.sizey >= y && a.y <= y && a.x + a.sizex + 15 > x && a.x - 15 < x)
            tus = !tus;
    }
    public void objeEkle(FonksiyonelObjeler obje){
        objeler.add(obje);
    }
    public void action(ArrayList<Entity> entities,ArrayList<Objeler> objeler){
        for (int i = 0; i < this.objeler.size(); i++) {
            this.objeler.get(i).start(tus,entities,objeler);
        }
    }
}