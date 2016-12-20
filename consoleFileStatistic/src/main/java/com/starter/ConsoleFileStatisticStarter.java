package com.starter;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.repository.IFileStatisticRepository;
import com.file.statistic.domain.file.FileStatistic;
import com.file.statistic.domain.line.LineStatistic;
import com.file.statistic.service.IFileService;

public class ConsoleFileStatisticStarter {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Firstn argument should be path to file or directory");
			return;
		}

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		IFileService fileService = applicationContext.getBean("fileService", IFileService.class);
		IFileStatisticRepository fileStatisticRepository = applicationContext.getBean("fineStatisticRepository", IFileStatisticRepository.class);

		List<FileStatistic> fileStatistics = fileService.getFileStatistic(args[0]);
		
		for (FileStatistic fileStatistic : fileStatistics) {
			System.out.println("-------");
			System.out.println("-------");
			System.out.println("File name = " + fileStatistic.getName());
			for (LineStatistic lineStatistic : fileStatistic.getLineStatistics()) {
				System.out.println("-------");
				System.out.println(lineStatistic.getLine());
				System.out.println("  LineLength = " + lineStatistic.getLineLength());
				System.out.println("  ShortestWord = " + lineStatistic.getShortestWord());
				System.out.println("  LongestWord = " + lineStatistic.getLongestWord());
				System.out.println("  AvarangeWordLength = " + lineStatistic.getAvarangeWordLength());
			}
		}
		
		fileStatisticRepository.savaeFileStatistics(fileStatistics);
	}

}
