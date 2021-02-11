import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

public class DayFive {

	public static void main(String[] args) throws FileNotFoundException {
		int max = 0;
		Hashtable<Integer, Integer> seatNums = new Hashtable<Integer, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader("day5.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    int seatNum = getNum(line);
		    if(max<seatNum){
		    	max = seatNum;
		    }
			seatNums.put(new Integer(seatNum), new Integer(seatNum));
			
			
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		System.out.println(max);

		int i = max;
		while(i > 0){
			if(seatNums.contains(i)){
				i--;
			}else{
				System.out.println(i);
				i = -10;
			}
		}
		
	}

	private static int getNum(String line) {
		int num = 0;
		int i = 0;
		while(i<line.length()){
			num = num*2;
			if(line.charAt(i) == 'B' || line.charAt(i) == 'R'){
				num++;
			}
			
			i++;
			
		}
		
		
		return num;
	}

}
