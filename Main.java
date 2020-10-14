package sudoko;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	
	public static final int gridSize = 9;
	public static final int subGridSize = 3;
	
	public static final int cellSize = 60 ; //pixel width/height
	public static final int canvasWidth = cellSize*gridSize*10000;
	public static final int canvasHeight = cellSize*gridSize*10000;
	
	public static final Color openCell_BGcolor = Color.yellow;
	public static final Color openCell_Text_Yes = new Color(0,255,0); //RGB
	public static final Color openCell_Text_No = Color.red;
	public static final Color closedCell_BGcolor = new Color(240,240,240); //RGB
	public static final Color closedCell_Text = Color.black;
	public static final Font fontNumbers = new Font("Monospaced", Font.BOLD, 20);
	
	public static int score=0;
	public static int scoreNeededToWin=0;
	
	private static JTextField[][] gameCells = new JTextField[gridSize][gridSize];
	
	private int[][] puzzle = Randomizer.puzzelNumberGenerator();
	private boolean[][] masks = Randomizer.maskGenerator();
	
	public Main() {
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(gridSize, gridSize));
		
		InputListener listener = new InputListener();
		
		for(int row = 0; row < gridSize ; ++row) {
			for(int col = 0; col < gridSize; ++col) {
				gameCells[row][col]= new JTextField();
				cp.add(gameCells[row][col]);
				
				if(masks[row][col]) {
					gameCells[row][col].setText("");
					gameCells[row][col].setEditable(true);
					gameCells[row][col].setBackground(openCell_BGcolor);
					gameCells[row][col].addActionListener(listener); 
					scoreNeededToWin++;
				} else {
					gameCells[row][col].setText(puzzle[row][col]+"");
					gameCells[row][col].setEditable(false);
					gameCells[row][col].setBackground(closedCell_BGcolor);
					gameCells[row][col].setForeground(closedCell_Text);
				}
				//beauty
				gameCells[row][col].setHorizontalAlignment(JTextField.CENTER);;
				gameCells[row][col].setFont(fontNumbers);
			}
		}
		
		Menu.initMenu(this); //MENU
		cp.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GAMETIME");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
		
	}
	
	public static JTextField[][] getGameCells() {
		return gameCells;
	}
	
	//Inner Classes
	
	//Listens to number input
	private class InputListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			int rowSelected = -1;
			int columnSelected = -1;
			
			JTextField sourceOfEvent = (JTextField) e.getSource();
			
			boolean found = false; //initially
			
			for(int row =0; row<gridSize && !found; ++row ) {
				for(int col=0; col<gridSize && !found; ++col) {
					if(gameCells[row][col]==sourceOfEvent) {
						rowSelected=row;
						columnSelected=col;
						found=true;
					}
				}
			}
			
			String inputValue = gameCells[rowSelected][columnSelected].getText();
			int inputValueInt = Integer.parseInt(inputValue);
			
			if(inputValueInt == puzzle[rowSelected][columnSelected]) {
				gameCells[rowSelected][columnSelected].setBackground(Color.green);
				score++;
			}
			else {
				gameCells[rowSelected][columnSelected].setBackground(Color.RED);
				score--;
			}
			
			if(score==scoreNeededToWin) {
				for(int row=0;row<gridSize;row++) {
					for(int col=0;col<gridSize;col++) {
						gameCells[row][col].setBackground(Color.green);
					}
				}
				JOptionPane.showMessageDialog(null, "Congrats");
			}
		}
	}
	
}
