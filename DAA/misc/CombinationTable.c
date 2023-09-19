#include<stdio.h>
#include<stdlib.h>
#define ull unsigned long long int

ull get_combination(int n, int k, ull comb_table[n][k], int n1, int k1)
{
    if(comb_table[n1][k1])
    {
        return comb_table[n1][k1];
    }
    else if(n1 <= k1 || k1 == 0 || n1 == 0)
    {
        comb_table[n1][k1] = 1;
        return 1;
    }
    else{
        comb_table[n1][k1] = get_combination(n,k, comb_table, n1-1, k1) + get_combination(n,k, comb_table, n1-1, k1-1);
        return comb_table[n1][k1];
    }

}

int main()
{
    int n = 1000;
    int k = 900;
    int n1, k1;
    ull comb_table[n+1][k+1];
    for(int i = 0; i < n+1; i++)
        for(int j =0; j < k+1; j++)
            comb_table[i][j] = 0;
    while(1)
    {
        scanf("%d%d",&n1,&k1);
        printf("\n%llu\n",get_combination(n+1,k+1,comb_table,n1,k1));
    
    }

}