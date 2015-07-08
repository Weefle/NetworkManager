package de.zortax.networkmanager.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
	
	private File f;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;
	private ArrayList<String> lines;
	
	
	
	public Config(String file) throws IOException{
		f = new File(file);
		
		fw = new FileWriter(f, false);
		fr = new FileReader(f);
		
		bw = new BufferedWriter(fw);
		br = new BufferedReader(fr);
		
		
	}
	
	public void loadConfig() throws IOException{
		String line;
		while(true){
			line = br.readLine();
			if(line != null){
				lines.add(line);
			}else{
				break;
			}
		}
	}
	
	public void saveConfig() throws IOException{
		
		for(String c : lines){
			bw.write(c + "\n");
		}
		
		bw.flush();
		
	}
	
	public void setString(String key, String value){
		
		String[] temp;
		
		for(int i = 0; i < lines.size(); i++){
			temp = lines.get(i).split(": ");
			if(temp[0].equalsIgnoreCase(key)){
				lines.set(i, temp[0] + ": " + value);
				return;
			}
		}
		
		addKey(key, value);
		
	}
	
	public void setBoolean(String key, boolean value){
		
		String[] temp;
		
		for(int i = 0; i < lines.size(); i++){
			temp = lines.get(i).split(": ");
			if(temp[0].equalsIgnoreCase(key)){
				lines.set(i, temp[0] + ": " + value);
				return;
			}
		}
		addKey(key, value);
		
		
	}
	
	public void setInt(String key, int value){
		
		String[] temp;
		
		for(int i = 0; i < lines.size(); i++){
			temp = lines.get(i).split(": ");
			if(temp[0].equalsIgnoreCase(key)){
				lines.set(i, temp[0] + ": " + value);
				return;
			}
		}
		addKey(key, value);
		
	}
	
	public String getString(String key){
		
		String[] temp;
		for(String c : lines){
			temp = c.split(": ");
			if(temp[0].equalsIgnoreCase(key)){
				return temp[1];
			}
		}
		
		return null;
	}
	
	public boolean getBoolean(String key){
		String[] temp;
		for(String c : lines){
			temp = c.split(": ");
			if(temp[0].equalsIgnoreCase(key)){
				if(temp[1].equalsIgnoreCase("true")){
					return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	
	public int getInt(String key){
		
		String[] temp;
		
		for(String c : lines){
			temp = c.split(": ");
			if(temp[0].equalsIgnoreCase(key)){
				return Integer.parseInt(temp[1]);
			}
		}
		
		return 0;
	}
	
	private void addKey(String key, String value){
		lines.add(key + ": " + value);
	}
	private void addKey(String key, boolean value){
		lines.add(key + ": " + value);
	}
	private void addKey(String key, int value){
		lines.add(key + ": " + value);
	}
	
	
	
	

}
