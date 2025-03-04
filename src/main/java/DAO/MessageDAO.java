package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    public Message createMessage(Message message) {
        String sql = "INSERT INTO messages (posted_by, message_text, time_posted_epoch) VALUES(?,?,?)";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
                
                ps.setInt(1,message.getPosted_by());
                ps.setString(2, message.getMessage_text());
                ps.setLong(3, message.getTime_posted_epoch());

                int rows = ps.executeUpdate();

                if(rows>0){
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        message.setMessage_id(rs.getInt(1));
                        return message;
                    }
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    messages.add(new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")
                    ));
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return messages;
    }
    
    public Message getMessageByID(int messageID){
        String sql = "SELECT * FROM messages WHERE messageID = ?";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, messageID);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    return new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")
                    );
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean deleteMessage(int messageID){
        String sql = "DELETE FROM messages WHERE message_id = ?";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, messageID);

                return ps.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Message updateMessage(int message_id, String newMessage) {
        String sql = "UPDATE messages SET message_text = ? WHERE message_id = ?";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,newMessage);
                ps.setInt(2, message_id);
                
                int rows = ps.executeUpdate();

                if(rows>0){
                    return new Message(message_id,newMessage, rows);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getMessageByUser(int userID) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE posted_by = ?";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, userID);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    messages.add(new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")
                    ));
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return messages;
    }
}
