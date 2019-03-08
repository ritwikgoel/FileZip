package com.agoda.app;

import java.io.IOException;

import com.agoda.compress.zip.ZipCompression;
import com.agoda.constants.Commands;
import com.agoda.constants.Compression;
import com.agoda.exceptions.InvalidCommandException;
import com.agoda.utils.FileUtils;

public class App {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			throw new InvalidCommandException("No arguments provided");
		}

		if (args[0].equalsIgnoreCase(Commands.COMPRESS.name())) {
			if (args.length != 4) {
				throw new InvalidCommandException("Incorrect number of arguments");
			}
			String inputDir = args[1];
			String outputDir = args[2];
			long fileSize = FileUtils.MBtobytes(Long.parseLong(args[3]));
			
			// Currently using only ZIP compression
			Compression compressionAlgorithm = Compression.ZIP;
			switch (compressionAlgorithm) {
			case ZIP:
				ZipCompression compressor = new ZipCompression();
				compressor.compress(inputDir, outputDir, fileSize);
				break;
			default:
				break;

			}

		} else if (args[0].equalsIgnoreCase(Commands.EXTRACT.name())) {
			if (args.length != 3) {
				throw new InvalidCommandException("Incorrect number of arguments");
			}
			String inputDir = args[1];
			String outputDir = args[2];
			// Currently using only ZIP compression
			Compression compressionAlgorithm = Compression.ZIP;
			switch (compressionAlgorithm) {
			case ZIP:
				ZipCompression compressor = new ZipCompression();
				compressor.extract(inputDir, outputDir);
				break;
			default:
				break;

			}
		} else {
			throw new InvalidCommandException("This command is not supported");
		}

		System.out.println(Runtime.getRuntime().maxMemory() / 1000000);
	}
}