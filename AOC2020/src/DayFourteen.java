import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class DayFourteen {
	public static Hashtable<Long, Long> memoryPt2 = new Hashtable<Long, Long>();
	public static ArrayList<Long> indexesPt2 = new ArrayList<Long>();
	
	public static void main(String[] args) throws FileNotFoundException {
		String mask = "";
		long num = 0;
		Hashtable<Long, Long> memory = new Hashtable<Long, Long>();
		ArrayList<Long> indexes = new ArrayList<Long>();
		
		
		
		BufferedReader br = new BufferedReader(new FileReader("day14.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		   if(line.charAt(1) == 'a'){
			   mask = line.substring(7);
		   }else{
			  String index = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
			  String value = line.substring(line.lastIndexOf(' ') + 1);
			  long mem = Long.parseLong(index);
			  num = Long.parseLong(value);
			  
			  //part1
			  if(memory.containsKey(mem)){
				  long finVal = maskNum(num, mask);
				  memory.replace(mem, finVal);
			  }else{
				  long finVal = maskNum(num, mask);
				  memory.put(mem, finVal);
				  indexes.add(mem);
			  }
			  
			  //part2
			  String adresses = getAdresses(mem, mask);
			  addElements(num, adresses);
		   }
			
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		
		//part1
		long sum = 0;
		int i = 0;
		while(i < indexes.size()){
			sum += memory.get(indexes.get(i));
			i++;
		}
		System.out.println(sum);
		
		//part2
		sum = 0;
		i = 0;
		while(i < indexesPt2.size()){
			sum += memoryPt2.get(indexesPt2.get(i));
			i++;
		}
		System.out.println(sum);
		
	}
   
	private static void addElements(long num, String adresses) {
		int Xs = countXs(adresses);
		long i = 0;
		while(i < (long)Math.pow(2, Xs)){
			long adress = getAdress(i, Xs, adresses);
			addElement(adress, num);
			i++;
		}
		
		
	}

	private static void addElement(long adress, long num) {
		if(memoryPt2.containsKey(adress)){
			memoryPt2.replace(adress, num);
		}else{
			memoryPt2.put(adress, num);
			indexesPt2.add(adress);
		}
		
	}

	private static long getAdress(long var, int length, String adresses) {
		int i = 0;
		int location = 0;
		String varStr = getBinaryString(var, length);
		String adress = "";
		while(i < adresses.length()){
			if(adresses.charAt(i) == 'X'){
				adress = adress + varStr.charAt(location);
				location++;
			}else{
				adress = adress + adresses.charAt(i);
			}
			i++;
		}
		long ret = Long.parseLong(adress, 2);
		return ret;
	}

	private static String getBinaryString(long varStr, int length) {
		String ret = Long.toBinaryString(varStr);
		while(length > ret.length()){
			ret = '0' + ret;
		}
		return ret;
	}

	private static int countXs(String adresses) {
		int i = 0;
		int sum = 0;
		while(i < adresses.length()){
			if(adresses.charAt(i) == 'X'){
				sum++;
			}
			i++;
		}
		return sum;
	}

	private static String getAdresses(long mem, String mask) {
		int i = 0;
		String ret = mask;
		while(i < mask.length()){
			if(mask.charAt(i) == '0'){
				long twoToThe = (long)Math.pow(2, mask.length()-i-1);
				long trunc = mem/twoToThe;
				long end = trunc%2;
				ret = ret.substring(0, i) + end + ret.substring(i+1);
			}
			i++;
		}
		return ret;
	}

	private static long maskNum(long num, String mask) {
		int i = 0;
		long ret = num;
		while(i < mask.length()){
			long twoToThe = (long)Math.pow(2, mask.length()-i-1);
			long trunc = ret/twoToThe;
			
			if(mask.charAt(i) == 'X'){
				
			}else if(mask.charAt(i) == '0'){
				if(trunc%2 == 1){
					ret = ret - twoToThe;
				}
			}else{
				if(trunc%2 == 0){					
					ret = ret + twoToThe;
				}
			}
			i++;
		}
		return ret;
	}

}
