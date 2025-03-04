package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message createMessage(Message message) {
        if(message.getMessage_text().isBlank() || message.getMessage_text().length() > 255) {
            return null;
        }
        return messageDAO.createMessage(message);
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageID){
        return messageDAO.getMessageByID(messageID);
    }

    public boolean deleteMessage(int messageID){
        return messageDAO.deleteMessage(messageID);
    }

    public Message updateMessage(int messageID, String newMessage){
        if(newMessage.isBlank() || newMessage.length() > 255){
            return null;
        }
        Message message = messageDAO.getMessageByID(messageID);
        if(message == null){
            return null;
        }
        message.setMessage_text(newMessage);
        return messageDAO.updateMessage(message.getMessage_id(), newMessage);
    }
    
    public List<Message> getMessagesByUser(int accountID){
        return messageDAO.getMessageByUser(accountID);
    }
}
