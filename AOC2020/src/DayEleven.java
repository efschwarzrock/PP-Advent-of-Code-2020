import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DayEleven {
	
	public static boolean stable = true;
	
	public static void main(String[] args) throws FileNotFoundException {
		int[][] map = new int[93][97];//1 emptyseat -1 full seat 0 floor
		int[][] mapPt2 = new int[93][97];//1 emptyseat -1 full seat 0 floor
		
		//int[][] map = new int[10][10];//1 emptyseat -1 full seat 0 floor
		//int[][] mapPt2 = new int[10][10];
		int i = 0;
		
		BufferedReader br = new BufferedReader(new FileReader("day11.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    int j = 0;
		    while(j < line.length()){
		    	if(line.charAt(j) == 'L'){
		    		map[i][j] = 1;
		    		mapPt2[i][j] = 1;
		    	}else if(line.charAt(j) == '#'){
		    		map[i][j] = -1;
		    		mapPt2[i][j] = -1;
		    	}else{
		    		map[i][j] = 0;
		    		mapPt2[i][j] = 0;
		    	}
		    	j++;
		    }
			i++;
			
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		
		//part 1
		
		
		
		boolean done = false;
		while(!done){
			map = updateMap(map);
			if(stable){
				done = true;
			}
		}
		
		System.out.println(sumOccupied(map));
		
		
		
		//part 2
		done = false;
		while(!done){
			mapPt2 = updateMapPt2(mapPt2);
			if(stable){
				done = true;
			}
		}
		System.out.println(sumOccupied(mapPt2));
	}
	
	
	
	
	
	
	

	private static int sumOccupied(int[][] map) {
		int sum = 0;
		int i = 0;
		while(i < map.length){
			int j = 0;
			while(j < map[i].length){
				if(map[i][j] == -1){
					sum++;
				}
				j++;
			}
			i++;
		}
		
		return sum;
	}

	private static int[][] updateMap(int[][] map) {
		stable = true;
		int[][] newMap = new int[map.length][map[0].length];
		int i = 0;
		while(i<map.length){
			int j = 0;
			while(j < map[i].length){
				newMap[i][j] = updateCell(i, j, map);
				if(newMap[i][j] != map[i][j]){
					stable = false;
				}
				j++;
			}
			i++;
		}
		return newMap;
	}
	
	private static int[][] updateMapPt2(int[][] map) {
		stable = true;
		int[][] newMap = new int[map.length][map[0].length];
		int i = 0;
		while(i<map.length){
			int j = 0;
			while(j < map[i].length){
				newMap[i][j] = updateCellPt2(i, j, map);
				if(newMap[i][j] != map[i][j]){
					stable = false;
				}
				j++;
			}
			i++;
		}
		return newMap;
	}

	private static int updateCell(int i, int j, int[][] map) {
		if(map[i][j] == 0){
			return 0;
		}else if(map[i][j] == 1){
			if(sum(i, j, map) == 0){
				return -1;
			}else{
				return 1;
			}
		}else{
			if(sum(i, j, map) >= 4){
				return 1;
			}else{
				return -1;
			}
		}
	}
	
	private static int updateCellPt2(int i, int j, int[][] map) {
		if(map[i][j] == 0){
			return 0;
		}else if(map[i][j] == 1){
			if(sumPt2(i, j, map) == 0){
				return -1;
			}else{
				return 1;
			}
		}else{
			if(sumPt2(i, j, map) >= 5){
				return 1;
			}else{
				return -1;
			}
		}
	}

	private static int sumPt2(int i, int j, int[][] map) {
		int sum = 0;
		//top
		sum += topSeat(i, j, map);
		//bottom
		sum += bottomSeat(i, j, map);
		//left
		sum += leftSeat(i, j, map);
		//right
		sum += rightSeat(i, j, map);
		//topright
		sum += topRightSeat(i, j, map);
		//topleft
		sum += topLeftSeat(i, j, map);
		//bottomright
		sum += bottomRightSeat(i, j, map);
		//bottomleft
		sum += bottomLeftSeat(i, j, map);
				
		return sum;
	}
	private static int sumPt2Test(int i, int j, int[][] map) {
		int sum = 0;
		//top
		if( topSeat(i, j, map) == 1){
			System.out.println("top");
		}
		sum += topSeat(i, j, map);
		//bottom
		sum += bottomSeat(i, j, map);
		if( bottomSeat(i, j, map) == 1){
			System.out.println("bottom");
		}
		//left
		sum += leftSeat(i, j, map);
		if( leftSeat(i, j, map) == 1){
			System.out.println("left");
		}
		//right
		sum += rightSeat(i, j, map);
		if( rightSeat(i, j, map) == 1){
			System.out.println("right");
		}
		//topright
		sum += topRightSeat(i, j, map);
		if( topRightSeat(i, j, map) == 1){
			System.out.println("topright");
		}
		//topleft
		sum += topLeftSeat(i, j, map);
		if( topLeftSeat(i, j, map) == 1){
			System.out.println("topleft");
		}
		//bottomright
		sum += bottomRightSeat(i, j, map);
		if( bottomRightSeat(i, j, map) == 1){
			System.out.println("br");
		}
		//bottomleft
		sum += bottomLeftSeat(i, j, map);
		if( bottomLeftSeat(i, j, map) == 1){
			System.out.println("bl");
		}
		System.out.println(sum);
		return sum;
	}
	
	private static int topRightSeat(int i, int j, int[][] map) {
		if(i <= 0 || j >= map[i].length-1){
			return 0;
		}else if(map[i-1][j+1] == -1){
			return 1;
		}else if(map[i-1][j+1] == 1){
			return 0;
		}else{
			return topRightSeat(i-1, j+1, map);
		}
	}
	
	private static int topLeftSeat(int i, int j, int[][] map) {
		if(i <= 0 || j <= 0){
			return 0;
		}else if(map[i-1][j-1] == -1){
			return 1;
		}else if(map[i-1][j-1] == 1){
			return 0;
		}else{
			return topLeftSeat(i-1, j-1, map);
		}
	}
	
	private static int bottomRightSeat(int i, int j, int[][] map) {
		if(i >= map.length-1 || j >= map[i].length-1){
			return 0;
		}else if(map[i+1][j+1] == -1){
			return 1;
		}else if(map[i+1][j+1] == 1){
			return 0;
		}else{
			return bottomRightSeat(i+1, j+1, map);
		}
	}
	
	private static int bottomLeftSeat(int i, int j, int[][] map) {
		if(i >= map.length-1 || j <= 0){
			return 0;
		}else if(map[i+1][j-1] == -1){
			return 1;
		}else if(map[i+1][j-1] == 1){
			return 0;
		}else{
			return bottomLeftSeat(i+1, j-1, map);
		}
	}
	
	private static int bottomSeat(int i, int j, int[][] map) {
		if(i >= map.length-1){
			return 0;
		}else if(map[i+1][j] == -1){
			return 1;
		}else if(map[i+1][j] == 1){
			return 0;
		}else{
			return bottomSeat(i+1, j, map);
		}
	}

	private static int topSeat(int i, int j, int[][] map) {
		if(i <= 0){
			return 0;
		}else if(map[i-1][j] == -1){
			return 1;
		}else if(map[i-1][j] == 1){
			return 0;
		}else{
			return topSeat(i-1, j, map);
		}
	}
	
	private static int rightSeat(int i, int j, int[][] map) {
		if(j >= map[i].length-1){
			return 0;
		}else if(map[i][j+1] == -1){
			return 1;
		}else if(map[i][j+1] == 1){
			return 0;
		}else{
			return rightSeat(i, j+1, map);
		}
	}

	private static int leftSeat(int i, int j, int[][] map) {
		if(j <= 0){
			return 0;
		}else if(map[i][j-1] == -1){
			return 1;
		}else if(map[i][j-1] == 1){
			return 0;
		}else{
			return leftSeat(i, j-1, map);
		}
	}

	private static int sum(int i, int j, int[][] map) {
		int sum = 0;
		if(i != 0){
			//top
			if(map[i-1][j] == -1){
				sum++;
			}
			//top left
			if(j > 0 && map[i-1][j-1] == -1){
				sum++;
			}
			//top right
			if(j < map[i].length-1 && map[i-1][j+1] == -1){
				sum++;
			}
		}
		if(i < map.length - 1){
			//bottom
			if(map[i+1][j] == -1){
				sum++;
			}
			//bottom left
			if(j > 0 && map[i+1][j-1] == -1){
				sum++;
			}
			//bottom right
			if(j < map[i].length-1 && map[i+1][j+1] == -1){
				sum++;
			}
		}
		//left
		if(j > 0){
			if(map[i][j-1] == -1){
				sum++;
			}
		}
		//right
		if(j < map[i].length-1){
			if(map[i][j+1] == -1){
				sum++;
			}
		}
		return sum;
	}
	
	private static int sumTest(int i, int j, int[][] map) {
		int sum = 0;
		if(i != 0){
			//top
			if(map[i-1][j] == -1){
				sum++;
				System.out.println("top");
			}
			//top left
			if(j > 0 && map[i-1][j-1] == -1){
				sum++;
				System.out.println("top left");
			}
			//top right
			if(j < map[i].length-1 && map[i-1][j+1] == -1){
				sum++;
				System.out.println("top right");
			}
		}
		if(i < map.length - 1){
			//bottom
			if(map[i+1][j] == -1){
				sum++;
				System.out.println("bottom");
			}
			//bottom left
			if(j > 0 && map[i+1][j-1] == -1){
				sum++;
				System.out.println("bottom left");
			}
			//bottom right
			if(j < map[i].length-1 && map[i+1][j+1] == -1){
				sum++;
				System.out.println("bottom right");
			}
		}
		//left
		if(j > 0){
			if(map[i][j-1] == -1){
				sum++;
				System.out.println("left");
			}
		}
		//right
		if(j < map[i].length-1){
			if(map[i][j+1] == -1){
				sum++;
				System.out.println("right");
			}
		}
		return sum;
	}
	
	

	private static void printMap(int[][] map) {
		int i = 0;
		while(i<map.length){
			int j = 0;
			while(j<map[i].length){
				if(map[i][j] == 1){
					System.out.print("L");
				}else if(map[i][j] == -1){
					System.out.print("#");
				}else{
					System.out.print(".");
				}
				j++;
			}
			System.out.print("\n");
			i++;
		}
		
	}

}
