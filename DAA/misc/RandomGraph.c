#include<stdio.h>
int main()
{
    int n = 10;
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n ;j++){
            printf("%d ",rand()%2);
            for(int k = 0; k < 10000; k++);
        }
        printf("\n");
    }
}