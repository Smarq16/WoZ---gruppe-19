package zuulframework;


//Game class. The class that begins a new game, by creating the rooms and printing 
//out the welcome to the user.
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    public Game(){
        createRooms();
        parser = new Parser();
    }

    //The method for creating the rooms, that will be available throughout the game
    private void createRooms(){
        Room dungeonCell, hallway, trophyRoom, lab, kitchen, library,
        guardRoom, diningRoom, privateChamber, wineCellar, mainHall,
        gardenSouth, gardenNorth;
      
        //Create instances of new rooms, with descriptions
        
        //The room objects for the cellar floor
        dungeonCell = new Room("in a dungeon cell" );
        
        hallway = new Room("in a hallway");
        
        lab = new Room("in a lab");
        
        guardRoom = new Room ("in a guardroom");
        
        
        //The room objects for the ground floor
        kitchen = new Room("in a kitchen");
        
        library = new Room ("in a library");
        
        diningRoom = new Room ("in a dining room");
        
        privateChamber = new Room ("in the private chamber");
        
        trophyRoom = new Room("in a trophy room");
        
        wineCellar = new Room ("in a wine cellar"); 
        
        mainHall = new Room("in a mainhall");
        
        gardenSouth = new Room("in the southern end of the garden");
        
        gardenNorth = new Room ("in the northen end of the garden");
        
        
        //The exits for the cellar floor
        dungeonCell.setExit("hallway", hallway); //Set the exits for the room "dungeonCell" 

        hallway.setExit("cell", dungeonCell); //Sets the exits for the room "hallway"
        hallway.setExit("lab", lab);
        hallway.setExit("guardroom", guardRoom);
        
        lab.setExit("metaldoor", hallway); //Sets the exits for the room "lab"
        
        guardRoom.setExit("hallway", hallway);
        
        //Sets the exits for the ground floor
        privateChamber.setExit("trophy room", trophyRoom);
        privateChamber.setExit("library", library);
        
        library.setExit("mainentrance", mainHall);
        library.setExit("private chamber", privateChamber);
        
        mainHall.setExit("library", library);
        mainHall.setExit("dining room", diningRoom);
        
        diningRoom.setExit("main hall", mainHall);
        diningRoom.setExit("kitchen", kitchen);
        
        kitchen.setExit("garden south", gardenSouth);
        kitchen.setExit("dining room", diningRoom);
        
        gardenSouth.setExit("garden north", gardenNorth);
        gardenSouth.setExit("kitchen", kitchen);
        
        gardenNorth.setExit("garden south", gardenSouth);
        

    
       

        currentRoom = dungeonCell; //Describes the current room - the room the player begins in
    }

    //The play method. Creates a new instance of a "game" 
    public void play(){            
        printWelcome();
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    //The first message that greets the user.
    //Gives description of the first room you find yourself in.
    //Gives you the command word, for listing the available commands
    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        //If the commandword is not part of the commandword ENUM, return this message
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        //Print the words, saved in the string HELP
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        //Go to the room defined by the commandword (east, west, south, north)
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        //Terminate the game
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }
 
    //The method for printing out Help to the user
    private void printHelp(){
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    //If the user only defines "Go", and not a direction, return this message
    private void goRoom(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        
        //If the user asks to go in a direction without a "door", return this message
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    
    private boolean quit(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
