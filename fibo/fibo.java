package practice;

public class fibo {

	public static void main(String[] args) {
		for(int a = 0 , b = 1; a + b < 1000; a = (b - a), b = (a + b) ) {
			System.out.print(b + " ");
		}
	}

}
