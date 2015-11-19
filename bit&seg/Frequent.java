import java.util.*;
import java.io.*;
class Frequent {
	static Data[] tree;
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		while(true) {
			int n = in.nextInt();
			if(n == 0)
				break;
			int q = in.nextInt();
			int[] a= new int[n + 1];
			for(int i = 1; i <= n; i++) {
				a[i] = in.nextInt();
			}

			tree = new Data[4*n + 4];
			init(a);
			for(int i = 0; i < q; i++) {
				int k = in.nextInt();
				int l = in.nextInt();
				out.println(query(a, k,l));
			}
		}
		out.flush();
		out.close();
	}

	static class Data {
		public int lf;
		public int mf;
		public int rf;
		public Data(int l , int r, int m) {
			this.lf = l;
			this.rf = r;
			this.mf = m;
		}
	}

	static int size(int n) {
		int r = 1;
		while(n > 0) {
			r = r<<1;
			n = n>>1;
		}
		return r<<1;
	}

	static void init(int[] a) {
		init(a, 1, 1, a.length-1);
	}

	static void init(int[] a, int node, int i, int j) {
		if(i == j) {
			tree[node] = new Data(1, 1, 1);
			return;
		}

		int mid = i + (j-i)/2;
		init(a, node<<1, i, mid);
		init(a, (node<<1) + 1, mid+1, j);
		if(a[mid] == a[mid+1]) {
			tree[node] = new Data(0, 0, 0);
			tree[node].lf = tree[node<<1].lf + (a[i] == a[mid] ? tree[(node<<1) + 1].lf :  0);
			tree[node].rf = tree[(node<<1) + 1].rf + (a[j] == a[mid+1] ? tree[node<<1].rf : 0);
			tree[node].mf = max((tree[node<<1].rf + tree[(node<<1) + 1].lf), max(tree[node<<1].mf, tree[(node<<1) + 1].mf));
		} else {
			tree[node] = new Data(tree[node<<1].lf, tree[(node<<1) + 1].rf, max(tree[node<<1].mf, tree[(node<<1) + 1].mf));
		}
	}

	static int query(int[] a, int i, int j) {
		return query(a, 1, i, j, 1, a.length - 1);
	}	

	static int query(int[] a, int node, int i, int j, int u, int v) {
		if(i == u && j == v) {
			return tree[node].mf;
		}
		int mid = u + (v-u)/2;
		if(j <= mid) {
			return query(a, node<<1, i, j, u, mid);
		}
		if(i > mid) {
			return query(a, (node<<1) + 1, i, j, mid + 1, v);
		}
		int lv = query(a, node<<1, i, mid, u, mid);
		int rv = query(a, (node<<1) + 1, mid + 1, j, mid+1, v);

		if(a[mid] == a[mid+1]) {
			int temp = min(mid-i + 1, tree[node<<1].rf) + min(tree[(node<<1) + 1].lf, j - mid);
			return max(temp, max(lv, rv));
		} else {
			return max(lv, rv);
		}
	}

	static int max(int a, int b) {
		return a > b ? a : b;
	}

	static int min(int a, int b) {
		return a > b ? b : a;
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