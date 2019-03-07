package com.agoda.compress.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.output.CountingOutputStream;

import com.agoda.compress.Compressable;
import com.agoda.utils.FileUtils;

public class ZipCompression implements Compressable {

	private int partFileNo = 1;

	private CountingOutputStream outStream;

	private long maxAllowedBytes = Long.MAX_VALUE;

	@Override
	public void compress(String inputDir, String outputDir, long maxFileSize) throws IOException {
		List<File> files = FileUtils.getFilesFromDirectory(inputDir);
		this.maxAllowedBytes = FileUtils.MBtobytes(maxFileSize);
		for (File file : files) {
			if (file.length() >= maxFileSize) {
				// read part file
			} else {

			}
		}
		FileOutputStream fos = new FileOutputStream(getPartFileName());
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		File fileToZip = new File(inputDir);

		compressFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
	}

	@Override
	public void extract(String inputDir, String outputDir) {

	}

	private void compressFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		// Don't compress invalid files
		if (fileToZip == null || fileToZip.isHidden() || !fileToZip.canRead()) {
			return;
		}

		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			// Recursively compress all files in the subtree
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				compressFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

	private String getPartFileName() {
		return "compressed-part" + String.valueOf(this.partFileNo++) + ".zip";
	}
}
