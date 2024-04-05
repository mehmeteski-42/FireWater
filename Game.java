import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener,MouseListener {
    private ArrayList<Integer> pressedKeys;
    private ArrayList<Objeler> objeler;
    private ArrayList<Entity> entities;
    private ArrayList<Objeler> zeminler;
    public boolean canShoot=true;
    int karakterSize;
    int score;
    public Game() {
        entities=new ArrayList<>();
        karakterSize=25;
        score=0;
        zeminler=new ArrayList<>();
        entities.add(new Red(karakterSize,20,750,new ArrayList<Bullet>(),false));
        setPreferredSize(new Dimension(1920, 900));
        setBackground(new ColorUIResource(54,23,32)); // Zemin rengi siyah olarak ayarlandı
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        pressedKeys = new ArrayList<>();
        objeler=new ArrayList<>();
        objeOlustur();

        Timer timer = new Timer(5, e -> {
            dunyeviIsler();
            repaint();
        });
        timer.start();
        Timer shootingTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canShoot = true;
            }
        });
        shootingTimer.start();
        Timer run = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count ++;
            }
        });
        run.start();
    }
    public Game(String save) {
        Game g;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(save))) {
            g = (Game) in.readObject();
            entities=g.entities;
            objeler=g.objeler;
            zeminler=g.zeminler;
            score=g.score;
            karakterSize=g.karakterSize;
            count=g.count;
            bulletDirection=g.bulletDirection;
            lockSpidey=g.lockSpidey;
            lockStone=g.lockStone;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(1920, 900));
        setBackground(new ColorUIResource(54,23,32));
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        pressedKeys = new ArrayList<>();
        pause=true;
        Timer timer = new Timer(5, e -> {
            dunyeviIsler();
            repaint();
        });
        timer.start();
        Timer shootingTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canShoot = true;
            }
        });
        shootingTimer.start();
        Timer run = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count ++;
            }
        });
        run.start();
    }
    private void objeOlustur(){
        objeler.add(new PasifObje(1560,0,10,1000));
        objeler.add(new PasifObje(0,0,1570,10));
        objeler.add(new PasifObje(0,780,1700,100));
        objeler.add(new PasifObje(50,720,50,10));

        objeler.add(new PasifObje(0,665,50,5));
        objeler.add(new SuZemin(0,660,45,5));
        zeminler.add(new SuZemin(0,660,45,5));
        objeler.add(new PasifObje(45,660,5,10));

        objeler.add(new PasifObje(50,605,50,5));
        objeler.add(new AtesZemin(55,600,45,5));
        zeminler.add(new AtesZemin(55,600,45,5));
        objeler.add(new PasifObje(50,600,5,10));

        objeler.add(new PasifObje(0,540,50,10));
        objeler.add(new PasifObje(100,420,350,10));
        objeler.add(new AtesZemin(330,420,100,5));
        zeminler.add(new AtesZemin(330,420,100,5));
        Switch switch0=new Switch(285,86,5,10,Color.WHITE);
        switch0.objeEkle(new Spawner(1300,100,50,switch0.c));
        objeler.add(switch0);
        for (int i = 0; i < switch0.objeler.size(); i++) {
            objeler.add(switch0.objeler.get(i));
        }
        Switch switch1=new Switch(445,405,5,10,Color.YELLOW);
        switch1.objeEkle(new Asansor(50,420,50,10,switch1.c,200));
        switch1.objeEkle(new Kapi(460,250,10,30,switch1.c,220));
        objeler.add(switch1);
        for (int i = 0; i < switch1.objeler.size(); i++) {
            objeler.add(switch1.objeler.get(i));
        }
        objeler.add(new PasifObje(500,420,1110,10));
        objeler.add(new PasifObje(500,420,10,300));
        objeler.add(new PasifObje(100,480,100,10));
        objeler.add(new PasifObje(200,480,10,60));
        objeler.add(new PasifObje(460,480,50,10));
        Switch switch2=new Switch(110,465,5,10,new Color(22,120,150));
        switch2.objeEkle(new Kapi(460,490,10,60,switch2.c,430));
        switch2.objeEkle(new Dispenser(300,430,25,25,switch2.c,Color.RED));
        objeler.add(switch2);
        for (int i = 0; i < switch2.objeler.size(); i++) {
            objeler.add(switch2.objeler.get(i));
        }
        objeler.add(new Duvar(470,540,30,10));
        objeler.add(new TNT(20,250,510,true));
        objeler.add(new PasifObje(200,540,260,10));
        objeler.add(new PasifObje(200,600,310,10));
        objeler.add(new PasifObje(150,480+entities.get(0).sizey*2,10,80));
        objeler.add(new TNT(20,145,480+entities.get(0).sizey*2-25,true));
        objeler.add(new PasifObje(100,660,310,10));
        objeler.add(new SuZemin(260,600,100,5));

        Switch switch3=new Switch(495,575,5,10,new Color(100,130,10));
        switch3.objeEkle(new Kapi(410,660,10,20,switch3.c,640));
        switch3.objeEkle(new Asansor(160,600,40,10,switch3.c,530));
        objeler.add(switch3);
        for (int i = 0; i < switch3.objeler.size(); i++) {
            objeler.add(switch3.objeler.get(i));
        }
        Switch switch4=new Switch(510,520,5,10,Color.BLACK);
        switch4.objeEkle(new Kapi(880,500,10,50,switch4.c,450));
        objeler.add(switch4);
        for (int i = 0; i < switch4.objeler.size(); i++) {
            objeler.add(switch4.objeler.get(i));
        }
        objeler.add(new PasifObje(200,720,350,10));
        objeler.add(new Treasure(515,690,30,30));
        objeler.add(new PasifObje(450,250,10,180));
        objeler.add(new Duvar(460,420,40,10));
        objeler.add(new PasifObje(450,250,310,10));
        objeler.add(new PasifObje(750,150,10,170));
        objeler.add(new PasifObje(600,310,150,10));
        objeler.add(new TNT(25,725,280,true));
        objeler.add(new PasifObje(750,150,700,40));
        objeler.add(new SuZemin(1100,190,10,230));
        zeminler.add(new SuZemin(1100,160,10,260));
        objeler.add(new PasifObje(100,420,10,400));
        objeler.add(new PasifObje(120,390,15,30));
        objeler.add(new PasifObje(100,0,10,170));
        Entity a=new Entity(35,150,165,true,Color.GREEN);
        objeler.add(a);
        objeler.add(new PasifObje(100,200,200,10));
        objeler.add(new SuZemin(150,200,100,5));
        zeminler.add(new SuZemin(150,200,100,5));
        objeler.add(new PasifObje(290,0,10,160));
        objeler.add(new PasifObje(240,100,50,10));
        objeler.add(new Entity(30,970,40,true,Color.GREEN));
        PasifObje objeler1=new PasifObje(980,0,10,60);
        objeler1.shootable=true;
        objeler.add(objeler1);
        entities.add(new Spider(70,950,80,objeler));
        objeler.add(new SureliObje(580,735,50,10));
        objeler.add(new SureliObje(680,665,50,10));
        objeler.add(new SureliObje(580,595,50,10));
        objeler.add(new SureliObje(780,595,50,10));
        objeler.add(new PasifObje(510,535,40,10));
        objeler.add(new SuZemin(560,430,10,350));
        zeminler.add(new SuZemin(560,430,10,350));
        objeler.add(new AtesZemin(650,430,10,350));
        zeminler.add(new AtesZemin(650,430,10,350));
        objeler.add(new SuZemin(740,430,10,350));
        zeminler.add(new SuZemin(740,430,10,350));
        objeler.add(new PasifObje(880,430,10,70));
        objeler.add(new PasifObje(880,550,10,330));
        objeler.add(new PasifObje(1400,580,200,330));
        entities.add(new Turret(1400,400,30,Color.pink));
        objeler.add(new PasifObje(0,0,5,800));
        objeler.add(new AtesZemin(550,250,100,5));
        zeminler.add(new AtesZemin(550,250,100,5));
        objeler.add(new AsitZemin(600,420,870,5));
        zeminler.add(new AsitZemin(600,420,870,5));
        objeler.add(new SuZemin(260,720,100,5));
        zeminler.add(new SuZemin(260,720,100,5));
        objeler.add(new AsitZemin(550,780,850,4));
        zeminler.add(new AsitZemin(550,780,850,4));
        objeler.add(new Duvar(500,730,10,50));
        objeler.add(new Duvar(1470,530,10,50));
        objeler.add(new PasifObje(1470,520,40,10));
        objeler.add(new PasifObje(1510,520,10,60));
        for (int i=0;i<objeler.size();i++) {
            if (objeler.get(i) instanceof PasifObje||objeler.get(i) instanceof SureliObje) {
                for (int x = objeler.get(i).x; x < objeler.get(i).x + objeler.get(i).sizex; x += 20) {
                    Coin coin = new Coin(x, objeler.get(i).y - 10); // Coin'in y koordinatını PasifObje'nin üstüne yerleştir
                    if (coin.isHanged(objeler,entities))
                        objeler.add(coin);
                }
            }
        }
        int maxWidth = 7;
        int width;
        for (int i = 0; i < maxWidth * 2 - 1; i++) {
            if (i < maxWidth) {
                width = i + 1;
            } else {
                width = maxWidth * 2 - 1 - i;
            }
            for (int j = 0; j < width; j++) {
                int x = 1200 + (maxWidth - width) * 5 + j * 10;
                int y = 250 + i * 10;
                objeler.add(new Coin(x, y));
            }
        }
        for (int i = 0; i < maxWidth * 2 - 1; i++) {
            if (i < maxWidth) {
                width = i + 1;
            } else {
                width = maxWidth * 2 - 1 - i;
            }
            for (int j = 0; j < width; j++) {
                int x = 900 + (maxWidth - width) * 5 + j * 10;
                int y = 250 + i * 10;

                objeler.add(new Coin(x, y));
            }
        }
        maxWidth = 10;
        for (int i = 0; i < maxWidth * 2 - 1; i++) {
            if (i < maxWidth) {
                width = i + 1;
            } else {
                width = maxWidth * 2 - 1 - i;
            }
            for (int j = 0; j < width; j++) {
                int x = 1100 + (maxWidth - width) * 5 + j * 10;
                int y = 500 + i * 10;
                objeler.add(new Coin(x, y));
            }
        }
        objeler.add(new KazanmaKapisi(1480,530,30,50));
    }
    int count=0;
    boolean bulletDirection=true;
    boolean lockSpidey=true;
    boolean lockStone=true;
    boolean pause=false;
    private void dunyeviIsler() {
        if (!pause) {
            if (bulletDirection)
                ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).durgun);
            else
                ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).durgunSol);
            if (((Karakter) entities.get(0)).dead && !(entities.get(0) instanceof Editor)) {
                entities = new ArrayList<>();
                karakterSize = 25;
                score = 0;
                entities.add(new Red(karakterSize, 20, 750, new ArrayList<Bullet>(), false));
                pressedKeys = new ArrayList<>();
                objeler = new ArrayList<>();
                objeOlustur();
            }
            if (((KazanmaKapisi) objeler.get(objeler.size() - 1)).kazandiMi((Karakter) entities.get(0))) {
                String message = "Tebrikler, kazandiniz!";
                String additionalMessage = "Skorunuz: " + score;
                JOptionPane.showMessageDialog(null, message + "\n" + additionalMessage, "Kazanma Bilgisi", JOptionPane.INFORMATION_MESSAGE);
                pause=true;
            }
            int sira = 0;
            int sira1 = 0;
            for (int i = 0; i < objeler.size(); i++) {
                if (objeler.get(i) instanceof Switch) {
                    sira = i;
                    ((Switch) objeler.get(i)).action(entities, objeler);
                }
                if ((objeler.get(i) instanceof Entity)) {
                    sira1 = i;
                    ((Entity) objeler.get(i)).it(entities.get(0), objeler);
                    ((Entity) objeler.get(i)).dus(objeler);
                }
                if ((objeler.get(i) instanceof SureliObje)) {
                    ((SureliObje) objeler.get(i)).kontrol(entities.get(0));
                }
                if ((objeler.get(i) instanceof Coin)) {
                    if (((Coin) objeler.get(i)).take((Karakter) entities.get(0), objeler))
                        score += 5;
                }
                if ((objeler.get(i) instanceof PasifObje)) {
                    if (((PasifObje) objeler.get(i)).isDead(((Karakter) entities.get(0)).bullets))
                        objeler.remove(i);
                }
            }
            ArrayList<Entity> f = new ArrayList<>();
            f.add(((Entity) objeler.get(sira1)));
            ((Switch) objeler.get(sira)).action(f, objeler);
            ((Karakter) entities.get(0)).duvaraCarpanMermiyiYokEt(objeler,entities);
            for (int i = 0; i < entities.size(); i++) {
                int index = entities.get(i).carptiMi(objeler);
                if (index != -1) {
                    entities.get(i).a = !entities.get(i).a;
                }
                entities.get(i).move();
                entities.get(i).dus(objeler);
                if (entities.get(i) instanceof Spider) {
                    ((Spider) entities.get(i)).shootWeb((Karakter) entities.get(0));
                    if (((Spider) entities.get(i)).isDead(objeler)) {
                        entities.remove(i);
                        lockSpidey = false;
                        ImageIcon icon = new ImageIcon("assets\\spider\\durgun.png");
                        JLabel message = new JLabel(" Orumcek adam acildi  4e basip kullanabilirsiniz");
                        message.setIcon(icon);
                        message.setHorizontalTextPosition(SwingConstants.CENTER);
                        message.setVerticalTextPosition(SwingConstants.BOTTOM);
                        JOptionPane.showMessageDialog(null, message, "SpiderMan", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if (entities.get(i) instanceof Turret) {
                    ((Turret) entities.get(i)).shoot((Karakter) entities.get(0), objeler);
                }
                if (entities.get(i) instanceof Canavar) {
                    if (((Canavar) entities.get(i)).hasarAldiMi(((Karakter) entities.get(0)).bullets)) {
                        score+=10;
                        entities.remove(i);
                    }
                }
            }
            ((Karakter) entities.get(0)).shootAction();
            ((Karakter) entities.get(0)).isDead(entities, zeminler);
            if (((Karakter) entities.get(0)).holdingItem != null && events.contains(1)) {
                if (((Karakter) entities.get(0)).holdingItem instanceof Hook) {
                    ((Hook) ((Karakter) entities.get(0)).holdingItem).action(((Karakter) entities.get(0)), coords.get(0), coords.get(1), objeler);
                }
                if (((Karakter) entities.get(0)).holdingItem instanceof Balyoz) {
                    ((Balyoz) ((Karakter) entities.get(0)).holdingItem).action(((Karakter) entities.get(0)), objeler, entities);
                }
                if (entities.get(0) instanceof Editor) {
                    new Balyoz().action(((Karakter) entities.get(0)), objeler, entities);
                }
            }
            if (pressedKeys.contains(KeyEvent.VK_W) && !entities.get(0).isJumping) {
                entities.get(0).yerCekimi = ((Karakter) entities.get(0)).zipHiz; // Zıplama hızı
                entities.get(0).isJumping = true;
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                ((Karakter) entities.get(0)).holdingItem = new Hook();
            }
            double h = entities.get(0).yerCekimi;
            if (pressedKeys.contains(KeyEvent.VK_1)) {
                if (entities.get(0) instanceof StoneMan)
                    entities.set(0, new Red(karakterSize, entities.get(0).x, entities.get(0).y + 20, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                else
                    entities.set(0, new Red(karakterSize, entities.get(0).x, entities.get(0).y, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                entities.get(0).yerCekimi = h;
            }
            if (pressedKeys.contains(KeyEvent.VK_2)) {
                if (entities.get(0) instanceof StoneMan)
                    entities.set(0, new Blue(karakterSize, entities.get(0).x, entities.get(0).y + 20, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                else
                    entities.set(0, new Blue(karakterSize, entities.get(0).x, entities.get(0).y, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                entities.get(0).yerCekimi = h;
            }
            if (pressedKeys.contains(KeyEvent.VK_3)) {
                if (entities.get(0) instanceof StoneMan)
                    entities.set(0, new Magenta(karakterSize, entities.get(0).x, entities.get(0).y + 20, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                else
                    entities.set(0, new Magenta(karakterSize, entities.get(0).x, entities.get(0).y, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                entities.get(0).yerCekimi = h;
            }
            if (pressedKeys.contains(KeyEvent.VK_4)&&!lockSpidey) {
                if (entities.get(0) instanceof StoneMan)
                    entities.set(0, new SpiderMan(karakterSize, entities.get(0).x, entities.get(0).y + 20, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                else
                    entities.set(0, new SpiderMan(karakterSize, entities.get(0).x, entities.get(0).y, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                entities.get(0).yerCekimi = h;
            }
            if (pressedKeys.contains(KeyEvent.VK_5) && !(entities.get(0) instanceof StoneMan) && !lockStone) {
                entities.set(0, new StoneMan(karakterSize + 20, entities.get(0).x, entities.get(0).y - 20, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                entities.get(0).yerCekimi = h;
            }
            if (pressedKeys.contains(KeyEvent.VK_6)) {
                if (entities.get(0) instanceof StoneMan)
                    entities.set(0, new Editor(karakterSize, entities.get(0).x, entities.get(0).y + 20, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                else
                    entities.set(0, new Editor(karakterSize, entities.get(0).x, entities.get(0).y, ((Karakter) entities.get(0)).bullets, entities.get(0).isJumping));
                entities.get(0).yerCekimi = h;
            }
            if (pressedKeys.contains(KeyEvent.VK_A)) {
                if (count % 3 == 0)
                    ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).kosma1Sol);
                else if (count % 3 == 1)
                    ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).kosma2Sol);
                else if (count % 3 == 2)
                    ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).kosma3Sol);
                bulletDirection = false;
                int index = entities.get(0).carptiMi(objeler);
                if (index != -1 && entities.get(0).x > objeler.get(index).x) {
                    entities.get(0).x = objeler.get(index).x + objeler.get(index).sizex;
                } else
                    entities.get(0).x -= entities.get(0).speed; // Sol yönde hareket
            }
            if (pressedKeys.contains(KeyEvent.VK_D)) {
                if (count % 3 == 0)
                    ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).kosma1);
                else if (count % 3 == 1)
                    ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).kosma2);
                else if (count % 3 == 2)
                    ((Karakter) entities.get(0)).durum.setIcon(((Karakter) entities.get(0)).kosma3);
                bulletDirection = true;
                int index = entities.get(0).carptiMi(objeler);
                if (index != -1 && entities.get(0).x < objeler.get(index).x) {
                    entities.get(0).x = objeler.get(index).x - entities.get(0).sizex;
                } else
                    entities.get(0).x += entities.get(0).speed; // Sağ yönde hareket
            }
            if (pressedKeys.contains(KeyEvent.VK_SPACE) && canShoot) {
                ((Karakter) entities.get(0)).shoot(bulletDirection);
                canShoot = false;
            }

            if (entities.get(0).isJumping && !entities.get(0).spidey) {
                boolean a = entities.get(0).tavanVarMi(objeler);
                if (a) {
                    entities.get(0).yerCekimi = Math.abs(entities.get(0).yerCekimi);
                }
                entities.get(0).y += entities.get(0).yerCekimi;
                entities.get(0).yerCekimi += 0.2; // Yer çekimi
                int index = entities.get(0).nerede(entities.get(0).y, objeler);
                if (index != -1) { // Yerde mi kontrol et
                    entities.get(0).y = objeler.get(index).y - entities.get(0).sizey;
                    entities.get(0).isJumping = false;
                }
            }
        }else {
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < ((Karakter)entities.get(0)).bullets.size(); i++) {
            g.setColor(((Karakter)entities.get(0)).bullets.get(i).c);
            g.fillRect((int)((Karakter)entities.get(0)).bullets.get(i).bulletx, (int)((Karakter)entities.get(0)).bullets.get(i).bullety, ((Karakter)entities.get(0)).bullets.get(i).bulletSize, ((Karakter)entities.get(0)).bullets.get(i).bulletSize);
        }

        for (int i = 0; i < objeler.size(); i++) {
            if (objeler.get(i)instanceof TNT) {
                if (((TNT) objeler.get(i)).patladi)
                    ((TNT) objeler.get(i)).patlamaCount++;
                if (((TNT) objeler.get(i)).patlamaCount>30)
                    objeler.remove(i);
            }
            if (objeler.get(i)instanceof Dispenser) {
                for (int j = 0; j < ((Dispenser) objeler.get(i)).bullets.size(); j++) {
                    g.setColor(((Dispenser)objeler.get(i)).bullets.get(j).c);
                    g.fillRect((int)((Dispenser)objeler.get(i)).bullets.get(j).bulletx, (int)((Dispenser)objeler.get(i)).bullets.get(j).bullety, ((Dispenser)objeler.get(i)).bullets.get(j).bulletSize, ((Dispenser)objeler.get(i)).bullets.get(j).bulletSize);

                }
            }
            if (objeler.get(i)instanceof Entity) {
                g.drawImage( ((Entity)objeler.get(i)).durum.getImage(), objeler.get(i).x, objeler.get(i).y, objeler.get(i).sizex, objeler.get(i).sizey, this);
            }else if (objeler.get(i)instanceof Treasure){
                ImageIcon im = new ImageIcon("assets\\chest.png");
                g.drawImage(im.getImage(), objeler.get(i).x, objeler.get(i).y, objeler.get(i).sizex, objeler.get(i).sizey, this);
            }
             else {
                 g.setColor(objeler.get(i).c);
                 g.fillRect(objeler.get(i).x, objeler.get(i).y, objeler.get(i).sizex, objeler.get(i).sizey);
             }
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i)instanceof Spider) {
                for (int j = 0; j < ((Spider) entities.get(i)).webs.size(); j++) {
                    g.setColor(((Spider) entities.get(i)).webs.get(j).c);
                    g.fillRect((int) ((Spider) entities.get(i)).webs.get(j).bulletx, (int) ((Spider) entities.get(i)).webs.get(j).bullety, ((Spider) entities.get(i)).webs.get(j).bulletSize, ((Spider) entities.get(i)).webs.get(j).bulletSize);
                }
                ImageIcon im = new ImageIcon("assets\\ads.png");
                g.drawImage(im.getImage(), entities.get(i).x, entities.get(i).y-entities.get(i).sizey/2, entities.get(i).sizex, entities.get(i).sizey*2,this);
            } else if (entities.get(i)instanceof Karakter) {
                Karakter karakter = (Karakter) entities.get(i);
                JLabel durumLabel = karakter.durum;
                Icon icon = durumLabel.getIcon();
                if (icon instanceof ImageIcon) {
                    Image image = ((ImageIcon) icon).getImage();
                    g.drawImage(image, karakter.x, karakter.y, karakter.sizex, karakter.sizey, this);
                }
                if (((Karakter) entities.get(i)).holdingItem instanceof Hook){
                    for (int j = 0; j < ((Hook) ((Karakter) entities.get(i)).holdingItem).webs.size(); j++) {
                        g.setColor(((Hook) ((Karakter) entities.get(i)).holdingItem).webs.get(j).c);
                        g.fillRect((int) ((Hook) ((Karakter) entities.get(i)).holdingItem).webs.get(j).bulletx, (int) ((Hook) ((Karakter) entities.get(i)).holdingItem).webs.get(j).bullety, ((Hook) ((Karakter) entities.get(i)).holdingItem).webs.get(j).bulletSize, ((Hook) ((Karakter) entities.get(i)).holdingItem).webs.get(j).bulletSize);
                    }
                }
            }else if (entities.get(i)instanceof Turret) {
                for (int j = 0; j < ((Turret) entities.get(i)).bullets.size(); j++) {
                    g.setColor(((Turret) entities.get(i)).bullets.get(j).c);
                    g.fillRect((int) ((Turret) entities.get(i)).bullets.get(j).bulletx, (int) ((Turret) entities.get(i)).bullets.get(j).bullety, ((Turret) entities.get(i)).bullets.get(j).bulletSize, ((Turret) entities.get(i)).bullets.get(j).bulletSize);
                }
                ImageIcon im = new ImageIcon("assets\\turret.png");
                g.drawImage(im.getImage(), entities.get(i).x, entities.get(i).y-entities.get(i).sizey/2, entities.get(i).sizex, entities.get(i).sizey*2,this);
            } else {
                g.setColor(entities.get(i).c);
                g.fillRect(entities.get(i).x, entities.get(i).y, entities.get(i).sizex, entities.get(i).sizey);
            }
        }
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Italic", Font.ITALIC, 20));
        g.drawString("Score: " + score, 10, 20);
        if (!(entities.get(0)instanceof Editor)) {
            for (int i = 0; i < 39; i++) {
                for (int j = 0; j < 35; j++) {
                    if (((Karakter) entities.get(0)).bullets.isEmpty()) {
                        if (!((entities.get(0) instanceof Red) && (entities.get(0).x - 50 < 110 + i * 10 && entities.get(0).x + 50 > 110 + i * 10)
                                && (entities.get(0).y - 50 < 430 + j * 10 && entities.get(0).y + 50 > 430 + j * 10))) {
                            g.setColor(Color.BLACK);
                            g.fillRect(110 + i * 10, 430 + j * 10, 10, 10);
                        }
                    } else {
                        if (!(((entities.get(0) instanceof Red) && (entities.get(0).x - 50 < 110 + i * 10 && entities.get(0).x + 50 > 110 + i * 10)
                                && (entities.get(0).y - 50 < 430 + j * 10 && entities.get(0).y + 50 > 430 + j * 10)) || (
                                (((Karakter) entities.get(0)).bullets.get(0).c == Color.RED) && (((Karakter) entities.get(0)).bullets.get(0).bulletx - 20 < 110 + i * 10 &&
                                        ((Karakter) entities.get(0)).bullets.get(0).bulletx + 20 > 110 + i * 10) &&
                                        (((Karakter) entities.get(0)).bullets.get(0).bullety - 20 < 430 + j * 10 &&
                                                ((Karakter) entities.get(0)).bullets.get(0).bullety + 20 > 430 + j * 10)))) {
                            g.setColor(Color.BLACK);
                            g.fillRect(110 + i * 10, 430 + j * 10, 10, 10);
                        }
                    }
                }
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_P)
            pause=!pause;
        if (e.getKeyCode()==KeyEvent.VK_K&&pause)
            kaydet();
        int key = e.getKeyCode();
        if (!pressedKeys.contains(key)) {
            pressedKeys.add(key);
        }
        if(key==(KeyEvent.VK_E)){
            for (int i = 0; i < objeler.size(); i++) {
                if (objeler.get(i)instanceof Switch)
                    ((Switch) objeler.get(i)).tusAyar(((Karakter)entities.get(0)));
                if (objeler.get(i) instanceof Treasure){
                    if (((Treasure) objeler.get(i)).open((Karakter) entities.get(0))) {
                        lockStone = false;
                        ImageIcon icon = new ImageIcon("assets\\tas\\durgun.png");
                        JLabel message = new JLabel(" Tas adam acildi  5e basip kullanabilirsiniz");
                        message.setIcon(icon);
                        message.setHorizontalTextPosition(SwingConstants.CENTER);
                        message.setVerticalTextPosition(SwingConstants.BOTTOM);
                        JOptionPane.showMessageDialog(null, message, "StoneMan", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
    public void kaydet() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save"));
            oos.writeObject(this);
            oos.close();
            JOptionPane.showMessageDialog(null, "Oyun basariyla kaydedildi.", "Kayit Basarili", JOptionPane.INFORMATION_MESSAGE);
            setBackground(new ColorUIResource(54,23,32));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Oyun kaydedilemedi.", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
            setBackground(new ColorUIResource(54,23,32));
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (pressedKeys.contains(key)) {
            pressedKeys.remove((Integer) key);
            if (key==KeyEvent.VK_A||key==KeyEvent.VK_D)
                ((Karakter)entities.get(0)).durum.setIcon(((Karakter)entities.get(0)).durgun);;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed for this example
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            if (args.length==0)
                frame.getContentPane().add(new Game());
            else
                frame.getContentPane().add(new Game(args[0]));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    ArrayList<Integer> events=new ArrayList<>();

    ArrayList<Integer> coords=new ArrayList<>();
    @Override
    public void mousePressed(MouseEvent e) {
        events.add(e.getButton());
        if (coords.isEmpty()){
            coords.add(e.getX());
            coords.add(e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        events=new ArrayList<>();
        coords=new ArrayList<>();
        ((Hook) ((Karakter) entities.get(0)).holdingItem).webs=new ArrayList<>();
        entities.get(0).spidey=false;
        ((Hook) ((Karakter) entities.get(0)).holdingItem).uret=true;
        ((Hook) ((Karakter) entities.get(0)).holdingItem).dur=true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
