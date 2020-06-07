import javax.swing.*;
import javax.imageio.*;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.io.*;


public class SeedChoose extends JLayeredPane implements MouseMotionListener {
    private Image bgImage;

    private Image cardplaceImage;
    private Image seedchooseImage;
    private Image beginImage;

    private Image newspaperZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/newspaperzombie/NewspaperZombieRelax.gif")).getImage();
    private Image coneHeadZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/coneheadzombie/ConeheadZombieRelax.gif")).getImage();
    private Image normalZombieImage1 = new ImageIcon(this.getClass().getResource("images/zombies/normalzombie/ZombieRelax1.gif")).getImage();
    private Image normalZombieImage2 = new ImageIcon(this.getClass().getResource("images/zombies/normalzombie/ZombieRelax2.gif")).getImage();
    private Image normalZombieImage3 = new ImageIcon(this.getClass().getResource("images/zombies/normalzombie/ZombieRelax3.gif")).getImage();
    private Image metalBucketZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/metalbucketzombie/BucketZombieRelax.gif")).getImage();
    private Image poleVaultingZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/polevaultingzombie/PoleVaultingZombieRelax.gif")).getImage();
    private Image footballZombieImage = new ImageIcon(this.getClass().getResource("images/zombies/footballzombie/FootballZombieRelax.gif")).getImage();

    public SeedCardpre[] place;
    public SeedCardpre[] preplace;
    public SeedCardpre beginbutton;

    public Timer ti;

    public int k = 0;

    private Timer redrawTimer;

    private SoundEffect seedchoose = new SoundEffect("./src/bgms/ChooseSeeds.wav");
    private SoundEffect seedclick = new SoundEffect("./src/bgms/grassstep.wav");

    public SeedChoose() {
        addMouseMotionListener(this);
        setSize(1400, 600);
        setLayout(null);
        //    addMouseMotionListener(this);
        setVisible(true);
        seedchoose.prepare();
        seedclick.prepare();
        seedchoose.player.loop(Clip.LOOP_CONTINUOUSLY);
        bgImage = new ImageIcon(this.getClass().getResource("images/background1.png")).getImage();

        cardplaceImage = new ImageIcon(this.getClass().getResource("images/cardplace1.png")).getImage();
        seedchooseImage = new ImageIcon(this.getClass().getResource("images/SeedChooser.png")).getImage();
        beginImage = new ImageIcon(this.getClass().getResource("images/begingame.png")).getImage();

        beginbutton = new SeedCardpre();
        beginbutton.setAction(0, 580, 500, "images/nobegingame.png", "", (ActionEvent e) -> {
            if (k == 9) {
                seedchoose.player.stop();
                GameWindow.begingame();
            }
        });
        ti = new Timer(0, (ActionEvent e) -> {
            if (k == 9) {
                Image img = new ImageIcon(this.getClass().getResource("images/begingame1.png")).getImage();
                beginbutton.setImage(img);
            }
            if (k < 9){
                Image img = new ImageIcon(this.getClass().getResource("images/nobegingame.png")).getImage();
                beginbutton.setImage(img);
            }
        });
        ti.start();

        beginbutton.setSize(154, 37);
        beginbutton.setLocation(580, 500);
        beginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                beginbtnMouseEntered(e);
            }

