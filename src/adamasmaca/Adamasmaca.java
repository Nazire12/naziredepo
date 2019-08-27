package adamasmaca;

import java.awt.*;
import java.applet.*;
import javax.swing.*;

public class Adamasmaca extends Applet {
    String aranankelime= "", kelimetahmin, listetahmin;

    int sayac;

    int maxhata;

    boolean kazan, kayıp, mausbuton = true;

    boolean astı;

    boolean[] bilinenkarakterler;

    Color renk = new Color(0x00dddddd);

    public void init() {
     
        setSize(1000, 625);
        setBackground(renk);
              
        yenioyun();
    }

    public void yenioyun() {
    
        aranankelime= getaranankelime();
        listetahmin = "";
        kelimetahmin = "";
      
        bilinenkarakterler = new boolean[aranankelime.length()];

        for (int i = 0; i < aranankelime.length(); i++) {
            if (aranankelime.charAt(i) == ' ') {
                bilinenkarakterler[i] = true;
            } else {
                bilinenkarakterler[i] = false;
            }
        }
      
        kazan = false;
        sayac = 0;
        maxhata = 7;
        kayıp = false;
        astı = false;
    }

    public void paint(Graphics g) {
       
        if (mausbuton) {
            g.setColor(Color.white);
        } else {
            g.setColor(new Color(0x00eeeeee));
        }
       
        g.fillRect(50, 60, 100, 30);
        g.setColor(new Color(0x00aaaaaa));
        g.drawRect(49, 59, 102, 32);
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.BOLD, 16));
        g.drawString("yeni kelime", 64, 80);
        g.setFont(new Font("Helvetica", Font.BOLD, 32));
       
        g.drawLine(50, 550, 375, 550);
        
        g.drawLine(150, 550, 150, 150);
        
        g.drawLine(150, 150, 375, 150);
       
        g.drawLine(375, 150, 375, 199);
       
        for (int i = 0; i <= (aranankelime.length() - 1) * 2; i++) {
            if (i % 2 == 0) {
                if (aranankelime.charAt(i / 2) != ' ') {
                    g.drawLine(i * 15 + 225, 100, (i + 1) * 15 + 225, 100);
                }
                if (bilinenkarakterler[i / 2] == true) {
                    g.drawString("" + aranankelime.charAt(i / 2), i * 15 + 224, 95);
                }
            }
        }
        switch (sayac) {
          
            case 7:
                animasyonsonu(g);
                break;
           
            case 6:
                g.drawLine(375, 270, 300, 280);
           
            case 5:
                g.drawLine(375, 270, 450, 280);
           
            case 4:
                g.drawLine(375, 400, 325, 450);
            
            case 3:
                g.drawLine(375, 400, 425, 450);
           
            case 2:
                g.drawLine(375, 250, 375, 400);
           
            case 1: {
                g.drawOval(349, 199, 51, 51);
                g.setColor(new Color(0x00ffcc99));
                g.fillOval(350, 200, 50, 50);
            }
        }
       
        g.setColor(Color.black);
        for (int i = 0; i < listetahmin.length(); i++) {
            g.drawString("" + listetahmin.charAt(i), 50 + i * 28, 40);
        }
        if (kazan == true) {
            
            g.setColor(new Color(0x00009900));
            g.drawString("KAZANDINIZ!", 600, 200);
            kayıp = true;
            for (int i = 0; i <= (aranankelime.length() - 1) * 2; i++) {
                if (i % 2 == 0) {
                    g.drawString("" + aranankelime.charAt(i / 2), i * 15 + 224, 95);
                }
            }
        }
       
        if (sayac == maxhata) {
            g.setColor(Color.red);
            g.drawString("KAYBETTİNİZ !", 600, 200);
            kayıp = true;
            for (int i = 0; i <= (aranankelime.length() - 1) * 2; i++) {
                if (i % 2 == 0) {
                    g.drawString("" + aranankelime.charAt(i / 2), i * 15 + 224, 95);
                }
            }
        }

        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.BOLD, 16));
        g.drawString("yanlış sayısı: " + sayac, 155, 168);

    }

    public String getaranankelime() {
      
        String[] kelimelistesi = {"çilek ", "muz", "kivi", "araba", "doktor", "müslüm gürses", "papatya", "klima", "gitar", "bilgisayar mühendisi"};
       
        aranankelime= kelimelistesi[(int) (Math.random() * (kelimelistesi.length + 1))];
        aranankelime= aranankelime.toLowerCase();

        return aranankelime;
    }

    public boolean icindevarmi(String a) {
        if (a.equalsIgnoreCase(aranankelime)) {
            return true;
        } else {
            return false;
        }
    }

    public void tammi() {
        kelimetahmin = JOptionPane.showInputDialog(null, "Tüm kelimeyi tahmin et:");
        if (icindevarmi(kelimetahmin)) {
            kazan = true;
        } else {
         
            sayac = maxhata;
        }
    }

    public boolean keyDown(Event e, int k) {
        if (!kayıp) {
            boolean rightA = false;
            char anahtarharf = (char) k;
            if (anahtarharf != ' ') {
                for (int i = 0; i < listetahmin.length(); i++) {
                    if (anahtarharf== listetahmin.charAt(i) || anahtarharf == listetahmin.toUpperCase().charAt(i)) {
                        return true;
                    }
                }
                listetahmin = listetahmin + anahtarharf;
                for (int i = 0; i < aranankelime.length(); i++) {
                    if (anahtarharf == aranankelime.charAt(i) || anahtarharf == aranankelime.toUpperCase().charAt(i)) {
                        rightA = true;
                        bilinenkarakterler[i] = true;
                    }
                }
                kazan = true;
                for (int i = 0; i < aranankelime.length(); i++) {
                    if (bilinenkarakterler[i] == false) {
                        kazan = false;
                    }
                }
                if (rightA == false) {
                    sayac++;
                }
            } else {
                tammi();
            }

            repaint();
        }
        return true;
    }

    public boolean mouseDown(Event evt, int x, int y) {
        if ((x > 50 && x < 150) && (y > 60 && y < 90)) {
            yenioyun();
            repaint();
        }
        return true;
    }

    public boolean mouseMove(Event evt, int x, int y) {
        boolean mouseToogle = mausbuton;
        if ((x > 50 && x < 150) && (y > 60 && y < 90)) {
            mausbuton = true;
        } else {
            mausbuton = false;
        }
        if (mouseToogle != mausbuton && !astı) {
            repaint();
        }

        return true;
    }

    public void animasyonsonu(Graphics g) {
    
        astı = true;

        g.setColor(Color.black);
        g.drawLine(375, 270, 335, 280);
        g.drawLine(375, 270, 415, 280);
        g.drawLine(335, 280, 375, 250);
        g.drawLine(415, 280, 375, 250);
        g.drawLine(375, 400, 350, 375);
        g.drawLine(375, 400, 400, 375);
        g.drawLine(350, 375, 350, 400);
        g.drawLine(400, 375, 400, 400);
        g.drawLine(375, 250, 375, 400);
        g.drawOval(349, 199, 51, 51);
        int c;
        for (int i = 0; i < 220000; i++) {
            c = i / 1000;
            Color faceHue = new Color(255 - c, 0, c);
            g.setColor(faceHue);	
            g.fillOval(350, 200, 50, 50);
        }
        g.setColor(renk);
        g.drawLine(375, 270, 335, 280);
        g.drawLine(375, 270, 415, 280);
        g.drawLine(335, 280, 375, 250);
        g.drawLine(415, 280, 375, 250);
        g.drawLine(375, 400, 350, 375);
        g.drawLine(375, 400, 400, 375);
        g.drawLine(350, 375, 350, 400);
        g.drawLine(400, 375, 400, 400);

        g.setColor(Color.black);
        g.drawLine(375, 250, 375, 400);
        
        g.drawLine(375, 270, 370, 330);
        g.drawLine(375, 270, 380, 330);
        g.drawLine(375, 400, 370, 460);
        g.drawLine(375, 400, 380, 460);

  
    }}