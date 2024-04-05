import java.util.ArrayList;

public class Balyoz extends Item{
    public void action(Karakter a, ArrayList<Objeler> objeler,ArrayList<Entity> entities){
        for (int i = 0; i < objeler.size(); i++) {
            double distance = Math.sqrt(Math.pow(a.x - objeler.get(i).x, 2) + Math.pow(a.y - objeler.get(i).y, 2));
            if (distance <= 40&&objeler.get(i) instanceof Duvar) {
                objeler.remove(i);
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            double distance = Math.sqrt(Math.pow(a.x - entities.get(i).x, 2) + Math.pow(a.y - entities.get(i).y, 2));
            if (distance <= 40&&entities.get(i) instanceof Turret) {
                entities.remove(i);
            }
        }
    }
}
