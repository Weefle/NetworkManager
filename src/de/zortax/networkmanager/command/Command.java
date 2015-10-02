package de.zortax.networkmanager.command;

public class Command {
	
	//Override
	public String name;
	public String description;
	public String usage;
	public String permission;
	
	
	//Override
	public void onCommand(Sender sender, String[] args){
		
		//example
		this.name = "default";
		this.description = "sample description";
		this.usage = "usage explanation";
		this.permission = "basic.defaultcmd";
		
	}

}
