package de.zortax.networkmanager.command;

public class Sender {
	
	public boolean started;
	public InputThread it;
	public Commander commander;
	public String name;
	
	public Sender(Commander cmder){
		
		commander = cmder;
		started = false;
		name = "Sender";
		
	}
	
	
	
	public void startSender(){
		if(!started){
			started = true;
			it.start();
		}
	}
	
	
}
