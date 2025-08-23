import pandas as pd

class SalesProcessor:
    # コンストラクタ
    def __init__(self, input_file, output_file="sales_output.csv"):
        self.input_file = input_file
        self.output_file = output_file
        self.df = None
        self.result = None

    # CSVファイル読み込み
    def load_data(self):
        self.df = pd.read_csv(self.input_file)
    
    # pd.read_csv(): CSVファイルをDataFrameとして読み込む

    # 売上列を追加
    def calculate_sales(self):
        if self.df is None:
            raise ValueError("データが読み込まれていません。先に load_data() を実行してください。")
        self.df["売上"] = self.df["販売数［個］"] * self.df["販売価格［円/個］"]


    # 商品ごとに売り上げ合計を集計
    def group_by_product(self):
        if "売上" not in self.df.columns:
            raise ValueError("売上列がありません。先に calculate_sales() を実行してください。")
        self.result = self.df.groupby("商品")["売上"].sum().reset_index()

    # self.df.columns: DataFrameの列名一覧
    # self.df.groupby("商品")["売上"].sum(): 商品ごとに売上を合計
    # reset_index(): 集計結果をDataFrameとして整形

    # 集計結果をCSVに出力
    def save_result(self):
        if self.result is None:
            raise ValueError("集計結果がありません。先に group_by_product() を実行してください。")
        self.result.to_csv(self.output_file, index=False, encoding="utf-8-sig")
        print(f"集計結果を {self.output_file} に出力しました。")

    # 一連の処理を実行
    def run(self):
        self.load_data()
        self.calculate_sales()
        self.group_by_product()
        self.save_result()


if __name__ == "__main__":
    processor = SalesProcessor("sales.csv", "sales_output.csv")
    processor.run()
