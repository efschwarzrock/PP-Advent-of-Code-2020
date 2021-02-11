import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DayTwelve {

	public static void main(String[] args) throws FileNotFoundException {
		int north = 0;
		int east = 0;
		int direction = 1; // 1-east, 2-south, 3-west, 0-north
						   // L90 -> -1
						   // R90 -> +1
		//pt2
		int northWP = 1;
		int eastWP = 10;
		int shipNorth = 0;
		int shipEast = 0;
		
		BufferedReader br = new BufferedReader(new FileReader("day12.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
			String sNum = line.substring(1);
			int num = Integer.parseInt(sNum);
		    if(line.charAt(0) == 'L'){
		    	//pt1
		    	direction = direction-(num/90);
		    	direction = (direction+16)%4;
		    	
		    	//pt2
		    	if(num == 270){
		    		int temp = eastWP;
		    		eastWP = northWP;
		    		northWP = -temp;
		    	}else if(num == 180){
		    		eastWP = -eastWP;
		    		northWP = -northWP;
		    	}else if(num == 90){
		    		int temp = northWP;
		    		northWP = eastWP;
		    		eastWP = -temp;
		    	}
		    	
		    }else if(line.charAt(0) == 'R'){
		    	//pt1
		    	direction = direction+(num/90);
		    	direction = (direction+16)%4;
		    	
		    	//pt2
		    	if(num == 90){
		    		int temp = eastWP;
		    		eastWP = northWP;
		    		northWP = -temp;
		    	}else if(num == 180){
		    		eastWP = -eastWP;
		    		northWP = -northWP;
		    	}else if(num == 270){
		    		int temp = northWP;
		    		northWP = eastWP;
		    		eastWP = -temp;
		    	}
		    	
		    }else if(line.charAt(0) == 'N'){
		    	//pt1
		    	north += num;
		    	
		    	//pt2
		    	northWP += num;
		    	
		    }else if(line.charAt(0) == 'S'){
		    	//pt1
		    	north -= num;
		    	
		    	//pt2
		    	northWP -= num;
		    	
		    }else if(line.charAt(0) == 'E'){
		    	//pt1
		    	east += num;
		    	
		    	//pt2
		    	eastWP += num;
		    	
		    }else if(line.charAt(0) == 'W'){
		    	//pt1
		    	east -= num;
		    	
		    	//pt2
		    	eastWP -= num;
		    	
		    }else if(line.charAt(0) == 'F'){
		    	//pt1
		    	if(direction == 1){
		    		east += num;
		    	}else if(direction == 2){
		    		north -= num;
		    	}else if(direction == 3){
		    		east -= num;
		    	}else if(direction == 0){
		    		north += num;
		    	}else{
		    		System.out.println("error");
		    	}
		    	
		    	//pt2
		    	shipNorth += northWP*num;
		    	shipEast += eastWP*num;
		    	
		    }else{
		    	System.out.println("error : ||" + line + "||");
		    }
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		System.out.println(Math.abs(east)+Math.abs(north));

		System.out.println(Math.abs(shipEast)+Math.abs(shipNorth));

	}

}
