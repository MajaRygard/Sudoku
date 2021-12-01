package sudoku;

import javax.swing.JOptionPane;

public class Sudoku implements SudokuSolver {
	private int[][] sudoku;

	public Sudoku() {
		sudoku = new int[9][9];

	}

	@Override
	public boolean solve() {
       if(resolve()== false) {
    	   JOptionPane.showMessageDialog(null, "Sudokun gick inte att lösa");
    	   return false;
    	   
       } 
		return resolve();
       
	}

	private boolean resolve() {
			
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					for (int a = 1; a < 10; a++) {
						if (isValid()) {
							sudoku[i][j] = a;

							if (resolve() && isValid()) {
								
								return true;
							} else {
								sudoku[i][j] = 0;
							}
						}
					}
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void add(int row, int col, int digit) {
		sudoku[row][col] = digit;

	}

	@Override
	public void remove(int row, int col) {
		sudoku[row][col] = 0;

	}

	@Override
	public int get(int row, int col) {
		return sudoku[row][col];
	}

	@Override
	public boolean isValid() {
		// Dubbelloop som kollar att inga värden är samma i kolonnerna
		Boolean[] isthere = new Boolean[10];
		for (int i = 0; i < 10; i++) {
			isthere[i] = false;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (isthere[sudoku[i][j]] == true && sudoku[i][j] != 0) {
					return false;
				} else {
					isthere[sudoku[i][j]] = true;
				}
			}
			for (int a = 0; a < 10; a++) {
				isthere[a] = false;
			}

		}

		// Dubbelloop som kollar att inga värden är samma i raderna
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (isthere[sudoku[j][i]] == true && sudoku[j][i] != 0) {
					return false;
				} else {
					isthere[sudoku[j][i]] = true;
				}
			}
			for (int a = 0; a < 10; a++) {
				isthere[a] = false;
			}
		}

		// Metod som kontrollerar om samma siffra finns i en region
		for (int i = 0; i <= 6; i = i + 3) {
			for (int j = 0; j <= 6; j = j + 3) {

				for (int v = 0; v < 3; v++) {
					for (int u = 0; u < 3; u++) {

						if (isthere[sudoku[i + v][j + u]] == true && sudoku[i + v][j + u] != 0) {
							return false;
						} else {
							isthere[sudoku[i + v][j + u]] = true;
						}

					}
				}

				for (int a = 0; a < 10; a++) {
					isthere[a] = false;
				}

			}
		}

		return true;
	}

	@Override
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = 0;
			}
		}

	}

	@Override
	public void setMatrix(int[][] m) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = m[i][j];
			}
		}

	}

	@Override
	public int[][] getMatrix() {
		return sudoku;
	}

}
