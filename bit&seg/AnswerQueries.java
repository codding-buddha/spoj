import java.io.*;
import java.util.*;
import static java.lang.Math.max;

class AnswerQueries {
	static Data[] tree;
	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = in.nextInt();
		int q = in.nextInt();
		tree = new Data[n*4+1];
		init(a);
		while(q-- > 0) {
			int i = in.nextInt();
			int j = in.nextInt();
			out.println(query(a, i-1, j-1).max);
		}
		
		out.flush();
		out.close();
	}

	static void init(int[] a) {
		init(a, 1, 0, a.length-1);
	}

	static void init(int[] a, int node, int i, int j) {
		if(i > j)
			return;

		if(i == j) {
			tree[node] = new Data(a[i], a[i], a[i], a[i]);
			return;
		}

		int mid = i+(j-i)/2;
		init(a, node<<1, i, mid);
		init(a, (node<<1)+1, mid+1, j);
		Data lv = tree[node<<1];
		Data rv = tree[(node<<1)+1];
		tree[node] = new Data(max(lv.ls, lv.sum + rv.ls), max(rv.rs, rv.sum + lv.rs), max(max(lv.max, rv.max), lv.rs+rv.ls), lv.sum + rv.sum);
	}

	static Data query(int[] a, int i, int j) {
		return query(a, 1, i, j, 0, a.length - 1);
	}

	static Data query(int[] a, int node, int i, int j, int u, int v) {
		if(u >= i && v <= j)
			return tree[node];

		int mid = u + (v-u)/2;
		if(j <= mid) {
			return query(a, node<<1, i, j, u, mid);
		} else if(i > mid) {
			return query(a, (node<<1)+1, i, j, mid+1, v);
		} else {
			Data lv = query(a, node<<1, i, mid, u, mid);
			Data rv = query(a, (node<<1)+1, mid+1, j, mid+1, v);
			return new Data(max(lv.ls, lv.sum + rv.ls), max(rv.rs, rv.sum + lv.rs), max(max(lv.max, rv.max), lv.rs+rv.ls), lv.sum + rv.sum);
		}
	}

	static class Data {
		public int max;
		public int sum;
		public int ls;
		public int rs;
		public Data(int l, int r, int m, int s) {
			ls = l;
			rs = r;
			max = m;
			sum = s;
		}
	}

static class Reader {
    final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
    public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
    }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
    }public String readLine() throws IOException{byte[] buf=new byte[64];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
    }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
    }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
    }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
    }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
    }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
    }public void close() throws IOException{if(din==null) return;din.close();}
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