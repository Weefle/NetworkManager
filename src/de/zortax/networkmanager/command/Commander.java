package de.zortax.networkmanager.command;

import java.util.ArrayList;

import de.zortax.networkmanager.main.Main;

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
		
		Main.log.write(sender.name + ": " + cmd + arg_str);
		
		for(Command c : cmds){
			if(c.name.equalsIgnoreCase(cmd)){
				c.onCommand(sender, args);
				return;
			}
		}
		
		Main.log.write("Unbekannter Befehl (" + sender.name + ").");
		
		
	}

}