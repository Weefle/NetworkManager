package de.zortax.networkmanager.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private File f;
	private FileWriter fw;
	private BufferedWriter bw;
	private SimpleDateFormat sdfday;
	private SimpleDateFormat sdftime;
	
	public Logger(String file) throws IOException{
		f = new File(file);
		fw = new FileWriter(f, true);
		bw = new BufferedWriter(fw);
		sdfday = new SimpleDateFormat("dd.MM.YY");
		sdftime = new SimpleDateFormat("HH:mm:ss");
	}
	
	public void write(String msg){
		System.out.println("[" + sdfday.format(new Date()) + " " + sdftime.format(new Date()) + "]: " + msg);
		try {
			bw.write("[" + sdfday.format(new Date()) + " " + sdftime.format(new Date()) + "]: " + msg);
			bw.flush();
			bw.write(System.lineSeparator());
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
