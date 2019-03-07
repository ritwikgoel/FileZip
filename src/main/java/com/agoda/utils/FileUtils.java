package com.agoda.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

	private static final long MEGABYTE = 1024L * 1024L;

	public static List<File> getFilesFromDirectory(String directory) {
		try {
			return Files.walk(Paths.get(directory)).filter(Files::isRegularFile).map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static long bytesToMB(long bytes) {
		return bytes / MEGABYTE;
	}

	public static long MBtobytes(long MB) {
		return MB * MEGABYTE;
	}
}
