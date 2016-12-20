package com.file.walker;

import java.io.File;
import java.util.Collection;

public interface IFileWalker {

	public Collection<File> getFileStatistic(String fileOrDirectoryPath);
}
