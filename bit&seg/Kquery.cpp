#include <iostream>
#include <cstdlib>
#include <algorithm>
using namespace std;
int bt[30010];
#define MAX 30001

void update(int i, int k){
    while(i<=MAX){
        bt[i]+=k;
        i += (i & -i);
    }
}

int query(int i){
    int s = 0;
    while(i){
        s += bt[i];
        i -= ( 	i & -i);
    }
    return s;
}

int range(int i, int j) {
	return query(j) - query(i-1);
}


struct data{
    int v , i;
};

struct qdata{
    int i , j , v , indx;
};

bool c1(const data &a , const data &b){
    return a.v > b.v;
}

bool c2(const qdata &a , const qdata &b){
    return a.v > b.v;
}

int main(){
    int n;
    scanf("%d",&n);
    data d[n];
    for(int j = 0 ; j < n ; j++) {
        scanf("%d",&d[j].v);
        d[j].i = j;
    }

    sort(d , d+n , c1);
    int qn;
    scanf("%d",&qn);
    qdata q[qn];
    for(int i = 0 ; i < qn ; i++) {
        scanf("%d%d%d",&q[i].i ,&q[i].j , &q[i].v);
        q[i].indx = i;
    }

    int result[qn];
    sort(q , q+qn , c2);
    int pos = 0;

    for(int i = 0 ; i<qn ; i++){
        while(pos < n && d[pos].v > q[i].v){
            update(d[pos].i + 1, 1);
            pos++;
        }
        result[q[i].indx] = range(q[i].i, q[i].j);
    }

    for(int i = 0 ; i < qn ; i++)
        printf("%d\n",result[i]);

    return 0;
}