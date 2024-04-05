import javax.swing.*;
import java.util.ArrayList;

public class StoneMan extends Karakter{

    public StoneMan(int cubeSize, int x, int y, ArrayList<Bullet> bullets, boolean isJumping) {
        super(cubeSize, x, y, bullets);
        durgun=new ImageIcon("assets\\tas\\durgun.png");
        kosma1=new ImageIcon("assets\\tas\\kosma1.png");
        kosma2=new ImageIcon("assets\\tas\\kosma2.png");
        kosma3=new ImageIcon("assets\\tas\\kosma3.png");
        durgunSol=new ImageIcon("assets\\tas\\durgunsol.png");
        kosma1Sol=new ImageIcon("assets\\tas\\kosma1sol.png");
        kosma2Sol=new ImageIcon("assets\\tas\\kosma2sol.png");
        kosma3Sol=new ImageIcon("assets\\tas\\kosma3sol.png");
        durum.setIcon(durgun);
        zipHiz=0;
        this.isJumping=isJumping;
        holdingItem=new Balyoz();
    }
}
