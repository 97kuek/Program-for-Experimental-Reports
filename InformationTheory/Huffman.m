function Huffman()
    clc;
    fprintf('=== Huffman符号の平均符号長・エントロピーレート計算 ===\n');

    %% 入力
    fprintf('■ 入力\n');
    syms_line  = input('記号（カンマ区切り）→ ', 's');
    probs_line = input('確率（カンマ区切り）→ ', 's');
    N          = input('N 連符号の N を入力 (1=通常) → ');

    syms = split_and_trim(syms_line);
    p    = str2double(split_and_trim(probs_line));

    %% 整合性チェック
    assert(~isempty(syms) && ~isempty(p), '入力が空です。');                   % assert(条件,メッセージ) 条件が偽ならエラー
    assert(numel(syms) == numel(p), '記号数と確率数が一致しません。');
    assert(all(~isnan(p)) && all(p>=0), '確率に不正値があります。');
    S = sum(p);
    if abs(S-1) > 1e-12
        if S == 0, error('すべての確率が0です。'); end
        p = p / S;  % 総和1に正規化
    end
    if numel(unique(syms)) ~= numel(syms)
        error('記号に重複があります。各記号は一意にしてください。');
    end
    keep = p > 0;  % 確率0は除外
    syms = syms(keep);
    p    = p(keep);
    assert(~isempty(syms), '有効な記号がありません（すべて確率0）。');
    if isempty(N) || ~isscalar(N) || N<1 || fix(N)~=N
        error('N は正の整数で指定してください。');
    end

    %% N連符号のブロック記号と確率を作る
    if N == 1
        block_syms = syms;
        block_p    = p(:);                                                  % pを列ベクトル化してblock_pへ
    else
        [grid{1:N}] = ndgrid(1:numel(syms));                                % ndgrid(1:M)をN回呼ぶ
        idx_mat = cellfun(@(x) x(:), grid, 'UniformOutput', false);
        idx_mat = [idx_mat{:}];                        % (M^N × N)
        M = size(idx_mat,1);
        block_p = ones(M,1);
        for r = 1:M
            block_p(r) = prod(p(idx_mat(r,:)));
        end
        keep = block_p > 0;                                                 % 0確率は除去
        block_p = block_p(keep);
        block_syms = strings(numel(block_p),1);
        for r = 1:numel(block_p)
            block_syms(r) = "S"+string(r);            % ダミー記号名
        end
    end

    %% ハフマン木の構築
    codes = huffman_build(cellstr(block_syms), block_p);

    %% 平均符号長
    Lavg_block = 0;
    for k = 1:numel(block_p)
        Lavg_block = Lavg_block + block_p(k) * strlength(codes(block_syms(k)));
    end

    %% エントロピー
    H_block = -sum(block_p .* log2(block_p));

    %% 出力表示
    fprintf('\n■ 結果')
    fprintf('\n平均符号長 : %.6f [bit/記号]\n', Lavg_block / N);
    fprintf('エントロピーレート : %.6f [bit/記号]\n', H_block / N);

end

%% ローカル関数
function parts = split_and_trim(line)
    parts = strsplit(string(line), ',');                                    % strsplit(string,',') カンマで区切りstring配列を返す
    parts = strtrim(parts);                                                 % 前後の空白除去
    parts = parts(parts ~= "");
end

function codes = huffman_build(syms, p)
    nodes = struct('sym', {}, 'prob', {}, 'left', {}, 'right', {});
    for k = 1:numel(syms)
        nodes(k).sym   = syms{k};
        nodes(k).prob  = p(k);
        nodes(k).left  = [];
        nodes(k).right = [];
    end
    pool = nodes;
    if numel(pool) == 1
        codes = containers.Map('KeyType','char','ValueType','char');
        codes(pool.sym) = '0';  % 1記号のみの特例
        return;
    end
    while numel(pool) > 1
        [~, idx] = sort([pool.prob], 'ascend');
        pool = pool(idx);
        a=pool(1); b=pool(2);
        newNode.sym=""; newNode.prob=a.prob+b.prob;
        newNode.left=a; newNode.right=b;
        pool=[pool(3:end), newNode]; %#ok<AGROW>
    end
    codes=containers.Map('KeyType','char','ValueType','char');
    assign_codes(pool,"",codes);
end

function assign_codes(node,prefix,codes)
    if isempty(node.left) && isempty(node.right)
        key = char(node.sym);
        if prefix==""; prefix="0"; end
        codes(key) = char(prefix);
        return;
    end
    if ~isempty(node.left)
        assign_codes(node.left,  prefix+"0", codes);
    end
    if ~isempty(node.right)
        assign_codes(node.right, prefix+"1", codes);
    end
end
