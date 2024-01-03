package app;

import control.GameController;
import domain_model.GameModel;
import domain_model.Player;
import view.MainWindow;

public class TicTacToe {
	
	
	public static void main(String[] args) {		
		GameController gc = new GameController();
		
		
		gc.start();
		
	}
	
}

