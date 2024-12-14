package com.cshop.cs_helper.db;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.constants.MessageConstants;
import com.cshop.cs_helper.helper.LogException;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Transactional
@SuppressWarnings("unchecked")
public class DBHelperEM {

	private static final Logger LOG = LogManager.getFormatterLogger();

	/**
	 * This method is used to save or update record to database if record not
	 * available to database it will save record or update the corresponding record
	 * 
	 * @param objectToSave    an object which is save to database
	 * @param saveDescription an description what is save
	 * @param requestID       an requestID used to trace service call
	 * @return String[2] Array of String. index 0-success/fail 1-reason in case fail
	 */
	public static DatabaseResult saveOrUpdate(Object objectToSave, String description, String requestID) {
		long startTime = System.currentTimeMillis();
		DatabaseResult databaseResult = new DatabaseResult();
		EntityManager entityManager = null;
		try {
			entityManager = DBConfiguration.ENTITY_MANAGER;
			entityManager.getTransaction().begin();

			Object merge = entityManager.merge(objectToSave);

			entityManager.getTransaction().commit();

			if (ObjectUtils.isNotEmpty(merge)) {
				databaseResult.setStatus(CommonConstants.SUCCESS);
			} else {
				databaseResult.setStatus(CommonConstants.FAIL);
			}
		} catch (Exception e) {
			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, requestID));

		} finally {
		}

		long endTime = System.currentTimeMillis();
		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));
		return databaseResult;

	}

	/**
	 * This method is used to save or update list of record to database if record
	 * not available to database it will save record or update the corresponding
	 * record
	 * 
	 * @param objectToSave an list object which is save to database
	 * @param description  an description what to save
	 * @param requestID    an requestID used to trace service call
	 * @return String[2] Array of String. index 0-success/fail 1-reason in case fail
	 */
	@Transactional
	public static DatabaseResult saveOrUpdateRecord(List<Object> objectToSave, String description, String requestID) {

		long startTime = System.currentTimeMillis();
		DatabaseResult databaseResult = new DatabaseResult();

		try {
			for (Object object : objectToSave) {
				Object merge = DBConfiguration.ENTITY_MANAGER.merge(object);
				if (ObjectUtils.isNotEmpty(merge)) {
					databaseResult.setStatus(CommonConstants.SUCCESS);
				} else {
					databaseResult.setStatus(CommonConstants.FAIL);
				}
			}

		} catch (Exception e) {
			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, requestID));
		} finally {
		}
		long endTime = System.currentTimeMillis();
		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));

		return databaseResult;
	}

	/**
	 * This method is used to execute get sql query
	 * 
	 * @param <T>
	 * 
	 * @param sqlQuery       an sql query
	 * @param description    an description of get query
	 * @param entityManager2
	 * @param requestID      an requestID used to trace service call
	 * @return list of records
	 */
	@Transactional
	public static DatabaseResult executeSqlQuery(String sqlQuery, String description, EntityManager entityManager,
			String requestID) {
		long startTime = System.currentTimeMillis();
		DatabaseResult databaseResult = new DatabaseResult();
		EntityTransaction transaction = null;

		try {
			LOG.info(requestID + MessageConstants.QUERY + sqlQuery);
			if (StringUtils.isNotEmpty(sqlQuery)) {
				sqlQuery = sqlQuery.trim();

				if (sqlQuery.startsWith(CommonConstants.SELECT) || sqlQuery.startsWith(CommonConstants.SELECT_C)) {
					LOG.info(requestID + "Session created for query");
					Query query = entityManager.createNativeQuery(sqlQuery);
					LOG.info(requestID + " Query Created");
					List<?> list = query.getResultList();
					LOG.info(requestID + " Records Fetched");
					databaseResult.setNoRowGet(list.size());
					databaseResult.setList(list);
					databaseResult.setStatus(CommonConstants.SUCCESS);
				} else if (sqlQuery.startsWith(CommonConstants.UPDATE) || sqlQuery.startsWith(CommonConstants.UPDATE_C)
						|| sqlQuery.startsWith(CommonConstants.DELETE)
						|| sqlQuery.startsWith(CommonConstants.DELETE_C)) {
					transaction = entityManager.getTransaction();
					transaction.begin();

					LOG.info(requestID + "Session created for query");
					Query query = entityManager.createNativeQuery(sqlQuery);
					LOG.info(requestID + " Query Created");
					int executeUpdate = query.executeUpdate();
					LOG.info(requestID + " Records Fetched");

					transaction.commit();
					LOG.info(requestID + " No of records updated: " + executeUpdate);

					databaseResult.setStatus(CommonConstants.SUCCESS);
					databaseResult.setNoRowUpdated(executeUpdate);
				}
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			LOG.error("{} - Database exception: {}", requestID, e.getMessage());
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, requestID));
		} finally {
			long endTime = System.currentTimeMillis();
			LOG.debug("{} - Execution time: {} ms", requestID, (endTime - startTime));
		}

		return databaseResult;
	}

}