package com.agoda.compress;

import java.io.IOException;

public interface Compressable {

	void compress(String inputDir, String outputDir, Integer fileSize) throws IOException;

	void extract(String inputDir, String outputDir);
}
