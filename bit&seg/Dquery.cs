}
using System;
using System.Linq;
using System.Collection.Generics;

class Dquery {
	static long[] bt;
    static readonly char[] split = { ' ' };
    private const int MAX = 10000001;

    public static void Main(string[] args)
    {
        var occ = new int[MAX];
        for (var i = 0; i < MAX; i++)
        {
            occ[i] = -1;
        }

        int n = readInt();
        var num = readArr();
        bt = new long[n + 1];
        int qc = readInt();
        var q = new QData[qc];
        for (int i = 0; i < qc; i++)
        {
            var ip = readArr();
            q[i] = new QData(ip[0], ip[1], i);
        }
        Comparison<QData> cmp = (q1, q2) => q1.j.CompareTo(q2.j);
        Array.Sort(q, cmp);
        var result = new long[qc];
        var pos = 0;
        for (var i = 0; i < qc; i++)
        {
            while (pos < n && pos <= q[i].j)
            {
                if (occ[num[pos]] != -1)
                {
                    update(occ[num[pos]] + 1, -1);
                }
                update(pos + 1, 1);
                occ[num[pos]] = pos++;
            }

            result[q[i].pos] = query(q[i].j) - query(q[i].i - 1);
        }

        for (var i = 0; i < result.Length; i++)
        {
            Console.WriteLine(result[i]);
        }
    }

    class QData
    {
        public int i;
        public int j;
        public int pos;
        public QData(int i, int j, int pos)
        {
            this.i = i;
            this.j = j;
            this.pos = pos;
        }
    }
    static int readInt()
    {
        return int.Parse(Console.ReadLine().Trim());
    }

    static int[] readArr()
    {
        return Console.ReadLine().Trim().Split(split).Select(n => int.Parse(n)).ToArray();
        /*var s = Console.ReadLine().Trim().Split(new char[] {' '});
        var r = new Data[s.Length];
        for(int i = 0; i < s.Length; i++) {
            r[i] = new Data(i, int.Parse(s[i]));
        }
        return r;*/
    }

    static void update(int i, int v)
    {
        while (i < bt.Length)
        {
            bt[i] += v;
            i += (i & -i);
        }
    }

    static long query(int i)
    {
        long r = 0;
        while (i > 0)
        {
            r += bt[i];
            i -= (i & -i);
        }
        return r;
    }