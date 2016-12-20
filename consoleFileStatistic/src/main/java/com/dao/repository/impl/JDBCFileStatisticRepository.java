package com.dao.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dao.repository.IFileStatisticRepository;
import com.file.statistic.domain.file.FileStatistic;
import com.file.statistic.domain.line.LineStatistic;

public class JDBCFileStatisticRepository implements IFileStatisticRepository {
	private static final String FILE_SEQUENCE_NAME = "FILE";
	private static final String LINE_SEQUENCE_NAME = "LINE";
	
	private final String driverName;
	private final String url;
	private final String user;
	private final String password;
	
	public JDBCFileStatisticRepository (String driverName, String url, String user, String password) {
		this.driverName = driverName;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public void savaeFileStatistics(List<FileStatistic> fileStatistics) {

		if (fileStatistics == null || fileStatistics.isEmpty()) {
			return;
		}
		
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			System.out.println("Connection was opened successfully");
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO FILE_STAT (ID, NAME) VALUES (?, ?);");
			for (FileStatistic fileStatistic : fileStatistics) {
				
				int generatedId = sequenceNextVal(connection, FILE_SEQUENCE_NAME);

				ps.setInt(1, generatedId);
				ps.setString(2, fileStatistic.getName());
				ps.execute();
				
				saveLineStatistics(connection, generatedId, fileStatistic.getLineStatistics());
			}
			ps.close();
			
			connection.commit();
			connection.close();
			System.out.println("Connection was closed successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void saveLineStatistics(Connection connection, int fileId, List<LineStatistic> lineStatistics) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO LINE_STAT (ID, LINE, LINE_LENGTH, LONGEST_WORD, "
				+ "SHORTEST_WORD, AVARAGE_LENGTH, FILE_STAT_ID) VALUES (?, ?, ?, ?, ?, ?, "+ fileId + ");");

		for (LineStatistic lineStatistic : lineStatistics) {
			int generatedId = sequenceNextVal(connection, LINE_SEQUENCE_NAME);

			ps.setInt(1, generatedId);
			ps.setString(2, lineStatistic.getLine());
			ps.setInt(3, lineStatistic.getLine().length());
			ps.setString(4, lineStatistic.getLongestWord());
			ps.setString(5, lineStatistic.getShortestWord());
			ps.setDouble(6, lineStatistic.getAvarangeWordLength());
			ps.execute();
		}
		
		ps.close();
	}

	private int sequenceNextVal(Connection connection, String sequenceName) throws SQLException {
		
		Statement selectStmt = connection.createStatement();
		ResultSet rs = selectStmt.executeQuery("SELECT SEQ_NUMBER FROM SEQUENCES WHERE SEQ_NAME = '" + sequenceName + "';");
		rs.next();
		int generatedFileId = rs.getInt("SEQ_NUMBER");
		rs.close();
		selectStmt.close();
		
		Statement updateStmt = connection.createStatement();
		updateStmt.executeUpdate("UPDATE SEQUENCES SET SEQ_NUMBER = " + (generatedFileId + 1) + " WHERE SEQ_NAME = '" + sequenceName + "';");
		updateStmt.close();
		
		return generatedFileId;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		Connection connection = DriverManager.getConnection(url, user, password);

		return connection;
	}
}
