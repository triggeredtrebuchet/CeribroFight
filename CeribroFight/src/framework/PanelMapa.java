package framework;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;

import obiektyWalki.Postac;
import obiektyWalki.Sedzia;

/* Klasa PanelMapa udpowiada mapie
 * wyswietla grid w tle TODO w przyszlosci tez dodac mozliwosc wyswietlania obrazow w tle
 * ikony postaci to elementy grafiki
 * podpisy postaci to labele
 * TODO dodac mozliwosc nakierowania postaci w miejce klikniecia na mapie - wybranie celu
 * TODO dodac metode wyswietlajaca pole razenia dzierzonej broni
 */

public class PanelMapa extends JLayeredPane implements MouseListener{
	
	Color color1 = new Color(60,10,20);
    Color color2 = new Color(60,20,50);
	int wielkoscSiatki = 50;//pxl
	int wielkoscPostaci = 50;//pxl
	int krok = 5;
	boolean zapytanieORuch = false;
	int pozostalyRuch = 0;
	
	Action wDol;
	Action wGore;
	Action wLewo;
	Action wPrawo;
	Action stop;
	
	
	public PanelMapa(Color kolor1, Color kolor2) {
		this.color1 = kolor1;
		this.color2 = kolor2;
		this.setPreferredSize(new Dimension(600,600));
		this.setLayout(null);
		this.addMouseListener(this);
		this.update();
	}
	
	public void update() {
		
		this.removeAll();  //TODO moznaby to zrobic lepiej bez tworzenia wszystkich label od nowa ale mi sie na razie nie chce tego robic
		for(Postac p: Sedzia.dostepnePostacie) {
			JLabel pp = new JLabel();
			pp.setForeground(Color.white);
			pp.setFont(new Font("Arial", Font.BOLD, 8));
			pp.setText(p.getNazwa());
			this.add(pp, Integer.valueOf(3));
			pp.setBounds(p.pozX - 50, p.pozY + this.wielkoscPostaci/2, 100, 10);//TODO jakis algorytm na dodawanie w takich miejscach, zeby sie nie zaslanialy
			pp.setBackground(Color.GRAY); // jesli sie da, to zmienic na polprzezroczyste
			pp.setHorizontalAlignment(WIDTH/2);;
			pp.setOpaque(true);
		}
		this.repaint();
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint(0, h/2 , this.color1, w, h/2, this.color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        
        g2d.setColor(Color.lightGray);
		g2d.setStroke(new BasicStroke(1));
		
		for(int i = 0; i < this.getWidth(); i += this.wielkoscSiatki)
			g2d.drawLine(i, 0, i, this.getHeight());
		
		for(int i = 0; i < this.getHeight(); i += this.wielkoscSiatki)
			g2d.drawLine(0, i, this.getWidth(), i);
		
		if(this.zapytanieORuch) {
			g2d.drawOval(MainFrame.aktywnaPostac.pozX - this.pozostalyRuch/2, MainFrame.aktywnaPostac.pozY - this.pozostalyRuch/2, this.pozostalyRuch, this.pozostalyRuch);
		}
		
		
		for(Postac p: Sedzia.dostepnePostacie) {
			g2d.setColor(p.kolor);
			int katP = 40;
			try {
				katP = 20 * p.getCechy().suma("BD");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int a = 180 - katP/2 + (int) Math.round(Math.toDegrees(p.pozAlpha));
			int dx= (int) (Math.round(this.wielkoscPostaci/3.5 * Math.cos(Math.toRadians(a - 180 + katP/2))));
			int dy = (int) (-Math.round(this.wielkoscPostaci/3.5 * Math.sin(Math.toRadians(a - 180 + katP/2))));
			g2d.fillArc(dx + p.pozX - this.wielkoscPostaci/2, dy + p.pozY - this.wielkoscPostaci/2, this.wielkoscPostaci, this.wielkoscPostaci, a, katP);
		}	
    }

	public void ruszaj(Postac p) {
		this.zapytanieORuch = true;
		pozostalyRuch = MainFrame.aktywnaPostac.getZakresRuchu() * this.wielkoscSiatki;		
		this.update();
		
		wDol = new wDol();
		wGore = new wGore();
		wLewo = new wLewo();
		wPrawo = new wPrawo();
		stop = new stop();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "wGore");
		this.getActionMap().put("wGore", wGore);
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "wDol");
		this.getActionMap().put("wDol", wDol);
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "wLewo");
		this.getActionMap().put("wLewo", wLewo);
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "wPrawo");
		this.getActionMap().put("wPrawo", wPrawo);
		this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "stop");
		this.getActionMap().put("stop", stop);
		
	}
	
	void koniecRuchu() {
		this.wDol = null;
		this.wGore = null;
		this.wLewo = null;
		this.wPrawo = null;
		
		this.getInputMap().remove(KeyStroke.getKeyStroke("UP"));
		this.getInputMap().remove(KeyStroke.getKeyStroke("DOWN"));
		this.getInputMap().remove(KeyStroke.getKeyStroke("LEFT"));
		this.getInputMap().remove(KeyStroke.getKeyStroke("RIGHT"));
		
		this.pozostalyRuch = 0;
		this.zapytanieORuch = false;
		this.update();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MainFrame.aktywnaPostac.pozAlpha = -Math.atan2(e.getLocationOnScreen().getY() - MainFrame.aktywnaPostac.pozY, e.getLocationOnScreen().getX() - MainFrame.aktywnaPostac.pozX);
		this.update();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class wDol extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.aktywnaPostac.pozY += 5;
			pozostalyRuch -= 5;
			if (pozostalyRuch <= 0)
				koniecRuchu();
			update();
			
		}
	}

	class wLewo extends AbstractAction{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.aktywnaPostac.pozX -= 5;
			pozostalyRuch -= 5;
			if (pozostalyRuch <= 0)
				koniecRuchu();
			update();
		}
		
	}
	class wGore extends AbstractAction{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.aktywnaPostac.pozY -= 5;
			pozostalyRuch -= 5;
			if (pozostalyRuch <= 0)
				koniecRuchu();
			update();
		}
		
	}
	class wPrawo extends AbstractAction{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.aktywnaPostac.pozX += 5;
			pozostalyRuch -= 5;
			if (pozostalyRuch <= 0)
				koniecRuchu();
			update();
		}
	
	}
	class stop extends AbstractAction{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			koniecRuchu();
		}
	
	}
}



