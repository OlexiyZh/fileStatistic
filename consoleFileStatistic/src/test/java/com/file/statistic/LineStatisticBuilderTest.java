package com.file.statistic;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.file.statistic.builder.line.ILineStatisticBuilder;
import com.file.statistic.builder.line.impl.LineStatisticBuilder;
import com.file.statistic.domain.line.LineStatistic;

public class LineStatisticBuilderTest {

	private static ILineStatisticBuilder lineStatisticBuilder;

	@BeforeClass
	public static void setUpClass() {
		lineStatisticBuilder = new LineStatisticBuilder();
	}
	
	@Test
	public void oneWordLine() {
		String line = "oneWord";
		LineStatistic lineStatistic = lineStatisticBuilder.buildLineStatistic(line);
		
		assertEquals(line, lineStatistic.getLine());
		assertEquals(line.length(), lineStatistic.getLineLength());
		assertEquals(line.length(), lineStatistic.getAvarangeWordLength(), 0.001);
		assertEquals(line, lineStatistic.getLongestWord());
		assertEquals(line, lineStatistic.getShortestWord());
	}
	
	@Test
	public void severalWordsLine() {
		String word1 = "oneWord";
		String word2 = "secondWord";
		String word3 = "3word";
		String line = word1 + " " + word2 + " " + word3;
		
		LineStatistic lineStatistic = lineStatisticBuilder.buildLineStatistic(line);
		
		assertEquals(line, lineStatistic.getLine());
		assertEquals(line.length(), lineStatistic.getLineLength());
		assertEquals(7.33, lineStatistic.getAvarangeWordLength(), 0.01);
		assertEquals(word2, lineStatistic.getLongestWord());
		assertEquals(word3, lineStatistic.getShortestWord());
	}
	
	@Test
	public void severalWordsSeveralSpacesLine() {
		String word1 = "This";
		String word2 = "is";
		String word3 = "several";
		String word4 = "spaces";
		String word5 = "separator";
		String line = word1 + "      " + word2 + "           " + word3 + " " + word4 + "  " + word5;
		
		LineStatistic lineStatistic = lineStatisticBuilder.buildLineStatistic(line);
		
		assertEquals(line, lineStatistic.getLine());
		assertEquals(line.length(), lineStatistic.getLineLength());
		assertEquals(5.6, lineStatistic.getAvarangeWordLength(), 0.01);
		assertEquals(word5, lineStatistic.getLongestWord());
		assertEquals(word2, lineStatistic.getShortestWord());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void lineIsNull() {
		lineStatisticBuilder.buildLineStatistic(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void lineIsEmpty() {
		lineStatisticBuilder.buildLineStatistic("");
	}
}
