import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from pathlib import Path

class StudentAnalyzer:
    def __init__(
        self,
        input_csv: str,
        excellent_csv: str = "excellent_students.csv",
        input_encoding: str | None = None,     # 例: 'cp932' または 'utf-8-sig'
        output_encoding: str = "cp932"         # Excelで開くなら 'cp932' が安全
    ):
        self.input_csv = input_csv
        self.excellent_csv = excellent_csv
        self.input_encoding = input_encoding
        self.output_encoding = output_encoding
        self.df: pd.DataFrame | None = None
        self.subject_cols = ["数学[点]", "情報[点]", "人文[点]"]

    # ---- 文字コード対策：自動でUTF-8-SIG → CP932の順に試す ----
    def load(self) -> pd.DataFrame:
        encs = [self.input_encoding] if self.input_encoding else ["utf-8-sig", "cp932"]
        last_err = None
        for enc in encs:
            try:
                self.df = pd.read_csv(self.input_csv, encoding=enc)
                break
            except UnicodeDecodeError as e:
                last_err = e
                self.df = None
        if self.df is None:
            raise UnicodeDecodeError(
                f"CSVを読み込めませんでした。試したencoding: {encs}. 最後のエラー: {last_err}"
            )

        # 数値列が文字として読まれても計算できるように強制数値化
        for c in self.subject_cols:
            self.df[c] = pd.to_numeric(self.df[c], errors="coerce")
        return self.df

    # ---- NumPyで平均 ----
    def compute_averages_numpy(self) -> np.ndarray:
        if self.df is None:
            raise RuntimeError("データ未読込。先に load() を実行してください。")
        scores = self.df[self.subject_cols].to_numpy(dtype=float)
        return scores.mean(axis=1)

    # ---- 平均列追加 ----
    def add_average_column(self) -> pd.DataFrame:
        self.df["Average"] = self.compute_averages_numpy()
        return self.df

    # ---- 日本語フォント対策（環境にあるものを順に試す）----
    def _setup_japanese_font(self) -> None:
        plt.rcParams["axes.unicode_minus"] = False
        for font in ["Yu Gothic", "Meiryo", "MS Gothic", "Noto Sans CJK JP"]:
            try:
                plt.rcParams["font.family"] = font
                # 実際に描画してエラーが出ないか最低限の確認に使ってもよいが、ここでは設定のみ
                return
            except Exception:
                continue

    # ---- 棒グラフ ----
    def plot_average_bar(self) -> None:
        if self.df is None or "Average" not in self.df.columns:
            raise RuntimeError("Average 列がありません。先に add_average_column() を実行してください。")

        self._setup_japanese_font()
        names = self.df["名前"].astype(str).to_list()
        avgs = self.df["Average"].to_list()

        plt.figure(figsize=(8, 5))
        plt.bar(names, avgs)
        plt.xlabel("名前")
        plt.ylabel("平均点")
        plt.title("3教科 平均点")
        plt.ylim(0, 100)
        plt.grid(axis="y", linestyle="--", alpha=0.4)
        plt.tight_layout()
        plt.show()

    # ---- 80点以上抽出 ----
    def filter_excellent(self, threshold: float = 80.0) -> pd.DataFrame:
        if self.df is None or "Average" not in self.df.columns:
            raise RuntimeError("Average 列がありません。先に add_average_column() を実行してください。")
        return self.df[self.df["Average"] >= threshold].reset_index(drop=True)

    # ---- CSV保存（Excelで開くならCP932推奨）----
    def save_excellent(self, excellent_df: pd.DataFrame) -> Path:
        out_path = Path(self.excellent_csv)
        excellent_df.to_csv(out_path, index=False, encoding=self.output_encoding)
        return out_path

    # ---- 一括実行 ----
    def run(self) -> None:
        self.load()
        self.add_average_column()
        self.plot_average_bar()
        excellent = self.filter_excellent(80.0)
        saved = self.save_excellent(excellent)
        print(f"抽出結果を {saved} に保存しました。")
        print("\n=== 抽出結果（80点以上） ===")
        print(excellent)


if __name__ == "__main__":
    analyzer = StudentAnalyzer(
        input_csv="students.csv",
        excellent_csv="excellent_students.csv",
        input_encoding=None,
        output_encoding="cp932"
    )
    analyzer.run()
