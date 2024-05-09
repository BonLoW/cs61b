public class Dessert {
    public int flavor;
    public int price;
    private static int numDesserts = 0;
    public Dessert(int flavor, int price) {
        numDesserts ++;
        this.flavor = flavor;
        this.price = price;
    }
    public void printDessert() {
        System.out.printf("%d %d %d", this.flavor, this.price, numDesserts);
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
