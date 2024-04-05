import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Red extends Karakter{

    public Red(int cubeSize, int x, int y, ArrayList<Bullet> bullets, boolean isJumping) {
        super(cubeSize, x, y,bullets);
        durgun=new ImageIcon("assets\\ates\\durgun.png");
        kosma1=new ImageIcon("assets\\ates\\kosma1.png");
        kosma2=new ImageIcon("assets\\ates\\kosma2.png");
        kosma3=new ImageIcon("assets\\ates\\kosma3.png");
        durgunSol=new ImageIcon("assets\\ates\\durgunsol.png");
        kosma1Sol=new ImageIcon("assets\\ates\\kosma1sol.png");
        kosma2Sol=new ImageIcon("assets\\ates\\kosma2sol.png");
        kosma3Sol=new ImageIcon("assets\\ates\\kosma3sol.png");
        durum.setIcon(durgun);
        c= Color.red;
        zipHiz=-5;
        this.isJumping=isJumping;
    }
    public void shoot(boolean bulletDirection){
        bullets.add(new Bullet(x-2+sizey/2,y-2+sizey/2,Color.RED,bulletDirection));
    }
}
