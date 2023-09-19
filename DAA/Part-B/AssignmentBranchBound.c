// Program to solve Job Assignment problem
// using Branch and Bound
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>


struct Node
{
    struct Node *parent;
    int pathCost;
    int cost;
    int workerID;
    int jobID;
    bool *assigned;
};

struct Node *newNode(int N,int x, int y, bool assigned[], struct Node *parent)
{
    struct Node *node = (struct Node *)malloc(sizeof(struct Node));
    node->assigned = (bool*)malloc(sizeof(bool)*N);
    for (int j = 0; j < N; j++)
        node->assigned[j] = assigned[j];
    node->assigned[y] = true;

    node->parent = parent;
    node->workerID = x;
    node->jobID = y;

    return node;
}

int calculateCost(int N,int costMatrix[N][N], int x, int y, bool assigned[])
{
    int cost = 0;
    bool available[N];
    for (int i = 0; i < N; i++)
        available[i] = true;

    for (int i = x + 1; i < N; i++)
    {
        int min = INT_MAX, minIndex = -1;

        for (int j = 0; j < N; j++)
        {
            if (!assigned[j] && available[j] && costMatrix[i][j] < min)
            {
                minIndex = j;
                min = costMatrix[i][j];
            }
        }

        cost += min;
        available[minIndex] = false;
    }

    return cost;
}

void printAssignments(struct Node *min)
{
    if (min->parent == NULL)
        return;

    printAssignments(min->parent);
    printf("Assign Worker %c to Job %d\n", min->workerID + 'A', min->jobID + 1);
}

int findMinCost(int N,int costMatrix[N][N])
{
    struct Node *pq[N * N];
    int pqSize = 0;

    bool assigned[N];
    for(int i = 0; i < N; i++)
        assigned[i] = false;
    struct Node *root = newNode(N,-1, -1, assigned, NULL);
    root->pathCost = root->cost = 0;
    root->workerID = -1;

    pq[pqSize++] = root;

    while (pqSize > 0)
    {
        int minIndex = 0;
        for (int i = 1; i < pqSize; i++)
        {
            if (pq[i]->cost < pq[minIndex]->cost)
                minIndex = i;
        }

        struct Node *min = pq[minIndex];
        pq[minIndex] = pq[--pqSize];

        int i = min->workerID + 1;
        if (i == N)
        {
            printAssignments(min);
            return min->cost;
        }

        for (int j = 0; j < N; j++)
        {
            if (!min->assigned[j])
            {
                struct Node *child = newNode(N,i, j, min->assigned, min);
                child->pathCost = min->pathCost + costMatrix[i][j];
                child->cost = child->pathCost + calculateCost(N,costMatrix, i, j, child->assigned);

                pq[pqSize++] = child;
            }
        }
    }
}

// Display with labels for rows and columns
void display(int N,int costMatrix[N][N])
{
    printf("\nCost Matrix:\n");

    printf("\t");
    for (int i = 0; i < N; i++)
        printf("Job %d\t", i + 1);
    printf("\n");

    for (int i = 0; i < N; i++)
    {
        printf("%c\t", i + 'A');
        for (int j = 0; j < N; j++)
            printf("%d\t", costMatrix[i][j]);
        printf("\n");
    }

    printf("\n");
}

int main()
{
    int N;
    printf("Enter N\n");
    scanf("%d",&N);
    int costMatrix[N][N];
    printf("Enter Cost Matrix\n");
    for(int i = 0; i <N; i++)
        for(int j = 0; j <N; j++)
            scanf("%d",&costMatrix[i][j]);
        
    display(N,costMatrix);

    printf("\nOptimal Cost is %d\n", findMinCost(N,costMatrix));

    return 0;
}
/*
        {9, 2, 7, 8},
        {6, 4, 3, 7},
        {5, 8, 1, 8},
        {7, 6, 9, 4}};

*/
