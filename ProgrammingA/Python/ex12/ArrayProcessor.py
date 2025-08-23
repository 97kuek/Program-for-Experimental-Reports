import numpy as np

class ArrayProcessor:

    # コンストラクタ
    def __init__(self, size=10, low=0, high=10):
        self.size = size
        self.low = low
        self.high = high
        self.arr = np.random.randint(low, high, size)

    # np.random.randint(low, high, size): 指定した範囲とサイズでランダムな整数の配列を生成
    
    # 元の配列を返す
    def get_original(self):
        return self.arr
    
    # 逆順の配列を返す
    def get_reversed(self):
        return self.arr[::-1]
    
    # スライス構文
    # 配列[開始:終了:ステップ]
    # 今回の場合、先頭から末尾まで逆順に1個ずつ取り出す
    
    # 各要素の和の配列を返す
    def get_sum(self):
        return self.arr + self.get_reversed()
    
    # 和の配列の平均値と中央値を返す
    def get_statistics(self):
        arr_sum = self.get_sum()
        return np.mean(arr_sum), np.median(arr_sum)
    
    # np.mean(): 平均値
    # np.median(): 中央値
    
    # 偶数のみの配列を返す
    def get_even_elements(self):
        arr_sum = self.get_sum()
        return arr_sum[arr_sum % 2 == 0]

if __name__ == "__main__":
    processor = ArrayProcessor(size=10, low=0, high=10)

    print("元の配列:", processor.get_original())
    print("逆順の配列:", processor.get_reversed())
    print("各要素の和の配列:", processor.get_sum())

    mean, median = processor.get_statistics()
    print("平均値:", mean)
    print("中央値:", median)

    print("偶数のみ:", processor.get_even_elements())
