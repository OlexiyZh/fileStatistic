package com.file.statistic.domain.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.file.statistic.domain.line.LineStatistic;

public class FileStatistic {

	private String name;
	private List<LineStatistic> lineStatistics = new ArrayList<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<LineStatistic> getLineStatistics() {
		return Collections.unmodifiableList(lineStatistics);
	}
	public void addLineStatistic(LineStatistic lineStatistic) {
		if (lineStatistic != null) {
			lineStatistics.add(lineStatistic);
		}
	}
	public void setLineStatistics(List<LineStatistic> lineStatistics) {
		if (lineStatistics != null) {
			this.lineStatistics = lineStatistics;
		}
	}
	
}
