// トップダウン型マージソート（分割統治法）の実装

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef int Item;

#define key(A)        (A)                                                       // 比較キー
#define eq(A, B)      (key(A) == key(B))                                        // A == B の比較  
#define lesseq(A, B)  (key(A) <= key(B))                                        // A <= B の比較    
#define less(A, B)    (key(A) <  key(B))                                        // A < B の比較
#define exch(A, B)    do { Item t_ = (A); (A) = (B); (B) = t_; } while (0)      // A と B を交換
#define compexch(A,B) do { if (less((B),(A))) exch((A),(B)); } while (0)        // A と B を比較して必要なら交換

/* 補助配列は 1 度だけ確保して全再帰で使い回す */
static Item *aux = NULL;

/* マージ処理
   a[l..m] と a[m+1..r] をマージして a[l..r] に格納（昇順） */
static void merge(Item a[], int l, int m, int r)
{
    int i, j, k;

    // 1) 左半分 a[l..m] を aux[l..m] にそのままコピー（昇順）
    for (i = l; i <= m; i++) aux[i] = a[i];

    // 2) 右半分 a[m+1..r] を「逆順」で aux[m+1..r] にコピー（降順列を作る）
    //    例: a[m+1..r] = [5, 7, 9]（昇順）
    //        aux[m+1..r] = [9, 7, 5]（降順）
    //    これで aux は [左:昇順 | 右:降順] のビトニック配列になる。
    for (i = m + 1, j = r; i <= r; i++, j--) {
        aux[i] = a[j];
    }

    // 3) 両端から小さい方を順に a へ戻す
    //    i は左列（小→大）を左から右へ、j は右列（大→小）を右から左へ進める。
    //    右列は「右端ほど小さい」配置なので、j を減らすごとに値は増えていく。
    i = l;      // 左列の先頭（最小から）
    j = r;      // 右列の“最小側”（右端）から
    for (k = l; k <= r; k++) {
        if (lesseq(aux[i], aux[j])) {
            a[k] = aux[i++];
        } else {
            a[k] = aux[j--];
        }
    }
}

/* マージソート（トップダウン型）
   配列 a[l..r] を昇順に整列
   手順：
     1) 中央 m で分割
     2) 左右を再帰的に整列
     3) merge で結合
*/
static void merge_sort(Item a[], int l, int r)
{
    if (r <= l) return;         // 要素 0 or 1 は整列済み
    int m = (l + r) / 2;        // 中央（分割点）
    merge_sort(a, l,   m);      // 左半分を整列
    merge_sort(a, m+1, r);      // 右半分を整列
    merge(a, l, m, r);          // 2 本をマージ
}

int main(int argc, char *argv[])
{
    if (argc < 3) {                                                                     // 引数チェック
        fprintf(stderr, "Usage: %s N sw\n", argv[0]);
        fprintf(stderr, "  N : 要素の最大数（容量）\n");
        fprintf(stderr, "  sw: 1なら乱数生成, 0なら標準入力から整数を最大N個まで読む\n");
        return 1;
    }

    clock_t t1, t2;
    int i;
    int N_cap = atoi(argv[1]);    // 容量（最大件数）
    int sw    = atoi(argv[2]);

    if (N_cap <= 0) {
        fprintf(stderr, "N must be positive.\n");
        return 1;
    }

    /* 配列と補助配列を容量ぶん確保（全再帰で使い回す） */
    Item *a = (Item*)malloc((size_t)N_cap * sizeof(Item));
    if (!a) { fprintf(stderr, "malloc failed for a\n"); return 1; }

    aux = (Item*)malloc((size_t)N_cap * sizeof(Item));
    if (!aux) { fprintf(stderr, "malloc failed for aux\n"); free(a); return 1; }

    int N = 0;  // 実際の要素数

    if (sw) {
        /* 乱数で N_cap 個作る（0..999 の一様乱数） */
        srand(1);
        N = N_cap;
        for (i = 0; i < N; i++)
            a[i] = (int)(1000.0 * rand() / (RAND_MAX + 1.0));
    } else {
        /* 標準入力から最大 N_cap 個まで読む（安全） */
        while (N < N_cap && scanf("%d", &a[N]) == 1) {
            N++;
        }
        if (N == 0) {
            fprintf(stderr, "no input\n");
            free(aux); free(a);
            return 1;
        }
        if (N == N_cap) {
            fprintf(stderr, "(info) reached capacity N=%d; extra inputs (if any) are ignored.\n", N_cap);
        }
    }

    printf("Input:  ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");

    t1 = clock();
    merge_sort(a, 0, N - 1);
    t2 = clock();

    printf("Result: ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");
    printf("cpu time=%10.6f\n", (double)(t2 - t1) / CLOCKS_PER_SEC);

    free(aux);
    free(a);
    return 0;
}
