import java.io.*;
import java.util.*;
import java.math.*;

class Rectangles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int s = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 2, len = (int)Math.sqrt(i); j <= len; j++) {
				if(i%j == 0)
					s++;
			}
			s++;
		}
		System.out.println(s);
	}
}