package view;

//creating the panels of the game

import javax.swing.JFrame;

import control.GameController;
import domain_model.GameModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	static public final int WIDTH = 1200;
	static public final int HEIGHT = 800;
	static public final int TOP_HEIGHT = 80;
	static public final int PLAYER_WIDTH = 300;
	
	private TopPanel topPanel;
	private PlayerPanel leftPnl;
	private PlayerPanel rightPnl;
	private MainAreaPanel mainPnl;
	private GameController gc;
	private GameModel gm;
	
	
	public MainWindow(GameController gc) {
		super("TUC-TAC-TOE");
		this.gc = gc;
		Container c = this.getContentPane();
		c.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.CYAN);
		
		topPanel = new TopPanel(this.gc);
		topPanel.setBackground(Color.BLACK);
		this.add(topPanel,BorderLayout.PAGE_START);
		
		leftPnl = new PlayerPanel(gc,0);
		leftPnl.setBackground(Color.BLACK);
		this.add(this.leftPnl,BorderLayout.LINE_START);
		
		rightPnl = new PlayerPanel(gc,1);
		rightPnl.setBackground(Color.BLACK);
		this.add(this.rightPnl,BorderLayout.LINE_END);
		
		mainPnl = new MainAreaPanel(this.gc);
		mainPnl.setBackground(Color.BLACK);
		this.add(mainPnl,BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.pack();
	}
	public TopPanel getTopPanel() {
		return topPanel;
	}


	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}


	public PlayerPanel getLeftPnl() {
		return leftPnl;
	}


	public void setLeftPnl(PlayerPanel leftPnl) {
		this.leftPnl = leftPnl;
	}


	public PlayerPanel getRightPnl() {
		return rightPnl;
	}


	public void setRightPnl(PlayerPanel rightPnl) {
		this.rightPnl = rightPnl;
	}


	public MainAreaPanel getMainPnl() {
		return mainPnl;
	}


	public void setMainPnl(MainAreaPanel mainPnl) {
		this.mainPnl = mainPnl;
	}


	
}
