package de.zortax.networkmanager.command;

import de.zortax.networkmanager.main.Main;

public class HelpCommand extends Command {
	
	public HelpCommand(){
		this.name = "help";
		this.description = "Zeigt die Beschreibungen aller Befehle an.";
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
