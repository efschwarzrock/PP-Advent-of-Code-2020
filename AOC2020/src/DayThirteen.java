
public class DayThirteen {

	public static void main(String[] args) {
		int time = 1004345;
		int[] busses = {41, 37, 379, 23, 13, 17, 29, 557, 19};
		int[] offset = {0, 35, 41, 49, 54, 58, 70, 72, 91};
		
		//int[] busses = {67,7,59,61};
		//int[] offset = {0, 1, 3, 4};
		
		int i = time;
		boolean done = false;
		int pickupTime = 0;
		int pickupBus = 0;
		while(!done){
			int j = 0;
			while(j< busses.length && !done){
				if(i%busses[j] == 0){
					done = true;
					pickupBus = busses[j];
					pickupTime = i;
				}
				j++;
			}
			i++;
		}
		
		System.out.println(pickupBus*(pickupTime-time));
		
		long jump = busses[0];
		long jumpOffset = 0;
		i = 0;
		int j = 1;
		done = false;
		while(!done){
			if((i*jump+offset[j]+jumpOffset)%busses[j] == 0){
				long prevJ = jump;
				jump = jump*busses[j];
				jumpOffset = (i*prevJ+jumpOffset) - jump;
				j++;
				i = 0;
			}
			if(j == busses.length){
				done = true;
			}
			i++;
		}
		System.out.println(jump+jumpOffset);
		

	}

}
