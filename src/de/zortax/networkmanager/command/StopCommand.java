package de.zortax.networkmanager.command;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.main.Main;

public class StopCommand extends Command {
	
	public StopCommand(){
		this.name = "stop";
		this.description = "Stoppt das gesamte Netzwerk, alle Server eines Spielmodus oder einen einzelnen Server.";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		
		if(args.length < 1){
			Main.log.write("Fehlendes Argument!");
			Main.log.write("  >> -all um das Netzwerk zu stoppen");
			Main.log.write("  >> -gamemode <gamemode> um alle server eines Spielmodus zu stoppen");
			Main.log.write("  >> -server <server> um einen Server zu stoppen");
			return;
		}
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("-all")){
				System.exit(0);
			}
			else{
				Main.log.write("Unbekannter Parameter oder fehlendes Argument!");
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
					
					Main.log.write("Spielmodus nicht Gefunden!");
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
