import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Editor extends Karakter{
    public Editor(int cubeSize, int x, int y, ArrayList<Bullet> bullets,boolean isJumping) {
        super(cubeSize, x, y, bullets);
        durgun=new ImageIcon("assets\\editor.png");
        kosma1=new ImageIcon("assets\\editor.png");
        kosma2=new ImageIcon("assets\\editor.png");
        kosma3=new ImageIcon("assets\\editor.png");
        durgunSol=new ImageIcon("assets\\editor.png");
        kosma1Sol=new ImageIcon("assets\\editor.png");
        kosma2Sol=new ImageIcon("assets\\editor.png");
        kosma3Sol=new ImageIcon("assets\\editor.png");
        durum.setIcon(durgun);
        zipHiz=-7;
        this.isJumping=isJumping;
        holdingItem=new Hook();
    }
    public void shoot(boolean bulletDirection){
        bullets.add(new Bullet(x-2+sizey/2,y-2+sizey/2, Color.WHITE,bulletDirection));
    }
}
