package Controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
    
        app.start(8080);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    
    // Register Handler
    private void postRegisterHandler(Context context) throws IOException {
        Account account = mapper.readValue(context.body(), Account.class);        

        if(account.getUsername().isEmpty() || account.getPassword().length() < 4 || accountExists(account.getUsername())){
            context.status(400).json("Invalid registration details");
            return;
        }
        
        account.setAccount_id(1);
        context.status(200).json(account);
    }
    // Helper Method to check if account exists
    private boolean accountExists(String username) {
        return false;
    }

    // Login Handler
    private void postLoginHandler(Context context) throws IOException{
        Account account = mapper.readValue(context.body(), Account.class);

        if(accountExists(account.getUsername()) || accountExists(account.getPassword())){
            context.status(401).json("Unauthorized Login");
            return;
        }

        context.status(201).json(account);

    }
}