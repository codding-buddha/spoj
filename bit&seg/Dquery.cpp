#include <iostream>
#include <cstdlib>
#include <algorithm>
using namespace std;
int bt[30010];
#define MAXN 1000010
#define MAXVAL 30001

void update(int i, int k){
    while(i<=MAXVAL){
        bt[i]+=k;
        i += (i & -i);
    }
}

int query(int i){
    int s = 0;
    while(i){
        s += bt[i];
        i -= (i & -i);
    }
    return s;
}

int range(int i, int j) {
	return query(j) - query(i-1);
}


struct qdata{
    long i , j , indx;
};


bool c1(const qdata &a , const qdata &b){
    return a.j < b.j;
}

int main(){
    int n;
    scanf("%d",&n);
    int d[n];
    int occ[MAXN];

    for(int i = 0; i < MAXN; i++) {
        occ[i] = -1;
    }

    for(int j = 0 ; j < n ; j++) {
        scanf("%d",&d[j]);
    }

    int qn;
    scanf("%d",&qn);
    qdata q[qn];
    for(int i = 0 ; i < qn ; i++) {
        scanf("%d%d",&q[i].i ,&q[i].j);
        q[i].indx = i;
    }

    int result[qn];
    sort(q , q+qn , c1);
    int pos = 0;

    for(int i = 0 ; i<qn ; i++) {
        while (pos < n && pos < q[i].j)
        {
            if (occ[d[pos]] != -1)
            {
                update(occ[d[pos]] + 1, -1);
            }
            update(pos + 1, 1);
            occ[d[pos]] = pos;
            pos++;
        }

        result[q[i].indx] = query(q[i].j) - query(q[i].i - 1);
    }

    for(int i = 0 ; i < qn ; i++)
        printf("%d\n",result[i]);

    return 0;
}