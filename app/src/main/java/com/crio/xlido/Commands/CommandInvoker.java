package com.crio.xlido.Commands;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
    
    Map<String, ICommand> command = new HashMap<>();

    public CommandInvoker(Map<String, ICommand> command){
        this.command = command;
    }

    public void registerCommand(String type, ICommand commandType){
        command.putIfAbsent(type, commandType);
    }

    public ICommand get(String type){
        return command.get(type);
    }

    public List<String> parse(String input){
        return Arrays.asList(input.split(","));
    }

    public void invoke(String input){
        List<String> tokens = parse(input);
        if(!command.containsKey(tokens.get(0)))
            throw new RuntimeException("INVALID COMMAND");
        ICommand command = get(tokens.get(0));
        command.execute(tokens);
    }




}
