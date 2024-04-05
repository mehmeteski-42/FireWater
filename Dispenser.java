import java.awt.*;
import java.util.ArrayList;

public class Dispenser extends FonksiyonelObjeler{
    ArrayList<Bullet> bullets;
    Color bulletC;
    int count=0;
    public Dispenser(int x, int y, int sizex, int sizey, Color c,Color bulletC) {
        super(x, y, sizex, sizey, true, c);
        this.bulletC=bulletC;
        bullets=new ArrayList<>();
    }
    public void shot(){
        if (count>30){
            bullets.add(new Bullet(x+sizex/2.0,y+sizey/2.0,bulletC,true));
            count=0;
        }
        count++;
    }
    @Override
    public void start(boolean tus, ArrayList<Entity> entities,ArrayList<Objeler> objeler) {
        if (tus) {
            shot();
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).bullety += bullets.get(i).speed;
            }
            duvaraCarpanMermiyiYokEt(objeler,entities);
        }
    }
    public void duvaraCarpanMermiyiYokEt(ArrayList<Objeler> objeler,ArrayList<Entity> entities) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet mermi = bullets.get(i);
            for (int j = 0; j < objeler.size(); j++) {
                Objeler obje = objeler.get(j);
                if (mermi.bulletx < obje.x + obje.sizex &&
                        mermi.bulletx + mermi.bulletSize > obje.x &&
                        mermi.bullety < obje.y + obje.sizey &&
                        mermi.bullety + mermi.bulletSize > obje.y&&!obje.equals(this)) {
                    if(obje instanceof TNT)
                        ((TNT) obje).patlat(objeler,entities);
                    bullets.remove(i);
                    return;
                }
            }
        }
    }
}
