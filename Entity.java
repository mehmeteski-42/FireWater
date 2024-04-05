import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Entity extends Objeler{
    public Color c;
    public int speed=0;
    public double yerCekimi=0;
    public boolean pushable;
    public boolean a;
    public void move(){}
    public boolean spidey;
    public boolean isJumping=false;
    ImageIcon durum;

    public Entity(int cubeSize, int x, int y,boolean pushable,Color c) {
        super(x,y,cubeSize,cubeSize,true,c);
        this.x = x;
        this.y = y;
        durum=new ImageIcon("assets\\kutu.png");
        sizex=cubeSize;
        sizey=cubeSize;
        this.pushable=pushable;
        c=Color.GREEN;
        spidey=false;
    }
    public void it(Entity a,ArrayList<Objeler> objeler){
        if (!isHanged(objeler)&&!spidey) {
            int index = carptiMi(objeler);
            if (pushable && a.x + a.sizex >= x && a.x <= x + sizex && a.y + a.sizey-5>= y && a.y <= y + sizey) {
                if (index == -1) {
                    if (a.x < x) {
                        x = a.x + a.sizex + a.speed;
                    } else if (a.x > x){
                        x = a.x - sizex - a.speed;
                    }
                } else {
                    if (x < objeler.get(index).x)
                        x = objeler.get(index).x - sizex;
                    else
                        x = objeler.get(index).x + objeler.get(index).sizex;
                }
            }
        }
    }
    public int nerede(double maxY, ArrayList<Objeler> objeler){
        int index=-1;
        for (int i = 0; i < objeler.size(); i++) {
            if(index==-1) {
                if(!pushable) {
                    if (objeler.get(i).solid && maxY <= objeler.get(i).y && ((y + sizey + yerCekimi >= objeler.get(i).y && y + sizey <= objeler.get(i).y + yerCekimi) || (y + sizey + yerCekimi <= objeler.get(i).y && y + sizey >= objeler.get(i).y + yerCekimi)) && x + sizex > objeler.get(i).x && x < objeler.get(i).x + objeler.get(i).sizex) {
                        index = i;
                    }
                }else {
                    if (objeler.get(i).solid && maxY <= objeler.get(i).y && ((y + sizey + yerCekimi >= objeler.get(i).y && y + sizey <= objeler.get(i).y + yerCekimi) || (y + sizey + yerCekimi <= objeler.get(i).y && y + sizey >= objeler.get(i).y + yerCekimi)) && x + sizex > objeler.get(i).x && x < objeler.get(i).x + objeler.get(i).sizex && !(objeler.get(i) instanceof Entity)) {
                        index = i;
                    }
                }
            }
            else {
                if (!pushable) {
                    if (objeler.get(i).solid && maxY <= objeler.get(i).y && ((y + sizey + yerCekimi >= objeler.get(i).y && y + sizey <= objeler.get(i).y + yerCekimi) || (y + sizey + yerCekimi <= objeler.get(i).y && y + sizey >= objeler.get(i).y + yerCekimi)) && x + sizex > objeler.get(i).x && x < objeler.get(i).x + objeler.get(i).sizex) {
                        index = i;
                    }
                } else if (objeler.get(i).solid && maxY <= objeler.get(i).y && ((y + sizey + yerCekimi >= objeler.get(i).y && y + sizey <= objeler.get(i).y + yerCekimi) || (y + sizey + yerCekimi <= objeler.get(i).y && y + sizey >= objeler.get(i).y + yerCekimi)) && x + sizex > objeler.get(i).x && x < objeler.get(i).x + objeler.get(i).sizex && !(objeler.get(i) instanceof Entity)) {
                    index = i;
                }
            }
        }
        return index;
    }
    public void dus(ArrayList<Objeler> objeler) {
        if (!isJumping&&!isHanged(objeler)&&!spidey) {
            int index2;
            index2 = nerede(y, objeler);
            if (index2 != -1) {
                y = objeler.get(index2).y - sizey;
                yerCekimi = 0;
            } else {
                y += yerCekimi;
                yerCekimi += 0.2;
            }
        }
    }
    public boolean tavanVarMi(ArrayList<Objeler> objeler){
        for (int i = 0; i < objeler.size(); i++) {
            if(y<objeler.get(i).y+objeler.get(i).sizey-yerCekimi&&y>objeler.get(i).y+objeler.get(i).sizey+yerCekimi&&objeler.get(i).solid&&x+sizex>objeler.get(i).x&&x<objeler.get(i).x+objeler.get(i).sizex) {
                return true;
            }
        }
        return false;
    }
    public int carptiMi(ArrayList<Objeler> objeler){
        int index=-1;
        for (int i = 0; i < objeler.size(); i++) {
            if (x <= objeler.get(i).x + objeler.get(i).sizex&&x + sizex >= objeler.get(i).x&&y+sizey>objeler.get(i).y&&y<objeler.get(i).y+objeler.get(i).sizey&&objeler.get(i).solid&& objeler.get(i) != this) {
                if (!tavanVarMi(objeler))
                    index = i;
            }
        }
        return index;
    }
    public boolean isHanged(ArrayList<Objeler> objeler) {
        for (Objeler obje : objeler) {
            if (obje instanceof PasifObje) {
                PasifObje pasifObje = (PasifObje) obje;
                if (this.x <= pasifObje.x && this.x + this.sizex>= pasifObje.x + pasifObje.sizex &&
                        this.y >= pasifObje.y && this.y <= pasifObje.y + pasifObje.sizey) {
                    return true;
                }
            }
        }
        return false;
    }
}
