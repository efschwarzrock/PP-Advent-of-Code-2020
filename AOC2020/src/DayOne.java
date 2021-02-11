import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class DayOne {

	public static void main(String[] args) throws IOException {
		Hashtable<Integer, Integer> years = new Hashtable<Integer, Integer>();
		String result = "";
		BufferedReader br = new BufferedReader(new FileReader("day1.txt"));
		Scanner scan = new Scanner(br);
		Integer i = scan.nextInt();
		Integer element = 0;
		while (i != null) {
			 years.put(i, i);
			 Integer j = 2020 - i;
			 if(years.contains(j)){
				 element = j;
				 scan = null;
			 }
		     
		     
		     try{
		    	 scan.nextLine();
		    	 i = scan.nextInt();
		     }catch(Exception e){
		    	 i = null;
		     }
		}
		
		System.out.println(element + ", " + (2020 - element));
		System.out.println(element*(2020- element));
		
		partTwo();
		
	}

	private static void partTwo() throws FileNotFoundException {
		Hashtable<Integer, Integer> years = new Hashtable<Integer, Integer>();
		ArrayList<Integer> yearsList = new ArrayList<Integer>();
		
		String result = "";
		BufferedReader br = new BufferedReader(new FileReader("day1.txt"));
		Scanner scan = new Scanner(br);
		Integer i = scan.nextInt();
		Integer element = 0;
		while (i != null) {
			 years.put(i, i);
			 yearsList.add(i);
		     
		     
		     try{
		    	 scan.nextLine();
		    	 i = scan.nextInt();
		     }catch(Exception e){
		    	 i = null;
		     }
		}
		int a = 1;
		Integer x = yearsList.get(0);
		boolean done = false;
		while(!done){
			int ab = a;
			while(yearsList.size()>ab && !done){
				Integer y = yearsList.get(ab);
				if(years.contains(2020-x-y)){
					done = true;
					System.out.println(x + ", " + y + ", " + (2020-x-y));
					System.out.println(x*y*(2020-x-y));
				}
				ab++;
			}
			x = yearsList.get(a);
			a++;
		}
		
	}

}
