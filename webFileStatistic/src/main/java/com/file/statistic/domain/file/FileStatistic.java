package com.file.statistic.domain.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.file.statistic.domain.line.LineStatistic;


@Entity
@Table(name = "FILE_STAT")
public class FileStatistic {

	@Id
	@Column(name = "ID")
	@TableGenerator(name = "FILE_GEN",
					table = "SEQUENCES",
					pkColumnName = "SEQ_NAME",
					valueColumnName = "SEQ_NUMBER",
					pkColumnValue = "FILE",
					allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="FILE_GEN")
	private int id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "fileStatistic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LineStatistic> lineStatistics = new ArrayList<>();

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<LineStatistic> getLineStatistics() {
		return Collections.unmodifiableList(lineStatistics);
	}
	public void setLineStatistics(List<LineStatistic> lineStatistics) {
		if (lineStatistics != null) {
			this.lineStatistics = lineStatistics;
			for (LineStatistic lineStatistic : lineStatistics) {
				lineStatistic.setFileStatistic(this);
			}
		}
	}
	public void addLineStatistic(LineStatistic lineStatistic) {
		if (lineStatistic != null) {
			lineStatistic.setFileStatistic(this);
			lineStatistics.add(lineStatistic);
		}
	}

}
