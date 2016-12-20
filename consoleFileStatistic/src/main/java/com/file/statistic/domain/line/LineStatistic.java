package com.file.statistic.domain.line;

public class LineStatistic {

	private String line;
	private String longestWord;
	private String shortestWord;
	private int lineLength;
	private double avarangeWordLength;

	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}

	public String getLongestWord() {
		return longestWord;
	}
	public void setLongestWord(String longestWord) {
		this.longestWord = longestWord;
	}

	public String getShortestWord() {
		return shortestWord;
	}
	public void setShortestWord(String shortestWord) {
		this.shortestWord = shortestWord;
	}

	public int getLineLength() {
		return lineLength;
	}
	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}

	public double getAvarangeWordLength() {
		return avarangeWordLength;
	}
	public void setAvarangeWordLength(double avarangeWordLength) {
		this.avarangeWordLength = avarangeWordLength;
	}

}
