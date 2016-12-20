package com.file.statistic.service;

import java.util.List;

import com.file.statistic.domain.file.FileStatistic;



public interface IFileStatisticService {
	public List<FileStatistic> getFileStatistics();
	public FileStatistic getFileStatistic(int fileId);
	
	public FileStatistic saveFileStatistic(FileStatistic fileStatistic);
}
