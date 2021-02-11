import java.util.Hashtable;

public class DayFifteen {

	public static void main(String[] args) {
		Hashtable<Integer, Integer> elements = new Hashtable<Integer, Integer>();
				//num     last turn said
		elements.put(20, 1);
		elements.put(9, 2);
		elements.put(11, 3);
		elements.put(0, 4);
		elements.put(1, 5);
		int turn = 6;
		int prevSaid = 2;
		/*
		elements.put(2, 1);
		elements.put(1, 2);
		int turn = 3;
		int prevSaid = 3;
		*/
		while(turn < 30000020){
			if(elements.containsKey(prevSaid)){
				int eleTurn = elements.get(prevSaid);
				elements.replace(prevSaid, turn);
				prevSaid = turn - eleTurn;
			}else{
				elements.put(prevSaid, turn);
				prevSaid = 0;
			}
			if(turn+1 == 2020){
				System.out.println(prevSaid + " - " + (turn));
			}
			if(turn+1 == 30000000){
				
				System.out.println(prevSaid + " - " + (turn));
			}
			turn++;
		}
		
		

	}
	
	

}
