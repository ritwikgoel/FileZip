package com.agoda.app;

import com.agoda.compress.FileCompressor;
import com.agoda.constants.Commands;
import com.agoda.exceptions.InvalidCommandException;
import com.agoda.extract.FileExtractor;

public class App {
	public static void main(String[] args) {
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
			FileCompressor compressor = new FileCompressor();
			compressor.compress(inputDir, outputDir, fileSize);
		} else if (args[0].equalsIgnoreCase(Commands.EXTRACT.name())) {
			if (args.length != 3) {
				throw new InvalidCommandException("Incorrect number of arguments");
			}
			String inputDir = args[1];
			String outputDir = args[2];
			FileExtractor extractor = new FileExtractor();
			extractor.extract(inputDir, outputDir);
		} else {
			throw new InvalidCommandException("This command is not supported");
		}
	}
}