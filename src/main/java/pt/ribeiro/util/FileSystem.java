package pt.ribeiro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystem {
	
	/*
	 * Creates the directory with the all its parent directories
	 * @param The full path String
	 * */
	public static void createDirectory(String directoryPath){
		File directory = new File(directoryPath);
		directory.mkdirs();
	}
	
	/*
	 * TODO
	 * @return
	 * */
	public static File createFileIfDoesntExist(String filePath) throws IOException{
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/*
	 * TODO
	 * */
	public static void eraseDirectoryContents(String directoryPath){
		File directory = new File(directoryPath);
	    File[] files = directory.listFiles();
	    if(files!=null) {
	        for(File f: files) {
	            if(!f.isDirectory()) {
	            	f.delete();
	            }
	        }
	    }
	}
	
	/*
	 * TODO
	 * */
	public static boolean fileExists(String filePath){
		Path path = Paths.get(filePath);
		return Files.exists(path);
	}
	
	/*
	 * TODO
	 * */
	public static void copyFile(String origin, String destination) throws IOException{

		CopyOption[] options = new CopyOption[]{
			      StandardCopyOption.REPLACE_EXISTING,
			      StandardCopyOption.COPY_ATTRIBUTES
			    };
		
		Path originPath = Paths.get(origin);
		Path destinationPath = Paths.get(destination);
		
		Files.copy(originPath, destinationPath, options);

	}

	/*
	 * TODO
	 * */
	public static void saveObject(Object serializableObject, String filepath) throws IOException{

		FileOutputStream fos = new FileOutputStream(filepath);
    	ObjectOutputStream out = new ObjectOutputStream(fos);
    	
    	out.writeObject(serializableObject);
    	
    	out.close();
    	fos.close();

	}

	/*
	 * TODO
	 * */
	public static Object loadObject(String filepath) throws IOException, ClassNotFoundException{
	      	
      	FileInputStream fis = new FileInputStream(filepath);
      	ObjectInputStream in = new ObjectInputStream(fis);
      	
      	Object serializableObject = in.readObject();
	
      	in.close();
      	fis.close();
      	
		return serializableObject;
	}
	

	public static String[] getDirectoryNamesFromFolder(String folderPath){
		
		File file = new File(folderPath);
		return file.list(
				new FilenameFilter() {
					public boolean accept(File current, String name) {
						return new File(current, name).isDirectory();
					}
				}
		);
			
	}
	
	public static void createFile(String filePath, String content) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filePath);
		out.print(content);
		out.close();
	}
	

}
