package com.vmware.example.sqlfire.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.example.sqlfire.data.CallDao;
import com.vmware.example.sqlfire.domain.Call;
import com.vmware.example.sqlfire.domain.CallStatus;
import com.vmware.example.sqlfire.util.SimpleIdGenerator;
import com.vmware.example.sqlfire.util.SimpleTimestampGenerator;

public class CallService {

	private static final Logger logger = LoggerFactory
			.getLogger(CallService.class);

	@Autowired
	CallDao dao;

	@Autowired
	SimpleIdGenerator idGenerator;

	@Autowired
	SimpleTimestampGenerator timestampGenerator;

	@Transactional
	public String saveCall(Call call) {
		logger.debug("Call to Save: " + call);

		call.setId(idGenerator.getNextId());
		call.setOn(timestampGenerator.getNow());

		dao.saveCall(call);
		logger.debug("Call Saved: " + call);

		return call.getId();
	}

	@Transactional
	public String updateCall(Call call) {
		logger.debug("Call to Update: " + call);

		// make sure the record is still there
		Call currentCall = dao.getCall(call.getId());
		
		if (currentCall != null){
			
			//update the necessary info
			currentCall.setStatus(call.getStatus());
			currentCall.setText(call.getText());
		
			dao.updateCall(currentCall);

			return currentCall.getId();
			
		}else{
			return null;
		}
	}

	public List<Call> getCalls() {
		return dao.getCalls();
	}

	public Call getCall(String id) {
		logger.debug("Id: " + id);
		return dao.getCall(id);
	}

	@Transactional
	public void deleteCall(String id) {
		logger.debug("Id: " + id);
		dao.deleteCall(id);
	}

	public CallStatus[] getCallStatuses() {
		return CallStatus.values();
	}

}
