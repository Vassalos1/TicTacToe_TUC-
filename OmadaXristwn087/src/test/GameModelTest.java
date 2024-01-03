package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import domain_model.Board;
import domain_model.GameModel;
import domain_model.Player;

public class GameModelTest {
	Player p1;
	Player p2;
	Board gb;
	GameModel gm;
	
	@Before
	public void setUp() {
		gm = new GameModel();
	}
	
	@Test
	public void PlayerVsPlayer() {
		setUp();
		Player[] p = new Player[2];
		p[0] = new Player("pipis",false);
		p[1] =  new Player("pipi", false);
		gm.setGamePlayers(p);
		System.out.println(gm.getGamePlayers());
		gm.inPlay();
		gm.makeMove(0, 0);
		gm.makeMove(0, 1);
		gm.makeMove(0, 2);
		gm.makeMove(1, 0);
		gm.makeMove(1, 1);
		gm.makeMove(1, 2);
		gm.makeMove(2, 0);
		//gm.makeMove(2, 1);
		//gm.makeMove(2, 2);
		
		assertEquals("Player X wins!", gm.getGamePlayers());
	}
}
