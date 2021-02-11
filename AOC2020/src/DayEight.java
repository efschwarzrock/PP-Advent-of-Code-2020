import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;


public class DayEight {
	
	public static int acc = 0;
	public static int pointer = 0;
	public static Hashtable<Integer, Instruction> code = new Hashtable<Integer, Instruction>();
	public static int codeLength = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		BufferedReader br = new BufferedReader(new FileReader("day8.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		   String comand = line.substring(0, 3);
		   String num = line.substring(4);
		   int i = Integer.parseInt(num);
		   code.put(pointer, new Instruction(i, comand));
		   pointer++;
		   codeLength++;
		    try{
		    	line = scan.nextLine();
		    	
		    }catch(Exception e){
		    	line = null;
		    }
		}
		runCode();
		System.out.println(acc);
		part2();
	}
	
	private static void part2() {
		int i = 0;
		while(i < codeLength){
			if(code.get(i).comand == 0){
				code.get(i).comand = 2;
				int result = runCode();
				if(result == 1){
					System.out.println("i = " + i);
					System.out.println("acc = " + acc);
					i = codeLength+2;
				}else{
					code.get(i).comand = 0;
				}
			}else if(code.get(i).comand == 2){
				code.get(i).comand = 0;
				int result = runCode();
				if(result == 1){
					System.out.println("i = " + i);
					System.out.println("acc = " + acc);
					i = codeLength+2;
				}else{
					code.get(i).comand = 2;
				}
			}
			i++;
		}
		System.out.println("done");
		
		
	}

	private static int runCode(){
		pointer = 0;
		acc = 0;
		Hashtable<Integer, Integer> allReadyRun = new Hashtable<Integer, Integer>();
		while(!allReadyRun.containsKey(pointer) && pointer < codeLength){
			allReadyRun.put(pointer, pointer);
			
			code.get(pointer).execute();
		}
		if(pointer >= codeLength){
			return 1;
		}
		return 0;
	}
	
	
	private static class Instruction{
		public int value;
		public int comand;//0 nop, 1 acc, 2 jmp
		public Instruction(int v, int c){
			value = v;
			comand = c;
		}
		public Instruction(int v, String c) {
			value = v;
			if(c.equals("nop")){
				comand = 0;
			}else if(c.equals("acc")){
				comand = 1;
			}else if(c.equals("jmp")){
				comand = 2;
			}
		}
		public void execute(){
			if(comand == 0){
				pointer++;
			}else if(comand == 1){
				acc += value;
				pointer++;
			}else if(comand == 2){
				pointer += value;
			}
		}
	}

}
