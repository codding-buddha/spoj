import java.io.*;
import java.util.*;

class NumberGame {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		if(n % 10 == 0) {
			System.out.println(2);
		} else {
			System.out.println(1);
			System.out.println(n%10);
		}
	}
}