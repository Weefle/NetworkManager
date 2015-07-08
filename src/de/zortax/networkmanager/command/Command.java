package de.zortax.networkmanager.command;

public class Command {
	
	//Override
	public String name;
	public String description;
	public String usage;
	
	
	//Override
	public void onCommand(Sender sender, String[] args){
		
		//example
		this.name = "default";
		
	}

}
