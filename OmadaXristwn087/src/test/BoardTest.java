package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain_model.Board;

public class BoardTest {
	private Board b1;
	private Board b2;
	
	@Before
	public void setUpBoard() {
		String[][] sB1 = {{"X",null,"O"},
				          {"X",null,"O"},
				          {"X",null,"O"}};
		
		String[][] sB2 = {{"X",null,null},
		                  {"X","O","O"},
		                  {"X",null,null}};
		
		
		b1 = new Board(sB1);
		b2 = new Board(sB2);
	}
	
	@Test
	public void testCheckXs() {
		assertEquals(3,b1.checkXs());
		assertEquals(3,b2.checkXs());
	}
	
	@Test
	public void testChecksOs() {
		assertEquals(3,b1.checkOs());
		assertEquals(2,b2.checkOs());
	}
	
	@Test
	public void testCheckNull() {
		assertEquals(3,b1.checkNullBoxes());
		assertEquals(4,b2.checkNullBoxes());
	}
	
	@Test
	public void testCheckFull() {
		assertEquals(6,b1.checkFullBoxes());
		assertEquals(5,b2.checkFullBoxes());
	}
	
	@Test
	public void testWin() {
		assertTrue(b2.Win("X"));
	}
	
	@Test
	public void testLegal() {
		assertFalse(b1.LegalBoard());
		assertTrue(b2.LegalBoard());
	}
	
	@Test
	public void testFinal() {
		assertTrue(b2.FinalBoard());
		assertFalse(b1.FinalBoard());
	}
}