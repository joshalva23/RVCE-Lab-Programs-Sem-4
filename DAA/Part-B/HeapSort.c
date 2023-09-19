#include<stdio.h>
#include<time.h>
void heapify(int n, int h[]){
    int k, v,heap, j,i;
    for(i=n/2; i>=1; i--){
        k=i;
        v = h[k];
        heap = 0;
        while(!heap && 2*k<=n){
            j = 2*k;
            if(j<n){
                //then two children
                if(h[j] < h[j+1])
                    j = j+1;
            }
            if(v >= h[j])
                heap = 1;
            else {
                h[k] = h[j];
                k = j;
            }
        }
        h[k] = v;
    }
}

void display(int n,int h[]){
    for(int i=1;i<=n;i++)
        printf("%d  ", h[i]);
}

void heapsort(int n, int h[]){
    heapify(n,h);
    for(int i=n; i>=1; i--){
        int temp = h[i];
        h[i] = h[1];
        h[1] = temp;
        heapify(i-1,h);
    }  
}

int main(){
    int n;
    int h[100];
    clock_t start,end;
    printf("enter the number of elements: ");
    scanf("%d", &n);
    printf("enter the elements: ");
    for(int i=1; i<=n; i++)
        scanf("%d", &h[i]);
    printf("unsorted list: ");
    display(n,h);
    start = clock(); heapsort(n,h); end = clock(); 
    double total_time_taken = ((double)(end-start)*1000)/(CLOCKS_PER_SEC);
    printf("\nsorted list: ");
    display(n,h);
    printf("\nTime taken = %lfms\n",total_time_taken);
    return 0;
}