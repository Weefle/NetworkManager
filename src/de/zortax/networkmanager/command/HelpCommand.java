package de.zortax.networkmanager.command;

import de.zortax.networkmanager.main.Main;

public class HelpCommand extends Command {
	
	public HelpCommand(){
		this.name = "help";
		this.description = "Shows alls commands and their description";
		this.usage = "help";
	}
	
	@Override
	public void onCommand(Sender sender, String[] args){
		for(Command c : Main.cmder.cmds){
			System.out.println("################## " + c.name + " ##################");
			System.out.println("Beschreibung:\n----------------------\n" + c.description + "\n");
			System.out.println("Syntax:\n----------------------\n" + c.usage + "\n");
			System.out.println("#######################################");
		}
	}

}
