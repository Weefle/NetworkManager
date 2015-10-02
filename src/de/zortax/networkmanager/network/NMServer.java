package de.zortax.networkmanager.network;

import de.zortax.networkmanager.main.Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class NMServer {

	
	private ServerSocket socket;
	private ArrayList<Connection> connections;
    private LoginThread loginThread;
	
	public NMServer(int port){

		connections = new ArrayList<>();
        loginThread = new LoginThread();
		
		try {
			socket = new ServerSocket(port);
			
			
			
		} catch (IOException e) {
			Main.log.write("Error while opening server socket...");
			e.printStackTrace();
		}
	}

    public void startServer(){
        loginThread.start();
    }
	
	public void sendPacket(Packet packet){
		
	}
	
	private void addConnection(Socket client, ArrayList<String> permissions){
		Connection connection = new Connection(client);

		connection.startListener();
		connections.add(connection);
	}
	
	
	
	private class LoginThread extends Thread{
		
		public LoginThread(){

		}
		
		
		@Override
		public void run(){
            while(true){

                try {
                    Socket client = socket.accept();
                    (new LoginChecker(client)).start();
                } catch (IOException e) {
                    break;
                }

            }
		}
		
	}
	
	private class LoginChecker extends Thread {

		private Socket client;
		private Scanner in;

		public LoginChecker(Socket client) {
			this.client = client;

			try {
				in = new Scanner(client.getInputStream());


			} catch (IOException e) {
				Main.log.write("Connection interrupted...");
			}
		}

		@Override
		public void run() {

			String firstMsg = in.nextLine();

			String[] cmd = firstMsg.split("%0%");

			if (cmd.length == 3) {
				if (cmd[0].equalsIgnoreCase("login")) {

					//TODO: Authentication!
					ArrayList<String> perms = new ArrayList<>();

					//if authenticated:
					addConnection(client, perms);

					return;
				}
			}

			try {

				Main.log.write("Client tried to logged in, but used wrong login-data or malformed login-command. Closing connection...");
				client.close();

			} catch (IOException e) {
				Main.log.write("Was already disconnected.");
			}

		}

	}

}
