import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class DayTen {

	public static void main(String[] args) throws FileNotFoundException {
		Hashtable<Integer, Integer> adapters = new Hashtable<Integer, Integer>();
		int max = 0;
		ArrayList<Integer> adaptersList = new ArrayList<Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader("day10.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    int i = Integer.parseInt(line);
		    adapters.put(i, i);
			if(i > max){
				max = i;
			}
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		
		int j = 0;
		adaptersList.add(j);
		int jump = 0;
		int numOneJump = 0;
		int numThreeJump = 0;
		int comp = max+3;
		while(j < comp){
			if(adapters.containsKey(j)){
				//System.out.println(jump);
				adaptersList.add(j);
				if(jump == 1){
					numOneJump++;
					jump = 0;
				}else if(jump == 3){
					numThreeJump++;
					jump = 0;
				}
				
			}
			jump++;
			j++;
		}
		System.out.println(numOneJump*(numThreeJump+1));
		
		//3 - 1
		//2 - 1 befor - 2		
		//1 - 2 befor - 2
		//1 - 1 befor - 2
		//345670
		//111111 = 24
		//11111 = 13
		//1111 = 7
		//111 = 4
		//11 = 2
		//1 = 1
		
		
		// 12345680123456
		// 	     *
		//Split up into groups, split at 3s, 123678 -> 123, 678
		//split at the 22s, 1235789 -> 1235, 5789
		
		//part 2
		adaptersList.add(comp);
	
		j = 1;
		long numCombos = 1;
		ArrayList<Integer> curSection = new ArrayList<Integer>();
		curSection.add(0);
		while(j < adaptersList.size()){
			curSection.add(adaptersList.get(j));
			if(adaptersList.get(j) - adaptersList.get(j-1) == 3){
				int cur = adaptersList.get(j);
				curSection.remove(curSection.size()-1);
				numCombos = numCombos*getNumCombos(curSection);
				curSection.clear();
				curSection.add(cur);
			}else if(adaptersList.get(j) - adaptersList.get(j-1) == 2 && j > 1){
				if(adaptersList.get(j-1) - adaptersList.get(j-2) == 2){
					//12357
					int cur = adaptersList.get(j);
					int prev = adaptersList.get(j-1);
					curSection.remove(curSection.size()-1);
					//1235
					numCombos = numCombos*getNumCombos(curSection);
					curSection.clear();
					curSection.add(prev);
					curSection.add(cur);
				}
			}
			j++;
		}
		System.out.println(numCombos);
		
		
	}
//(0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
	private static long getNumCombos(ArrayList<Integer> vals) {
		if(vals.size() < 3){
			return 1;
		}
		int i = 0;
		long sum = 0;
		while(i < Math.pow(2, vals.size()-2)){
			if(isValid(getVals(i, vals))){
				sum++;
			}
			i++;
		}
		return sum;
	}

	private static ArrayList<Integer> getVals(int i, ArrayList<Integer> vals) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int j = i;
		int k = 1;
		ret.add(vals.get(0));
		while(k < vals.size()-1){
			if(j%2 == 0){
				ret.add(vals.get(k));
			}
			j = j/2;
			k++;
		}
		
		ret.add(vals.get(k));
		
		return ret;
	}

	private static boolean isValid(ArrayList<Integer> vals) {
		int i = 1;
		while(i < vals.size()){
			if(vals.get(i) - vals.get(i-1) > 3){
				return false;
			}
			i++;
		}
		return true;
	}

}

/*
1
1
1
1
3
1
1
1
1
3
3
1
1
1
3
1
1
3
3
1
1
1
1
3
1
3
3
1
1
1
1
*/