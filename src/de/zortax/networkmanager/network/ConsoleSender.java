package de.zortax.networkmanager.network;

import de.zortax.networkmanager.gameserver.Gameserver;

import java.util.Scanner;

public class ConsoleSender extends Thread {


    private Gameserver gs;
    private Scanner scn;
    private Connection connection;

    public ConsoleSender(Gameserver gs, Connection connection){

        this.gs = gs;
        this.scn = new Scanner(gs.getInputStream());
        this.connection = connection;

    }

    @Override
    public void run(){

        String[] args;

        while(true){

            try{

                args = new String[2];
                args[0] = gs.getGamemode() + gs.getId();
                args[1] = scn.nextLine();

                Packet packet = new Packet("console", args);

                connection.sendPacket(packet);


            }catch(Exception e){
                break;
            }

        }
    }
}
