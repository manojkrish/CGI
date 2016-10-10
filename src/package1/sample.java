package package1;

import java.util.Random;

public class sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(randomSeriesForThreeCharacter());
	}
	
	public static char randomSeriesForThreeCharacter() {
	    Random r = new Random();
	    char random_3_Char = (char) (48 + r.nextInt(47));
	    return random_3_Char;
	}

}
