package tictactoe_ai;

import control.GameController;
import domain_model.*;
public class MrBean {
	private GameModel gm;
	private boolean play = true;
	private String name;
	
	public MrBean() {
		this.gm = gm;
	}
	

		
	
	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public void setName(String name) {
		this.name=name;
		
	}
	
}
