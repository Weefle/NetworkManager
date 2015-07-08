package de.zortax.networkmanager.command;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.main.Main;

public class MsgCommand extends Command {
	
	public MsgCommand(){
		this.name = "msg";
		this.description = "Sendet eine Nachricht an alle Gameserver";
		this.usage = "msg <Nachricht>";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		
		
		
		String msg = args[0];
		
		for(int i = 1; i < args.length; i++){
			msg = msg + " " + args[i];
		}
		for(Gamemode c : Main.man.modes){
			
			for(Gameserver c1 : c.servers){
				c1.sendCommand("say " + msg);
			}
		}
		
	}

}
