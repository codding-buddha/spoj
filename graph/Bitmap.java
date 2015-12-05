import java.io.*;
import java.util.*;

class Bitmap {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int m = in.nextInt();
			int n = in.nextInt();
			boolean[][] w = new boolean[m][n];
			int[][] map = new int[m][n];
			boolean[][] visited = new boolean[m][n];
			Queue<Pair> q = new LinkedList<Pair>();
			for(int i = 0; i < m; i++) {
				String l = in.nextString();
				for(int j = 0, len = l.length(); j < len; j++) {
					w[i][j] = (l.charAt(j) - '0') == 0 ? false: true;
					map[i][j] = Integer.MAX_VALUE;
					if(w[i][j]) {
						q.add(new Pair(i, j));
						map[i][j] = 0;
					}

				}
			}

			while(q.size() > 0) {
				Pair p = q.poll();
				int i = p.i;
				int j = p.j;
				if(visited[i][j])
					continue;
				visited[i][j] = true;

				// out.println(" I = " + i + " J = " + j);

				if(i-1 >= 0 && !w[i-1][j]) {
					// out.println(String.format("map[%d][%d] = %d, map[%d][%d] = %d", i-1, j, map[i-1][j], i, j, map[i][j]));
					map[i-1][j] = min(map[i-1][j], map[i][j] + 1);
					q.add(new Pair(i-1, j));
				}

				if(i+1 < m && !w[i+1][j]) {
					// out.println(String.format("map[%d][%d] = %d, map[%d][%d] = %d", i+1, j, map[i+1][j], i, j, map[i][j]));
					map[i+1][j] = min(map[i+1][j], map[i][j] + 1);
					q.add(new Pair(i+1, j));
				}


				if(j-1 >= 0 && !w[i][j-1]) {
					// out.println(String.format("map[%d][%d] = %d, map[%d][%d] = %d", i, j-1, map[i][j-1], i, j, map[i][j]));
					map[i][j-1] = min(map[i][j-1], map[i][j] + 1);
					q.add(new Pair(i, j-1));
				}

				if(j+1 < n && !w[i][j+1]) {
					// out.println(String.format("map[%d][%d] = %d, map[%d][%d] = %d", i, j+1, map[i][j+1], i, j, map[i][j]));
					map[i][j+1] = min(map[i][j+1], map[i][j] + 1);
					q.add(new Pair(i, j+1));
				}
			}

			for(int i = 0; i < m; i++) {
				for(int j = 0; j < n; j++) {
					out.print((j > 0 ? " " : "") + map[i][j]);
				}
				out.println();
			}

		}

		out.flush();
		out.close();
	}

	static class Pair implements Comparable<Pair> {
		public int i;
		public int j;
		public Pair(int a, int b) {
			i = a;
			j = b;
		}
		@Override
		public int compareTo(Pair p) {
			if(this.i < p.i)
				return this.i - p.i;
			if(this.j < p.j)
				return this.j - p.j;

			return 0;
		}
	}
	static int min(Integer... numbers) {
		int min = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			if(numbers[i] < min)
				min = numbers[i];
		}
		return min;
	}

	static <T> void print(T[] items) {
		if(items == null)
			return;

		for(int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}

	static <T extends Comparable<? super T>> T[] nextPermutation(T[] items) {
		int first = -1;
		for(int i = items.length - 2; i >= 0; i--) {
			if(items[i].compareTo(items[i+1]) < 0) {
				first = i;
				break;
			}
		}

		if(first < 0)
		return null;

		for(int j = items.length - 1; j > first; j--) {
			if(items[j].compareTo(items[first]) > 0) {
				swap(items, first, j);
				break;
			}
		}

		for(int i = first + 1, j = items.length - 1; i <= j; i++, j--) {
			swap(items, i, j);
		}

		return items;
	}

	static void swap(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	static int setBitCount(int n) {
		int count = 0;
		while(n != 0) {
			n = n & (n-1);
			count++;
		}

		return count;
	}

	static class InputReader
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

	static class OutputWriter
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