// 挿入ソートの実装

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
typedef int Item;

#define key(A)     (A)                                      // 比較キー
#define less(A,B)  (key(A) < key(B))                        // A < B の比較    
#define exch(A,B)  { Item t = (A); (A) = (B); (B) = t; }    // A と B を交換
#define compexch(A,B) if (less((B),(A))) exch((A),(B))      // A と B を比較して必要なら交換

// insertion関数の実装
void insertion(Item a[], int l, int r)
{
    int i;

    for (i = r; i > l; i--) compexch(a[i-1], a[i]); // 隣接交換で a[l] を最小にする
    for (i = l + 2; i <= r; i++) {                  // a[l+2] から順に、左側の整列済み部分へ挿入
        int j = i;                                  // j は挿入位置の候補
        Item v = a[i];                              // 挿入したい値を保持
        while (less(v, a[j-1])) {                   // v より大きい要素があれば右へシフト
            a[j] = a[j-1];
            j--;
        }
        a[j] = v;                                   // 空いた位置にvを挿入
    }
}

int main(int argc, char *argv[])
{
    if (argc < 3) {
        fprintf(stderr, "Usage: %s N sw(0|1)\n", argv[0]);
        return 1;
    }

    clock_t t1, t2;
    int i, N = atoi(argv[1]), sw = atoi(argv[2]);
    int *a = malloc(N * sizeof(int));
    if (!a) { perror("malloc"); return 1; }

    srand(1);
    if (sw) {
        for (i = 0; i < N; i++)
            a[i] = (int)(1000.0 * rand() / (RAND_MAX + 1.0));
    } else {
        for (N = 0; scanf("%d", &a[N]) == 1; N++);
    }

    printf("Input:  ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");

    t1 = clock();
    insertion(a, 0, N - 1);
    t2 = clock();

    printf("Result: ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");
    printf("cpu time=%10.6f\n", (double)(t2 - t1) / CLOCKS_PER_SEC);

    free(a);
    return 0;
}