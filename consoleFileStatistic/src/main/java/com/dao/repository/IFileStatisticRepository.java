package com.dao.repository;

import java.util.List;

import com.file.statistic.domain.file.FileStatistic;

public interface IFileStatisticRepository {
	public void savaeFileStatistics(List<FileStatistic> fileStatistics);
}
