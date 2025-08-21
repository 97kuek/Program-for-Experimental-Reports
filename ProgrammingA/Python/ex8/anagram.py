# アナグラム判定関数
def is_anagram(s1: str, s2: str) -> bool:
    # 空白や大文字小文字の違いを無視する場合は以下で正規化
    # s1.replace("A","B")でAをBに置き換えるなどの処理も可能
    # lower()で小文字に変換
    s1 = s1.replace(" ", "").lower()
    s2 = s2.replace(" ", "").lower()
    return sorted(s1) == sorted(s2)                 # 文字をソートして比較

if __name__ == "__main__":
    s1 = input("1つ目の文字列を入力してください: ")
    s2 = input("2つ目の文字列を入力してください: ")
    print(is_anagram(s1, s2))
