package sudoko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {

	private static int size = Main.gridSize;
	
	public static void initMenu(JFrame f) {
	      JMenuBar menuBar = new JMenuBar();
	      InputListener listener = new InputListener();
	      
	      f.setJMenuBar(menuBar);
	      
	      JMenu file = new JMenu("Main Menu");
	      
	      JMenuItem newGame = new JMenuItem("New Game");
	      JMenuItem resetGame = new JMenuItem("Reset Game");
	      JMenuItem quit = new JMenuItem("Quit");
	      newGame.addActionListener(listener);
	      resetGame.addActionListener(listener);
	      quit.addActionListener(listener);
	      file.add(newGame);
	      file.add(resetGame);
	      file.add(quit);
	      menuBar.add(file);
	   }
	
	private static class InputListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String temp = e.getActionCommand();
			
			if(temp.equalsIgnoreCase("New Game")) {
				
				JTextField[][] gameCells = Main.getGameCells();
				for(int row=0; row<size;row++) {
					for(int col=0; col<size;col++) {
						if(gameCells[row][col].isEditable()) {
							gameCells[row][col].setText("");
							gameCells[row][col].setBackground(Main.openCell_BGcolor);
						}
					}
				}
			}
			if(temp.equalsIgnoreCase("Reset Game")) {
				System.exit(0);
			}
			if(temp.equalsIgnoreCase("Quit")) {
				System.exit(0);
			}
		}
		
	}
}
