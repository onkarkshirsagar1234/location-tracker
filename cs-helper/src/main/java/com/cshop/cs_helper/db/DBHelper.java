package com.cshop.cs_helper.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.constants.MessageConstants;
import com.cshop.cs_helper.helper.LogException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SuppressWarnings({ "deprecation", "unchecked" })
public class DBHelper {

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
		Transaction transaction = null;
		Session session = null;
		List record = new ArrayList<>();
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if (!ObjectUtils.isEmpty(objectToSave)) {
				session.saveOrUpdate(objectToSave);
				
			}
			
			transaction.commit();
			record.add(objectToSave);
			databaseResult.setList(record);
			databaseResult.setStatus(CommonConstants.SUCCESS);
		} catch (Exception e) {
			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, requestID));

		} finally {
			transaction = null;
			if (session != null)
				session.close();
			LOG.debug(requestID + "session closed saveOrUpdateRecord");
		}

		long endTime = System.currentTimeMillis();
		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));
		return databaseResult;

	}

	/**
	 * This method is used to save or update record to database if record not
	 * available to database it will save record or update the corresponding record
	 * 
	 * @param objectToSave    an object which is save to database
	 * @param saveDescription an description what is save
	 * @param requestID       an requestID used to trace service call
	 * @return String[2] Array of String. index 0-success/fail 1-reason in case fail
	 */
	public static DatabaseResult saveOrUpdate(List<Object> objectToSave, String description, String requestID) {
		long startTime = System.currentTimeMillis();
		DatabaseResult databaseResult = new DatabaseResult();
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if (!ObjectUtils.isEmpty(objectToSave)) {
				for (Object object : objectToSave) {
					session.saveOrUpdate(object);
				}
			}
			transaction.commit();
		} catch (Exception e) {
			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, requestID));

		} finally {
			transaction = null;
			if (session != null)
				session.close();
			LOG.debug(requestID + "session closed saveOrUpdateRecord");
		}

		long endTime = System.currentTimeMillis();
		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));
		return databaseResult;

	}

	@SuppressWarnings("unchecked")
	public static DatabaseResult executeSqlQuery(String sqlQuery, String description, String requestID) {
		DatabaseResult databaseResult = new DatabaseResult();
		Session session = null;
		Transaction transaction = null;
		List list = null;
		Query query = null;
		try {

			if (sqlQuery.startsWith(CommonConstants.SELECT) || sqlQuery.startsWith(CommonConstants.SELECT_C)) {
				session = HibernateUtil.getSessionFactory().openSession();

				LOG.info(requestID + "Session created for query");
				query = session.createQuery(sqlQuery);

				LOG.info(requestID + "created Query");
				list = query.list();

				LOG.info(requestID + "Records fetched");

				databaseResult.setList(list);
				databaseResult.setStatus(CommonConstants.SUCCESS);

			} else if (sqlQuery.startsWith(CommonConstants.UPDATE) || sqlQuery.startsWith(CommonConstants.UPDATE_C)
					|| sqlQuery.startsWith(CommonConstants.DELETE) || sqlQuery.startsWith(CommonConstants.DELETE_C)) {
				session = HibernateUtil.getSessionFactory().openSession();

				LOG.info(requestID + "Session created for query");
				transaction = session.beginTransaction();

				query = session.createQuery(sqlQuery);
				LOG.info(requestID + "created Query");

				int executeUpdate = query.executeUpdate();
				LOG.info(requestID + "Records fetched");

				transaction.commit();
				LOG.info(requestID + MessageConstants.NO_RECORD_UPDATE + executeUpdate);
				databaseResult.setStatus(CommonConstants.SUCCESS);
				databaseResult.setNoRowUpdated(executeUpdate);
				databaseResult.setList(list);
			}

		} catch (Exception e) {
			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, requestID));
		} finally {
			query = null;
			list = null;
			if (transaction != null) {
				transaction = null;
			}
			if (session != null)
				session.close();
			LOG.debug(requestID + "session closed executeSqlQuery");
		}
		return databaseResult;
	}

	/**
	 * This method is used to get records from database
	 * 
	 * @param <T>
	 * 
	 * @param persistentClass class object
	 * @param criteriaMap     #1. an criteria map pass key value pair e.g
	 *                        "OrderId":"12584" #2.for not equal criteria append !
	 *                        before key. #3.For pagination pass variables
	 *                        1.START_INDEX & 2.END_INDEX
	 * @param description     an get description
	 * @param logId           an logId used to trace service call
	 * @return list of records from database
	 */
	public static DatabaseResult getRecordFromDB(Class persistentClass, HashMap<String, Object> criteriaMap,
			String description, String logId) {
		DatabaseResult databaseResult = new DatabaseResult();
		List records = null;
		long startTime = System.currentTimeMillis();
		Session session = null;
//		Criteria criteria = null;
		CriteriaBuilder criteriaBuilder = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			LOG.debug(logId + "Session created for query get record from db 2");

			criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(persistentClass);
			Root<?> root = criteriaQuery.from(persistentClass);

			List<Predicate> predicates = new ArrayList<Predicate>();

//			criteria = session.createc;
			for (Entry<String, Object> entry : criteriaMap.entrySet()) {
				if (entry.getKey() != null && entry.getValue() != null) {
					
					predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue()));

				}

				// else if
				// (entry.getKey().startsWith(CommonConstants.RESTRICTION_LE)) {
				// /* Restriction less then equals to */
				// criteria.add(
				// Restrictions.le(entry.getKey().substring(1,
				// entry.getKey().length()), entry.getValue()));
				// } else if
				// (entry.getKey().startsWith(CommonConstants.RESTRICTION_GT)) {
				// /* Restriction grater then equals to */
				// criteria.add(
				// Restrictions.ge(entry.getKey().substring(1,
				// entry.getKey().length()), entry.getValue()));
				// } else if
				// (entry.getKey().equals(CommonConstants.ORDER_BY_DESC)) {
				// criteria.addOrder(Order.desc((String) entry.getValue()));
				// } else if
				// (entry.getKey().equals(CommonConstants.ORDER_BY_ASC)) {
				// criteria.addOrder(Order.asc((String) entry.getValue()));
				// } else {
				// /* Restriction equal */
				// criteria.add(Restrictions.eq(entry.getKey(),
				// entry.getValue()));
				// }

			}
			if (!predicates.isEmpty()) {

				criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
				Query<?> query = session.createQuery(criteriaQuery);
				records = query.getResultList();
			}

			databaseResult.setList(records);
			// LOG.debug(logId + MessageConstants.CRITERIA + criteria.toString());
			// fetch records from database
			// records = criteria.list();
			LOG.debug(logId + "Records fetched  get record from db 2");

			if (records != null) {
				databaseResult.setNoRowGet(records.size());
				LOG.debug(logId + "setting no of row get  get record from db 2");
			}
			if (records != null && records.isEmpty()) {
				records = new ArrayList();
			}

			databaseResult.setList(records);
			databaseResult.setStatus(CommonConstants.SUCCESS);
			LOG.debug(logId + "retruning data from get record from db 2");
		} catch (Exception e) {
			LOG.error(logId + MessageConstants.DATABASE_EXCEPTION + description);
			databaseResult.setStatus(CommonConstants.FAIL);
			databaseResult.setExceptionStr(LogException.logException(e, logId));
		} finally {
			if (session != null)
				session.close();
			LOG.debug(logId + "session closed getRecordFromDB");
		}

		long endTime = System.currentTimeMillis();
		LOG.debug(logId + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));

		return databaseResult;

	}
}
