package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account) {
        if(account.getUsername().isBlank() || account.getPassword().length() < 5) {
            return null;
        }
        
        Account existingAccount = accountDAO.getAccountByUsername(account.getUsername());
        if(existingAccount != null){
            return null;
        }

        return accountDAO.createAccount(account);
    }

    public Account login(String username, String password) {
        Account account = accountDAO.getAccountByUsername(username);
        if(account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }
}
