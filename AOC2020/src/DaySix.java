import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DaySix {

	public static void main(String[] args) throws FileNotFoundException {
		int numQuestions = 0;
		String group = "";
		
		BufferedReader br = new BufferedReader(new FileReader("day6.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    if(line.equals("")){
		    	numQuestions += getNumQuestions(group);
		    	group = "";
		    }else{
		    	group = group + line;
		    }
			
			
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	numQuestions += getNumQuestions(group);
		    	group = "";
		    	line = null;
		    }
		}
		System.out.println(numQuestions);
		
		partTwo();
	}

	private static void partTwo() throws FileNotFoundException {
		int numQuestions = 0;
		String group = "";
		boolean first = true;
		
		BufferedReader br = new BufferedReader(new FileReader("day6.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    if(line.equals("")){
		    	numQuestions += group.length();
		    	group = "";
		    	first = true;
		    }else if(first){
		    	group = line;
		    	first = false;
		    }else{
		    	group = getQuestions(group, line);
		    }
			
			
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	numQuestions += group.length();
		    	group = "";
		    	line = null;
		    }
		}
		System.out.println(numQuestions);
		
	}

	private static String getQuestions(String group, String line) {
		int i = 0;
		String newGroup = "";
		while(i<group.length()){
			if(line.indexOf(group.charAt(i)) >= 0){
				newGroup = newGroup + group.charAt(i);
			}
			i++;
		}
		return newGroup;
	}

	private static int getNumQuestions(String group) {
		int a = 97;
		int count = 0;
		while(a < 123){
			if(group.indexOf(a) >= 0){
				count++;
			}
			a++;
		}
		
		return count;
	}

}
