package com.file.statistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.file.statistic.builder.file.IFileStatisticBuilder;
import com.file.statistic.builder.file.impl.FileStatisticBuilder;
import com.file.statistic.builder.line.impl.LineStatisticBuilder;
import com.file.statistic.domain.file.FileStatistic;
import com.file.statistic.domain.line.LineStatistic;

public class FileStatisticBuilderTest {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String FILE_NAME = "TestFile";
	private static IFileStatisticBuilder fileStatisticBuilder;

	@BeforeClass
	public static void setUpClass() {
		fileStatisticBuilder = new FileStatisticBuilder(new LineStatisticBuilder());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void emptyFileName() throws IllegalArgumentException, IOException {
		InputStream stream = getInputStreamFromString("");
		fileStatisticBuilder.buildFileStatistic("", stream);
		stream.close();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullFileName() throws IllegalArgumentException, IOException {
		InputStream stream = getInputStreamFromString("");
		fileStatisticBuilder.buildFileStatistic(null, stream);
		stream.close();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void severalSpacesFileName() throws IllegalArgumentException, IOException {
		InputStream stream = getInputStreamFromString("   ");
		fileStatisticBuilder.buildFileStatistic(null, stream);
		stream.close();
	}
	
	@Test
	public void emptyInputFile() throws IOException {
		InputStream stream = getInputStreamFromString("");
		FileStatistic fileStatistic = fileStatisticBuilder.buildFileStatistic(FILE_NAME, stream);
		stream.close();
		assertTrue(fileStatistic.getLineStatistics().isEmpty());
	}
	
	@Test
	public void oneSpaceInputFile() throws IOException {
		InputStream stream = getInputStreamFromString(" ");
		FileStatistic fileStatistic = fileStatisticBuilder.buildFileStatistic(FILE_NAME, stream);
		stream.close();
		assertTrue(fileStatistic.getLineStatistics().isEmpty());
	}
	
	@Test
	public void severalSpacesInputFile() throws IOException {
		InputStream stream = getInputStreamFromString("   			   ");
		FileStatistic fileStatistic = fileStatisticBuilder.buildFileStatistic(FILE_NAME, stream);
		stream.close();
		assertTrue(fileStatistic.getLineStatistics().isEmpty());
	}
	
	@Test
	public void spacesAndLineSeparatorInputFile() throws IOException {
		InputStream stream = getInputStreamFromString(LINE_SEPARATOR + "   " + LINE_SEPARATOR);
		FileStatistic fileStatistic = fileStatisticBuilder.buildFileStatistic(FILE_NAME, stream);
		stream.close();
		assertTrue(fileStatistic.getLineStatistics().isEmpty());
	}
	
	@Test
	public void lineCountInputFile() throws IOException {
		String line = "this is line 1";
		
		InputStream stream = getInputStreamFromString(line + LINE_SEPARATOR + line + LINE_SEPARATOR + line);
		FileStatistic fileStatistic = fileStatisticBuilder.buildFileStatistic(FILE_NAME, stream);
		stream.close();

		assertEquals(3, fileStatistic.getLineStatistics().size());
	}
	
	@Test
	public void lineOrderInputFile() throws IOException {
		String line1 = "this is line 1";
		String line2 = "simple second line";
		String line3 = "simple line 3";
		
		InputStream stream = getInputStreamFromString(line1 + LINE_SEPARATOR + line2 + LINE_SEPARATOR + line3 + "     ");
		FileStatistic fileStatistic = fileStatisticBuilder.buildFileStatistic(FILE_NAME ,stream);
		stream.close();
		
		List<LineStatistic> lineStatistic = fileStatistic.getLineStatistics();
		assertEquals(3, lineStatistic.size());
		assertEquals(line1, lineStatistic.get(0).getLine());
		assertEquals(line2, lineStatistic.get(1).getLine());
		assertEquals(line3, lineStatistic.get(2).getLine());
	}
	
	private InputStream getInputStreamFromString(String string) {
		return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
	}
}
