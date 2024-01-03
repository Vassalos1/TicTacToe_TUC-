package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import domain_model.Player;
import domain_model.PlayerRoster;

public class PlayerRosterTest {
	PlayerRoster pr = new PlayerRoster();
	Player p1 = new Player("Despoina", false);
	Player p2 = new Player("Kwstas",false);
	
	@Before
	void setUp() {
		p1.addWin();
		p1.addTie();
		p2.addWin();
		p2.addLost();
		
		pr.AddPlayer(p1);
		pr.AddPlayer(p2);
	}
	
	@Test
	public void testAddPlayer() {
		String name = "Mikros";
		Player mikros = new Player("Mikros",false);
		mikros.setWins(2);
		mikros.setTies(1);
		mikros.setDefeats(1);
		
		pr.AddPlayer(mikros);
	}
	
	@Test
	public void testGetPlayersNames() {
		setUp();
		String[] s = new String[10];
		s[0] = "Despoina";
		s[1] =  "Kwstas";
		assertArrayEquals(pr.getPlayersNames(),s);
	}
	
	@Test
	public void testFindPlayer() {
		setUp();
		assertEquals("Should be p1",p1,pr.findPlayer("Despoina"));
	}
	
	@Test 
	public void testHallOfFame() {
		setUp();
	
		assertEquals(p1.getScore()>p2.getScore(),true);
	}
	
}