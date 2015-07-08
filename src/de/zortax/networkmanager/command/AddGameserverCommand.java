package de.zortax.networkmanager.command;

import java.io.IOException;

import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.main.Main;

public class AddGameserverCommand extends Command {
	
	public AddGameserverCommand(){
		this.name = "addserver";
		this.description = "Fügt manuell einen Gameserver hinzu und startet ihn. Er wird NICHT in der Config gespeichert. Diesen Befehl nur für Testzwecke nutzen.";
		this.usage = "addserver <Startbefehl>";
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
