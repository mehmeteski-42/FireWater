
import java.awt.*;

public class SureliObje extends Objeler {
    private int count = 0;
    private int count2 = 0;

    public SureliObje(int x, int y, int sizex, int sizey) {
        super(x, y, sizex, sizey,true, new Color(255,255,255));
    }

    public void kontrol(Entity a) {
        boolean ustunde = a.x < this.x + this.sizex && a.x + a.sizex > this.x && a.y + a.sizey == this.y;
        if (ustunde||(count>123)) {
            if(count<123)
            c=new Color(255-count*2,255-count*2,255-count*2);
            count++;
            if (count > 123) {
                c=new Color(0,0,0);
                solid=false;
            }
            if (count>500)
                count=0;
            count2=0;
        }
        else if(count2<500){
            c=new Color(255-count*2,255-count*2,255-count*2);
            solid = true;
            count2++;
        }else{
            c=new Color(255,255,255);
            solid = true;
            count=0;
            count2=0;
        }
    }
}