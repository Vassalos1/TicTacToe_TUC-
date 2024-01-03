package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import domain_model.Player;

public class PlayerTest {
	
	Player p1;
	Player p2;
	Player p3;
	int wins=40;
	int defeats=30;
	int ties=10;
	
	@Before
	public void setUp() {
		p1=new Player("Player1",false);
		p2=new Player("Player2",false);
		
		 p3= new Player("Kwstas",false);
		p3.setWins(wins);
		p3.setDefeats(defeats);
		p3.setTies(ties);
		p3.setTotalGames(wins+defeats+ties);
		p3.calculateScore();
	}
	@Test
	public void testTotalgames() {
		setUp();
		assertEquals("Total games should be "+(wins+defeats+ties),(wins+defeats+ties),p3.getTotalGames());
	}
	@Test
	public void testScores() {	
		setUp();
		float score = (50*(2*wins+ties))/(wins+defeats+ties);
		assertEquals("The score should be "+score,score,p3.getScore(),0.0f);
	}
	@Test
	public void testWin() {
		setUp();
		assertEquals("The wins should be "+wins,wins,p3.getWins());
		
	}
	public void testDefeats() {
		setUp();
		assertEquals("The wins should be "+defeats,defeats,p3.getDefeats());
		
	}
	public void testTies() {
		setUp();
		assertEquals("The wins should be "+ties,ties,p3.getTies());
		
	}
}
