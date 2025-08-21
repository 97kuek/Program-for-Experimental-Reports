# 素数判定関数
def is_prime(n: int) -> bool:
    if n <= 1:
        return False
    # 2～n//2 まで試す（n/2回以下）
    # // 演算子は整数除算
    for i in range(2, n // 2 + 1):
        if n % i == 0:
            return False
    return True

if __name__ == "__main__":
    n = int(input("整数 n を入力してください: "))
    print(is_prime(n))
