package com.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.statistic.domain.file.FileStatistic;
import com.file.statistic.service.IFileStatisticService;

@Component
@Path("/file")
public class StatisticResource {
	
	@Autowired
	private IFileStatisticService fileStatisticService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FileStatistic> getFileStatistics() {
		return fileStatisticService.getFileStatistics();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FileStatistic getFileStatistic(@PathParam("id") int id) {
		return fileStatisticService.getFileStatistic(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FileStatistic saveFileStatistic(FileStatistic fileStatistic) {
		return fileStatisticService.saveFileStatistic(fileStatistic);
	}
}
