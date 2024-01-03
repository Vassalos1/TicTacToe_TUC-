package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;
import domain_model.GameModel;
import domain_model.Player;

public class PlayerPanel extends GamePanel{
	
	JButton selPlayerBtn;
	int pos;
	JTextField plName;
	JLabel plMark;
	JTextArea plStats;
	String currentPlayer;
	GameModel gm;
	TopPanel tPnl;
	
	public PlayerPanel(GameController c,int pos) {
		super(c);
		this.pos=pos;
		this.tPnl = new TopPanel(c);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.RED,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(Color.BLACK);
		//creating the select player button
		selPlayerBtn = new JButton("Choose Player");
		selPlayerBtn.setForeground(Color.red);
		selPlayerBtn.setBackground(Color.BLACK);
		selPlayerBtn.setPreferredSize(new Dimension(150,40));
		selPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selPlayerBtn.addActionListener((e)->{changePlayer();});
		this.add(selPlayerBtn);
		
		//writing the name in the player panel
		plName = new JTextField();
		plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,40));
		plName.setMaximumSize(plName.getPreferredSize() );
		plName.setAlignmentX(CENTER_ALIGNMENT);
		plName.setHorizontalAlignment(JTextField.CENTER);
		Font nameF = new Font("Verdana",Font.ITALIC,18);
		plName.setFont(nameF);
		plName.setForeground(Color.RED);
		plName.setBackground(Color.black);
		plName.setEnabled(true);
		
		//writing the sign of the player 
		plMark = new JLabel(pos==0? "X" : "O");
		plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
		plMark.setAlignmentX(CENTER_ALIGNMENT);
		plMark.setHorizontalAlignment(JTextField.CENTER);
		plMark.setEnabled(true);
		Font markf = new Font("SansSerif", Font.BOLD ,90);
		plMark.setFont(markf);
		plMark.setForeground(Color.red);
	
		//writing the stats of the player under their name and sign
		plStats = new JTextArea(10,100);		
		plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
		plStats.setAlignmentX(CENTER_ALIGNMENT);
		plStats.setBackground(Color.BLACK);
		Font statsf = new Font("Verdana", Font.ITALIC,18);
		plStats.setFont(statsf);
		plStats.setMargin(new Insets(10, 10, 10, 10));
		plStats.setForeground(Color.red);
		plStats.setEnabled(true);		
		
		this.add(plMark);
		this.add(plName);		
		this.add(plStats);
		
	}
	
	public void changePlayer() {
		String[] allPlayers = gc.getModel().getPlRoster().getPlayersNames();
		Player p=null;
		
		//show player selection catalogue
		String selPlayer  =  (String) JOptionPane.showInputDialog(null,
				"Choose Player",
				"Player Selection",
				JOptionPane.PLAIN_MESSAGE,
				null,
			    allPlayers, null
				);
		
		//Set the selected player
		if(selPlayer!=null) {
			if (selPlayer.equals(gc.getModel().getGamePlayers()[pos==0?1:0])) {
				JOptionPane.showMessageDialog(gc.getView(), 						
						"Player already selected",
						"Ooops...",
						JOptionPane.ERROR_MESSAGE);
				return;
			}		
			this.currentPlayer=selPlayer;
			gc.selectPlayer(gc.getModel().getPlRoster().findPlayer(selPlayer), pos);
			this.plName.setText(currentPlayer);
			this.repaint();
		
		}
	}
	public void showPlayerStats(Player p) {
		plName.setText(p.getName());
		plName.setBackground(Color.BLACK);
		plStats.setText("Won: "+ p.getWins()+ "\n" + "Lost: "+ p.getDefeats()+ "\n"+ "Ties: "+ p.getTies()+ "\n"+"Total Games: "+p.getTotalGames());
				//"\n"+ "Best Games: " + p.getPlBestGames().toString()+ "\n" + "Last Games: "+p.getPlLastGames().toString());
	}
	public void hidePlayerStats() {
		plName.setText("");
		plStats.setText("");
	}
	public JButton getSelPlayerBtn() {
		return selPlayerBtn;
	}

	public void setSelPlayerBtn(JButton selPlayerBtn) {
		this.selPlayerBtn = selPlayerBtn;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public JTextField getPlName() {
		return plName;
	}

	public void setPlName(JTextField plName) {
		this.plName = plName;
	}

	public JTextArea getPlStats() {
		return plStats;
	}

	public void setPlStats(String plStats) {
		this.plStats.setText(plStats);
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}	
	
}
