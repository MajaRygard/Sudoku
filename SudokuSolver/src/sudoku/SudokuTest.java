package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {
	Sudoku s;

	@BeforeEach
	void setUp() throws Exception {
		s = new Sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	@Test
	void testAdd() {
		s.add(1, 1, 2);
	assertEquals(s.getMatrix()[1][1], 2, "Siffran inte insatt");
	}
	
	@Test
	void testRemove() {
		s.add(5, 8, 2);
		s.remove(5, 8);
		assertEquals(s.getMatrix()[5][8], 0, "Siffran togs inte bort");
	}
	
	@Test
	void testGet() {
		s.add(5, 8, 2);
		
		assertEquals(s.get(5,8), 2, "Siffran inte hämtad");
	}
	
	@Test
	void testIsValidRow() {
		s.add(3, 3, 5);
		s.add(3, 7, 5);
		assertFalse(s.isValid());

	}
	
	@Test
	void testIsValidCol() {
		s.add(3, 3, 5);
		s.add(7, 3, 5);
		assertFalse(s.isValid());

	}
	@Test
	void testIsValidRegin() {
		s.add(0, 0, 5);
		s.add(2, 2, 5);
		assertFalse(s.isValid());

	}
	
	@Test
	void testIsValidRegin2() {
		s.add(0, 0, 5);
		s.add(2, 2, 2);
		assertTrue(s.isValid());

	}
	
	@Test
	void testClear() {
		s.add(0, 0, 5);
		s.add(2, 2, 5);
		s.clear();
		assertEquals(s.get(0, 0), 0, "inte tom");
		assertEquals(s.get(2, 2), 0, "inte tom");
	}
	
	@Test
	void testSetmatrix() {
		int [][] mat = new int[9][9];
		mat[8][8]= 3;
		mat[7][7]= 7;
		
		s.setMatrix(mat);
		assertEquals(s.get(8, 8), 3);
		assertEquals(s.get(7, 7), 7);
	}
	
	@Test
	void testSolve() {
		s.add(0, 2, 8);
		s.add(0, 5, 9);
		s.add(0, 7, 6);
		s.add(0, 8, 2);
		
		s.add(1, 8, 5);
		s.add(2, 0, 1);
		s.add(2, 2, 2);
		s.add(2, 3, 5);
		s.add(3, 3, 2);
		s.add(3, 4, 1);
		s.add(3, 7, 9);
		
		s.add(4, 1, 5);
		s.add(4, 6, 6);
		s.add(5, 0, 6);
		s.add(5, 7, 2);
		s.add(5, 8, 8);
		
		s.add(6, 0, 4);
		s.add(6, 1, 1);
		s.add(6, 3, 6);
		s.add(6, 5, 8);
		
		s.add(7, 0, 8);
		s.add(7, 1, 6);
		s.add(7, 4, 3);
		s.add(7, 6, 1);
		s.add(8, 6, 4);
		
		assertTrue(s.solve());
		
	}
	

	@Test
	void testSolveNot() {
		s.add(3, 3, 5);
		s.add(3, 7, 5);
		assertFalse(s.solve());

	}	
}
