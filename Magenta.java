import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Magenta extends Karakter{

    public Magenta(int cubeSize, int x, int y, ArrayList<Bullet> bullets, boolean isJumping) {
        super(cubeSize, x, y,bullets);
        durgun=new ImageIcon("assets\\magenta\\durgun.png");
        kosma1=new ImageIcon("assets\\magenta\\kosma1.png");
        kosma2=new ImageIcon("assets\\magenta\\kosma2.png");
        kosma3=new ImageIcon("assets\\magenta\\kosma3.png");
        durgunSol=new ImageIcon("assets\\magenta\\durgunsol.png");
        kosma1Sol=new ImageIcon("assets\\magenta\\kosma1sol.png");
        kosma2Sol=new ImageIcon("assets\\magenta\\kosma2sol.png");
        kosma3Sol=new ImageIcon("assets\\magenta\\kosma3sol.png");
        durum.setIcon(durgun);
        c= Color.MAGENTA;
        zipHiz=-7;
        this.isJumping=isJumping;
    }
}
