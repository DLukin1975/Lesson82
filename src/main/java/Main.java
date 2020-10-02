import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnknownAccountException, NotEnouthMoneyException {
        MyAccountService msa = new MyAccountService();
        Scanner input = new Scanner(System.in);
        int accountidFrom;
        int accountidTo;
        int amount;

        System.out.println("Выберите тип операции");
        System.out.println("1 - Списание со счета");
        System.out.println("2 - Запрос баланса");
        System.out.println("3 - Пополнение счета");
        System.out.println("4 - Перевод со счета на счет");
        System.out.println("Номера существующих счетов - от 0 до 9");

        switch (input.nextInt()) {
            case (1): {
                System.out.println("Введите номер счета и сумму для списания");
                accountidFrom = input.nextInt();
                amount = input.nextInt();
                msa.withdraw(accountidFrom, amount);
                break;
            }
            case (2): {
                System.out.println("Введите номер счета");
                accountidFrom = input.nextInt();
                msa.balance(accountidFrom);
                break;
            }
            case (3): {
                System.out.println("Введите номер счета и сумму для внесения");
                accountidTo = input.nextInt();
                amount = input.nextInt();
                msa.deposit(accountidTo, amount);
                break;
            }
            case (4): {
                System.out.println("Введите номер счета списания, номер счета для внесения и сумму");
                accountidFrom = input.nextInt();
                accountidTo = input.nextInt();
                amount = input.nextInt();
                msa.transfer(accountidFrom, accountidTo, amount);
                break;
            }
            default: {
                System.out.println("Выбран несуществующий тип операции");
                break;
            }
        }
        //       msa.withdraw(3,11);
    }
}



