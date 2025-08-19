// クイックソートと挿入ソートを組み合わせたソートアルゴリズム
// - クイックソートで大まかに整列し、挿入ソートで細かい整列を行う
// - 小区間は挿入ソートに任せることで高速化

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef int Item;

#define key(A)        (A)                                   // 比較キー
#define less(A, B)    (key(A) < key(B))                     // A < B の比較
#define exch(A, B)    { Item t = (A); (A) = (B); (B) = t; } // A と B を交換
#define compexch(A,B) if (less((B),(A))) exch((A),(B))      // A と B を比較して必要なら交換

// insertion関数の実装
void insertion(Item a[], int l, int r)
{
    int i;
    for (i = r; i > l; i--) compexch(a[i-1], a[i]);         // a[l..r] の隣接要素を比較して交換
    for (i = l+2; i <= r; i++) {                            // a[l+2] から a[r] まで
        int j = i;
        Item v = a[i];
        while (less(v, a[j-1])) {
            a[j] = a[j-1];
            j--;
        }
        a[j] = v;
    }
}

// partition関数の実装
int partition(Item a[], int l, int r)
{
    int i = l - 1;                  // 左走査の開始位置（まず ++i して l から参照）
    int j = r;                      // 右走査の開始位置（まず --j して r-1 から参照）
    Item v = a[r];                  // ピボットを末尾にする

    for (;;) {
        while (less(a[++i], v))     // 左からピボットの数未満を通過,v以上に当たれば停止
            ;
        while (less(v, a[--j])) {   // 右からピボットの数以上を通過,v未満に当たれば停止
            if (j == l) break;      // 走査の下限を越えないようガード
        }
        if (i >= j) break;          // 走査が交差したらforループを抜け出して終了
        exch(a[i], a[j]);           // 左で見つけたv以上と右で見つけたv未満を交換
    }
    exch(a[i], a[r]);               // 停止位置iにピボットを移動
    return i;                       // ピボットの確定位置を返す
}

// quicksort関数の実装
#define M 10                        // 10 以下の小区間は挿入ソートへ任せる

void quicksort(Item a[], int l, int r)
{
    if (r - l <= M) return;         // 小区間は後で挿入ソートへ任せる
    int i = partition(a, l, r);     // ピボット位置 i を境に左右へ分割
    quicksort(a, l, i - 1);         // 左側を再帰ソート
    quicksort(a, i + 1, r);         // 右側を再帰ソート
}

// sort関数の実装
void sort(Item a[], int l, int r)
{
    quicksort(a, l, r);             // クイックソートで大まかに整列
    insertion(a, l, r);             // 挿入ソートで細かい整列を行う
}

int main(int argc, char *argv[])
{
    // 引数チェック
    if (argc < 3) {
        fprintf(stderr, "Usage: %s N sw(0|1)\n", argv[0]);
        return 1;
    }

    clock_t t1, t2;
    // atoi は引数を整数に変換する関数, argv[]はコマンドライン引数を格納する配列
    int i, N = atoi(argv[1]), sw = atoi(argv[2]);                   // N: 要素数, sw: 乱数生成フラグ
    int *a = (int*)malloc(N * sizeof(int));
    // 動的メモリ確保失敗
    if (!a) {
        fprintf(stderr, "malloc failed\n");
        return 1;
    }
    srand(1);                                                       // 乱数の初期化（固定値で再現性を持たせる）
    if (sw) {
        /* 乱数で配列を初期化（0..999） */
        for (i = 0; i < N; i++)
            a[i] = (int)(1000.0 * rand() / (RAND_MAX + 1.0));
    } else {
        /* 標準入力から読み込む（最大 N 要素） */
        for (i = 0; i < N && scanf("%d", &a[i]) == 1; i++)
            ;
        N = i; /* 実際に読めた要素数で更新 */
    }

    // 出力
    printf("Input:  ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");
    t1 = clock();
    sort(a, 0, N - 1);
    t2 = clock();
    printf("Result: ");
    for (i = 0; i < N; i++) printf("%3d ", a[i]);
    printf("\n");
    printf("cpu time=%10.6f\n", (double)(t2 - t1) / CLOCKS_PER_SEC);
    free(a);
    return 0;
}
