clear; clc;
fprintf('=== マルコフ連鎖のエントロピーレート計算 ===\n\n');

%% 入力
disp('遷移確率行列 P を入力してください（各行の総和=1）');
P = input('P = ');

%% 整合性チェック
if ~ismatrix(P) || size(P,1) ~= size(P,2)
    error('P は正方行列である必要があります。');
end
P = double(P);
rowsum = sum(P,2);
if max(abs(rowsum-1)) > 1e-8
    error('各行の総和が1ではありません。確率行列を入力してください。');
end

%% 各状態のエントロピー H_i
Hi = row_entropy(P);   % n×1 ベクトル

%% 定常分布 u
u = stationary_distribution_power(P, 1e-12, 1e6);  % 行ベクトル 1×n

%% エントロピーレート
H = sum(u .* Hi.', 2);  % スカラー

%% 表示
fprintf('\n--- 結果 ---\n');
fprintf('■ 各状態 i のエントロピー H_i（単位: bit）:\n');
for i = 1:length(Hi)
    fprintf('  H_%d = %.12g\n', i, Hi(i));
end

fprintf('\n■ 定常分布 u（行ベクトル）:\n');
disp(u);

fprintf('■ エントロピーレート H = %.12g  [bit/symbol]\n', H);

%% ローカル関数

% 各状態のエントロピーを求める
function H = row_entropy(P)
mask = P > 0;                           % 行列Pの要素ごとに0より大きいかを判定
tmp = zeros(size(P));                   % Pと同じサイズのゼロ行列を作る
tmp(mask) = P(mask) .* log2(P(mask));   % P(mask)はmaskが0でない位置のみを抜き出す
H = -sum(tmp, 2);                       % 第2引数が2なので行方向に合計
end

% 定常分布を求める
function u = stationary_distribution_power(P, tol, maxiter)
n = size(P,1);
u = ones(1,n)/n;
for k = 1:maxiter
    un = u * P;
    if norm(un - u, 1) < tol
        u = un; break;
    end
    u = un;
end
u = u / sum(u);           % 正規化
u(u<0 & u>-1e-14) = 0;    % 数値誤差クリップ
u = max(u, 0);
u = u / sum(u);
end
