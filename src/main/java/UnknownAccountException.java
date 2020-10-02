

public class UnknownAccountException extends Exception {
    public UnknownAccountException() {
    }

    public void message() {
        System.out.println("Счет не существует");
    }
}
