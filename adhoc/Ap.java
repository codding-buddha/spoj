import java.io.*;
import java.util.*;

class Ap {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			long a3 = in.nextLong(), l3 = in.nextLong(), s = in.nextLong();
			long n = (2*s)/(a3+l3);
			long d = (l3 - a3)/(n-5);
			long a = a3 - (2*d);
			out.println(n);
			for(int i = 0; i < n; i++) {
				out.print((a+(i*d)) + (i!=n-1 ? " " : "\n"));
			}
		}
		out.flush();
		out.close();
	}
}