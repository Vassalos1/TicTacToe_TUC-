package domain_model;

import java.io.Serializable;

import tictactoe_ai.AlphaBetaPruning;


public class Player implements Serializable, Comparable{
	private String name;
	private int totalGames;
	private int wins;
	private int defeats;
	private int ties;
	float score;
	private GameRecord[] plBestGames;
	private GameRecord[] plLastGames;
	private boolean AI=false;
	public final static int NUMBER_OF_BEST_GAMES = 5;
	public final static int NUMBER_OF_MOST_RECENT_GAMES = 5;
	
	public Player() {
	}
	
	public Player(String name,Boolean AI) {
		this.AI=AI;
		setName(name);
		 plBestGames= new GameRecord[NUMBER_OF_BEST_GAMES];
		 plLastGames= new GameRecord[NUMBER_OF_MOST_RECENT_GAMES];
	}
	
//method to add the best games checks the scores based on the name of the player
	public void addPlayerBestGames(GameRecord bg) {
		for(int i=0; i<this.plBestGames.length; i++) {
			if(this.plBestGames[i]==null) {
				this.plBestGames[i] = bg;
				return;
			}
			else if(this.plBestGames[i].getPlayerX().getName().equals(bg.getPlayerX().getName()) && this.plBestGames[i].getScore()<bg.getScore()) {
				this.plBestGames[i] = bg;
				plBestGames[i].getPlayerX().addWin();
				return;
			}
			else if(this.plBestGames[i].getPlayerO().getName().equals(bg.getPlayerO().getName()) && this.plBestGames[i].getScore()<bg.getScore()) {
				this.plBestGames[i] = bg;
				plBestGames[i].getPlayerO().addWin();
				return;
			}
		}
	}

	/*public void addLastGame(GameRecord lg) {
		for(int i=0; i<this.plLastGames.length; i++) {
			if(this.plLastGames[i]==null) {
				this.plLastGames[i] = lg;
			}
		}
	}
	*/
	
	//method for the most recent games
	public void addLastGame(GameRecord lg) {
		for(int i=0;i<5;i++) {
			if(plLastGames[i]==null) {
				plLastGames[i] = lg;
				break;
			}else if (lg.getDate().compareTo(plLastGames[i].getDate())>0) {
				for(int j=plLastGames.length-1 ;j>= 0;i--) {
					if(j != 0) {
						plLastGames[j]= plLastGames[j-1];
					}
				}
				plLastGames[0] = null;
				plLastGames[i] = lg;
				break;
			}
		}
	}
	
	public void calculateScore() {
		if(totalGames>0) {
		score = 50*(2*wins + ties)/(wins+defeats+ties);
		System.out.println("The score is: "+score);
	}
}
	
	public void addWin() {
		this.wins++;
		this.totalGames++;
		calculateScore();
	}
	
	public void addLost() {
		this.defeats++;
		this.totalGames++;
		calculateScore();
	}
	
	public void addTie() {
		this.ties++;
		this.totalGames++;
		calculateScore();
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	//Getters
		public String getName() {
			return name;
		}

		public int getTotalGames() {
			//calculating the total games
			totalGames = getWins() + getDefeats() + getTies();
			return totalGames;
		}

		public int getWins() {
			return wins;
		}

		public int getDefeats() {
			return defeats;
		}

		public int getTies() {
			return ties;
		}

		public float getScore() {
			return score;
		}

		public GameRecord[] getPlBestGames() {
			return plBestGames;
		}

		public GameRecord[] getPlLastGames() {
			return plLastGames;
		}

		//Setters
		public void setName(String name) {
			this.name = name;
		}

		public void setTotalGames(int totalGames) {
			this.totalGames = totalGames;
		}

		public void setWins(int wins) {
			this.wins = wins;
		}

		public void setDefeats(int defeats) {
			this.defeats = defeats;
		}

		public void setTies(int ties) {
			this.ties = ties;
		}

		public void setScore(float score) {
			this.score = score;
		}

		public void setPlBestGames(GameRecord[] plBestGames) {
			this.plBestGames = plBestGames;
		}

		public void setPlLastGames(GameRecord[] plLastGames) {
			this.plLastGames = plLastGames;
		}
		
		public boolean isAI() {
			return this.AI;
		}

		public void setAI(boolean aI) {
			AI = aI;
		}
		public int chooseAI() {
			if (this.name=="Hal") {
				return 1;
			}
			else {
				return 0;
			}
		}
		
		
		
}
