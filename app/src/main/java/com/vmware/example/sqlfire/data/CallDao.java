package com.vmware.example.sqlfire.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.vmware.example.sqlfire.domain.Call;
import com.vmware.example.sqlfire.domain.CallStatus;

public class CallDao {

	private static final Logger logger = LoggerFactory.getLogger(CallDao.class);

	@Autowired
	JdbcTemplate dataSource;

	final String INSERT_SQL = "INSERT INTO CALL_LOG (CALL_ID,CALL_CENTER,CALLER_NAME,CALL_DETAIL,CALLED_AT,CALL_STATUS) VALUES (?,?,?,?,?,?)";
	final String UPDATE_PK_SQL = "UPDATE CALL_LOG SET CALL_STATUS = ?, CALL_DETAIL = ? WHERE CALL_ID = ?";
	final String UPDATE_SQL = "UPDATE CALL_LOG SET CALL_STATUS = ?, CALL_DETAIL = ? WHERE CALLED_AT = ?";
	final String QUERY_SQL = "SELECT CALL_ID,CALL_CENTER,CALLER_NAME,CALLED_AT,CALL_STATUS FROM CALL_LOG ORDER BY CALLED_AT";
	final String SELECT_SQL = "SELECT * FROM CALL_LOG WHERE CALL_ID = ?";
	final String DELETE_SQL = "DELETE FROM CALL_LOG WHERE CALL_ID = ?";

	public class CallRowMapper implements RowMapper<Call> {

		Boolean mapText;

		public CallRowMapper(Boolean mapText) {
			this.mapText = mapText;
		}

		public Call mapRow(ResultSet rs, int i) throws SQLException {
			Call call = new Call();

			call.setId(rs.getString("CALL_ID"));
			call.setCode(rs.getString("CALL_CENTER"));
			call.setName(rs.getString("CALLER_NAME"));
			call.setOn(rs.getTimestamp("CALLED_AT"));
			call.setStatus(CallStatus.valueOf(rs.getString("CALL_STATUS")));
			if (mapText) {
				call.setText(rs.getString("CALL_DETAIL"));
			}

			return call;
		}

	}

	public void saveCall(Call call) {
		logger.debug("Call: " + call);

		try {
			dataSource.update(INSERT_SQL,
					new Object[] { call.getId(), call.getCode(),
							call.getName(), call.getText(), call.getOn(),
							call.getStatus().toString() });
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void updateCall(Call call) {
		logger.debug("Call: " + call);

		try {
			// TODO: Change back to update by PK when the replication issue is
			// resolved
			dataSource.update(UPDATE_SQL,
					new Object[] { call.getStatus().toString(), call.getText(),
							call.getOn() });
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public List<Call> getCalls() {
		try {
			return dataSource.query(QUERY_SQL, new CallRowMapper(false));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Call getCall(String id) {
		logger.debug("Id: " + id);

		try {
			return dataSource.queryForObject(SELECT_SQL, new Object[] { id },
					new CallRowMapper(true));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void deleteCall(String id) {
		logger.debug("Id: " + id);

		try {
			dataSource.update(DELETE_SQL, new Object[] { id });
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
