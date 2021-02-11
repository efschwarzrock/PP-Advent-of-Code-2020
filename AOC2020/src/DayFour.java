import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DayFour {

	public static void main(String[] args) throws FileNotFoundException {
		int numValid = 0;	
		boolean byr = false;
		boolean iyr = false;
		boolean eyr = false;
		boolean hgt = false;
		boolean hcl = false;
		boolean ecl = false;
		boolean pid = false;
		
		
		
		BufferedReader br = new BufferedReader(new FileReader("day4.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    if(line.equals("")){
		    	if(byr && iyr && eyr && hgt && hcl && ecl && pid){
		    		numValid++;
		    	}
		    	byr = false;
				iyr = false;
				eyr = false;
				hgt = false;
				hcl = false;
				ecl = false;
				pid = false;
		    }else{
		    	int i = 0;
		    	boolean field = true;
		    	String FieldName = "";
		    	while(i<line.length()){
		    		if(field && line.charAt(i) != ':'){
		    			FieldName = FieldName + line.charAt(i);
		    		}else if(field){
		    			if(FieldName.equals("byr")){
		    				byr = true;
		    			}else if(FieldName.equals("iyr")){
		    				iyr = true;
		    			}else if(FieldName.equals("eyr")){
		    				eyr = true;
		    			}else if(FieldName.equals("hgt")){
		    				hgt = true;
		    			}else if(FieldName.equals("hcl")){
		    				hcl = true;
		    			}else if(FieldName.equals("ecl")){
		    				ecl = true;
		    			}else if(FieldName.equals("pid")){
		    				pid = true;
		    			}
		    			field = false;
		    			FieldName = "";
		    		}else if(!field && line.charAt(i) == ' '){
		    			field = true;
		    		}
		    		i++;
		    	}
		    }
			
			
		    try{
		    	line = scan.nextLine();
		    }catch(Exception e){
		    	if(byr && iyr && eyr && hgt && hcl && ecl && pid){
		    		numValid++;
		    	}
		    	line = null;
		    }
		}
		System.out.println(numValid);
		partTwo();
	}

	private static void partTwo() throws FileNotFoundException {
		int numValid = 0;	
		boolean byr = false;
		boolean iyr = false;
		boolean eyr = false;
		boolean hgt = false;
		boolean hcl = false;
		boolean ecl = false;
		boolean pid = false;
		
		
		
		BufferedReader br = new BufferedReader(new FileReader("day4.txt"));
		Scanner scan = new Scanner(br);
		String line = scan.nextLine();
		
		while (line != null) {
		    if(line.equals("")){
		    	if(byr && iyr && eyr && hgt && hcl && ecl && pid){
		    		numValid++;
		    	}
		    	byr = false;
				iyr = false;
				eyr = false;
				hgt = false;
				hcl = false;
				ecl = false;
				pid = false;
		    }else{
		    	int i = 0;
		    	boolean field = true;
		    	String FieldName = "";
		    	while(i<line.length()){
		    		if(field && line.charAt(i) != ':'){
		    			FieldName = FieldName + line.charAt(i);
		    		}else if(field){
		    			if(FieldName.equals("byr")){
		    				byr = validateBYR(line, i);
		    			}else if(FieldName.equals("iyr")){
		    				iyr = validateIYR(line, i);
		    			}else if(FieldName.equals("eyr")){
		    				eyr = validateEYR(line, i);
		    			}else if(FieldName.equals("hgt")){
		    				hgt = validateHGT(line, i);
		    			}else if(FieldName.equals("hcl")){
		    				hcl = validateHCL(line, i);
		    			}else if(FieldName.equals("ecl")){
		    				ecl = validateECL(line, i);
		    			}else if(FieldName.equals("pid")){
		    				pid = validatePID(line, i);
		    			}
		    			field = false;
		    			FieldName = "";
		    		}else if(!field && line.charAt(i) == ' '){
		    			field = true;
		    		}
		    		i++;
		    	}
		    }
			
			
		    try{
		    	line = scan.nextLine();
		    }catch(Exception e){
		    	if(byr && iyr && eyr && hgt && hcl && ecl && pid){
		    		numValid++;
		    	}
		    	line = null;
		    }
		}
		System.out.println(numValid);
	}

	private static boolean validatePID(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		
		try{
			int year = Integer.parseInt(data);
			if(data.length() == 9){
				return true;
			}
		}catch(Exception e){
			
		}
		
		return false;
	}

	private static boolean validateECL(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		if(data.equals("amb")){
			return true;
		}else if(data.equals("blu")){
			return true;
		}else if(data.equals("brn")){
			return true;
		}else if(data.equals("gry")){
			return true;
		}else if(data.equals("grn")){
			return true;
		}else if(data.equals("hzl")){
			return true;
		}else if(data.equals("oth")){
			return true;
		}
		
		return false;
	}

	private static boolean validateHCL(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		try{
			if(data.length() == 7 && data.charAt(0) == '#'){
				Integer.parseInt(data.substring(1), 16);
				return true;
			}
		}catch(Exception e){
			
		}
		
		return false;
	}

	private static boolean validateHGT(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		String unit = data.substring(data.length()-2);
		String num = data.substring(0, data.length()-2);
		try{
			if(unit.equals("in")){
				int h = Integer.parseInt(num);
				return h >= 59 && h <= 76;
			}else if(unit.equals("cm")){
				int h = Integer.parseInt(num);
				return h >= 150 && h <= 193;
			}
		}catch(Exception e){
			
		}
		
		
		return false;
	}

	private static boolean validateEYR(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		try{
			int year = Integer.parseInt(data);
			if(year >= 2020 && year <= 2030){
				return true;
			}
		}catch(Exception e){
			
		}
		
		return false;
	}

	private static boolean validateIYR(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		try{
			int year = Integer.parseInt(data);
			if(year >= 2010 && year <= 2020){
				return true;
			}
		}catch(Exception e){
			
		}
		
		return false;
	}

	private static boolean validateBYR(String line, int i) {
		int j = i+1;
		String data = "";
		while(j < line.length()){
			if(line.charAt(j) == ' '){
				j = line.length() + 3;
			}else{
				data = data + line.charAt(j);
			}
			j++;
		}
		
		try{
			int year = Integer.parseInt(data);
			if(year >= 1920 && year <= 2002){
				return true;
			}
		}catch(Exception e){
			
		}
		
		return false;
	}

}
