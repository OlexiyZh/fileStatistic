package com.file.statistic.builder.file;

import java.io.IOException;
import java.io.InputStream;

import com.file.statistic.domain.file.FileStatistic;

public interface IFileStatisticBuilder {
	public FileStatistic buildFileStatistic(String fileName, InputStream file) throws IOException;
}
