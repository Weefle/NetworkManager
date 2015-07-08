package de.zortax.networkmanager.command;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.main.Main;

public class SetpcountCommand extends Command {
	
	public SetpcountCommand(){
		this.name = "setpcount";
		this.description = "Sign-Update Befehl für Lobbyserver (nur Gameserver)";
		this.usage = "Irrelevant";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		if(sender instanceof Gameserver){
			if(args.length >= 2){
				for(Gamemode c : Main.man.modes){
					for(Gameserver c1 : c.servers){
						if(c1.isLobby()){
							Main.log.write("Sende Sign-Updatebefehl an " + c1.getGamemode() + c1.getId() + "...");
							if(args.length == 2)
								c1.sendCommand("setsign " + ((Gameserver) sender).getGamemode() + " " + ((Gameserver) sender).getGamemode() + ((Gameserver) sender).getId() + " " + args[0] + " " + args[1] + " HUB");
							if(args.length == 3)
								c1.sendCommand("setsign " + ((Gameserver) sender).getGamemode() + " " + ((Gameserver) sender).getGamemode() + ((Gameserver) sender).getId() + " " + args[0] + " " + args[1] + " " + args[2]);
						}
					}
				}
			}else{
				Main.log.write("Ungültige Argumente! (" + sender.name + ")");
			}
		}else{
			Main.log.write("Dieser Befehl kann nur von Gameservern ausgeführt werden!");
		}
	}

}
