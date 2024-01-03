package domain_model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;



public class PlayerRoster implements Serializable{
	private static Player [] player;
	
	public PlayerRoster() {
		//loadPlayerRoster();
		player = new Player[100];
	
	}
	
	public PlayerRoster(Player[] player) {
		player = new Player[10];
		
	}
	
	public Player[] getPlayer() {
		return player;
	}


	public void setPlayer(Player[] player) {
		this.player = player;
	}

	
	//adding players later it is used when you press the add new player button
	public  void AddPlayer(Player p) {

		
		for (int i=0;i<10;i++) {
			if(player[i]== p) {
				break;
			}else if(player[i]==null) {
				player[i]=p;
				break;
			}
		}
	//	k++;
		
	//	player[k]=p;
	//	System.out.println(player[k].getName());
	
		
		
	}

	public String[] getPlayersNames() {
		String[] names=new String[10];

		for(int i=0;i<10;i++) {
			if(player[i]!=null) {
				
		    names[i]=player[i].getName();
		    
		}
			}
		//System.out.println(names[0]+ names[1]+names[2]+names[3]+names[4]+names[5]);
		return names;
	}
	
	public Player findPlayer(String name) {
		for(int i=0;i<10;i++) {
			if(name==player[i].getName()) {
			return player[i];	
			}
			
		}
	return null;
	}
	
	public Player[] findHallOfFame(int n) {
		sortScore(this.getPlayer());
		Player[] p= new Player[10];
		for (int i=0;i<10;i++) {
			if(player[i]!=null) {
				p[i]=player[i];
			}
		}
	return p;
	}

	//sorting the score in order to get the right order in the hall of fame
	private Player[] sortScore(Player[] p) {
		Player temp = null;
		for (int i=10;i>=0;i--) {
			for(int k=10;k>=0;k--) {
				if(p[i]!=null &&p[k]!=null ) {
				if(p[i].getScore()<p[k].getScore()) {
					temp=p[i];
					p[i]=player[k];	
					p[k]=temp;
					
					}
				}
			}
		}
		return p;
	}

	public void DummyPlayers() {
		Player p1 = new Player("Mr.Bean", true);
		Player p2 = new Player("Hal",true);
		AddPlayer(p1);
		AddPlayer(p2);
	}
	
	public void storePlRoster() {
		ObjectOutputStream x = null;
		FileOutputStream y = null;
		try {
			
			y = new FileOutputStream("tuctactoe1.txt");
			x = new ObjectOutputStream(y);
			x.flush();
			
			x.writeObject(player);
		
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
			
			player=(Player[])x.readObject();
			//this.model.setPlRoster(plRoster);
			x.close();
			y.close();

		} catch (Exception e) {
			System.out.println("loading failled");
			
						}finally {
			try {
				x.close(); 
				y.close();
				}catch (Exception e) {
			}
		}
	}

}