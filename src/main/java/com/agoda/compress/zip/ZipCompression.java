package com.agoda.compress.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.output.CountingOutputStream;

import com.agoda.compress.Compressable;
import com.agoda.utils.FileUtils;

public class ZipCompression implements Compressable {

	private int partFileNo = 1;

	private CountingOutputStream outStream;

	@Override
	public void compress(String inputDir, String outputDir, long maxFileSize) throws IOException {
		List<File> files = FileUtils.getFilesFromDirectory(inputDir);
		FileOutputStream fos = new FileOutputStream(outputDir+getPartFileName());
		this.outStream = new CountingOutputStream(fos);
		ZipOutputStream zipOut = new ZipOutputStream(this.outStream);
		for (File file : files) {
				FileInputStream fis = new FileInputStream(file);
				zipOut.putNextEntry(new ZipEntry(FileUtils.getRelativePath(inputDir,file.getPath())));
				
				byte[] bytes = new byte[1024];
	            int bytesRead;
	            while((bytesRead = fis.read(bytes)) >= 0) {
	            	if(getAvailableBytesInStream(maxFileSize)> bytesRead){
	            		zipOut.write(bytes, 0, bytesRead);
	            	} else {
	            		fos = new FileOutputStream(outputDir+getPartFileName());
	            		this.outStream = new CountingOutputStream(fos);
	            		this.outStream.resetByteCount();
	            		zipOut = new ZipOutputStream(this.outStream);
	            		zipOut.write(bytes, 0, bytesRead);
	            	}
	                
	            }
	            fis.close();
				
		}
		zipOut.close();
		this.outStream.close();
		fos.close();
	}

	@Override
	public void extract(String inputDir, String outputDir) {

	}


	private String getPartFileName() {
		return "compressed-part-" + String.valueOf(this.partFileNo++) + ".zip";
	}
	
	private long getAvailableBytesInStream(long maxFileSizeBytes){
		return maxFileSizeBytes - this.outStream.getByteCount();
	}
}
