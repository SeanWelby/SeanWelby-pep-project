package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account getAccountById(int account_id){
        return accountDAO.getAccountById(account_id);
    }

    public Account getLogin(String username, String password){
        return accountDAO.getLogin(username, password);
    }

    public Account addAccount(Account account){
        Account persisAccount = accountDAO.addAccount(account);
        return persisAccount;
    }
}