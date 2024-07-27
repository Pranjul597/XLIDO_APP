package com.crio.xlido.Commands;

import java.util.List;

public interface ICommand {
    
    public void execute(List<String> tokens);

}
