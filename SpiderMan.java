import javax.swing.*;
import java.util.ArrayList;

public class SpiderMan extends Karakter{

    public SpiderMan(int cubeSize, int x, int y, ArrayList<Bullet> bullets, boolean isJumping) {
        super(cubeSize, x, y,bullets);
        durgun=new ImageIcon("assets\\spider\\durgun.png");
        kosma1=new ImageIcon("assets\\spider\\kosma1.png");
        kosma2=new ImageIcon("assets\\spider\\kosma2.png");
        kosma3=new ImageIcon("assets\\spider\\kosma3.png");
        durgunSol=new ImageIcon("assets\\spider\\durgunsol.png");
        kosma1Sol=new ImageIcon("assets\\spider\\kosma1sol.png");
        kosma2Sol=new ImageIcon("assets\\spider\\kosma2sol.png");
        kosma3Sol=new ImageIcon("assets\\spider\\kosma3sol.png");
        durum.setIcon(durgun);
        zipHiz=-5;
        this.isJumping=isJumping;
        holdingItem=new Hook();
    }
}
