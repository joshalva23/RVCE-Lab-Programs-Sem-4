#include <stdio.h>
#include <string.h>
#define MAX 256
int t[MAX];
int count = 1;

//Generates shift table for a pattern
void shifttable(char pat[])
{
    int i, j, m;
    m = strlen(pat);
    //Assigns string length to all ASCII characters
    for (i = 0; i < MAX; i++)
        t[i] = m;
    //Assigns shifttable values for pattern values except last char
    for (j = 0; j < m - 1; j++)
        t[pat[j]] = m - 1 - j;
}

//Horsepool String Matching
int horspool(char src[], char pat[])
{
    int i, j, k, m, n;
    n = strlen(src);
    m = strlen(pat);
    i = m - 1;
    while (i < n) //Checks for end of search
    {
        k = 0;
        //k represents the number of matches
        while ((k < m) && (pat[m - 1 - k] == src[i - k]))
            k++;
        //if k is equal to pattern length, return index of 1st char
        if (k == m)
            return (i - m + 1);
        //else adds shifttable value for last char  
        else
        {
            i = i + t[src[i]];
            count = count + 1;
        }
    }
    return -1;
}
int main()
{
    char src[100], pat[10];
    int pos;

    printf("\nEnter the main source string\n");
    scanf(" %[^\n]s",src);
    printf("\nEnter the pattern to be searched\n");
    scanf(" %[^\n]s",pat);

    shifttable(pat);
    pos = horspool(src, pat);

    if (pos >= 0)
    {
        printf("\nFound at % d position", pos + 1);
        printf("\nNumber of shifts are %d\n", count);
    }
    else
        printf("\nString match failed\n");
    return 0;
}

/*
Horspool String Matching
    Based on Space Time Tradeoff concept
    Time Complexity 
        Preprocessing O(m+charset)
        Searching     O(nm)
    Space Complexity O(charset)
    
*/
