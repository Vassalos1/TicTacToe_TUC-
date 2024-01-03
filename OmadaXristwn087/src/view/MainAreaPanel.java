package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;

public class MainAreaPanel extends GamePanel{
	public static final String HOF = "HALL_OF_FAME";
	public static final String BOARD = "GAME_BOARD";
	
	HallOfFame hallOfFame;
	JPanel gameBoard;
	CardLayout cards;
	
	public MainAreaPanel(GameController gc) {
		super(gc);
		this.cards = new CardLayout();
		this.setLayout(this.cards);
		
		this.setBackground(Color.BLACK);
		this.setBorder(new LineBorder(Color.BLACK,1,true));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH - 2 * MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
		
		hallOfFame = new HallOfFame(this.gc);
		gameBoard = new GameBoard(this.gc);
		this.add(HOF, hallOfFame);
		this.add(BOARD, gameBoard);
	}
	
	//cards: game board and hall of fame
	public void showCard(String s) {		
		cards.show(this, s);
	}

	public HallOfFame getHallOfFame() {
		return hallOfFame;
	}
	
	public JPanel getGameBoard() {
		return gameBoard;
	}

	
}
