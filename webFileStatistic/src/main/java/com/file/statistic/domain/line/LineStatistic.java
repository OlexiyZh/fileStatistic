package com.file.statistic.domain.line;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.file.statistic.domain.file.FileStatistic;



@Entity
@Table(name = "LINE_STAT")
public class LineStatistic {
	
	@Id
	@Column(name = "ID")
	@TableGenerator(name = "LINE_GEN",
					table = "SEQUENCES",
					pkColumnName = "SEQ_NAME",
					valueColumnName = "SEQ_NUMBER",
					pkColumnValue = "LINE",
					allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="LINE_GEN")
	private int id;
	@Column(name = "LINE")
	private String line;
	@Column(name = "LONGEST_WORD")
	private String longestWord;
	@Column(name = "SHORTEST_WORD")
	private String shortestWord;
	@Column(name = "LINE_LENGTH")
	private int lineLength;
	@Column(name = "AVARAGE_LENGTH")
	private Double avarangeWordLength;

	@ManyToOne
	@JoinColumn(name="FILE_STAT_ID")
	private FileStatistic fileStatistic;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public FileStatistic getFileStatistic() {
		return fileStatistic;
	}
	public void setFileStatistic(FileStatistic fileStatistic) {
		this.fileStatistic = fileStatistic;
	}

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

	public Double getAvarangeWordLength() {
		return avarangeWordLength;
	}
	public void setAvarangeWordLength(Double avarangeWordLength) {
		this.avarangeWordLength = avarangeWordLength;
	}

}
