package de.zortax.networkmanager.command;

import java.util.ArrayList;

import de.zortax.networkmanager.main.Main;
import de.zortax.networkmanager.network.Connection;
import de.zortax.networkmanager.network.Packet;

public class Commander {
	
	public ArrayList<Command> cmds;
	
	public Commander(){
		cmds = new ArrayList<>();
	}
	
	public void addCommand(Command cmd){
		cmds.add(cmd);
	}
	
	public void runCommand(String cmd, String[] args, Sender sender){


		String arg_str = "";
		
		for(String c : args){
			arg_str = arg_str + " " + c;
		}
		

		
		for(Command c : cmds){
			if(c.name.equalsIgnoreCase(cmd)){

				if(sender instanceof Connection){

					Connection connection = (Connection) sender;
					if(!connection.hasPermission(c.permission)){
						Main.log.write("[ClientConnection] " + connection.getClient().getInetAddress().getHostName() + " tried to execute \"" + c.name + "\", didn't have enough permissions.");
						return;
					}

				}

				Main.log.write(sender.name + ": " + cmd + arg_str);

				c.onCommand(sender, args);
				return;
			}
		}
		
		Main.log.write("Unknown command (" + sender.name + ").");
		
		
	}

}