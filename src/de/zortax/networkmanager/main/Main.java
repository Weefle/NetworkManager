package de.zortax.networkmanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import de.zortax.networkmanager.command.AddGameserverCommand;
import de.zortax.networkmanager.command.Commander;
import de.zortax.networkmanager.command.ConsoleSender;
import de.zortax.networkmanager.command.DebugCommand;
import de.zortax.networkmanager.command.HelpCommand;
import de.zortax.networkmanager.command.LoginCommand;
import de.zortax.networkmanager.command.MsgCommand;
import de.zortax.networkmanager.command.RestartCommand;
import de.zortax.networkmanager.command.SetpcountCommand;
import de.zortax.networkmanager.command.StopCommand;
import de.zortax.networkmanager.data.Config;
import de.zortax.networkmanager.gameserver.AutoLoader;
import de.zortax.networkmanager.gameserver.Gameserver;
import de.zortax.networkmanager.gameserver.ServerManager;
import de.zortax.networkmanager.gameserver.ServerManager.Gamemode;
import de.zortax.networkmanager.logger.Logger;

public class Main {
	
	static Scanner in;
	public static Commander cmder;
	public static Logger log;
	public static Config mainconfig;
	public static AutoLoader al;
	public static ArrayList<String>  autoload;
	public static ServerManager man;

	public static void main(String[] args) {
		
		Runtime.getRuntime().addShutdownHook(new Thread() { 
            public void run() { 
            	log.write("Stoppe Gameserver...");
            	for(Gamemode c : man.modes){
            		for(Gameserver c1 : c.servers){
            			c1.stop();
            		}
            	}
            	log.write("Stoppe Networkmanager...");
            } 
        }); 
		
		try {
			log = new Logger("networkmanager.log");
			log.write("Logger gestartet!");
			
			Properties prop = System.getProperties();
			log.write(prop.getProperty("os.name") + " erkannt.");
			
			man = new ServerManager();
			
		
			mainconfig = new Config("config.nmcfg");
			in = new Scanner(System.in);
		
			
		
			cmder = new Commander();
		
			ConsoleSender cs = new ConsoleSender(cmder);
			cmder.addCommand(new DebugCommand());
			cmder.addCommand(new AddGameserverCommand());
			cmder.addCommand(new MsgCommand());
			cmder.addCommand(new LoginCommand());
			cmder.addCommand(new HelpCommand());
			cmder.addCommand(new SetpcountCommand());
			cmder.addCommand(new RestartCommand());
			cmder.addCommand(new StopCommand());
			log.write("Commandmanager gestartet!");
			cs.startSender();
			
			autoload = new ArrayList<>();
			
			autoload.add("proxy.server");
			autoload.add("lobbys.server");
			autoload.add("sg.server");
			autoload.add("bw.server");
			autoload.add("oneshot.server");
			
			al = new AutoLoader(autoload);
			
			log.write("Starte Gameserver...");
			al.load();
			al.startServers();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<String> toArrayList(String[] array){
		
		
		ArrayList<String> list = new ArrayList<>();
		
		for(String c : array){
			list.add(c);
		}
		
		return list;
		
	}
	
	public static String[] toArray(ArrayList<String> list){
		String[] array = new String[list.size()];
		
		for(int i = 0; i < array.length; i++){
			array[i] = list.get(i);
		}
		
		
		return array;
	}

}