public class Cart {
    // カート内の商品を格納する配列（上限10個）
    private Item[] items = new Item[10];
    private int count = 0; // 現在の商品の数

    // 商品をカートに追加するメソッド
    public void addItem(Item item) {
        // もし10個未満なら追加
        if (count < 10) {
            items[count] = item;
            count++;
        }
    }

    // カート内の商品情報を表示するメソッド
    public void info() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }

    // mainメソッド
    public static void main(String[] args) {
        System.out.println(Item.getNumberOfInstances());
        Cart cart = new Cart();
        Item i1 = new Item("PC", 98000);
        Item i2 = new Item("Display", 40000);
        cart.addItem(i1);
        cart.addItem(i2);
        cart.info();
        System.out.println(Item.getNumberOfInstances());
    }
}
