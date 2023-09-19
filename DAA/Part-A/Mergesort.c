#include<stdio.h>
#include<stdlib.h>
#define SIZE 1000
int count;

void merge(int A[SIZE], int l, int m, int r)
{
	int i, j, k, B[SIZE];
	i = l;
	j = m+1;
	k = l;
	while(i <= m && j <= r)
	{
		if(A[i] < A[j])
		{
			B[k++] = A[i++];
			count++;
		}
		else
		{
			B[k++] = A[j++];
			count++;
		}
	}
	while( i <= m)
		B[k++] = A[i++];
	while(j <= r)
		B[k++] = A[j++];
	for(i = l; i < k; i++)
		A[i] = B[i];
}

void mergesort(int A[SIZE], int l, int r)
{
	if(l >= r)
		return;
	int m = l + (r-l)/2;
	mergesort(A,l,m);
	mergesort(A,m+1,r);
	merge(A,l,m,r);
}

int main()
{
	int A[SIZE], X[SIZE], Y[SIZE], Z[SIZE];
	int n, i, j, c1, c2, c3;
	printf("Enter the size of the array:");
	scanf("%d",&n);
	printf("Read elements \n");
	for( i = 0; i < n; i++)
		scanf("%d",&A[i]);
	mergesort(A,0,n-1);
	printf("The sorted elements are\n");
	for(i = 0; i < n; i++)
		printf("%d\t", A[i]);
	printf("\nThe basic operation executes %d times\n",count);
	printf("\nSIZE\tASC\tDESC\tRANDOM\n");
	for(i = 16; i < SIZE; i = i*2)
	{
		for(j = 0; j < i; j++)
		{
			X[j] = j;
			Y[j] = i-j-1;
			Z[j] = rand()%i;
		}
		count = 0;
		mergesort(X,0,i-1);
		c1 = count;
		count = 0;
		mergesort(Y,0,i-1);
		c2 = count;
		count = 0;
		mergesort(Z, 0, i-1);
		c3 = count;
		printf("%d\t%d\t%d\t%d\n",i,c1,c2,c3);
	}
	return 0;
}
