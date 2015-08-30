package de.zortax.networkmanager.command;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.main.Main;

public class LoginCommand extends Command{
	
	public LoginCommand(){
		this.name = "login";
		this.description = "Log in of gameservers. Can't be used by console";
		this.usage = "login <gamemode> <ID> <phase>";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		
		if(sender instanceof Gameserver){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("lobby")){
					((Gameserver) sender).setLobby(true);
					((Gameserver) sender).setGamemode("lobby");
				}else{
					((Gameserver) sender).setGamemode(args[0]);
				}
				
				Main.man.addServer((Gameserver) sender);
			}
			
		}else{
			Main.log.write("This command can only be executed by gameservers");
		}
		
	}

}
