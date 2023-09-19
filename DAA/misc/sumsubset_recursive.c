#include<stdio.h>
#include<stdlib.h>
int count=0;
int compare(const void * a, const void *b)
{
	return *(int*)a - *(int*)b;
}
void print(int k, int arr[])
{
	for(int i = 0; i < k; i++)
		printf("%d\t",arr[i]);
	printf("\n");
}
void check(int N, int index, int arr[], int subset[], int targetSum, int k)
{
	if(targetSum == 0){
		print(k+1,subset);
		count++;
		return ;
	}
	if(index == N || arr[index] > targetSum)
		return;
	int check_variable;
	
	subset[k+1] = arr[index];
	check(N, index + 1, arr, subset, targetSum - arr[index], k+1);
	check(N, index+1, arr, subset, targetSum,k);
}
int main()
{
	int size ,targetSum;
	printf("Enter the size of the set:");
	scanf("%d",&size);
	
	int arr[size],subset[size],sum=0;
	printf("Enter the elements of the set\n");
	for(int i = 0; i < size; i++){
		scanf("%d",&arr[i]);
        sum += arr[i];
    }
	printf("Enter target sum:");
	scanf("%d",&targetSum);
    printf("\n");
    if(sum < targetSum || arr[0] > targetSum || size == 0)
    {
        printf("Not possible\n");
    }
    else{
        qsort(arr, size, sizeof(int), compare);
        check(size,0, arr, subset, targetSum ,-1);
        if(count)
            printf("Total = %d\n",count);
        else
            printf("No solution found\n");
    }
}






/**/
