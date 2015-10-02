package de.zortax.networkmanager.command;

import java.io.IOException;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.main.Main;

public class RestartCommand
  extends Command
{
  public RestartCommand(){
	  this.name = "restart";
	  this.description = "Restarts a server";
	  this.usage = "restart <id>";
      this.permission = "gameserver.restart";

  }
  
  public void onCommand(Sender sender, String[] args)
  {
    if (args.length == 1){
    	
    	for(Gamemode c : Main.man.modes){
    		for(Gameserver c1 : c.servers){
    			if(args[0].equalsIgnoreCase(c1.getGamemode() + c1.getId())){
    				Main.log.write("Restarte " + c1.getGamemode() + c1.getId() + "!");
    				try {
						c1.restart();
						return;
					} catch (IOException e) {
						
						Main.log.write(e.getMessage());
						return;
						
					}
    			}
    		}
    	}
    	
    	Main.log.write("Gameserver konnte nicht gefunden werden. Gib \"debug\" f�r eine List aller gestarteten Server ein. Server, die nicht eingeloggt sind werden nicht ber�cksichtigt.");
    	
    }else if (args.length < 1){
    	if(sender instanceof Gameserver){
    		Gameserver gs = (Gameserver) sender;
    		try {
				gs.restart();
				return;
			} catch (IOException e) {
				
				Main.log.write(e.getMessage());
				return;
				
			}
    	}else{
    		Main.log.write("Fehlendes Argument!");
    	}
    }
    
      
  }
}
