package de.zortax.networkmanager.command;

public class ConsoleSender extends Sender {
	
	
	Sender sender = this;
	InputThread itt;
	
	public ConsoleSender(Commander cmder){
		
		
		super(cmder);
		itt = new InputThread(System.in, commander, this);
		
		this.it = itt;
		this.name = "Console";
		
		
		commander = cmder;
		
		
	}
	
	

}
