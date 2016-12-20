package com.file.statistic.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.file.statistic.builder.file.IFileStatisticBuilder;
import com.file.statistic.domain.file.FileStatistic;
import com.file.statistic.service.IFileService;
import com.file.walker.IFileWalker;

public class FileService implements IFileService {
	private static final int THREADS_COUNT = 7;

	private IFileWalker fileWalker;
	private IFileStatisticBuilder fileStatisticBuilder;

	public FileService(IFileWalker fileWalker, IFileStatisticBuilder fileStatisticBuilder) {
		this.fileWalker = fileWalker;
		this.fileStatisticBuilder = fileStatisticBuilder;
	}

	@Override
	public List<FileStatistic> getFileStatistic(String fileOrDirectoryPath) {
		Collection<File> files = fileWalker.getFileStatistic(fileOrDirectoryPath);

		ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);

		List<Future<FileStatistic>> futureFileStatistics = new ArrayList<Future<FileStatistic>>();
		for (File file : files) {
			futureFileStatistics.add(executor.submit(new CallableFileStatistic(file)));
		}

		List<FileStatistic> fileStatistics = new ArrayList<>();
		for (Future<FileStatistic> futureFileStatistic : futureFileStatistics) {
			try {
				fileStatistics.add(futureFileStatistic.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();

		return fileStatistics;
	}

	private class CallableFileStatistic implements Callable<FileStatistic> {

		private File file;

		public CallableFileStatistic(File file) {
			this.file = file;
		}

		@Override
		public FileStatistic call() throws Exception {
			StringBuilder sb = new StringBuilder();
			sb.append("Thread [").append(Thread.currentThread().getName()).append("] processing file with path [")
					.append(file.getAbsolutePath()).append("]");
			System.out.println(sb.toString());
			
			return fileStatisticBuilder.buildFileStatistic(file.getName(), new FileInputStream(file));
		}

	};

}
