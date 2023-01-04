package com.agoda.app;

import java.io.IOException;

import com.agoda.compress.zip.Zip;
import com.agoda.constants.Commands;
import com.agoda.constants.CompressionAlgo;
import com.agoda.exceptions.InvalidCommandException;
import com.agoda.utils.FileUtils;

public class App {
	public static void main(String[] args) throws IOException {
		if (args[0].equalsIgnoreCase(Commands.COMPRESS.name())) {
			String inputDir = "/Users/ritwikgoel/Desktop/input.txt";
			String outputDir = "/Users/ritwikgoel/Desktop/";
			long fileSize = FileUtils.MBtobytes(Double.parseDouble(args[3]));

			// Currently using only ZIP compression
			CompressionAlgo compressionAlgorithm = CompressionAlgo.ZIP;
			switch (compressionAlgorithm) {
			case ZIP:
				Zip compressor = new Zip();
				System.out.println("Starting to compress " + inputDir + " to " + outputDir + " using "
						+ CompressionAlgo.ZIP.name() + " compression");
				long startTime = System.nanoTime();
				compressor.compress(inputDir, outputDir, fileSize);
				System.out.println("Compression finished in " + (System.nanoTime() - startTime) / 1000000 + " ms");
				System.out
						.println("Maximum memory used (MB):" + FileUtils.bytesToMB(Runtime.getRuntime().totalMemory()));
				break;
			default:
				break;
			}
		} else if (args[0].equalsIgnoreCase(Commands.EXTRACT.name())) {
			String inputDir = args[1];
			String outputDir = args[2];

			// Currently using only ZIP extraction
			CompressionAlgo extractionAlgorithm = CompressionAlgo.ZIP;
			switch (extractionAlgorithm) {
			case ZIP:
				System.out.println("Starting to extract " + inputDir + " to " + outputDir + " using "
						+ CompressionAlgo.ZIP.name() + " extraction");
				long startTime = System.nanoTime();
				Zip extractor = new Zip();
				extractor.extract(inputDir, outputDir);
				System.out.println("Extraction finished in " + (System.nanoTime() - startTime) / 1000000 + " ms");
				System.out
						.println("Maximum memory used (MB):" + FileUtils.bytesToMB(Runtime.getRuntime().totalMemory()));
				break;
			default:
				break;

			}
		}

	}

}