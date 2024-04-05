import java.awt.Color;
import java.util.ArrayList;
public class Spider extends Entity {
    public int webCooldown = 0;
    public boolean startedWebbing = false;
    public ArrayList<Bullet> webs=new ArrayList<>();
    public int border;
    public int border1;
    public double playerCenterX=0;
    public double playerCenterY=0;

    public double spiderCenterX=0;
    public double spiderCenterY=0;
    public double xDirection;
    public double yDirection;
    public boolean catched=false;
    public boolean a=true;
    public ArrayList<Objeler> objeler;

    public Spider(int cubeSize,int x,int y, ArrayList<Objeler> objeler) {
        super(cubeSize, x, y, false,Color.BLACK);
        border=x;
        this.border1=border1;
        this.objeler = objeler;
        sizey=cubeSize/2;
    }

    public void shootWeb(Karakter player) {
        if (catched)
            cek(player,a);
        else if (!startedWebbing && webCooldown <= 0) {
            playerCenterX = player.x + ((double)player.sizex / 2);
            playerCenterY = player.y + ((double)player.sizey / 2);

            spiderCenterX = this.x + ((double)this.sizex / 2);
            spiderCenterY = this.y + (this.sizey)-10;
            startedWebbing = true;
            webCooldown = 75;
            if(playerCenterX-spiderCenterX<0)
                a=true;
            else
                a=false;

        } else if (startedWebbing) {
            boolean shouldShoot = true;
            double deltaX = spiderCenterX - playerCenterX;
            double deltaY = spiderCenterY - playerCenterY;
            double steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
            double stepX = deltaX / steps;
            double stepY = deltaY / steps;
            for (int i = 1; i <= steps; i++) {
                double currentX = playerCenterX + (stepX * i);
                double currentY = playerCenterY + (stepY * i);
                for (Objeler obje : objeler) {
                    if (currentX >= obje.x && currentX <= obje.x + obje.sizex &&
                            currentY >= obje.y && currentY <= obje.y + obje.sizey && obje.solid) {
                        shouldShoot = false;
                        break;
                    }
                }

                if (!shouldShoot) {
                    break;
                }
            }

            if (shouldShoot) {
                webs.add(new Bullet(spiderCenterX, spiderCenterY, Color.WHITE, false));
                xDirection = playerCenterX - spiderCenterX;
                yDirection = playerCenterY - spiderCenterY;
                double speedMultiplier = 2.0 / Math.max(Math.abs(xDirection), Math.abs(yDirection));

                for (int i = 0; i < webs.size(); i++) {
                    webs.get(i).bulletx += 3*speedMultiplier * xDirection;
                    webs.get(i).bullety += 3*speedMultiplier * yDirection;
                }

                if (hasarAldiMi(player,a)) {
                    catched=true;
                }else if (a&&webs.get(0).bulletx<playerCenterX) {
                    webs=new ArrayList<>();
                    startedWebbing = false;
                }else if (!a&&webs.get(0).bulletx>playerCenterX) {
                    webs=new ArrayList<>();
                    startedWebbing = false;
                }
            }
        }
    }

    private boolean hasarAldiMi(Karakter a,boolean b) {
        if (webs.get(0).bulletx < a.x + a.sizex &&
                webs.get(0).bulletx + webs.get(0).bulletSize > a.x &&
                webs.get(0).bullety < a.y + a.sizey &&
                webs.get(0).bullety + webs.get(0).bulletSize > a.y) {
            cek(a,b);
            return true;
        }
        return false;
    }

    private void cek(Karakter a,boolean b) {
        double speedMultiplier = 2.0 / Math.max(Math.abs(xDirection), Math.abs(yDirection));

        for (int i = 0; i < webs.size(); i++) {
            webs.get(i).bulletx -= 2*speedMultiplier * xDirection;
            webs.get(i).bullety -= 2*speedMultiplier * yDirection;
            if (b && webs.get(i).bulletx > spiderCenterX)
                webs.remove(i);
            if (!b && webs.get(i).bulletx < spiderCenterX) {
                webs.remove(i);
            }
            if (!webs.isEmpty()) {
                a.x = (int) webs.get(0).bulletx;
                a.y = (int) webs.get(0).bullety;
            }else
                a.dead=true;
        }
        if(!webs.isEmpty()) {
            if (b && webs.get(0).bulletx > spiderCenterX + 20) {

            }
            if (!b && webs.get(0).bulletx + 20 < spiderCenterX) {

            }
        }
    }

    @Override
    public void move() {
        super.move();
        if (webCooldown > 0)
            webCooldown--;
        else
            startedWebbing=false;
    }
    public boolean isDead(ArrayList<Objeler> objeler) {
        for (Objeler obje : objeler) {
            if (obje instanceof Entity) {
                Entity entity = (Entity) obje;
                if (entity.x <= this.x + this.sizex &&
                        entity.x + entity.sizex >= this.x &&
                        entity.y <= this.y &&
                        entity.y + entity.sizey >= this.y) {
                    return true;
                }
            }
        }
        return false;
    }

}
