#include<stdio.h>

int strlen(char * s)
{
    int i ;
    for( i = 0; s[i] != '\0';i++);
    return i;
}

int main()
{
    char string1[] = "nonononomnomnononono";
    int n1 = strlen(string1);
    char string2[] = "mno";
    int n2 = strlen(string2);
    int i = 0;
    int count = 0;
    int flag = 1;
    while(i < n1-n2+1)
    {
        int j = 0;
        while(j < n2)
        {
            count++;
            if(string2[n2-j-1] != string1[i+n2-j-1])
                break;
            j++;
        }
        if(j == n2)
        {
            flag = 0;
            printf("Strings match at %d\n", i);
        }
        i++;
    }
    if(flag)
        printf("\nStrings don't match\n");
    printf("\nThe number of comparison is %d\n",count);

}