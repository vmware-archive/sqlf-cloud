var DEBUG = true;

$(document).ready(function(){

	jQuery.fn.log = function (msg) {
		if (DEBUG && window.console)
			console.log("%s: %o", msg, this);
	 	return this;
	};
	
	$.ajaxSetup({
  		crossDomain: true,
		contentType: "application/json",
		error: function(request, status, error) {
			if (request){
				showMessage(request.responseText, true);
				saveButton.log("Error: " + request.responseText);
			}
	    }
	});

	var loger = $("<log>"),
		infoIcon = $("#header-info-icon"),
		infoText = $("#header-info-text"),
		defaultMessage = infoText.html(),
    	infoBox = $("#header-info"),
    	callId = $("#callId"),
    	callOn = $("#callOn"),
    	centerCode = $("#centerCode"),
    	callerName = $("#callerName"),
    	callDetail = $("#callDetail"),
    	callStatus = $("#callStatus"),
    	callQueue = $("#callQueue"),
    	callOnLabel = $("#callOnLabel"),
    	tableRefreshRateLabel = $("#tableRefreshRateLabel"),
		cancelButton = $("#cancelButton").button({ 
			icons: { primary: "ui-icon-cancel" }
		}),
		saveButton = $("#saveButton").button({ 
			icons: { primary: "ui-icon-disk" }
		}),
		deleteButton = $("#deleteButton").button({ 
			icons: { primary: "ui-icon-trash" }
		}),
    	tableRefreshRateVal = refreshUpdate(1),
    	tableRefreshRate = $("#tableRefreshRate");
	
	
	function refreshUpdate(sec){
		tableRefreshRateLabel.html("Refresh table every " + sec + " sec.");
		loger.log("Refreshing on: " + sec + " sec.");
		return sec;
	}
	
	function showMessage(text, alert){
		if(alert){
			infoBox.removeClass("ui-state-highlight")
				    .addClass("ui-state-error");
			infoIcon.removeClass("ui-icon-info")
				    .addClass("ui-icon-alert");
		}else{
			infoBox.removeClass("ui-state-error")
				    .addClass("ui-state-highlight");
			infoIcon.removeClass("ui-icon-alert")
					.addClass("ui-icon-info");
		}
		infoText.html(text);
		return false;
	};
	
	// on row selected
	function toggleButtons(rowSelected){
		if (rowSelected){
			deleteButton.button("enable");
		}else{
			deleteButton.button("disable");
		}
	}
	
	
	function getCallData(onVal){
		return {
		   id: callId.val(),
		   code: centerCode.val(),
		   name: callerName.val(),
		   text: callDetail.val(),
		   status: callStatus.val(),
		   on: onVal | callOn.val()
		};
	}
	
	function clearDetail(){
		toggleButtons(false);
		callId.val("");
		callerName.val("");
		callDetail.val("");
		callStatus.val("OPENED");
		callOn.val("");
		callOnLabel.html("");
	}
	
	function refreshTable(){
		
		makeRequest("GET", "call/list/", null, function(data){
			if (data){
				
				$("tbody", callQueue).empty();
				
				$.each(data, function(i, call) { 
					
					var on = new Date(call.on);
					var html = "<tr class='status-" + call.status.toLowerCase() + "'>";
						html += "<td>" + on.format("longTime") + "</td>";
						html += "<td>" + call.code + "</td>";
						html += "<td>" + call.name + "</td>";
						html += "<td>" + call.status + "</td>";
						html += "</tr>";
						
					var row = $(html).data("call", call)
					                 .click(function(){
					                	 loadCall($(this).data("call"));
					                 });
					
					$("tbody", callQueue).prepend(row);
					
				});
			}
			
			setTimeout(refreshTable, (tableRefreshRateVal * 1000));
		});
	}
	
	function loadCall(call){
		if (call){
			loger.log("Id: " + call.id);
			makeRequest("GET", "call/" + call.id, null, function(data){
				if (data){
					callId.val(data.id);
					centerCode.val(data.code);
					callerName.val(data.name);
					callDetail.val(data.text);
					callStatus.val(data.status);
					callOn.val(data.on);
					
					var createdOn = new Date(data.on);
					callOnLabel.html(createdOn.format("yyyy-mm-dd HH:MM:ss Z"));
					toggleButtons(true);
				}
			});
		}else{
			showMessage("Selected row had no data", true);
		}
	}
	
	function makeRequest(ajaxType, uri, content, callback){
		$.ajax({
		    contentType : "application/json",
		    dataType : 'json',
		    type : ajaxType,
		    url : uri,
		    data : content,
		    success : function(data) {
		    	callback(data);
		    },
		    error : function(msg, status, error) {
		    	
		    	loger.log("msg: " + msg);
		    	loger.log("status: " + status);
		    	loger.log("error: " + error);
		    	
		    	if (msg){
		    		showMessage(msg.text, true);
		    	}else{
		    		showMessage(error, true);
		    	}
		    	callback(null);
		    }
		});
	};
	
	function isEmpty(str) {
	    return (!str || 0 === str.length);
	}
	
	function isBlank(str) {
	    return (!str || /^\s*$/.test(str));
	}
	
	
	function appendToDetail(status, noSpace) {
		 var currentTime = new Date();
		 var newVal = callDetail.val();
		 	 if (!noSpace){
		 		 newVal += "\n\n";
		 	 }
		     newVal += currentTime.format("yyyy-mm-dd HH:MM:ss Z");
		     if (status){
		    	 newVal += " [" + status + "]";
		     }
		     newVal += "\n";
		 callDetail.val(newVal);
	}
	
	

	// cancelButton click
	cancelButton.click(function(){
		clearDetail();
	});
	
	deleteButton.click(function(){
		var call = getCallData(null);
		makeRequest("DELETE", "call/" + call.id, null, function(data){
			if (data){
				loger.log(data.text);
				showMessage("Call deleted");
				clearDetail();
			}
		});
		
	});
	
	// saveButton click
	saveButton.click(function(){
		
		var call = getCallData(new Date());
		var method = "POST";
		
		loger.log("Save or Update on: " + call.id);
		if (!isEmpty(call.id)){
			method = "PUT";
		}

		loger.log("Method: " + method);
		makeRequest(method, "call/", JSON.stringify(call), function(data){
			if (data){
				loger.log(data.text);
				showMessage("Call saved");
				clearDetail();
			}
		});
		
	});
	

	tableRefreshRate.slider({
		value: tableRefreshRateVal,
		min: 0,
		max: 30,
		step: 1,
		slide: function(event, ui) {
			tableRefreshRateVal = refreshUpdate(parseInt(ui.value));
		}
	});
	
	callDetail.dblclick(function() {
		appendToDetail(null);
	});
	
	callStatus.change(function(){
		appendToDetail($(this).val());
	});
	
	callDetail.focus(function() {
		var currentVal = $(this).val();
		if (isEmpty(currentVal)){
			appendToDetail(callStatus.val(), true);
		}
	});
	
	
	
	
	// on load
	
	toggleButtons(false);
	showMessage(defaultMessage);
	refreshTable();

	// load statuses
	makeRequest("GET", "call/status/", null, function(data){
		if (data){
			$.each(data,
               function(i, item) {
				callStatus.append('<option value="' + item + '">' + item.toLowerCase() + '</option>');
            });
		}
	});
	

});



