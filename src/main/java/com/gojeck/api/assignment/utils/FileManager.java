package com.gojeck.api.assignment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This utility can be used for file read/write operations
 * @author Prerit Jain
 *
 */
public class FileManager {

	private File file;

	public FileManager(File file) {
		this.file = file;
	}

	public List<String> readLinesFromFile(){
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine())!=null){
				lines.add(line);
			}
		}catch(IOException e){
			CustomLogger.error("Error reading file "+file.getAbsolutePath());
			System.exit(1);
		}finally {
			closeReader(reader);
		}
		return lines;
	}

	public void closeReader(BufferedReader reader){
		try {
			if(reader!=null)
				reader.close();
		} catch (IOException e) {
			CustomLogger.error("Error closing connection");
		}
	}
}
