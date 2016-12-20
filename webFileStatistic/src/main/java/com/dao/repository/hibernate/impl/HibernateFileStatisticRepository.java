package com.dao.repository.hibernate.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dao.repository.IFileStatisticRepository;
import com.dao.repository.hibernate.util.HibernateUtil;
import com.file.statistic.domain.file.FileStatistic;

public class HibernateFileStatisticRepository implements IFileStatisticRepository {

	
	@Override
	public List<FileStatistic> findAll() {
		List<FileStatistic> fileStatistics = null;
		
		Session session = null;
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();

			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM FileStatistic", FileStatistic.class);
			fileStatistics = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}
		return fileStatistics;
	}

	@Override
	public FileStatistic findById(int id) {
		FileStatistic fileStatistic = null;
		
		Session session = null;
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();

			session = factory.getCurrentSession();
			session.beginTransaction();
			
			fileStatistic = session.get(FileStatistic.class, id);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}
		return fileStatistic;
	}

	@Override
	public FileStatistic savaeFileStatistic(FileStatistic fileStatistic) {
		Session session = null;
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();

			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.save(fileStatistic);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}
		
		return fileStatistic;
	}
}
