import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Karakter extends Entity{
    public void shoot(boolean a){}
    public ArrayList<Bullet> bullets;
    public double zipHiz;
    public Item holdingItem;
    public ImageIcon durgun;
    public ImageIcon kosma1;
    public ImageIcon kosma2;
    public ImageIcon kosma3;
    public ImageIcon durgunSol;
    public ImageIcon kosma1Sol;
    public ImageIcon kosma2Sol;
    public ImageIcon kosma3Sol;
    public JLabel durum;
    boolean dead;

    public Karakter(int cubeSize, int x, int y, ArrayList<Bullet> bullets) {
        super(cubeSize,x,y,false,null);
        sizey = cubeSize;
        sizex = cubeSize/2;
        this.x = x;
        this.y = y;
        this.bullets=bullets;
        speed=4;
        durum=new JLabel();
        durum.setIcon(durgun);
        dead=false;
    }
    public void isDead(ArrayList<Entity> entities,ArrayList<Objeler> zeminler){
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.x >= x && entity.x <= x + sizex &&
                    entity.y >= y && entity.y <= y + sizey&&(entity instanceof Canavar||entity instanceof Turret)&&!(this instanceof StoneMan)) {
                dead=true;
                break;
            }
        }
        for (Objeler zemin:zeminler) {
            if (zemin.x <= x && zemin.x +zemin.sizex >= x &&
                    zemin.y + zemin.sizey >= y && zemin.y <= y + sizey&&!(zemin.c.equals(c))) {
                if (this instanceof StoneMan)
                    dead=false;
                else
                    dead=true;
                break;
            }
        }
    }
    public void shootAction(){
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).bulletx+=bullets.get(i).speed;
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).bulletx>1600)
                bullets.remove(i);
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
                        mermi.bullety + mermi.bulletSize > obje.y && obje.solid) {
                    if(obje instanceof TNT)
                        ((TNT) obje).patlat(objeler,entities);
                    bullets.remove(i);
                    return;
                }
            }
        }
    }
}
