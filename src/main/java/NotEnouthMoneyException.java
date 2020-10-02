

public class NotEnouthMoneyException extends Exception {
    public NotEnouthMoneyException() {
    }

    public void message() {
        System.out.println("Недостаточно средств на счете");
    }
}
