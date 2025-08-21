# to_binary 関数を定義し、整数を2進数に変換する
def to_binary(x: int) -> str:
    if x == 0:
        return "0"
    bits = []
    while x > 0:
        bits.append(str(x % 2))  # 余りを記録
        x //= 2                  # 商を更新
    return "".join(reversed(bits))  # 逆順に並べ替えて返す


def main():
    # 入力
    n = int(input("正の整数 n を入力してください: "))
    m = int(input("正の整数 m を入力してください: "))

    # n の2進数表示
    print("n の2進数:", to_binary(n))

    # 左シフト (n << m)
    shifted = n << m
    print(f"n を {m} ビット左シフトした結果の2進数:", to_binary(shifted))
    print(f"n を {m} ビット左シフトした結果の10進数:", shifted)


if __name__ == "__main__":
    main()
