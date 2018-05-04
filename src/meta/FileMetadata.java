package meta;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;


public class FileMetadata {
	
	File file;
	
	public FileMetadata(String filename) {
		this.file=new File(filename);
	}
	
	/**
	 * 
	 * @return extension
	 */
	public String readExtensionFile() {
		String extension=null;
		if (file.getAbsolutePath().length()>0) {
			int i=file.getAbsolutePath().lastIndexOf(".");
			if(i>=0)
				extension=file.getAbsolutePath().substring(i+1);
		}
		return extension;
	}
	
	/**
	 * 
	 * @return date
	 */
	public Date readLastModified(){
		Date date=new java.sql.Date(file.lastModified());
		return date;
	}
	
	public boolean setLastModified(Long newLastModified){
		return file.setLastModified(newLastModified);
	}
	
	public String SizeFile() {
		try{
			BasicFileAttributes bfa;
			bfa = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			return bfa.size()+" bytes";
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
