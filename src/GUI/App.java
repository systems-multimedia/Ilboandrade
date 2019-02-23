/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Meson;
import Logic.Producer;
import GUI.Buttons.FireBtn;
import GUI.Buttons.HireBtn;
import Logic.Restaurant;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class App extends JFrame {

    private final int width;
    private final int height;
    private final int btnPercent, marginPercent;
    private final int lblHeight;
    private final int btnSizeW, btnSizeH;
    private final int smallSquareBtnSize, marginX, marginY;

    private final JFrame parent;
    private final ImageIcon info, bg, bc, ex;
    private final JButton hideBtn;
    private final JButton exitBtn;
    private final JButton infoBtn;
    private final Cursor pointer;
    private final JLabel background;
    private final InfoWindow infoWindow;

    private final HireBtn h1, h2, h3, h4;
    private final FireBtn f1, f2, f3, f4;

    //  Avatars
    private final ImageIcon gm, wb, w, mc, ec, dc;
    private final JLabel gManager;
    private final JLabel wBoss;
    private final JLabel waiter;
    private final JLabel mChef;
    private final JLabel eChef;
    private final JLabel dChef;

    private final Restaurant restaurant;

    public App(final int width, final int height, final JFrame parent, Restaurant restaurant) {
        this.width = width;
        this.height = height;
        this.parent = parent;

        this.setState(JFrame.NORMAL);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        this.bg = new ImageIcon(getClass().getResource("/Img/Background/4. Main.png"));
        this.background = new JLabel();
        this.background.setSize(new Dimension(width, height));
        this.background.setIcon(new ImageIcon(this.bg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));

        this.pointer = new Cursor(Cursor.HAND_CURSOR);

        this.btnPercent = 2;
        this.marginPercent = 7;
        this.smallSquareBtnSize = (this.width * this.btnPercent) / 100;
        this.btnSizeH = this.smallSquareBtnSize + 20;
        this.btnSizeW = (this.btnSizeH * 386) / 144;
        this.marginX = (this.width * this.marginPercent + 3) / 100;
        this.marginY = (this.height * this.marginPercent) / 100;

        this.bc = new ImageIcon(getClass().getResource("/Img/Buttons/3. Minimize (Black).png"));
        this.hideBtn = new JButton();
        this.hideBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.hideBtn.setIcon(new ImageIcon(this.bc.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.hideBtn.setLocation(this.width - (this.smallSquareBtnSize * 2) - 15 - ((this.width * (this.btnPercent + 3)) / 100), 40);
        this.hideBtn.setCursor(pointer);
        this.hideBtn.setContentAreaFilled(false);
        this.hideBtn.setBorder(null);
        this.hideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hideBtnActionPerformed();
            }
        });

        this.info = new ImageIcon(getClass().getResource("/Img/Buttons/5. Info (Black).png"));
        this.infoBtn = new JButton();
        this.infoBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.infoBtn.setIcon(new ImageIcon(this.info.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.infoBtn.setLocation(10, this.height - this.smallSquareBtnSize - 10);
        this.infoBtn.setContentAreaFilled(false);
        this.infoBtn.setFocusPainted(false);
        this.infoBtn.setCursor(pointer);
        this.infoBtn.setBorder(null);
        this.infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                infoBtnActionPerformed();
            }
        });

        this.ex = new ImageIcon(getClass().getResource("/Img/Buttons/1. Close (Black).png"));
        this.exitBtn = new JButton();
        this.exitBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.exitBtn.setIcon(new ImageIcon(this.ex.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.exitBtn.setLocation(this.width - this.smallSquareBtnSize - ((this.width * (this.btnPercent + 3)) / 100), 40);
        this.exitBtn.setContentAreaFilled(false);
        this.exitBtn.setCursor(pointer);
        this.exitBtn.setBorder(null);
        this.exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to close the app?", "ATENTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        this.infoWindow = new InfoWindow(width, height, this);

        this.lblHeight = (this.height * 35) / 100;

        this.gm = new ImageIcon(getClass().getResource("/Img/Avatars/1.-General-Manager.png"));
        this.gManager = new JLabel();
        this.gManager.setSize(new Dimension((this.lblHeight * 275) / 400, this.lblHeight));
        this.gManager.setIcon(new ImageIcon(this.gm.getImage().getScaledInstance((this.lblHeight * 275) / 400, this.lblHeight, Image.SCALE_SMOOTH)));
        this.gManager.setLocation((this.width * 5) / 100, this.height - this.lblHeight - (this.height * 30) / 100);

        this.wb = new ImageIcon(getClass().getResource("/Img/Avatars/2.-Waiter-Boss.png"));
        this.wBoss = new JLabel();
        this.wBoss.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.wBoss.setIcon(new ImageIcon(this.wb.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.wBoss.setLocation((this.width * 23) / 100, this.height - this.lblHeight - (this.height * 30) / 100);

        this.w = new ImageIcon(getClass().getResource("/Img/Avatars/3.-Waiter.png"));
        this.waiter = new JLabel();
        this.waiter.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.waiter.setIcon(new ImageIcon(this.w.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.waiter.setLocation((this.width * 44) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        this.ec = new ImageIcon(getClass().getResource("/Img/Avatars/4.-Entries-Chef.png"));
        this.eChef = new JLabel();
        this.eChef.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.eChef.setIcon(new ImageIcon(this.ec.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.eChef.setLocation((this.width * 61) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        this.mc = new ImageIcon(getClass().getResource("/Img/Avatars/5.-Main-Chef.png"));
        this.mChef = new JLabel();
        this.mChef.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.mChef.setIcon(new ImageIcon(this.mc.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.mChef.setLocation((this.width * 74) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        this.dc = new ImageIcon(getClass().getResource("/Img/Avatars/6.-Desserts-Chef.png"));
        this.dChef = new JLabel();
        this.dChef.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.dChef.setIcon(new ImageIcon(this.dc.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.dChef.setLocation((this.width * 87) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        this.h1 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 65) / 100, this.height - (this.height * 12) / 100);
        this.h1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire1ActionPerformed();
            }
        });
        this.h2 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 78) / 100, this.height - (this.height * 12) / 100);
        this.h2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire2ActionPerformed();
            }
        });

        this.h3 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 91) / 100, this.height - (this.height * 12) / 100);
        this.h3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire3ActionPerformed();
            }
        });

        this.h4 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 48) / 100, this.height - (this.height * 12) / 100);
        this.h4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire4ActionPerformed();
            }
        });

        this.f1 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 61) / 100, this.height - (this.height * 12) / 100);
        this.f1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire1ActionPerformed();
            }
        });

        this.f2 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 74) / 100, this.height - (this.height * 12) / 100);
        this.f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire2ActionPerformed();
            }
        });

        this.f3 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 87) / 100, this.height - (this.height * 12) / 100);
        this.f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire3ActionPerformed();
            }
        });

        this.f4 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 44) / 100, this.height - (this.height * 12) / 100);
        this.f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire4ActionPerformed();
            }
        });

        this.add(this.background);
        this.background.add(this.hideBtn);
        this.background.add(this.exitBtn);
        this.background.add(this.infoBtn);
        this.background.add(this.gManager);
        this.background.add(this.wBoss);
        this.background.add(this.waiter);
        this.background.add(this.mChef);
        this.background.add(this.eChef);
        this.background.add(this.dChef);
        this.background.add(this.h1);
        this.background.add(this.h2);
        this.background.add(this.h3);
        this.background.add(this.h4);
        this.background.add(this.f1);
        this.background.add(this.f2);
        this.background.add(this.f3);
        this.background.add(this.f4);

        this.restaurant = restaurant;
    }

    private void hideBtnActionPerformed() {
        this.setState(JFrame.ICONIFIED);
    }

    private void infoBtnActionPerformed() {
        this.infoWindow.setVisible(true);
        this.infoWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void hire1ActionPerformed() {
        this.restaurant.hireEntryChef();
    }

    private void hire2ActionPerformed() {
        this.restaurant.hireMainChef();
    }

    private void hire3ActionPerformed() {
        this.restaurant.hireDessertChef();
    }

    private void hire4ActionPerformed() {
        this.restaurant.hireWaiter();
    }

    private void fire1ActionPerformed() {
        this.restaurant.fireEntryChef();
    }

    private void fire2ActionPerformed() {
        this.restaurant.fireMainChef();
    }

    private void fire3ActionPerformed() {
        this.restaurant.fireDessertChef();
    }

    private void fire4ActionPerformed() {
        this.restaurant.fireWaiter();
    }

}
