//One 2-D matrix to denote graph
// One column matrix to denote the visiting status
//One array to store popped elements
#include<stdio.h>
#include<error.h>
#include<stdlib.h>
#include<stdbool.h>

#define STACK_MAX_SIZE 100

typedef struct{
    int data[STACK_MAX_SIZE];
    int top;
} stack;

void push(stack* s, int item)
{
    if(s->top == STACK_MAX_SIZE)
    {
        perror("Stack overflow\n");
        exit(0);
    }
    s->data[s->top++] = item;
    return;
}

int pop(stack* s)
{
    if(s->top == 0)
    {
        return -999;
    }
    return s->data[--s->top];
}

int not_visited(bool *a, int n){
    for(int i = 0; i < n ; i++)
        if(!a[i])
            return i;
    return -1;
}

void dfs(int current, int n, bool graph[][n], bool *visited, stack* stack_popped)//, stack* s)
{
    visited[current] = true;
    int i = 0;
    for(int i = 0; i < n ; i++)
    {
        if( graph[current][i] && !visited[i])
        {
            dfs( i ,n, graph, visited, stack_popped);// s);
        }
    }
    printf("%d\t", (current+1));
    push(stack_popped,(current+1));
    return;
    
}

void traverse(int n, bool graph[][n], bool *visited, stack* stack_popped)// stack* s)
{
    int current;
    int count = 0;
    printf("Pop order:\n");
    while(1)
    {
        current = not_visited(visited, n);
        if(current == -1)
            break;
        dfs(current, n, graph, visited, stack_popped);// s);
        count++;  
    }
    printf("\n\nNumber of discontinued graphs is %d\n\n",count);
    return;
}

int main()
{
    int n,count;
    printf("Enter the number of nodes in the graph:");
    scanf("%d",&n);
    stack popped;
    popped.top = 0;
    bool graph[n][n];
    bool visited[n];
    printf("Enter the graph matrix:\n");
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            scanf("%d",(int*)&graph[i][j]);
        }
    }
    for(int i = 0; i < n; i++)
    {
        visited[i] = false;
    }
    traverse(n,graph, visited, &popped);
    printf("\nTopological Sorting:\n");
    for(int i = 0; i < n; i++)
        printf("%d\t", pop(&popped));
    return 0;
}


/*
0 1 0 1
0 0 1 1
0 0 0 1
0 0 0 0
*/