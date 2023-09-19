#include<stdio.h>
#include<stdlib.h>
#define SIZE 100
void merge(int A[],int l, int m, int r )
{
    int B[r-l+1];
    int i = l, j = m+1, k = 0;
    while( i <= m &&  j <= r )
    {
        if(A[i] > A[j])
            B[k++] = A[j++];
        else
            B[k++] = A[i++];
    }
    while(i <= m)
        B[k++] = A[i++];
    while(j <= r)
        B[k++] = A[j++];
    for(i = l; i <= r; i++)
        A[i] = B[i-l];
    return;
}
void mergesort(int arr[], int l, int r)
{
    if(l >= r)
        return;    
    int m = l + (r-l)/2;
    mergesort(arr, l , m);
    mergesort(arr, m+1, r);
    merge(arr, l , m, r);
}

int main()
{
    int arr[SIZE],n;
    printf("Enter the number of elements\n");
    scanf("%d",&n);
    printf("Enter the elements\n");
    for(int i = 0; i < n; i++)
        scanf("%d",&arr[i]);
    mergesort(arr, 0, n-1);
    printf("\n");
    for(int i = 0; i < n ; i++)
        printf("%d ",arr[i]);
    for(int i = 1; i < n; i++)
        if(arr[i-1] == arr[i])
        {
            printf("\nThe elements are not unique\n");
            exit(0);
        }
    printf("\nThe elements are unique\n");
    return 0;
}