package de.zortax.networkmanager.gameserver;

import java.util.ArrayList;

public class ServerManager {
	
	public ArrayList<Gamemode> modes;
	public ArrayList<Gameserver> allservers;
	
	public ServerManager(){
		allservers = new ArrayList<>();
		modes = new ArrayList<>();
		
	}

	
	public void removeServer(Gameserver gs){
		for(Gamemode c : modes){
			if(c.name.equalsIgnoreCase(gs.getGamemode())){
				c.removeId(gs.getId());
				for(int i = 0; i < c.servers.size(); i++){
					if(c.servers.get(i).getId() == gs.getId()){
						c.servers.remove(i);
						gs.setId(0);
					}
				}
			}
		}
	}
	
	public void addServer(Gameserver gs){
		for(Gamemode c : modes){
			if(c.name.equalsIgnoreCase(gs.getGamemode())){
				c.addServer(gs);
				//if(gs.getId() == 0)
					//gs.setId(c.nextId());
				
				return;
			}
		}
		addGamemode(gs.getGamemode(), gs.getGamemode());
		addServer(gs);
		
	}
	
	public void addGamemode(String name, String sname){
		modes.add(new Gamemode(name, sname));
	}
	
	
	@SuppressWarnings("unused")
	public class Gamemode{
		public String name = "default";
		private String sname = "df";
		
		public ArrayList<Gameserver> servers;
		private ArrayList<ID> ids;
		
		public Gamemode(String name, String sname){
			servers = new ArrayList<>();
			ids = new ArrayList<>();
			this.name = name;
			this.sname = sname;
		}
		
		public void addServer(Gameserver gs){
			servers.add(gs);
			//gs.setId(nextId());
		}
		
		public int nextId(){
			for(int i = 0; i < ids.size(); i++){
				if(ids.get(i).used == false){
					ids.get(i).used = true;
					return i + 1;
				}
				
			}
			
			ids.add(new ID());
			return ids.size();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public void removeId(int id){
			ids.get(id - 1).used = false;
		}
		
	}
	
	private class ID{
		public boolean used = true;
		public ID(){
			
		}
	}
}
