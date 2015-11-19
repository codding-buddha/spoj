import java.util.*;
import java.io.*;

class Canton {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		int[] a = new int[tc];
		int mx = 0;
		for(int i = 0; i < a.length; i++) {
			a[i] = in.nextInt();
			if(mx < a[i]) {
				mx = a[i];
			}
		}

		boolean ltr = true;
		String[] terms = new String[mx+1];
		int indx = 1;
		for(int i = 1; indx<=mx ; i++) {
			for(int j =i, k=1 ; j >= 1 && indx <= mx; j--, k++) {
				if(ltr) {
					terms[indx++] = j+"/" +k;
				} else {
					terms[indx++] = k + "/" + j;
				}
			}
			
			ltr = !ltr;
		}

		for(int i = 0; i < a.length; i++)
			out.println("TERM " + a[i] + " IS " + terms[a[i]]);

		out.flush();
		out.close();
	}
}