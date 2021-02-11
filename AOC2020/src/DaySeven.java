import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class DaySeven {
	
	public static Hashtable<String, Node> nodes = new Hashtable<String, Node>();

	
	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader("day7.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
			
		    createRule(line);
			
		    
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		
		Node n = nodes.get("shiny gold bag");
		ArrayList<String> x = getNumParents(n);
		int i = 0;
		Hashtable<String, String> names = new Hashtable<String, String>();
		while(i < x.size()){
			if(!names.containsKey(x.get(i))){
				names.put(x.get(i),x.get(i));
			}
			i++;
		}
		System.out.println(names.size());
		
		int numBags = countBags(n);
		System.out.println(numBags);
	}
	
	




	private static int countBags(Node n) {
		if(n.kids.size() == 0){
			return 0;
		}else{
			int i = 0;
			int sum = 0;
			while(i < n.kids.size()){
				sum += (countBags(n.kids.get(i).kid) + 1)*n.kids.get(i).num;
				i++;
			}
			
			return sum;
		}
	
	}






	private static ArrayList<String> getNumParents(Node n) {
		ArrayList<String> sum = new ArrayList<String>();
		int i = 0;
		while(i < n.parents.size()){
			sum.add(n.parents.get(i).name);
			sum.addAll(getNumParents(n.parents.get(i)));
			i++;
		}
		return sum;
	}

	private static void createRule(String line) {
		String name = line.substring(0, line.indexOf(" contain")-1);
		ArrayList<KidPairs> kids = new ArrayList<KidPairs>();
		if(line.indexOf("contain no other bags") == -1){
			String kidLine = line.substring(line.indexOf(" contain") + 9);
			kids = getKids(kidLine);
		}
		Node n = null;
		if(nodes.containsKey(name)){
			n = nodes.get(name);
		}else{
			n = new Node(name);
			nodes.put(name, n);
		}
		
		addKids(n, kids);
		
	}

	private static void addKids(Node n, ArrayList<KidPairs> kids) {
		int i = 0;
		while(i < kids.size()){
			Node kid = null;
			if(nodes.containsKey(kids.get(i).kid.name)){
				kid = nodes.get(kids.get(i).kid.name);
			}else{
				nodes.put(kids.get(i).kid.name, kids.get(i).kid);
				kid = kids.get(i).kid;
			}
			n.kids.add(new KidPairs(kids.get(i).num, kid));
			kid.parents.add(n);
			
			i++;
		}
		
	}

	private static ArrayList<KidPairs> getKids(String line) {
		String newLine = line;
		String num = "";
		String name = "";
		boolean done = false;
		ArrayList<KidPairs> ret = new  ArrayList<KidPairs>();
		while(!done){
			num = newLine.substring(0, newLine.indexOf(' '));
			if(newLine.indexOf(',') == -1){
				name = newLine.substring(newLine.indexOf(' ')+1, newLine.indexOf('.'));
				done = true;
			}else{
				name = newLine.substring(newLine.indexOf(' ')+1, newLine.indexOf(','));
				newLine = newLine.substring(newLine.indexOf(',') + 2);
			}
			int amount = Integer.parseInt(num);
			Node kn = null;
			if(amount == 1){
				kn = new Node(name);
			}else{
				kn = new Node(name.substring(0, name.length()-1));
			}
			KidPairs kp = new KidPairs(amount, kn);
			ret.add(kp);
		}
		return ret;
	}

	private static class Node{
		public ArrayList<Node> parents;
		public ArrayList<KidPairs> kids;
		public String name;
		public Node(String n){
			name = n;
			parents = new ArrayList<Node>();
			kids = new ArrayList<KidPairs>();
			
		}
		
		
		
	}
	
	private static class KidPairs{
		public int num;
		public Node kid;
		public KidPairs(int n, Node k){
			num = n;
			kid = k;
		}
	}

}
