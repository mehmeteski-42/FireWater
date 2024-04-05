import java.awt.*;
import java.util.ArrayList;

public class Hook extends Item{
    public ArrayList<Bullet> webs=new ArrayList<>();
    public boolean uret=true;
    public boolean dur=true;
    public Hook(){

    }
    public void action(Karakter a, int x, int y, ArrayList<Objeler> objeler) {
        for (Objeler obje : objeler) {
            if (obje.x <= x && x <= obje.x + obje.sizex &&
                    obje.y <= y && y <= obje.y + obje.sizey) {
                boolean shouldShoot = true;
                double deltaX = x - a.x;
                double deltaY = y - a.y;
                double steps = Math.min(Math.abs(deltaX), Math.abs(deltaY));
                double stepX = deltaX / steps;
                double stepY = deltaY / steps;
                for (int i = 1; i <= steps; i++) {
                    double currentX = a.x + (stepX * i);
                    double currentY = a.y + (stepY * i);
                    for (Objeler obje1 : objeler) {
                        if ((currentX >= obje1.x && currentX <= obje1.x + obje1.sizex &&
                                currentY >= obje1.y && currentY <= obje1.y + obje1.sizey) && !(x >= obje1.x && x <= obje1.x + obje1.sizex &&
                                y >= obje1.y && y <= obje1.y + obje1.sizey)&&!(obje1 instanceof Coin)&&obje1.solid) {
                            shouldShoot = false;
                            break;
                        }
                    }

                    if (!shouldShoot) {
                        break;
                    }
                }
                if (shouldShoot) {
                    if (obje instanceof Entity){
                        Entity entity = (Entity) obje;
                        double cekmeHizi = 0.1;
                        double xFark = a.x - entity.x;
                        double yFark = a.y - entity.y;
                        double yeniX = entity.x + cekmeHizi * xFark;
                        entity.x = (int)yeniX;
                        if (entity.nerede(entity.y,objeler)==-1||(int)(entity.y + cekmeHizi * yFark)<entity.y)
                            entity.y = (int)(entity.y + cekmeHizi * yFark);
                        webs.clear();
                        if (uret) {
                            double distance = Math.sqrt(xFark * xFark + yFark * yFark);
                            int adimSayisi = (int) (distance / 10);

                            for (int i = 0; i <= adimSayisi; i++) {
                                double bulletX = a.x - (xFark * i / adimSayisi);
                                double bulletY = a.y + (double) a.sizey /2 - (yFark * i / adimSayisi);
                                webs.add(new Bullet(bulletX, bulletY, Color.WHITE, true));
                            }
                        }
                    }else if (a.y-10>y){
                        a.spidey = true;
                        if (uret)
                            webs.add(new Bullet(a.x, a.y, Color.WHITE, false));
                        double xDirection = x - a.x;
                        double yDirection = y - a.y;
                        double speedMultiplier = 2.0 / Math.max(Math.abs(xDirection), Math.abs(yDirection));
                        for (int i = 0; i < webs.size(); i++) {
                            if (uret) {
                                webs.get(i).bulletx += speedMultiplier * xDirection;
                                webs.get(i).bullety += speedMultiplier * yDirection;
                            }
                            if (webs.get(i).bullety < obje.y) {
                                uret = false;
                                dur = false;
                            }
                        }
                        double distanceX = x - a.x;
                        double distanceY = y - a.y;
                        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                        speedMultiplier = 5.0 / distance;
                        for (int i = 0; i < webs.size(); i++) {
                            webs.get(i).bulletx = x - (x - a.x) * (double) ((double) (i) / (double) webs.size());
                            webs.get(i).bullety = (Math.sqrt(distance * distance - (webs.get(i).bulletx - x) * (webs.get(i).bulletx - x))) * (double) ((double) (i) / ((double) webs.size() - 1)) + y;
                        }
                        if (a.y-30-obje.sizey>y)
                            a.y -= 1;
                        if (a.carptiMi(objeler) == -1)
                            a.x += speedMultiplier * distanceX;
                        if (a.y-obje.sizey-30>y)
                            a.yerCekimi = -3;
                        else
                            a.yerCekimi = 0;
                    }
                }
            }
        }
    }
}
