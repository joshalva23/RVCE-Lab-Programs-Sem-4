#include <stdio.h>
#include <error.h>
#include <stdlib.h>
#include <stdbool.h>

#define QUEUE_MAX_SIZE 200
#define QUEUE_EMPTY -999

typedef struct
{
    int front;
    int rear;
    int data[QUEUE_MAX_SIZE];
} queue;

void enqueue(queue *q, int item)
{
    if ((q->rear + 1) % QUEUE_MAX_SIZE == q->front)
    {
        perror("QUEUE overflow\n");
        exit(0);
    }
    q->rear = (q->rear + 1) % QUEUE_MAX_SIZE;
    q->data[q->rear] = item;
    return;
}

int dequeue(queue *q)
{
    if (q->front == q->rear)
    {
        return QUEUE_EMPTY;
    }
    q->front = (q->front + 1) % QUEUE_MAX_SIZE;
    return q->data[q->front];
}

int not_queued(bool *a, int n)
{
    for (int i = 0; i < n; i++)
        if (!a[i])
            return i;
    return -1;
}

void bfs(int current, int n, bool graph[][n], bool *queued, queue *Q) //, stack* s)
{
    printf("%d\t", current + 1);
    for (int i = 0; i < n; i++)
    {
        if (graph[current][i] && !queued[i])
        {
            enqueue(Q, i);
            queued[i] = true;
        }
    }
    int x = dequeue(Q);
    if (x != QUEUE_EMPTY)
        bfs(x, n, graph, queued, Q);
    return;
}

void traverse(int n, bool graph[][n], bool *queued, queue *Q)
{
    int current;
    int count = 0;
    printf("Dequeue order:\n");
    while (1)
    {
        current = not_queued(queued, n);
        if (current == -1)
            break;
        queued[current] = true;
        bfs(current, n, graph, queued, Q);
        count++;
    }
    printf("\n\nNumber of graphs is %d\n\n", count);
    return;
}

int main()
{
    int n, count;
    printf("Enter the number of nodes in the graph:");
    scanf("%d", &n);
    queue Q = {QUEUE_MAX_SIZE - 1, QUEUE_MAX_SIZE - 1};
    bool graph[n][n];
    bool queued[n];
    printf("Enter the graph matrix:\n");
    for (int i = 0; i < n; i++)
    {
        queued[i] = false;
        for (int j = 0; j < n; j++)
        {
            scanf("%d",(int*)&graph[i][j]);
        }
    }
    traverse(n, graph, queued, &Q);
    return 0;
}

/*
0 1 1 0 1 0
1 0 0 0 0 1
1 0 0 1 0 0
0 0 1 0 1 0
1 0 0 1 0 0
0 1 0 0 0 0
1 2 3 5 6 4

0 1 0 1
0 0 1 1
0 0 0 1
0 0 0 0
*/
