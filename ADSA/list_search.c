#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// ノード構造体
typedef struct Node {
    int value;                              // 格納する値
    int order;                              // 入力順序（何番目に入力されたか）
    struct Node *next;                      // 次ノードへのポインタ
} Node;

// ノードをリスト末尾に追加する関数
Node* append(Node **head, Node *tail, int val, int order) {
    // 新しいノードを確保
    Node *n = (Node*)malloc(sizeof(Node));
    if (!n) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }
    n->value = val;                         // 格納する値
    n->order = order;                       // 入力順序
    n->next  = NULL;                        // 次ノードはまだないのでNULL

    if (*head == NULL) {                    // リストが空のとき
        // リストが空のとき → 先頭をこのノードに
        *head = n;                          // 先頭をこのノードに
        return n;
    } else {                                // リストが空でないとき 
        tail->next = n;                     // 現在の末尾ノードの次に新しいノードをつなぐ
        return n;
    }
}

// 線形リスト探索関数
Node* search(Node *head, int key) {
    // *p = head; 先頭ノードから探索開始
    // p != NULL; NULLになるまで繰り返す
    // p = p->next; 次のノードへ移動
    for (Node *p = head; p != NULL; p = p->next) {
        if (p->value == key) return p;      // 一致したら返す
    }
    return NULL;                            // 見つからなかった
}

// リスト全体を解放する関数
void destroy(Node *head) {
    while (head) {                          // headがNULLになるまで繰り返す
        Node *tmp = head->next;             // 次のノードを保存
        free(head);                         // 現在のノードを解放
        head = tmp;                         // headを次のノードに更新
    }
}

// 入力処理
// 戻り値: 1=整数読み取り成功, 0=終了入力, -1=エラー
int read_int_or_end(int *out) {
    char buf[256];
    if (!fgets(buf, sizeof(buf), stdin)) {  // 標準入力から1行読み取り
        // EOF（Ctrl+D/Ctrl+Z）
        return 0;
    }
    // 空行なら終了
    if (buf[0] == '\n' || buf[0] == '\r') return 0;
    // q/Q で終了
    if (buf[0] == 'q' || buf[0] == 'Q') return 0;

    // 文字列を整数に変換
    char *endp = NULL;
    errno = 0;
    long v = strtol(buf, &endp, 10);
    if (errno != 0) return -1;  // オーバーフロー等

    // 末尾に余計な文字がないかチェック
    while (*endp == ' ' || *endp == '\t') endp++;
    if (*endp != '\n' && *endp != '\0') return -1;

    *out = (int)v;
    return 1;
}

// メイン関数
int main(void) {
    Node *head = NULL, *tail = NULL;
    int count = 0; // 入力されたデータ数

    puts("整数を任意個入力してください。終了するには q / 空行 / EOF:");

    // 入力ループ
    while (1) {
        int x;
        int r = read_int_or_end(&x);
        if (r == 0) break; // 終了条件
        if (r < 0) {
            puts("整数として解釈できませんでした。再入力してください。");
            continue;
        }
        // ノードを追加
        count++;
        tail = append(&head, tail, x, count);
    }

    // 入力が0件なら終了
    if (head == NULL) {
        puts("データが1つも入力されていません。終了します。");
        return 0;
    }

    // 探索キーの入力
    printf("探索値を入力してください: ");
    int key;
    if (scanf("%d", &key) != 1) {
        puts("整数の読み取りに失敗しました。");
        destroy(head);
        return 0;
    }

    // リストを探索
    Node *hit = search(head, key);
    if (!hit) {
        puts("探索に失敗しました（見つかりません）。");
    } else {
        printf("値 %d はリストの %d 番目のノードにあります。\n", key, hit->order);
    }

    // 後処理（メモリ解放）
    destroy(head);
    return 0;
}
