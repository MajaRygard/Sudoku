package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuController {

	public SudokuController(Sudoku s) {
		SwingUtilities.invokeLater(() -> createWindow("Sudoku", s, 300, 300));
	}

	void createWindow(String title, Sudoku s, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		JPanel panel = new JPanel();

		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");

		panel.add(solve);
		panel.add(clear);
		pane.add(panel, BorderLayout.SOUTH);

		JPanel panel2 = new JPanel();
		JTextField[][] fields = new JTextField[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				JTextField field = new JTextField();
				field.setPreferredSize(new Dimension(40, 40));
				fields[i][j] = field;
				panel2.add(field);

			}
		}
		setBackgroundColor(fields);

		panel2.setLayout(new GridLayout(9, 9));
		pane.add(panel2);
		frame.pack();
		frame.setVisible(true);

		solve.addActionListener(e -> {
			// Dubbel forloop som går igenom alla rutor
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					// Om textrutan är tom adderas en 0a
					if (fields[i][j].getText().equals("")) {
						s.add(i, j, 0);
					} else {

						try { // Testar om det som skrivits in är en siffra i rätt spann, då adderas siffran
							if (Integer.valueOf(fields[i][j].getText()) > 0
									&& Integer.valueOf(fields[i][j].getText()) < 10) {

								s.add(i, j, Integer.valueOf(fields[i][j].getText()));
							} else { // Annars adderas 0 i rutan och ett felmeddelande skrivs ut

								JOptionPane.showMessageDialog(null, "Du måste skriva en siffra 1-9");

								return;
							}
							// Om det som skrevs in inte var en siffra adderas 0 och ett meddelande skrivs
							// ut
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(null, "Du måste skriva en siffra 1-9");

							return;
						}

					}

				}
			}

			s.solve();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					if(s.get(i, j)==0){
						return;
					}
					fields[i][j].setText(Integer.toString(s.get(i, j)));

				}
			}

		});

		clear.addActionListener(e -> {

			s.clear();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					fields[i][j].setText("");
				}
			}
		});

	}

	public void setBackgroundColor(JTextField[][] fields) {
		int count = 0;
		// Metod som kontrollerar om samma siffra finns i en region
		for (int i = 0; i <= 6; i = i + 3) {
			for (int j = 0; j <= 6; j = j + 3) {

				for (int v = 0; v < 3; v++) {
					for (int u = 0; u < 3; u++) {
						if (count % 2 == 0) {
							fields[i + v][j + u].setBackground(new Color(238, 174, 208));
						}
					}
				}
				count++;

			}
		}

	}
}