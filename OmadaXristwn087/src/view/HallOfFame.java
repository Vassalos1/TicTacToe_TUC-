package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.GameController;
import domain_model.GameModel;
import domain_model.Player;

public class HallOfFame extends GamePanel{
	private GameController gc;
	private GameModel gm;
	JLabel bestTitle;
	JTextArea[] bestNames;
	
	
	public HallOfFame(GameController gc) {
		super(gc);
		this.gc = gc;
		this.setBackground(Color.BLACK);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.RED,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		bestNames = new JTextArea[10];
		
	}
	
	/*@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		int x = this.getWidth()/2 - 50;
		int y = this.getHeight()/2;		
		g.drawString("Hall Of Fame", x, y);
	}*/
		
	public void createTheBest() {
		//creating the hall of fame view
		this.setBackground(Color.RED);
		bestTitle = new JLabel("HALL OF FAME");
		bestTitle.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,150));
		bestTitle.setAlignmentX(CENTER_ALIGNMENT);
		Font bestF = new Font("SansSerif", Font.BOLD,30);
	    bestTitle.setFont(bestF);
		bestTitle.setEnabled(true);
		this.add(bestTitle);
		
		//create view for their names
		for(int i=0; i<10;i++) {
			bestNames[i] = new JTextArea();		
			bestNames[i].setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,200));
			bestNames[i].setAlignmentX(CENTER_ALIGNMENT);
			Font bestN = new Font("Verdana", Font.ITALIC,20);
			bestNames[i].setFont(bestN);
			bestNames[i].setEnabled(false);
			bestNames[i].setMargin(new Insets(10, 10, 10, 10));
			this.add(bestNames[i]);
		    bestNames[i].setBackground(Color.BLACK);
			bestNames[i].setDisabledTextColor(Color.RED);
			
		}
				
				
		}
		

	
	public void setTheNames(Player[] bestPl) {
		//find the names and set them to the hall of fame
		for(int i=0;i<10;i++) {
			if(bestPl[i]!=null) {
				String s = String.valueOf(i+1) + ". " + bestPl[i].getName() + " : " + bestPl[i].getScore();
				bestNames[i].setText(s);
			}
			else if (bestPl[i]==null) {
				String ss = null;
				if(bestNames[i]!=null) {
				bestNames[i].setText(ss);
				}
			}
		}
	}
}