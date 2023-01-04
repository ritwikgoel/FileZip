package com.agoda.app;

import java.io.IOException;
import java.util.Scanner;

import com.agoda.compress.zip.Zip;
import com.agoda.constants.Commands;
import com.agoda.constants.CompressionAlgo;
import com.agoda.exceptions.InvalidCommandException;
import com.agoda.utils.FileUtils;

public class App {
	public static void main(String[] args) throws IOException {
		int input;
		Scanner io= new Scanner(System.in);
		input=io.nextInt();
		if (input==0) {
			String inputDir = "/Users/ritwikgoel/Desktop/hello.txt";
			String outputDir = ".";
			long fileSize = FileUtils.MBtobytes(Double.parseDouble("100"));

			// Currently using only ZIP compression
			CompressionAlgo compressionAlgorithm = CompressionAlgo.ZIP;
			switch (compressionAlgorithm) {
				case ZIP:
					Zip compressor = new Zip();
					System.out.println("Starting to compress " + inputDir + " to " + outputDir + " using "
							+ CompressionAlgo.ZIP.name() + " compression");
					long startTime = System.nanoTime();
					System.out.println("Start Time:::: "+ startTime);
					compressor.compress(inputDir, outputDir, fileSize);
					System.out.println("Compression finished in " + (System.nanoTime() - startTime) / 1000000 + " ms");
					System.out
							.println("Maximum memory used (MB):" + FileUtils.bytesToMB(Runtime.getRuntime().totalMemory()));
					break;
				default:
					break;
			}
		} else if (input==1) {
			String inputDir = ".";
			String outputDir = "/output";

			// Currently using only ZIP extraction
			CompressionAlgo extractionAlgorithm = CompressionAlgo.ZIP;
			switch (extractionAlgorithm) {
				case ZIP:
					System.out.println("Starting to extract " + inputDir + " to " + outputDir + " using "
							+ CompressionAlgo.ZIP.name() + " extraction");
					long startTime = System.nanoTime();
					System.out.println("Start Time:::: "+ startTime);
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