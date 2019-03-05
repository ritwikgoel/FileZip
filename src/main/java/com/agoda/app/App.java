package com.agoda.app;

import java.io.IOException;

import com.agoda.compress.ZipCompression;
import com.agoda.constants.Commands;
import com.agoda.constants.Compression;
import com.agoda.exceptions.InvalidCommandException;

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
			Integer fileSize = Integer.parseInt(args[3]);
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
	}
}