import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DayTwo {

	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader("day2.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		Integer numAccept = 0;
		int numAcceptPT2 = 0;
		while (line != null) {
			 Scheme s = getScheme(line);
			 String pw = getPassword(line);
		     numAccept = numAccept + checkValid(pw, s);
		     numAcceptPT2 = numAcceptPT2 + checkValidPT2(pw, s);
		     try{
		    	 line = scan.nextLine();
		     }catch(Exception e){
		    	 line = null;
		     }
		}
		System.out.println(numAccept);
		System.out.println(numAcceptPT2);

	}
	
	
	
	
	private static int checkValidPT2(String pw, Scheme s) {
		try{
			if(s.letter == pw.charAt(s.min-1) ^ s.letter == pw.charAt(s.max-1)){
				return 1;
			}
			return 0;
		}catch(Exception e){
			
		}
		return 0;
	}




	private static Integer checkValid(String pw, Scheme s) {
		int i = 0;
		char c = s.letter;
		int numChars = 0;
		while(i < pw.length()){
			if(pw.charAt(i) == c){
				numChars++;
			}
			i++;
		}
		if(numChars >= s.min && numChars <= s.max){
			return 1;
		}
		
		
		
		return 0;
	}




	private static String getPassword(String line) {
		return line.substring(line.lastIndexOf(" ")+1);
	}




	private static Scheme getScheme(String line) {
		char letter = line.charAt(line.lastIndexOf(":")-1);
		int i = 0;
		int min = 0;
		int max = 0;
		int pos = 0;
		String collect = "";
		while(pos < 10){
			String newCollect = collect+ line.charAt(i);
			try{
				Integer.parseInt(newCollect);
				collect = new String(newCollect);
			}catch(Exception e){
				if(pos == 0){
					min = Integer.parseInt(collect);
					pos = 1;
					collect = "";
				}else{
					max = Integer.parseInt(collect);
					pos = 100;
				}
			}
			
			
			i++;
		}
		Scheme s = new Scheme(min, max, letter);
		return s;
	}




	private static class Scheme{
		public int min;
		public int max;
		public char letter;
		
		public Scheme(int amin, int amax, char l){
			min = amin;
			max = amax;
			letter = l;
		}
	}

}
