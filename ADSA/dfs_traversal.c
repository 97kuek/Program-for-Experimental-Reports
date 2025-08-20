#include <stdio.h>
#include <stdlib.h>
#define V 8                                     // 頂点数（グラフのサイズ）

// 隣接リスト用ノード
typedef struct node *link;
struct node {
    int v;                                      // 隣接頂点の番号
    link next;                                  // 次の隣接頂点へのポインタ
};

link adj[V];                                    // 各頂点の隣接リスト先頭ポインタ
int visited[V];                                 // 訪問済みフラグ

void traverse(int);                             // 深さ優先探索(DFS)の関数プロトタイプ宣言

// 隣接リスト用ノードを確保・初期化して返す
link NEW(int v, link next)
{
    link x = malloc(sizeof(*x));
    x->v = v;
    x->next = next;
    return x;
}

int main(void)
{
    // 隣接リストの構築
    // 1) すべての隣接リストを空に初期化
    // 2) 標準入力から「i j」のペアを読み取る限り反復
    //    - 無向グラフとみなし、jのリストにiを、iのリストにjを先頭挿入
    //    ※ 先頭挿入なので読み取り順と逆順につながるが、DFSの正しさに影響なし
    int i, j;
    link t;

    for (i = 0; i < V; i++) adj[i] = NULL;
    while (scanf("%d %d", &i, &j) == 2) {
        // 範囲チェックを厳密にするなら 0<=i,j<V を確認する
        adj[j] = NEW(i, adj[j]); // jの隣接先にiを追加
        adj[i] = NEW(j, adj[i]); // iの隣接先にjを追加（無向）
    }
    // 構築した隣接リストの表示
    // 各頂点 i について、隣接する頂点を前から順に出力
    for (i = 0; i < V; i++){                        // 各頂点 i について
        printf("Node %d is connected ", i);         // 頂点 i の隣接リストを表示
        for (t = adj[i]; t != NULL; t = t->next){   // tはiの隣接リストを走査
            printf("Node %d ", t->v);
        }
        printf("\n");
    }
    // DFSの前処理と起動
    for (i = 0; i < V; i++) visited[i] = 0;         // 訪問済みフラグを0に初期化
    traverse(0);                                    // 頂点0からDFSを開始
    return 0;
}

// 深さ優先探索(DFS)の実装
// 1) 今訪れた頂点kを「訪問済み」にして表示
// 2) kに隣接する頂点uを先頭から順に調べる
// 3) その中でまだ訪問していない頂点uがあれば、再帰呼び出しでuへ潜る
// 4) すべての隣接頂点を調べ終えたら再帰から戻る
void traverse(int k)
{
    link t;

    printf("visit %d\n", k);                        // 訪問した頂点を表示
    visited[k] = 1;                                 // 訪問済みフラグを立てる

    // 隣接頂点を順に走査
    // t = adj[k]; kの隣接リストの先頭をtにセット
    // ここでadj[k]はkの隣接リストの先頭ノードへのポインタ
    // t->vは隣接頂点の番号を表す
    // t->nextは次の隣接頂点へのポインタ
    // つまり、tはkの隣接リストのノードを順にたどるためのポインタ
    // なお、隣接リストは単方向リストなので、t->nextがNULLになるまで走査する
    for (t = adj[k]; t != NULL; t = t->next) {
        int u = t->v;
        if (!visited[u]) {                          // uが未訪問なら、再帰的にuを探索
            traverse(u);
        }
    }
}