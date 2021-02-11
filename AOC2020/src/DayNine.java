import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

public class DayNine {

	public static void main(String[] args) throws FileNotFoundException {
		Hashtable<Integer, Long> nums = new Hashtable<Integer, Long>();
		int i = 0;
		
		BufferedReader br = new BufferedReader(new FileReader("day9.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		
		while (line != null) {
		    long l = Long.parseLong(line);
		    nums.put(i, l);
			i++;
			
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		
		//part one
		long invalid = 0;
		int j = 25;
		while(j < i){
			int ki = j-25;
			long target = nums.get(j);
			boolean found = false;
			while(ki < j){
				int kj = ki + 1;
				long kiNum = nums.get(ki);
				while(kj < j){
					if(kiNum + nums.get(kj) == target){
						found = true;
						kj = j+1;
						ki = kj;
					}
					kj++;
				}
				ki++;
			}
			if(!found){
				System.out.println(target);
				invalid = target;
				j = i;
			}
			j++;			
		}
		
		
		//part 2;
		j = 0;
		while(j < i){
			long sum = nums.get(j);
			int k = j + 1;
			while(k<i){
				sum += nums.get(k);
				if(sum == invalid){
					long answer = getSum(j, k, nums);
					System.out.println(j + ", " + k + ", -> " + answer);
					k = i + 1;
					j = i + 1;
				}else if(sum > invalid){
					k = i + 1;
				}
				k++;
			}
			j++;
		}

	}

	private static long getSum(int j, int k, Hashtable<Integer, Long> nums) {
		long min = nums.get(j);
		long max = nums.get(j);
		int i = j;
		while(i <= k){
			if(nums.get(i) < min){
				min = nums.get(i);
			}
			if(nums.get(i) > max){
				max = nums.get(i);
			}
			i++;
		}
		return min + max;
	}

}
