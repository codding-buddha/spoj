import java.io.*;
import java.util.*;

class Willits {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		long n = in.nextLong();
		System.out.println(willEnd(n) ? "TAK" :  "NIE");
	}

	static boolean willEnd(long n) {
		while(n > 0) {
			if((n%2) > 0)
				return false;
			else {
				if(n == 2)
					return true;
				
				n = n >> 1;
			}

		}
		return true;
	}
}