
package zuulframework;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


//The room class, describes what a room is able to do. It has a direction, a description and a neighbour(Which it exits leads to)
public class Room{
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description){
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    //A method for describing a rooms direction and adjacent rooms
    public void setExit(String direction, Room neighbor){
        exits.put(direction, neighbor);
    }
    
    //A method for getting the description of the room
    public String getShortDescription(){
        return description;
    }

    
    public String getLongDescription(){
        return "\nYou are " + description + "\n" + getExitString();
    }

    private String getExitString(){
        String returnString = "\nExits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction){
        return exits.get(direction);
    }
}

