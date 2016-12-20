package com.file.statistic.service;

import java.util.List;

import com.file.statistic.domain.file.FileStatistic;

public interface IFileService {
	public List<FileStatistic> getFileStatistic(String fileOrDirectoryPath);
}
