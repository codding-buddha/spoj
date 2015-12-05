import java.io.*;
import java.util.*;
import java.math.*;

class LastDigit2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			String a = in.next();
			BigInteger b = new BigInteger(in.next());
			int d = a.charAt(a.length() - 1) - '0';
			// System.out.println("A -> "  + a + " B -> " + b);
			if(a.equals("0") || b.equals(BigInteger.ZERO)) {
				out.println( a.equals("0") ? 0 : 1);
				continue;
			}

			b = b.mod(new BigInteger("4"));
			int p = Integer.parseInt(b.toString());
			p = p == 0 ? 4 : p;
			out.println(((int)Math.pow(d, p))%10);
		}
		
		out.flush();
		out.close();
	}
}