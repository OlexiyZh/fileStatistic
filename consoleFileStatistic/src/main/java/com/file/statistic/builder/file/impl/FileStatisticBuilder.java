package com.file.statistic.builder.file.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.file.statistic.builder.file.IFileStatisticBuilder;
import com.file.statistic.builder.line.ILineStatisticBuilder;
import com.file.statistic.domain.file.FileStatistic;



public class FileStatisticBuilder implements IFileStatisticBuilder {
	
	private ILineStatisticBuilder lineStatisticBuilder;
	
	public FileStatisticBuilder(ILineStatisticBuilder lineStatisticBuilder) {
		this.lineStatisticBuilder = lineStatisticBuilder;
	}

	@Override
	public FileStatistic buildFileStatistic(String fileName, InputStream file) throws IOException {
		if (fileName == null || fileName.trim().isEmpty()) {
			throw new IllegalArgumentException("Incorrect file name");
		}
		
		FileStatistic fileStatistic = new FileStatistic();
		fileStatistic.setName(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
	    
	    String line;
	    while ((line = reader.readLine()) != null) {
	    	line = line.trim();
	    	if (!line.isEmpty()) {
	    		fileStatistic.addLineStatistic(lineStatisticBuilder.buildLineStatistic(line));
	    	}
	    }
		
		return fileStatistic;
	}

}
