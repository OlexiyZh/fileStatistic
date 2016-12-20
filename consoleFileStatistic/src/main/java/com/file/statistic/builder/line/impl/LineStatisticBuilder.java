package com.file.statistic.builder.line.impl;

import java.util.Arrays;
import java.util.Comparator;

import com.file.statistic.builder.line.ILineStatisticBuilder;
import com.file.statistic.domain.line.LineStatistic;


public class LineStatisticBuilder implements ILineStatisticBuilder {

	@Override
	public LineStatistic buildLineStatistic(String line) throws IllegalArgumentException {
		
		if (line == null || line.isEmpty()) {
			throw new IllegalArgumentException("Line can't be null and can't be empty");
		}
		
		LineStatistic lineStatistic = new LineStatistic();
		lineStatistic.setLine(line);
		lineStatistic.setLineLength(line.length());
		
		String[] words = splitLineByWords(line);
		
		lineStatistic.setAvarangeWordLength(Arrays.stream(words).mapToInt(String::length).average().getAsDouble());
		lineStatistic.setShortestWord(Arrays.stream(words).min(Comparator.comparing(String::length)).get());
		lineStatistic.setLongestWord(Arrays.stream(words).max(Comparator.comparing(String::length)).get());
		
		return lineStatistic;
	}
	
	private String[] splitLineByWords(String line) {
		return line.split("\\s+");
	}

}
