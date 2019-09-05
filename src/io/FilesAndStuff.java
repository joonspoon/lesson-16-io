package io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FilesAndStuff {
	public static void main(String[] args) throws IOException {
		
		File file = new File("src/pasta");
		file.createNewFile();
		System.out.println(file.getAbsolutePath()); 	//absolute path
		System.out.println(file.getPath());             //relative path
		
		/* Writing to a file using commons.io */
		FileUtils.write(file, "wine", true);
		
		/* Reading a file using commons.io */
		String fileContents = IOUtils.toString(file.toURI());
		System.out.println("pasta contains: " + fileContents);

	}
}

// Copyright JoonSpoon, 2019
