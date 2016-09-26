
package zuulframework;

import java.util.HashMap; //Her importere vi HashMap - som er et "plugin" som gør vi kan bevæge os rundt tekstbaseret, ved at have nogle commands, som allerede er lavet.

public class CommandWords{
    private HashMap<String, CommandWord> validCommands;

    public CommandWords(){
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

	CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    public boolean isCommand(String aString){
        return validCommands.containsKey(aString);
    }

    public void showAll(){
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
