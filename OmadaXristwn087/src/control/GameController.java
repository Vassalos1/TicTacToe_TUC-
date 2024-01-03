package control;

//controller unites view with domain model

import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import domain_model.Board;
import domain_model.GameModel;
import domain_model.Player;
import domain_model.PlayerRoster;
import view.GameBoard;
import view.MainAreaPanel;
import view.MainWindow;
import view.PlayerPanel;

public class GameController extends WindowAdapter implements ActionListener, MouseListener{
	MainWindow view;
	Player p;
	GameModel model;
	GameBoard gb;
	PlayerPanel pPnl;
	PlayerRoster plRoster;
	
	public GameController() {
		plRoster = new PlayerRoster();
		this.p = new Player();
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		//storePlRoster();
		quit();
	}
	
	public void start() {
		this.view = new MainWindow(this);
		
		plRoster.loadPlayerRoster();

		this.model = new GameModel(this);
		
		//this.model.DummyPlayers();
		
		this.gb = new GameBoard(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);
		
		
		this.view.getMainPnl().getHallOfFame().createTheBest();
		this.view.getMainPnl().getHallOfFame().setTheNames(this.model.getPlRoster().findHallOfFame(10));
		System.out.println("The Tic Tac Toe started!");
	}
	
	public void startGame() {
		
		if(model.getGamePlayers()[0].isAI()) {
			if(model.getGamePlayers()[0].chooseAI()==1) {
				model.aiMoves();
			}else {
				model.mrBeanMoves();
			}
			
		}
		this.view.getTopPanel().getStartGameBtn().setEnabled(false);
		this.view.getMainPnl().showCard(MainAreaPanel.BOARD);
		this.view.getLeftPnl().getSelPlayerBtn().setEnabled(model.NoPlay());
		this.view.getRightPnl().getSelPlayerBtn().setEnabled(model.NoPlay());
		
		
	}
	public void quit() {
		System.out.println("Nice Game!");
		plRoster.storePlRoster();
		System.exit(0);
	}
	
	public void selectPlayer(Player selPlayer, int pos) {
		if(this.model.getGamePlayers()[1]==selPlayer||this.model.getGamePlayers()[0]==selPlayer) {
			JOptionPane.showMessageDialog(null , "Player already selected!" , "Warning" , JOptionPane.WARNING_MESSAGE );
			return;
		}else {
		
		if(pos == 0) {
			
			this.view.getLeftPnl().showPlayerStats(selPlayer);
		}
		else if(pos==1) {
			
			this.view.getRightPnl().showPlayerStats(selPlayer);
			
		}
		
		this.model.selectPlayer(selPlayer, pos);
		System.out.println("Player " + pos + " set to " +selPlayer.getName());
		if(model.getGamePlayers()[0]!=null&& model.getGamePlayers()[1]!=null) {
			this.view.getTopPanel().getStartGameBtn().setEnabled(true);
		}
		
		
	}
	}
	public void addNewPlayer() {
		this.view.getTopPanel().getAddPlayerBtn().setEnabled(false);
		String addPlayer = (String) JOptionPane.showInputDialog(null,"Name","New Player",JOptionPane.INFORMATION_MESSAGE);
		
		if(addPlayer==null || addPlayer=="" || addPlayer.equals("")) {
			System.out.println("cannot add this player");
		}
		else {
			Player p = new Player(addPlayer,false);
			this.model.getPlRoster().AddPlayer(p);
			
			//this.view.getMainPnl().getHallOfFame().setVisible(true);
		//	storePlRoster();

		}
		//storePlRoster();
		this.view.getMainPnl().getHallOfFame().setTheNames(this.model.getPlRoster().findHallOfFame(10));
		
		this.view.getTopPanel().getAddPlayerBtn().setEnabled(true);
	}
	public void done() {
		this.model=new GameModel(this);
		
	/*	Board [] b= new Board[9];
		for(int i=0; i<b.length; i++) {
			b[i] = new Board();
			}
		this.model.setBoard(b);
		this.model.setMoves(0);
		this.model.getGamePlayers()[0]=null;
		this.model.getGamePlayers()[1]=null;
		this.model.setMover(false);*/
		//GameRecord gr = new GameRecord();
		//gamePlayers[0].addLastGame(gr);
		//gamePlayers[1].addLastGame(gr);
		
		this.view.getTopPanel().getDoneBtn();
		
		this.gb = new GameBoard(this);
		
		this.view.getMainPnl().showCard(MainAreaPanel.HOF);
		this.view.getLeftPnl().hidePlayerStats();
		this.view.getRightPnl().hidePlayerStats();
		this.view.getTopPanel().getStartGameBtn().setEnabled(false);
		this.view.getTopPanel().getAddPlayerBtn().setEnabled(true);
		this.view.getLeftPnl().getSelPlayerBtn().setEnabled(true);
		this.view.getRightPnl().getSelPlayerBtn().setEnabled(true);
		
		
		
	}
	
	
	/*//to save the roster in a file
		public void storePlRoster() {
			ObjectOutputStream x = null;
			FileOutputStream y = null;
			try {
				
				y = new FileOutputStream("tuctactoe1.txt");
				x = new ObjectOutputStream(y);
				x.flush();
				
				x.writeObject(plRoster);
			
				System.out.println("file is written!");
			}
			catch (Exception e) {
				
			}finally {
				try {
					x.close(); 
					y.close();
					}catch (Exception e) {
				}
			}
		}
		
		//to load a player from the roster 
		public void loadPlayerRoster() {
			ObjectInputStream x = null;
			FileInputStream y = null;		
			try {
				y = new FileInputStream("tuctactoe1.txt");			
				x = new ObjectInputStream(y);			
				
				plRoster=(PlayerRoster)x.readObject();
				//this.model.setPlRoster(plRoster);
				System.out.println(this.model.getPlRoster().getPlayersNames());

			} catch (Exception e) {
				System.out.println("loading failled");
				plRoster=new PlayerRoster();
							}finally {
				try {
					x.close(); 
					y.close();
					}catch (Exception e) {
				}
			}
		}*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	public MainWindow getView() {
		return view;
	}

	public GameModel getModel() {
		return model;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public PlayerRoster getPlRoster() {
		return plRoster;
	}
	

}
