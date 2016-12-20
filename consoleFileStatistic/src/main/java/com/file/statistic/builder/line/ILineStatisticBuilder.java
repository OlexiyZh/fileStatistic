package com.file.statistic.builder.line;

import com.file.statistic.domain.line.LineStatistic;

public interface ILineStatisticBuilder  {
	public LineStatistic buildLineStatistic(String line) throws IllegalArgumentException;
}
