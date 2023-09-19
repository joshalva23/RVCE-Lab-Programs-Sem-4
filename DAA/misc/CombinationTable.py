import sys
sys.setrecursionlimit(10**8)

def get_combination(n, k,comb_table,n1,k1):
    if comb_table[n1][k1]:
        return comb_table[n1][k1]
    elif (n1 <= k1 or k1 == 0 or n1 == 0):
        comb_table[n1][k1] = 1
        return 1
    else:
        comb_table[n1][k1]= comb_table[n1][n1-k1] = get_combination(n,k, comb_table, n1-1, k1) + get_combination(n,k, comb_table, n1-1, k1-1)
    return comb_table[n1][k1]



def main():
    n,k = 5000,5000
    n1,k1=0,0
    comb_table = [[0]*(k+1) for i in range(n+1)]
    print("start")
    while 1:
        n1,k1 = map(int, input().split())
        print(get_combination(n+1,k+1,comb_table,n1,k1))

main()