import random

def split_text(text, n):
    return [text[i:i+n] for i in range(0, len(text), n)]

def shuffle_blocks(blocks):
    indices = list(range(len(blocks)))
    random.shuffle(indices)
    shuffled = [blocks[i] for i in indices]
    return shuffled, indices

def lcm(a, b):
    import math
    return abs(a*b) // math.gcd(a, b)

def permutation_order(indices):
    """
    順列の位数（元に戻るまでのループ回数）を計算
    """
    visited = [False]*len(indices)
    order = 1
    for i in range(len(indices)):
        if not visited[i]:
            length = 0
            j = i
            while not visited[j]:
                visited[j] = True
                j = indices[j]
                length += 1
            if length > 0:
                order = lcm(order, length)
    return order

if __name__ == "__main__":
    text = "学問の独立を全うし 学問の活用を効し"
    blocks = split_text(text, 3)
    print("分割結果:", blocks)

    shuffled, indices = shuffle_blocks(blocks)
    print("ランダム並び替え:", shuffled)
    print("順列インデックス:", indices)

    loops = permutation_order(indices)
    print("元に戻すために必要なループ回数:", loops)
