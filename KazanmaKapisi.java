import java.awt.*;

public class KazanmaKapisi extends Objeler{
    public KazanmaKapisi(int x, int y, int sizex, int sizey) {
        super(x, y, sizex, sizey, false, new Color(150,0,250));
    }
    public boolean kazandiMi(Karakter a){
        if (a.x+a.sizex>x&&a.x<x+sizex&&a.y<y+sizey&&y<a.y+a.sizey){
            return true;
        }
        return false;
    }
}
