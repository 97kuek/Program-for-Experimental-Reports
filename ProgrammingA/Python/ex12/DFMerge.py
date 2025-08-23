import pandas as pd

class DataFrameMerger:
    # コンストラクタ
    def __init__(self, df1, df2):
        self.df1 = df1
        self.df2 = df2
        self.result = None

    # ID をキーに外部結合して 1 つの DataFrame にまとめる
    def merge_on_id(self):
        self.result = pd.merge(self.df1, self.df2, on="ID", how="outer")
        return self.result

    # pd.merge(self.df1, self.df2, on="ID", how="outer"):
    # df1 と df2 を ID 列で外部結合
    # 外部結合とは、両方の DataFrame に存在する行は結合し、片方にしか存在しない行もすべて含める結合方法

    # NaN を任意の値で埋める
    def fill_empty(self, value=""):
        if self.result is not None:
            self.result = self.result.fillna(value) # NaN を指定した値で埋める
        return self.result

    # 結果を表示
    def show(self):
        if self.result is not None:
            print(self.result)
        else:
            print("まだ結合処理が実行されていません。")


if __name__ == "__main__":
    df1 = pd.DataFrame({
        'ID': [1, 2, 3],
        'Name': ['山田', '斎藤', '加藤']
    })

    df2 = pd.DataFrame({
        'ID': [2, 3, 4],
        'Age': [23, 35, 29]
    })

    merger = DataFrameMerger(df1, df2)

    merger.merge_on_id()
    merger.fill_empty()  # NaN を空欄にする
    merger.show()
