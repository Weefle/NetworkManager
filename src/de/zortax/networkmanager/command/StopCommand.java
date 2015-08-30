package de.zortax.networkmanager.command;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.main.Main;

public class StopCommand extends Command {
	
	public StopCommand(){
		this.name = "stop";
		this.description = "Stopps the whole network, each gameserver of a specific gamemode or a specific gameserver.";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		
		if(args.length < 1){
			Main.log.write("Missing argument!");
			Main.log.write("  >> -all to stop the whole network");
			Main.log.write("  >> -gamemode <gamemode> to stop each gameserver of the specific gamemode");
			Main.log.write("  >> -server <server> to stop a specific server");
			return;
		}
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("-all")){
				System.exit(0);
			}
			else{
				Main.log.write("Unknown parameter or missing argument!");
				return;
			}
		}
		if(args.length > 1){
			if(args[0].equalsIgnoreCase("-gamemode")){
				for(Gamemode c : Main.man.modes){
					if(c.getName().equalsIgnoreCase(args[1])){
						for(Gameserver c1 : c.servers){
							c1.stop();
						}
						
						return;
					}
					
					Main.log.write("Couldn't find that gamemode!");
				}
			}
			
			if(args[0].equalsIgnoreCase("-server")){
				for(Gamemode c : Main.man.modes){
					for(Gameserver c1 : c.servers){
						if(args[1].equalsIgnoreCase(c1.getGamemode() + c1.getId())){
							c1.stop();
						}
					}
				}
			}
		}
		
	}

}
