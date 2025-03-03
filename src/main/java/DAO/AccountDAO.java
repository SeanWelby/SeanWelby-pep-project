package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
    public Account createAccount(Account account){
        String sql = "INSERT INTO accounts(username,password) VALUES (?,?)";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    account.setAccount_id(rs.getInt(1));
                    return account;
                }
            }
        }catch(SQLException e){
                e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByUsername(String username) {
        String sql = "SELECT * FROM accounts WHERE username = ?";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, username);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    return new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
        }catch( SQLException e){
            e.printStackTrace();
        }
     return null;
    }
}
