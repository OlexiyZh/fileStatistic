package com.dao.repository;

import java.util.List;

import com.file.statistic.domain.file.FileStatistic;



public interface IFileStatisticRepository {
	public List<FileStatistic> findAll();
	public FileStatistic findById(int id);
	
	public FileStatistic savaeFileStatistic(FileStatistic fileStatistic);
}
