package services;

import java.util.ArrayList;
import java.util.List;

import domain.Metadata;
import meta.FileMetadata;

public class ServiceMetadata {

	public List<Metadata> readMetadata(String folder) {
		List<Metadata> metadatas=new ArrayList<Metadata>();

		FileMetadata fm=new FileMetadata(folder);
		
		//	Generic Metadata
		metadatas.add(new Metadata("Extension", fm.readExtensionFile(), false,false));
		metadatas.add(new Metadata("Last Modified", fm.readLastModified().toString(), true,true));
		metadatas.add(new Metadata("Size", fm.SizeFile(), false, false));
		
		return metadatas;
	}
	
	public List<String> deleteMetadata(String folder){
		List<String> metadataDelete=new ArrayList<String>();

		FileMetadata fm=new FileMetadata(folder);
		
		if(fm.setLastModified(new Long(0)))
			metadataDelete.add("Last Modified");
		
		return metadataDelete;
	}
	
	
	
}
