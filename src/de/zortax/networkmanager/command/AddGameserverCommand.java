package de.zortax.networkmanager.command;

import java.io.IOException;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.main.Main;

public class AddGameserverCommand extends Command {
	
	public AddGameserverCommand(){
		this.name = "addserver";
		this.description = "Starts a new server manually. Doesn't add it to the config, use this for testing only!";
		this.usage = "addserver <start-command>";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		
		String cmd;
		
		if(args.length >= 1){
			try {
				cmd = args[0];
				for(int i = 1; i < args.length; i++){
					cmd = cmd + " " + args[i];
				}
				Main.man.allservers.add(new Gameserver(cmd, 0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
