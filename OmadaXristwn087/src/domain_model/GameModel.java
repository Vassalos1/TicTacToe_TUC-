package domain_model;


import javax.swing.Timer;

import javax.swing.JOptionPane;

import control.GameController;

import tictactoe_ai.AlphaBetaPruning;

public class GameModel {
	
	Board []board;
	Player[] gamePlayers;	
	GameController gc;
	PlayerRoster plRoster;
	GameRecord gameRecord;
	Boolean mover;
	int moves;
	
	public GameModel(GameController gc) {
		this.gc = gc;
		this.board = new Board[9];
		for(int i=0; i<this.board.length; i++) {
			this.board[i] = new Board();
		}
		plRoster=gc.getPlRoster();
		
		gamePlayers = new Player[2];
		gameRecord= new GameRecord();
		mover = false;
		
		
	}
	
	public GameModel() {
		this.board = new Board[9];
		for(int i=0; i<this.board.length; i++) {
			this.board[i] = new Board();
		}
		
		plRoster = new PlayerRoster();
		//DummyPlayers();
		gamePlayers = new Player[2];
		gameRecord= new GameRecord();
		mover = false;
		this.moves = 0;
	}

	public void selectPlayer(Player selPlayer, int pos) {
		if(pos<0 && pos>1)return;
		gamePlayers[pos] = selPlayer;
		
		}
	
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] != null);
	}
	
	
	public boolean inPlay() {
		return (board!=null && moves<10);
			
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	public int getMover() {
		return mover.compareTo(false);
	}
	
	public void setMover(Boolean mover) {
		this.mover = mover;
	}
	
	public Player[] getGamePlayers() {
		return gamePlayers;
	} 	

	public void checkDimValidity(int row, int col) {
		if (row <0 || col < 0 || row > 2 || col >2) {
			throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
		}
	}
	
	public void checkMoveValidity(int row, int col) {
		
		checkDimValidity(row,col);
		if(board[moves].getBoard()[row][col]!=null) {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	public String getBoardMark(int row, int col) {
		checkDimValidity(row,col);
		if(moves!=0) {
			return board[moves-1].getBoard()[row][col];
		}
		return board[moves].getBoard()[row][col];
	}
	
//	
	
	public void makeMove(int row, int col) {
		
		checkMoveValidity(row,col);
				if(moves==9) {
					return;
				}
				
				if(moves==0) {
					board[0]=new Board();
					board[moves].setCell(row, col, getMoverMark());
				}
				else {
				
					board[moves].setBoard(board[moves-1].getBoard());
					if(board[moves].getBoard()[row][col]==null) {
					board[moves].setCell(row, col, getMoverMark());
							}
				
					
				}
				
				
				mover=!mover;
				moves++;
				checkBoard();
	}
	
	//method to check the board
	public void checkBoard() {
		
		if(!board[moves-1].FinalBoard()&&moves<9) {
		if( mover==false && getGamePlayers()[0].getName().equals("Mr.Bean")) {
			
			mrBeanMoves();
		}
		if (mover==true&& getGamePlayers()[1].getName().equals("Mr.Bean")) {
		
			mrBeanMoves();
		} 	
		if(mover==false && getGamePlayers()[0].getName().equals("Hal")) {
		
			aiMoves();
		}
		if (mover==true&& getGamePlayers()[1].getName().equals("Hal")) {	
			aiMoves();
		}
	}else {
		
	System.out.println(board[moves-1].checkOs()+"   "+ board[moves-1].checkXs());
		if(board[moves-1].LegalBoard()) {	
			System.out.println("Board is valid");
		}
		else if(!board[moves-1].LegalBoard() || board[moves-1].FinalBoard()) {

			NoPlay();
		}
		
		if((board[moves-1].Win("X")||board[moves-1].Win("O"))&& board[moves-1].FinalBoard()) {
				
			if(board[moves-1].Win("X")) {
				
				JOptionPane.showMessageDialog(gc.getView(), "Player X wins!", "Game Over!", JOptionPane.PLAIN_MESSAGE);
				gameRecord.setWinner(gamePlayers[0]);
				gamePlayers[0].addWin();
				gamePlayers[1].addLost();
			
				
			}
			else if(board[moves-1].Win("O")) {
				JOptionPane.showMessageDialog(gc.getView(), "Player O wins!", "Game Over!", JOptionPane.PLAIN_MESSAGE);
				gameRecord.setWinner(gamePlayers[1]);
				gamePlayers[0].addLost();
				gamePlayers[1].addWin();
				
				}
			gameRecord.setDate(null);
			NoPlay();
			
			gamePlayers[0].addPlayerBestGames(gameRecord);
			gamePlayers[0].addLastGame(gameRecord);
			gamePlayers[1].addPlayerBestGames(gameRecord);
			gamePlayers[1].addLastGame(gameRecord);
			gamePlayers[0].calculateScore();
			gamePlayers[1].calculateScore();

			gamePlayers[0].setScore(gamePlayers[0].getScore());
			gamePlayers[1].setScore(gamePlayers[1].getScore());

		}
		else if(!board[moves-1].Win(getMoverMark()) && moves==9) {
			JOptionPane.showMessageDialog(gc.getView(), "Draw!", "Game Over!", JOptionPane.PLAIN_MESSAGE);
			
			gamePlayers[0].addTie();
			gamePlayers[1].addTie();
			gamePlayers[0].addLastGame(gameRecord);
			gamePlayers[1].addLastGame(gameRecord);
			gamePlayers[1].addPlayerBestGames(gameRecord);
			gamePlayers[0].addPlayerBestGames(gameRecord);
			
		}
	}
	}
	
	
	//making dummy players 
	/*public void DummyPlayers() {
		Player p1 = new Player("Mr.Bean", true);
		Player p2 = new Player("Hal",true);
		plRoster.AddPlayer(p1);
		plRoster.AddPlayer(p2);
		
		for(int i=0;i<3;i++) {
			Player pl = new Player("Player"+i,false);
			pl.addWin();
			pl.setWins((int) (Math.random()*10) + 1);
			pl.addWin();
			pl.setTies((int) (Math.random()*10) + 1);
			pl.addTie();
			pl.setDefeats((int) (Math.random()*10) + 1);
			pl.addLost();
			plRoster.AddPlayer(pl);
		}
	}*/
	
	
	
public void aiMoves()  {
		
		int[] move=new int []{-1,-1};
			
			if(inPlay()) {
				if( getMoverMark().equals("X")) {
					if(moves==0) {
						move = AlphaBetaPruning.getBestMove(getMoverMark(),getBoard()[moves]);
					
					if(move[0]!=-1	|| move[1]!=-1) {
						System.out.println("AI:"+move[0]+","+move[1]);
						
						makeMove(move[0], move[1]);
						
					}
					}
					else if(!board[moves-1].FinalBoard()){
						
						move=AlphaBetaPruning.getBestMove(getMoverMark(),getBoard()[moves-1]);
						if(move[0]!=-1 || move[1]!=-1) {
							System.out.println("AI:"+move[0]+","+move[1]);
							
							makeMove(move[0], move[1]);
							
						}
					}
					
					}
				else if (getMoverMark().equals("O")) {
					if (moves==0){
						move = AlphaBetaPruning.getBestMove(getOppOfMoverMark(),getBoard()[moves]);
					
					if(move[0]!=-1||move[1]!=-1) {
						System.out.println("AI:"+move[0]+","+move[1]);
						
						makeMove(move[0], move[1]);
						
					}
					}
					else {
						
						move=AlphaBetaPruning.getBestMove(getOppOfMoverMark(),getBoard()[moves-1]);
						if(move[0]!=-1 || move[1]!=-1) {
							System.out.println("AI:"+move[0]+","+move[1]);
						
							makeMove(move[0], move[1]);
							
							
						}
					}
				
				}
			}
		}
	public void mrBeanMoves() {
	
		
		if (inPlay()) {
			
			if(moves%2 ==0 ) {
				int x=(int)(Math.random()*3);//Math random returns values within the 0 and 1 range thats why we multiply with 3 and we cast it as int 
				int y=(int)(Math.random()*3);
				if (x>=0 && x<=2 && y>=0 && x<=2) {
					if(getMoves()>0) {
					if(board[moves-1].getBoard()[x][y]==null) {
					System.out.println( "BEAN"+x + "    "+ y);
			
					makeMove(x, y);
				}
					else {
					
						mrBeanMoves();
					}
				
				}
					else {
						if(board[moves].getBoard()[x][y]==null) {
							System.out.println( "BEAN"+x + "    "+ y);
				
							makeMove(x, y);
						}
							else {
								mrBeanMoves();
							}
				}
				}
		}
			else if( moves%2 ==1) {
				int x=(int)(Math.random()*3);//Math random returns values within the 0 and 1 range thats why we multiply with 3 and we cast it as int 
				int y=(int)(Math.random()*3);
				if (x>=0 && x<=2 && y>=0 && x<=2) {
					if(moves>0) {
					if(board[moves-1].getBoard()[x][y]==null) {
						
					
					System.out.println("BEAN"+ x + "    "+ y);
		
					makeMove(x, y);
					}
					else {
						
						mrBeanMoves();
						}
				
			}
					else {
						if(board[moves].getBoard()[x][y]==null) {
						
					
					System.out.println("BEAN"+ x + "    "+ y);
			
					makeMove(x, y);
					}
					else {
						mrBeanMoves();
						}
						
					}
				}
			}
}
	}
		
			
	public String getMoverMark() {
		return mover ? "O": "X";
	}
	public String getOppOfMoverMark() {
		
		return mover? "X": "O";
	}
	
	public Board[] getBoard() {
		return board;
	}

	public void setBoard(Board[] board) {
		this.board = board;
	}

	public PlayerRoster getPlRoster() {
		return plRoster;
	}

	public void setPlRoster(PlayerRoster plRoster) {
		this.plRoster = plRoster;
	}

	public GameRecord getGameRecord() {
		return gameRecord;
	}

	public void setGameRecord(GameRecord gameRecord) {
		this.gameRecord = gameRecord;
	}


	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}
	public void setGamePlayers(Player[] gamePlayers) {
		this.gamePlayers = gamePlayers;
	}
}


