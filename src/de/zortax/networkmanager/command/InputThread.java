package de.zortax.networkmanager.command;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import de.zortax.networkmanager.main.Main;

public class InputThread extends Thread {

	Scanner in;
	InputStream instr;
	Commander commander;
	ArrayList<String> argList;
	Sender sender;
	
	public InputThread(InputStream instream, Commander cmder, Sender sender_){
		instr = instream;
		in = new Scanner(instr);
		commander = cmder;
		argList = new ArrayList<>();
		sender = sender_;
	}
	
	@Override
	public void run(){
	
		String[] args;
		try{
			while(true){
				args = in.nextLine().split(" ");
			
				argList = Main.toArrayList(args);
				argList.remove(0);
			
				commander.runCommand(args[0], Main.toArray(argList), sender);
			
			}
		}catch(Exception e){
			
		}
		
		
		
	}
	
}
