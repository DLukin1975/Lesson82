
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyAccountService implements AccountService {

    public void withdraw(int accountId, int amount) throws NotEnouthMoneyException, UnknownAccountException {
        String myUpdate = "UPDATE ACCOUNTS SET amount = ? where id = ?";
        String mySelect = "SELECT * FROM ACCOUNTS where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        int oldAmount;
        try {
            try {
                connection = new MyDbConnection().myDbConnection();
                statement = connection.prepareStatement(mySelect);
                statement.setInt(1, accountId);
                ResultSet resultset = statement.executeQuery();
                if (resultset.next()) {
                    oldAmount = resultset.getInt("amount");
                } else {
                    throw new UnknownAccountException();
                }
                if (oldAmount < amount) {
                    throw new NotEnouthMoneyException();
                }
                statement = connection.prepareStatement(myUpdate);
                statement.setInt(1, oldAmount - amount);
                statement.setInt(2, accountId);
                statement.executeUpdate();
                System.out.println("Списание со счета " + accountId + " выполнено");
                System.out.println("Было средств - " + oldAmount + " Стало - " + (oldAmount - amount));
            } finally {
                statement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnknownAccountException ex) {
            ex.message();
        } catch (NotEnouthMoneyException ex2) {
            ex2.message();
        }
    }

    public void balance(int amount) throws UnknownAccountException {
        String mySelect = "SELECT * FROM ACCOUNTS where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            try {
                connection = new MyDbConnection().myDbConnection();
                statement = connection.prepareStatement(mySelect);
                statement.setInt(1, amount);
                ResultSet resultset = statement.executeQuery();
                if (resultset.next()) {
                    System.out.println("Счет - " + resultset.getInt(1));
                    System.out.println("Владелец счета - " + resultset.getString(2));
                    System.out.println("Баланс - " + resultset.getInt(+3));
                } else {
                    throw new UnknownAccountException();
                }
            } finally {
                statement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnknownAccountException ex) {
            ex.message();
        }
    }

    public void deposit(int accountId, int amount) throws NotEnouthMoneyException, UnknownAccountException {
        String myUpdate = "UPDATE ACCOUNTS SET amount = ? where id = ?";
        String mySelect = "SELECT * FROM ACCOUNTS where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        int oldAmount;
        try {
            try {
                connection = new MyDbConnection().myDbConnection();
                statement = connection.prepareStatement(mySelect);
                statement.setInt(1, accountId);
                ResultSet resultset = statement.executeQuery();
                if (resultset.next()) {
                    oldAmount = resultset.getInt("amount");
                } else {
                    throw new UnknownAccountException();
                }
                statement = connection.prepareStatement(myUpdate);
                statement.setInt(1, oldAmount + amount);
                statement.setInt(2, accountId);
                statement.executeUpdate();
                System.out.println("Пополнение счета " + accountId + " выполнено");
                System.out.println("Было средств - " + oldAmount + " Стало - " + (oldAmount + amount));
            } finally {
                statement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnknownAccountException ex) {
            ex.message();
        }
    }

    public void transfer(int accountIdFrom, int accountIdTo, int amount) throws NotEnouthMoneyException, UnknownAccountException {
        String myUpdate = "UPDATE ACCOUNTS SET amount = ? where id = ?";
        String mySelect = "SELECT * FROM ACCOUNTS where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        int oldAmountFrom;
        int oldAmountTo;
        try {
            try {
                connection = new MyDbConnection().myDbConnection();
                statement = connection.prepareStatement(mySelect);
                statement.setInt(1, accountIdFrom);
                ResultSet resultset = statement.executeQuery();
                if (resultset.next()) {
                    oldAmountFrom = resultset.getInt("amount");
                } else {
                    throw new UnknownAccountException();
                }
                statement.setInt(1, accountIdTo);
                resultset = statement.executeQuery();
                if (resultset.next()) {
                    oldAmountTo = resultset.getInt("amount");
                } else {
                    throw new UnknownAccountException();
                }
                if (oldAmountFrom < amount) {
                    throw new NotEnouthMoneyException();
                }
                statement = connection.prepareStatement(myUpdate);
                statement.setInt(1, oldAmountFrom - amount);
                statement.setInt(2, accountIdFrom);
                statement.executeUpdate();
                System.out.println("Списание со счета " + accountIdFrom + " выполнено");
                System.out.println("Было средств - " + oldAmountFrom + " Стало - " + (oldAmountFrom - amount));
                statement = connection.prepareStatement(myUpdate);
                statement.setInt(1, oldAmountTo + amount);
                statement.setInt(2, accountIdTo);
                statement.executeUpdate();
                System.out.println("Пополнение счета " + accountIdTo + " выполнено");
                System.out.println("Было средств - " + oldAmountTo + " Стало - " + (oldAmountTo + amount));
            } finally {
                statement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UnknownAccountException ex) {
            ex.message();
        } catch (NotEnouthMoneyException ex2) {
            ex2.message();
        }
    }
}
