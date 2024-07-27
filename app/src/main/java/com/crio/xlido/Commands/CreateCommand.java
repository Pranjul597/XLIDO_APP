package com.crio.xlido.Commands;

import java.util.List;
import com.crio.xlido.Services.UserService;
import com.crio.xlido.Entities.*;

public class CreateCommand implements ICommand{

    UserService userService;

    public void execute(List<String> tokens){
        String emailId = tokens.get(1);
        String password = tokens.get(2);
        // User user = userService.createUser(emailId, password);
        // System.out.println("USER ID: "+user.getUserId());
    }
    


}
