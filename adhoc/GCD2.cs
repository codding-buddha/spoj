using System;
using System.Linq;

class GCD2 {
	public static void Main(String[] args) {
		int tc = int.Parse(Console.ReadLine().Trim());
		var input = new string[0];
		var splitChar = new char[] {' '};
		while(tc-- > 0) {
			input = Console.ReadLine().Trim().Split(splitChar, StringSplitOptions.RemoveEmptyEntries);
			int a = 0;
			string b = null;
			if(input.Length != 2) {
				a = int.Parse(input[0]);	
				b = Console.ReadLine().Trim();
			} else {
				a = int.Parse(input[0]);
				b = input[1];
			}

			if(a != 0) {
				int bmoda = mod(b, a);
				Console.WriteLine(gcd(a, bmoda));
			}
			else {
				Console.WriteLine(b);
			}
		}
	}

	static int gcd(int a, int b) {
		if(a > b)
			gcd(b, a);
		if(a == 0)
			return b;
		if(a == 1)
			return 1;
		return gcd(b%a, a);
	}

	static int mod(string b, int a) {
		int p = 0;
		for(int i = 0; i < b.Length; i++) {
			p = ((b[i]-'0') + p*10)%a;
		}
		return p;
	}
}