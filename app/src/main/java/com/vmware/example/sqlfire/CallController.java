package com.vmware.example.sqlfire;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmware.example.sqlfire.domain.Call;
import com.vmware.example.sqlfire.domain.CallStatus;
import com.vmware.example.sqlfire.domain.Message;
import com.vmware.example.sqlfire.service.CallService;

@Controller
@RequestMapping(value = "/call")
public class CallController {

	private static final Logger logger = LoggerFactory
			.getLogger(CallController.class);

	@Autowired
	CallService service;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody
	Message makeRequest(@RequestBody Call call) {
		return new Message(service.saveCall(call));
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public @ResponseBody
	Message updateRequest(@RequestBody Call call) {
		return new Message(service.updateCall(call));
	}

	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public @ResponseBody
	List<Call> getCalls() {
		return service.getCalls();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Call getCalls(@PathVariable String id) {
		return service.getCall(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Message deleteCalls(@PathVariable String id) {
		service.deleteCall(id);
		return new Message("DELETED");
	}

	@RequestMapping(value = "/status/", method = RequestMethod.GET)
	public @ResponseBody
	CallStatus[] getCallStatuses() {
		return service.getCallStatuses();
	}

	@ExceptionHandler
	public @ResponseBody
	Message handle(Exception e) {
		logger.error(e.getMessage(), e);
		return new Message("Critical server error, see logs for detail");
	}

}
