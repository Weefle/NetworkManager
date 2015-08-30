package de.zortax.networkmanager.gameserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.zortax.networkmanager.main.Main;

public class AutoLoader {
	
	private ArrayList<File> f;
	private ArrayList<FileReader> fr;
	private ArrayList<BufferedReader> br;
	private ArrayList<ArrayList<String>> lines;
	private ArrayList<String> files;
	
	public AutoLoader(ArrayList<String> file) throws FileNotFoundException{
		
		f = new ArrayList<>();
		fr = new ArrayList<>();
		br = new ArrayList<>();
		lines = new ArrayList<>();
		files = file;
		
		
		
	}
	
	public void startServers() throws IOException{
		//int i = 1;
		for(ArrayList<String> c : lines){
			//i = 1;
			//for(String cu : c){
			for(int i = 0; i < c.size(); i++){
				//if(i <= Main.mainconfig.getInt("autoload_server_per_game_count")){
					Main.log.write("Starting a gameserver...");
					Main.man.allservers.add(new Gameserver(c.get(i), i + 1));
					
					//i++;
				//}
			}
		}
		
	}
	
	
	public void load() throws IOException{
		
		for(String c : files){
			f.add(new File(c));
		}
		
		for(File c : f){
			fr.add(new FileReader(c));
		}
		
		for(FileReader c : fr){
			br.add(new BufferedReader(c));
		}
		
		Main.log.write("Loading Autostart-files...");
		String line;
		ArrayList<String> tmp = new ArrayList<>();
		
		for(int i = 0; i < br.size(); i++){
			tmp = new ArrayList<>();
			while(true){
				line = br.get(i).readLine();
				if(line == null){
					break;
				}else{
					tmp.add(line);
				}
			}
			
			lines.add(tmp);
		}
		
	}

}
