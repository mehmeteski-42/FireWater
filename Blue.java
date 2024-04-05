import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Blue extends Karakter{


    public Blue(int cubeSize, int x, int y, ArrayList<Bullet> bullets, boolean isJumping) {
        super(cubeSize, x, y,bullets);
        durgun=new ImageIcon("assets\\su\\durgun.png");
        kosma1=new ImageIcon("assets\\su\\kosma1.png");
        kosma2=new ImageIcon("assets\\su\\kosma2.png");
        kosma3=new ImageIcon("assets\\su\\kosma3.png");
        durgunSol=new ImageIcon("assets\\su\\durgunsol.png");
        kosma1Sol=new ImageIcon("assets\\su\\kosma1sol.png");
        kosma2Sol=new ImageIcon("assets\\su\\kosma2sol.png");
        kosma3Sol=new ImageIcon("assets\\su\\kosma3sol.png");
        durum.setIcon(durgun);
        c= Color.BLUE;
        zipHiz=-5;
        this.isJumping=isJumping;
    }
    public void shoot(boolean bulletDirection){
        bullets.add(new Bullet(x-2+ (double) sizey /2,y-2+ (double) sizey /2,Color.BLUE,bulletDirection));
    }

}
