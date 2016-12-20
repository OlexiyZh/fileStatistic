package com.file.walker.impl;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import com.file.walker.IFileWalker;

public class FileWalker implements IFileWalker {

	@Override
	public Collection<File> getFileStatistic(String fileOrDirectoryPath) {
		Collection<File> files = null;
		
		File inputFileOrDirectory = new File(fileOrDirectoryPath);
		
		if (inputFileOrDirectory.isDirectory()) {
			files = FileUtils.listFiles(inputFileOrDirectory,  new SuffixFileFilter(".txt"),  DirectoryFileFilter.DIRECTORY);
		} else {
			files = new LinkedHashSet<>();
			files.add(inputFileOrDirectory);
		}
		return files;
	}

}
