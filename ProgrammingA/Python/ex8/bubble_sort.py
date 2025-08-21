import random

# バブルソート関数
def bubble_sort(arr):
    n = len(arr)                                            # arrの長さを取得
    for i in range(n-1):
        for j in range(n-1-i):                              # 後ろに大きい値が集まるので範囲を縮める
            if arr[j] > arr[j+1]:
                # 入れ替え
                arr[j], arr[j+1] = arr[j+1], arr[j]
    return arr

if __name__ == "__main__":
    n = int(input("生成する個数 n を入力してください: "))
    
    # 1～100 の範囲で n 個の乱数を生成
    numbers = [random.randint(1, 100) for _ in range(n)]
    print("生成した配列:", numbers)
    
    sorted_numbers = bubble_sort(numbers.copy())
    print("ソート後配列:", sorted_numbers)
