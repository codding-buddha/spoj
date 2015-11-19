import java.io.*;
import java.util.*;

class Kquery {
	static int[] bt;
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		Comparator<Data> c1 = new Comparator<Data>() {
			@Override
			public  int compare(Data d1, Data d2) {
				return d2.v - d1.v;
			}
		};
		
		Comparator<Qdata> c2 = new Comparator<Qdata>() {
			@Override
			public  int compare(Qdata d1, Qdata d2) {
				return d2.v - d1.v;
			}
		};

		int n = in.nextInt();
		bt = new int[n+1];
		Data[] d = new Data[n];
		for(int i = 0; i < n; i++) {
			d[i] = new Data(i, in.nextInt());
		}

		int k = in.nextInt();
		int[] result = new int[k];
		Qdata[] q = new Qdata[k];
		for(int i = 0; i < k; i++) {
			q[i] = new Qdata(i, in.nextInt(), in.nextInt(), in.nextInt());
		}
		Arrays.sort(d, c1);
		Arrays.sort(q, c2);

		int p = 0;
		for(int i = 0; i < k; i++) {
			while(p < n && d[p].v > q[i].v) {
				update(d[p].i + 1, 1);
				p++;
			}
			result[q[i].indx] = range(q[i].i, q[i].j);
		}

		for(int i = 0; i < result.length; i++) {
			out.println(result[i]);
		}

		out.flush();
		out.close();
	}

	static void update(int i, int k) {
		while(i < bt.length) {
			bt[i] += k;
			i += i & (-i);
		}
	}

	static int query(int i) {
		int sum = 0;
		while(i > 0) {
			sum += bt[i];
			i -= i & (-i);
		}
		return sum;
	}

	static int range(int i, int j) {
		return query(j) - query(i-1);
	}

	static class Data {
		public int v;
		public int i;
		public Data(int indx, int val) {
			this.i = indx;
			this.v = val;
		}
	}

	static class Qdata {
		public int indx;
		public int i;
		public int j;
		public int v;
		public Qdata(int indx, int i, int j, int v) {
			this.indx = indx;
			this.i = i;
			this.j = j;
			this.v = v;
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