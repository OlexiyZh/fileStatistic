package com.file.statistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.repository.IFileStatisticRepository;
import com.file.statistic.domain.file.FileStatistic;
import com.file.statistic.service.IFileStatisticService;

public class FileStatisticService implements IFileStatisticService {

	@Autowired
	private IFileStatisticRepository fileStatisticRepository;
	
	@Override
	public List<FileStatistic> getFileStatistics() {
		return fileStatisticRepository.findAll();
	}

	@Override
	public FileStatistic getFileStatistic(int fileId) {
		return fileStatisticRepository.findById(fileId);
	}

	@Override
	public FileStatistic saveFileStatistic(FileStatistic fileStatistic) {
		return fileStatisticRepository.savaeFileStatistic(fileStatistic);
	}

}
