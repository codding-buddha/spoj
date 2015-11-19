import java.io.*;
import java.util.*;

class CandyI {
	public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int a = in.nextInt();
			long b = in.nextLong();
			if(a == 0 || b == 0) {
				if(b == 0) {
					out.println(1);
				}else {
					out.println(0);
				}
			} else {
				b %= 4;
				if(b == 0)
					b = 4;
				out.println(((int)Math.pow(a, b) % 10));
			}			
		}

		out.flush();
		out.close();	
	}
}