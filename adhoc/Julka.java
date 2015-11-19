import java.io.*;
import java.util.*;
import java.math.*;

class Julka {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		Scanner in = new Scanner(System.in);
		int tc = 10;
		while(tc-- > 0) {
			BigInteger c = new BigInteger(in.nextLine().trim());
			BigInteger m = new BigInteger(in.nextLine().trim());
			BigInteger k = c.add(m).divide(new BigInteger("2"));
			out.println(k);
			out.println(c.subtract(k));
		}

		out.flush();
		out.close();
	}
}