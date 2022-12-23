public class Main {
    public static void main(String[] args) {
        BonusService service = new BonusService();
        int expected = 10;
        int actual = (int) service.calculate(100, true);
        System.out.println("1. " + expected + " ==?== " + actual);
    }
}
