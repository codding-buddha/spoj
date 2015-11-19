import java.io.*;
import java.util.*;

class Incseq {
	static int[][] bit;
	static int MAX = 100000;
	static int MOD = 5000000;
	public static void main(String[] args) {

		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		
		int n = in.nextInt();
		int k = in.nextInt();
		Pair[] p = new Pair[n];
		bit = new int[n+2][k+1];
		update2(1, 0, 1);
		for(int i = 2; i <= (n + 1); i++) {
			p[i-2] = new Pair(i, in.nextInt());
		}
		Arrays.sort(p);
		for(int i = 0; i < n; i++) {
			for(int j = k-1; j >= 0; j--) {
				update2(p[i].a, j+1, query2(p[i].a, j));
			}
		}

		out.println(query2(n+1, k));
		/*bit = new int[k+1][MAX];
		for(int i = 1; i <= n; i++) {
			int x = in.nextInt();
			update(1, x, 1);
			for(int j = 2; j <= k; j++) {
				update(j, x, query(j - 1, x -1));
			}
		}

		out.println(query(k, MAX-1));
		*/
		out.flush();
		out.close();
	}

	static void print() {
		for(int i = 0; i < bit.length; i++) {
			for(int j = 0; j < bit[i].length; j++) {
				System.out.print(bit[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Pair implements Comparable<Pair> {
		public int a;
		public int b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Pair other) {
			return b == other.b ? other.a - a : b - other.b;
		}

	}

	static void update2(int i, int k, int v) {
		while(i < bit.length) {
			bit[i][k] += v;
			if(bit[i][k] >= MOD)
				bit[i][k] -= MOD;
			i+= (i & -i);
		}
	}

	static int query2(int i, int k) {
		int s = 0;
		while(i > 0) {
			s += bit[i][k];

			if(s >= MOD) {
				s -= MOD;
			}
			i -= (i&-i);
		}

		return s;
	}

	static int query(int i, int j) {
		int s = 0;
		while(j > 0) {
			s += bit[i][j];
			if(s >= MOD) {
				s -= MOD;
			}
			j -=(j & -j);
		}

		return s;
	}

	static void update(int i, int j, int v) {
		while(j < MAX) {
			bit[i][j] += v;
			if(bit[i][j] >= MOD)
				bit[i][j] -= MOD;

			j += (j & -j);
		}
	}

	private static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;
 
        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }
 
        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
 
        public int nextInt()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public String nextString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }
        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }
        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public String next()
        {
            return nextString();
        }
 
        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }
 
    private static class OutputWriter
    {
        private final PrintWriter writer;
 
        public OutputWriter(OutputStream outputStream)
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }
 
        public OutputWriter(Writer writer)
        {
            this.writer = new PrintWriter(writer);
        }
 
        public void print(Object... objects)
        {
            for (int i = 0; i < objects.length; i++)
            {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }
 
        public void println(Object... objects)
        {
            print(objects);
            writer.println();
        }
 
        public void close()
        {
            writer.close();
        }
 
        public void flush()
        {
            writer.flush();
        } 
    }
}