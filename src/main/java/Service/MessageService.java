package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public List<Message> getAllMessagesByAccountId(int account_id){
        return messageDAO.getAllMessagesByAccountId(account_id);
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    public Message addMessage(Message message){
        if(message == null){
            return null;
        }
        Message persisMessage = messageDAO.addMessage(message);

        return persisMessage;
    }

    public Message updateMessage(int message_id, Message message){
        Message updateMessage = messageDAO.getMessageById(message_id);

        if(updateMessage == null){
            return null;
        }else{
            updateMessage.setMessage_text(message.getMessage_text());
            return updateMessage;
        }
    }

    public boolean deleteMessage(int message_id){
        return messageDAO.deleteMessage(message_id);
    }
}
