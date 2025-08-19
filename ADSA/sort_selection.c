// 選択ソートの実装

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef int Item;

#define key(A)        (A)                                   // 比較キー
#define less(A, B)    (key(A) < key(B))                     // A < B の比較
#define exch(A, B)    { Item t = (A); (A) = (B); (B) = t; } // A と B を交換
#define compexch(A,B) if (less((B),(A))) exch((A),(B))      // A と B を比較して必要なら交換

// selection関数の実装
void selection(Item a[], int l, int r)
{
    int i, j;

    for (i = l; i < r; i++) {           // 先頭から順にa[i]に入るべき最小値の位置を見つける
        int min = i;                    // いまの区間での最小要素のインデックス
        for (j = i + 1; j <= r; j++) {  // a[i+1] から a[r] までを走査し最小位置を更新
            if (less(a[j], a[min])) {
                min = j;
            }
        }
        exch(a[i], a[min]);             // 見つけた最小要素 a[min] を先頭 a[i] と交換（同じ位置なら実質 no-op）
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
    int *a = (int*)malloc(N * sizeof(int));
    if (!a) { perror("malloc"); return 1; }

    srand(1);
    if (sw) {                                                   // sw == 1 の場合は乱数生成
        for (i = 0; i < N; i++)
            a[i] = (int)(1000.0 * rand() / (RAND_MAX + 1.0));   // 0..999 の一様乱数
    } else {
        for (i = 0; i < N && scanf("%d", &a[i]) == 1; i++) ;    // sw == 0 の場合は標準入力から整数を読み込む
        N = i;
    }

    printf("Input:  ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");

    t1 = clock();
    selection(a, 0, N - 1);
    t2 = clock();

    printf("Result: ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");
    printf("cpu time=%10.6f\n", (double)(t2 - t1) / CLOCKS_PER_SEC);

    free(a);
    return 0;
}