            public void mouseExited(MouseEvent e) {
                beginbtnMouseExited(e);
            }
        });
        add(beginbutton, new Integer(0));

        place = new SeedCardpre[9];
        preplace = new SeedCardpre[40];


        for (int i = 0; i < 9; i++) place[i] = new SeedCardpre();
        for (int i = 0; i < 40; i++) preplace[i] = new SeedCardpre();


        for (int i = 0; i < 9; i++) {
            SeedCardpre a = new SeedCardpre();
            a.setAction(i, 418 + 65 * i, 8, null, null, (ActionEvent e) -> {
                seedclick.prepare();
                seedclick.player.start();
                if (a.getImage() != null) {
                    preplace[a.reid].setImage(a.getImage());
                    preplace[a.reid].setname(a.getname());
                    a.setImage(null);
                    a.setname(null);
                    for (int j = a.id; j < k; ++j) {
                        if (j == 8) {
                            place[j].setImage(null);
                            place[j].setname(null);
                            place[j].reid = 0;
                            break;
                        }
                        place[j].setImage(place[j + 1].getImage());
                        place[j].setname(place[j + 1].getname());
                        place[j].reid = place[j + 1].reid;
                        place[j + 1].setImage(null);
                        place[j + 1].setname(null);
                    }
                    k--;
                }
            });
            a.setLocation(418 + 65 * i, 8);
            place[i] = a;
            add(place[i], new Integer(0));
        }


        for (int i = 0; i < 40; i++) {


            SeedCardpre a = new SeedCardpre();
            if (i == 0)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_sunflower.png", "sunflower", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 1)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_peashooter.png", "peashooter", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 2)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_freezepeashooter.png", "freezepeashooter", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 3)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_twicepeashooter.png", "twicepeashooter", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 4)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_threepeashooter.png", "threepeashooter", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 5)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_torchwood.png", "torchwood", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 6)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_wallnut.png", "wallnut", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 7)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_chomper.png", "chomper", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 8)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_potatomine.png", "potatomine", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 9)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_gatling.png", "gatling", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 10)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_cherrybomb.png", "cherrybomb", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 11)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_tallwallnut.png", "tallwallnut", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 12)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_twinsunflower.png", "twinsunflower", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 13)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_jalapeno.png", "jalapeno", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 14)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_spikeweed.png", "spikeweed", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i == 15)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, "images/cards/card_spikerock.png", "spikerock", (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            if (i > 15)
                a.setAction(i, 337 + 65 * (i % 10), 135 + (i / 10) * 90, null, null, (ActionEvent e) -> {
                    seedclick.prepare();
                    seedclick.player.start();
                    if (a.getImage() != null && k < 9) {
                        place[k].setImage(a.getImage());
                        place[k].setname(a.getname());
                        place[k].reid = a.id;
                        a.setImage(null);
                        a.setname(null);
                        k++;
                    }
                });

            a.setLocation(337 + 65 * (i % 10), 135 + (i / 10) * 90);
            preplace[i] = a;
            add(preplace[i], new Integer(0));
        }


        redrawTimer = new Timer(5, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);

        g.drawImage(cardplaceImage, 322, 0, null);
        g.drawImage(seedchooseImage, 322, 110, null);

        for (int i = 0; i < 9; i++) {
            g.drawImage(place[i].getImage(), place[i].getX(), place[i].getY(), null);
        }

        for (int i = 0; i < 40; i++) {
            g.drawImage(preplace[i].getImage(), preplace[i].getX(), preplace[i].getY(), null);
        }

        g.drawImage(beginbutton.getImage(), beginbutton.getX(), beginbutton.getY(), null);

        g.drawImage(newspaperZombieImage, 1100, 50, null);
        g.drawImage(coneHeadZombieImage, 1085, 150, null);
        g.drawImage(normalZombieImage1, 1050, 20, null);
        g.drawImage(normalZombieImage2, 1100, 350, null);
        g.drawImage(normalZombieImage3, 1280, 300, null);
        g.drawImage(metalBucketZombieImage, 1210, 200, null);
        g.drawImage(poleVaultingZombieImage, 1030, 250, null);
        g.drawImage(footballZombieImage, 1110, 400, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void beginbtnMouseEntered(MouseEvent e) {
        if (k == 9)
            setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void beginbtnMouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public String[] getplace() {
        String[] na = new String[9];
        for (int i = 0; i < 9; ++i) {
            na[i] = place[i].getname();
        }
        return na;
    }

    public SoundEffect getSound() {
        return seedchoose;
    }
}