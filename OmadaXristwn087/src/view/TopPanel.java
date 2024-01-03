package view;

//creating the buttons of the top panel

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;

public class TopPanel extends GamePanel{
	JButton quitBtn;
	JButton startGameBtn;
	JButton doneBtn;
	JButton addPlayerBtn;
	
	public TopPanel(GameController gc) {
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.BLACK,1,true));
		
		quitBtn = new JButton("Quit App");
		quitBtn.setPreferredSize(new Dimension(100,40));
		quitBtn.setBackground(Color.BLACK);
		quitBtn.setForeground(Color.RED);
		quitBtn.addActionListener((e)->{this.gc.quit();});
		
		startGameBtn = new JButton("Start Game");
		startGameBtn.setPreferredSize(new Dimension(100,40));
		startGameBtn.setBackground(Color.BLACK);
		startGameBtn.setForeground(Color.RED);
		startGameBtn.setEnabled(false);
		startGameBtn.addActionListener((e)->{this.gc.startGame();});
		
		doneBtn = new JButton("Done");
		doneBtn.setPreferredSize(new Dimension(100,40));
		doneBtn.setBackground(Color.BLACK);
		doneBtn.setForeground(Color.RED);
		doneBtn.setEnabled(true);
		doneBtn.addActionListener((e)->{this.gc.done();
			System.out.println("done pressed");});
		
		addPlayerBtn = new JButton("Add Player");
		addPlayerBtn.setPreferredSize(new Dimension(100,40));
		addPlayerBtn.setBackground(Color.BLACK);
		addPlayerBtn.setForeground(Color.RED);
		addPlayerBtn.setEnabled(true);
		addPlayerBtn.addActionListener((e)->{this.gc.addNewPlayer();
			System.out.println("add player pressed");});
	
		add(startGameBtn);
		add(quitBtn);
		add(doneBtn);
		add(addPlayerBtn);
	}

	public JButton getQuitBtn() {
		return quitBtn;
	}

	public JButton getStartGameBtn() {
		return startGameBtn;
	}

	public JButton getDoneBtn() {
		return doneBtn;
	}
	
	public JButton getAddPlayerBtn() {
		return addPlayerBtn;
	}
	
}