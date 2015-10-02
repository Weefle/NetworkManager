package de.zortax.networkmanager.network;

import de.zortax.networkmanager.command.Sender;
import de.zortax.networkmanager.gameserver.ServerManager;
import de.zortax.networkmanager.main.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Connection extends Sender{
	
	private Socket client;
	private InputThread it;
	private Scanner in;
    private PrintWriter pw;
	private Connection instance;
	private ArrayList<String> permissions;
    private ArrayList<ConsoleSender> consoleSenders;
	
	public Connection(Socket client){
		super(Main.cmder);
		
		
		
		this.client = client;
		
		this.name = "ClientConnection";
		
		try {
			in = new Scanner(client.getInputStream());
		} catch (IOException e) {
			Main.log.write("Connection interrupted...");
		}
		
		instance = this;
		permissions = new ArrayList<>();
		it = new InputThread();

        try {
            pw = new PrintWriter(this.client.getOutputStream(), true);
        } catch (IOException e) {
            Main.log.write("[ClientConnection] [" + client.getInetAddress().getHostName() + "] Error while trying to get OutputStream: " + e.getMessage() + "\n  --> Detailed stack trace: \n \n" + e.getStackTrace());
            Main.log.write("\n[ClientConnection] [" + client.getInetAddress().getHostName() + "] Closing connection...\n");

            try {
                client.close();
            } catch (IOException e1) {

                Main.log.write("Unhandled Exception!");

                e1.printStackTrace();

                return;
            }

            return;
        }


        consoleSenders = new ArrayList<>();

        if(this.hasPermission("gameserver.console")){
            for(ServerManager.Gamemode c : Main.man.modes){
                consoleSenders.addAll(c.servers.stream().map(c1 -> new ConsoleSender(c1, this)).collect(Collectors.toList()));
            }
        }

    }
	
	public void startListener(){
		this.started = true;
		it.start();
	}

    public void sendPacket(Packet packet){
        String msg = packet.getName();

        for(String c : packet.getArgs()){
            msg += "%0%" + c;
        }

        pw.write(msg);
        pw.flush();
    }
	
	public Socket getClient(){
		return client;
	}

	public void setPermissions(ArrayList<String> permissions){
		this.permissions = permissions;
	}

	public void addPermission(String permission){
		this.permissions.add(permission);
	}

	public void addPermissions(ArrayList<String> permissions){
		this.permissions.addAll(permissions);
	}

	public boolean hasPermission(String permission) {
		if (this.permissions.contains(permission)) return true;
		return false;
	}



    public class Updater extends Thread{

        public Updater(){

        }

        @Override
        public void run(){

            while(true){

				//TODO: test for new gameservers and so on and create a ConsoleSender for them





            }

        }
    }
	
	private class InputThread extends Thread{
		
		public InputThread(){
			
		}
		
		@Override
		public void run(){
			
			String msg;
			String command;
			
			while(true){
				
				msg = in.nextLine();
				
				String[] cmd = msg.split("%0%");
				
				if(cmd.length >= 1){
					command = cmd[0];
					String[] args;
					if(cmd.length > 1){
						
						ArrayList<String> argsList = Main.toArrayList(cmd);
						argsList.remove(0);
						
						args = Main.toArray(argsList);
						
						
					}else{
						args = null;
					}
					
					Main.cmder.runCommand(command, args, instance);
				}else{
					Main.log.write("Invalid client message...");
				}
				
				
			}
		}
		
	}
}
