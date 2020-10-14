package sudoko;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Randomizer {
	
	//determines how puzzle numbers are made
	public static int[][] puzzelNumberGenerator(){
		
		int size = Main.gridSize;
		int puzzle[][] = new int[size][size];
		Integer[] puzzleNumbers = {1,2,3,4,5,6,7,8,9};
		Integer[] rowRandomizer = {1,2,3,4,5,6,8};
		
		List<Integer> randomNumbers = Arrays.asList(puzzleNumbers);
		Collections.shuffle(randomNumbers);
		randomNumbers.toArray(puzzleNumbers);
		
		List<Integer> randomRowShift = Arrays.asList(rowRandomizer);
		Collections.shuffle(randomRowShift);
		randomRowShift.toArray(rowRandomizer);
		
		for(int i=0;i<size;i++) {
			
			//inputs randomized number(1-9) to each row
			for(int j=0;j<size;j++) {
				puzzle[i][j]= puzzleNumbers[j];
			}
			
			for(int l=0;l<rowRandomizer[l];l++) {
				int last= puzzleNumbers[size-1];
				
				for(int k=size-1;k>0;k--) {
					puzzleNumbers[k]=puzzleNumbers[k-1];
				}
				
				puzzleNumbers[0]=last;
			}
		}
		
		return puzzle;
	}

	//determines masks
	public static boolean[][] maskGenerator(){
		int size = Main.gridSize;
		boolean[][] masks = new boolean[size][size];
		Boolean [] maskValues = {true, false, false, false, 
							false, false, false, false, false};

		List<Boolean> randomMasks = Arrays.asList(maskValues);
		
		for(int i=0; i<size; i++) {
			Collections.shuffle(randomMasks);
			randomMasks.toArray(maskValues);
			
			for(int j=0; j<size; j++) {
				masks[i][j]=maskValues[j];
			}
			
		}
			
			
		return masks;
	}
}
