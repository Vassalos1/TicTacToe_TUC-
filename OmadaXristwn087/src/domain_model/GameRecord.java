package domain_model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class GameRecord implements Serializable{
	private Player playerX;
	private Player playerO;
	private float score=0.0f;
	private String wPl;
	private Player winner;
	private Date date;
	
	public GameRecord() {
		playerX= new Player();
		playerO= new Player();
	}
	

	//Getters
	
	public Player getPlayerX() {
		return playerX;
	}
	
	public Player getPlayerO() {
		return playerO;
	}

	public float getScore() {
		return score;
	}

	public Player getWinner() {
		return winner;
	}

	public Date getDate() {
		return date;
	}

	public String getwPl() {
		return wPl;
	}
	
	//Setters
	
	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}
	
	public void setPlayerO(Player playerO) {
		this.playerO = playerO;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setwPl(String wPl) {
		this.wPl = wPl;
	}
	
}
