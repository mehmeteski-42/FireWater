import java.awt.Color;
import java.util.ArrayList;

public class Turret extends Entity {
    private int shootInterval = 3000;
    private long lastShootTime = 0;
    ArrayList<Bullet> bullets;

    public Turret(int x, int y, int cubeSize, Color c) {
        super(cubeSize,x,y,false,Color.pink);
        bullets=new ArrayList<>();
    }
    public void shoot(Karakter a,ArrayList<Objeler> objeler) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= shootInterval&&clearLineOfSight(a,objeler)) {
            Bullet bullet = new Bullet(this.x + this.sizex / 2.0, this.y, Color.GRAY, true );
            bullets.add(bullet);
            lastShootTime = currentTime;
        }
        double xDirection = a.x - x;
        double yDirection = a.y - y;
        double speedMultiplier = 2.0 / Math.max(Math.abs(xDirection), Math.abs(yDirection));
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).bulletx += (2*speedMultiplier * xDirection)+0.2;
            bullets.get(i).bullety += (2*speedMultiplier * yDirection)+0.2;
        }
        removeCollidingBullets(objeler);
        isKilled(a);
    }
    public void isKilled(Karakter a){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.bulletx >= a.x && bullet.bulletx <= a.x + a.sizex &&
                    bullet.bullety >= a.y && bullet.bullety <= a.y + a.sizey&&!(a instanceof StoneMan)) {
                bullets.remove(i);
                a.dead=true;
                i--;
                break;
            }
        }
    }
    public boolean clearLineOfSight(Karakter a, ArrayList<Objeler> objeler) {
        boolean shouldShoot = true;
        double deltaX = x - a.x;
        double deltaY = y - a.y;
        double steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        double stepX = deltaX / steps;
        double stepY = deltaY / steps;
        for (int i = 1; i <= steps; i++) {
            double currentX = a.x + (stepX * i);
            double currentY = a.y + (stepY * i);
            for (Objeler obje : objeler) {
                if (currentX >= obje.x && currentX <= obje.x + obje.sizex &&
                        currentY >= obje.y && currentY <= obje.y + obje.sizey) {
                    shouldShoot = false;
                    break;
                }
            }

            if (!shouldShoot) {
                break;
            }
        }
        return shouldShoot;
    }
    public void removeCollidingBullets(ArrayList<Objeler> objeler) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (Objeler obje : objeler) {
                if (bullet.bulletx >= obje.x && bullet.bulletx <= obje.x + obje.sizex &&
                        bullet.bullety >= obje.y && bullet.bullety <= obje.y + obje.sizey&&!(obje instanceof Coin)) {
                    bullets.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
}