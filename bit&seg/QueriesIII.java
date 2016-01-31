import java.io.*;
import java.util.*;

class QueriesIII {
	static int[] a;
	static Data[] tree;
	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		OutputWriter out = new OutputWriter(System.out);
		int n = in.nextInt();
		a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}

		tree = new Data[n*4];
		n = in.nextInt();
		int j, k;
		init(1, 0, a.length-1);
		for(int i = 0; i < n; i++) {
			int t = in.nextInt();
			j = in.nextInt() - 1;
			k = in.nextInt() - 1;
			if(t == 0) {
				update(j, k+1);
			}else {
				out.println(query(j, k).m);
			}
		}
		
		out.flush();
		out.close();
	}

	static class Data {
		int m;	int lm;	int rm;	int s;
		public Data(int i) {
			m = i; lm = i; rm = i; s= i;
		}
	}

	static void init(int n, int i, int j) {
		if(i > j)
			return;

		if(i == j) {
			tree[n] = new Data(a[i]);
		} else {
			int m = i + (j-i)/2;
			init(n<<1, i, m);
			init((n<<1)+1, m+1, j);
			tree[n] = merge(new Data(Integer.MIN_VALUE), tree[n<<1], tree[(n<<1)+1]);
		}
	}

	static Data merge(Data d, Data ld, Data rd) {
		d.lm = Math.max(ld.lm, ld.s+rd.lm);
		d.rm = Math.max(rd.rm, ld.rm + rd.s);
		d.s = ld.s + rd.s;
		d.m = Math.max(ld.rm + rd.lm, Math.max(Math.max(ld.m, rd.m), Math.max(d.s, Math.max(d.lm, d.rm))));
		return d;
	}

	static Data query(int i, int j) {
		return query(1, 0, a.length - 1, i, j);
	}

	static void update(int i, int v) {
		update(1, 0, a.length -1, i, v);
	}


	static void update(int n, int i, int j, int indx, int v) {
		if(i == j && i == indx) {
			tree[n].m = v;tree[n].lm = v; tree[n].rm = v; tree[n].s = v;
		} else {
			int m = i + (j-i)/2;
			if(indx <= m)
				update(n<<1, i, m, indx, v);
			else
				update((n<<1)+1, m+1, j, indx, v);

			merge(tree[n], tree[n<<1], tree[(n<<1)+1]);
		}
	}

	static Data query(int n, int u, int v, int i, int j) {
		if(i > v || j < u) {
			return new Data(Integer.MIN_VALUE);
		}

		if(u>=i && v<=j) {
			return tree[n];
		}else {
			int m = u+ (v-u)/2;
			if(m < i) {
				return query((n<<1)+1, m+1, v, i, j);
			} else if( m >= j) {
				return query(n<<1, u, m, i, j);
			} else {
				Data lv = query(n<<1, u, m, i, m);
				Data rv = query((n<<1)+1, m+1, v, m+1, j);
				return merge(new Data(Integer.MIN_VALUE), lv, rv);
			}
		}

	}

	
	static class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    public Reader(){
        din=new DataInputStream(System.in);
        buffer=new byte[BUFFER_SIZE];
        bufferPointer=bytesRead=0;
    }
    public int nextInt() throws IOException{
        int ret=0;byte c=read();
        while(c<=' ')c=read();
        boolean neg=(c=='-');
        if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
    }
    private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
    }
    private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
    }
    public void close() throws IOException{if(din==null) return;din.close();}
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