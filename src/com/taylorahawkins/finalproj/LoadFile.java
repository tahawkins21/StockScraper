package com.taylorahawkins.finalproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Taylor Hawkins
 * 	LoadFile will load a file from its absolute path you pass to the constructor. Use getFileContents to return an
 * ArrayList that has a line of the file as each member.
 *
 */
public class LoadFile {
	private FileReader fr;
	private BufferedReader br;
	private ArrayList<String> fileContents = new ArrayList<String>();
	
	public LoadFile(String file) {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File not found...creating " + e.getMessage());
			File newFile = new File(file);
			try {
				fr = new FileReader(newFile);
				br = new BufferedReader(fr);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Couldn't creat the new file: "+ e1.getMessage());
			}
			
		}
		
	}
	
	public FileReader getFr() {
		return fr;
	}

	public void setFr(FileReader fr) {
		this.fr = fr;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
	
	public ArrayList<String> getFileConents(){
		String line = "";
		try {
			while((line = br.readLine()) != null) {
				//System.out.println(line);
				fileContents.add(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading file: \n" + e.getMessage());
			e.printStackTrace();
		}
		return fileContents;

	}
	
	public static void main(String[] args) {
		LoadFile file = new LoadFile("C:\\Users\\tahaw\\AngularProjects\\FinalAngularProject\\SP500tickers.csv");
		System.out.println(file.getFileConents());
	}
}
