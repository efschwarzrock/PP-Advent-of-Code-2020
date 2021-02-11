import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class DayThree {

	public static void main(String[] args) throws FileNotFoundException {		
		int height = 323;
		int width = 31;
		
		//int height = 11;
		//int width = 11;
				
		int[][] map = new int[height][width];
		int i = 0;
		
		BufferedReader br = new BufferedReader(new FileReader("day3.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    int j = 0;
		    while(j < line.length()){
		    	if(line.charAt(j) == '.'){
		    		map[i][j] = 0;
		    	}else{
		    		map[i][j] = 1;
		    	}
		    	j++;
		    }
		    try{
		    	line = scan.nextLine();
		    	i++;
		    }catch(Exception e){
		    	line = null;
		    }
		}
		
		//part one
		int latitude = 0; //----
		int longitude = 0; // |
		int numTrees = 0;
		while(latitude < height){
			numTrees += map[latitude][longitude%width];
			latitude++;
			longitude += 3;
			
		}
		System.out.println(numTrees);
		
		
		//part 2
		//slopes right 3 1 5 7 1
		//        down 1 1 1 1 2
		int[] allNumTrees = {numTrees, 0, 0, 0, 0};
		int[][] slopes = {{3, 1}, {1, 1}, {5, 1}, {7, 1}, {1, 2}};
		int slopeFinder = 1;  //we allready got 3,1
		long multiplyAll = numTrees;
		while(slopeFinder < 5){
			latitude = 0; //----
			longitude = 0; // |
			numTrees = 0;
			while(latitude < height){
				numTrees += map[latitude][longitude%width];
				latitude += slopes[slopeFinder][1];
				longitude += slopes[slopeFinder][0];
			}
			allNumTrees[slopeFinder] = numTrees;
			multiplyAll = multiplyAll*numTrees;
			slopeFinder++;
			System.out.println(numTrees);
		}
		System.out.println(multiplyAll);
	}

}
