#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// ノード構造体
typedef struct Node {
    int value;                     // 格納する値
    int order;                     // 入力順序
    struct Node *next;             // 次ノードへのポインタ
} Node;

// ノードをリスト末尾に追加する関数
static Node* append(Node **head, Node *tail, int val, int order) {
    Node *n = (Node*)malloc(sizeof(Node));
    if (!n) { perror("malloc"); exit(EXIT_FAILURE); }
    n->value = val;
    n->order = order;
    n->next  = NULL;

    if (*head == NULL) { *head = n; return n; }
    tail->next = n;
    return n;
}

// リスト全解放
static void destroy(Node *head) {
    while (head) { Node *nx = head->next; free(head); head = nx; }
}

// 入力処理
static int read_int_or_end(int *out) {
    char buf[256];
    if (!fgets(buf, sizeof(buf), stdin)) return 0;             // EOF
    if (buf[0] == '\n' || buf[0] == '\r') return 0;            // 空行
    if (buf[0] == 'q' || buf[0] == 'Q') return 0;              // q/Q

    char *endp = NULL;
    errno = 0;
    long v = strtol(buf, &endp, 10);
    if (errno) return -1;                                      // 桁あふれ等
    while (*endp==' ' || *endp=='\t') ++endp;
    if (*endp != '\n' && *endp != '\0') return -1;             // 余計な文字
    *out = (int)v;
    return 1;
}

// 二分探索用のリストの中央ノードを求める関数
// 2倍速さの違うポインタを使って中央を求める
static Node* middle(Node *start, Node *end) {
    Node *slow = start, *fast = start;                          // slowは1歩ずつ、fastは2歩ずつ進む
    while (fast != end && fast->next != end) {                  // fastがendに到達するまで
        fast = fast->next->next;                                // fastを2歩進める
        slow = slow->next;                                      // slowを1歩進める
    }
    return slow;                                                 // slowが中央ノードを指しているのでそれを返す
}

// 二分探索を行う関数
static Node* bin_search_list(Node *head, int key) {
    Node *start = head;                     // 探索開始位置（先頭）
    Node *end   = NULL;                     // 「ここまで」を表す上限（未包含）
    while (start != end) {                  // start と end が同じでない限り繰り返す
        Node *mid = middle(start, end);     // 中央ノードを求める
        if (mid->value == key) return mid;  // 中央ノードとキーが一致したら返す
        else if (key < mid->value) {        // キーが中央ノードの値より小さい場合
            end = mid;                      // 右側を捨てる → 上限を mid に
        } else {                            // キーが中央ノードの値より大きい場合
            start = mid->next;              // 左側を捨てる → 下限を mid の次に
        }
    }
    return NULL;                            // 見つからず
}

// メイン関数
int main(void) {
    Node *head = NULL, *tail = NULL;
    int count = 0;

    puts("昇順（前の値以下にならない）で整数を任意個入力してください。");
    puts("終了するには q / 空行 / EOF（Ctrl+D, WindowsはCtrl+Z）:");
    int prev_ok = 0;        // 直前の入力値があるか
    int prevVal = 0;

    while (1) {
        int x;
        int r = read_int_or_end(&x);
        if (r == 0) break;                  // 終了
        if (r < 0) { puts("整数として解釈できません。再入力してください。"); continue; }

        if (prev_ok && x < prevVal) {
            puts("非減少（昇順）にしてください。直前以上の値を再入力。");
            continue;
        }
        prev_ok = 1; prevVal = x;

        count++;
        tail = append(&head, tail, x, count);  // 末尾追加（入力順＝リスト順）
    }

    if (!head) { puts("データがありません。終了します。"); return 0; }

    printf("探索値を入力してください: ");
    int key;
    if (scanf("%d", &key) != 1) {
        puts("整数の読み取りに失敗しました。");
        destroy(head);
        return 0;
    }

    Node *hit = bin_search_list(head, key);
    if (!hit) {
        puts("探索に失敗しました（見つかりません）。");
    } else {
        printf("値 %d はリストの %d 番目のノードにあります。\n", key, hit->order);
    }

    destroy(head);
    return 0;
}
