package de.zortax.networkmanager.gameserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import de.zortax.networkmanager.command.Sender;
import de.zortax.networkmanager.main.Main;

public class Gameserver extends Sender {
	
	private Process proc;
	//private ProcessBuilder pb;
	private InputStream in;
	private OutputStream out;
	private PrintWriter pw;
	private Scanner output;
	private boolean isstarted;
	private String starter;
	private InputThread it;
	private Sender thisinstance;
	private boolean islobby = false;
	private String gamemode = "default";
	private int id = 0;
	
	
	public Gameserver(String startCommand, int id) throws IOException{
		super(Main.cmder);
		starter = startCommand;
		init();
		it = new InputThread();
		thisinstance = this;
		this.name = "Gameserver";
		startSender();
		this.id = id;
		
	}
	
	@Override
	public void startSender(){
		it.start();
	}
	
	public void setLobby(boolean value){
		islobby = value;
	}
	
	private void init() throws IOException{
		proc = Runtime.getRuntime().exec(starter.split(": ")[1], null, new File(starter.split(": ")[0]));
		//pb = new ProcessBuilder(starter.split(": ")[1]);
		//pb.directory(new File(starter.split(": ")[0]).getAbsoluteFile());
		//proc = pb.start();
		isstarted = true;
		in = proc.getInputStream();
		out = proc.getOutputStream();
		pw = new PrintWriter(out, true);
		output = new Scanner(in);
		
		
	}
	
	public String nextLine(){
		return output.nextLine();
	}
	
	public void sendCommand(String cmd){
		Main.log.write("Sende an " + this.gamemode + getId() + ": " + cmd);
		pw.println(cmd);
	}
	
	public boolean isStarted(){
		return isstarted;
	}
	
	public void restart() throws IOException{
		if(isstarted){
			stop();
			
			
		}
		
		StartTester st = new StartTester();
		st.start();
		
		
	}
	public class StartTester extends Thread{
		public StartTester(){
			
		}
		
		public void run(){
			while(true){
				if(!proc.isAlive()){
					try {
						init();
						return;
					} catch (IOException e) {
						Main.log.write(e.getMessage());
					}
				}
				
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					Main.log.write(e.getMessage());
				}
			}
		}
	}
	
	public boolean isLobby(){
		return islobby;
	}
	
	public void start() throws IOException{
		restart();
		
	}
	
	@SuppressWarnings("deprecation")
	public void stop(){
		if(isstarted){
			sendCommand("stop");
			isstarted = false;
			it.stop();
		}
	}
	
	public String getGamemode() {
		return gamemode;
	}

	public void setGamemode(String gamemode) {
		this.gamemode = gamemode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private class InputThread extends Thread{
		@Override
		public void run(){
			String input;
			String[] input_array;
			String[] args;
			ArrayList<String> arg_list;
			String cmd;
			try{
				while(true){
				
					input = output.nextLine();
					input_array = input.split("]: @networkmanager%0%");
					if(input_array.length == 2){
						args = input_array[1].split("%0%");
						cmd = args[0];
						arg_list = Main.toArrayList(args);
						arg_list.remove(0);
						if(cmd.equalsIgnoreCase("lobbylogin")){
							islobby = true;
						}else{
							Main.cmder.runCommand(cmd, Main.toArray(arg_list), thisinstance);
						}
					}
				}
				
				//System.out.println(input);
				
			}catch(Exception e){
				
			}
		}
	}

}