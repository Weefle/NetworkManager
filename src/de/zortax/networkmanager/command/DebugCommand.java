package de.zortax.networkmanager.command;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.main.Main;

public class DebugCommand extends Command {
	

	public DebugCommand(){
		this.name = "debug";
		this.description = "Nur so ein unnötiger Testbefehl";
		this.usage = "debug";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		Main.log.write("-------- DEBUG ---------");
		Main.log.write("--- GAMEMODES ---");
		for(Gamemode c : Main.man.modes){
			Main.log.write(c.name);
			
		}
		Main.log.write("--- GAMESERVERS ---");
		for(Gamemode c : Main.man.modes){
			for(Gameserver c1 : c.servers){
				Main.log.write(c1.getGamemode() + c1.getId() + ", " + c1.name + ", Lobby: " + c1.isLobby() + ", Started: " + c1.isStarted());
			}
		}
		//Main.log.write("--- NOT LOGGED IN ---");
		
	}
	
	
}
