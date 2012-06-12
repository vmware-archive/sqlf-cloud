<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <link rel="shortcut icon" href="favicon.ico" />
  <title>Call Center - <c:out value="${envId}" /></title>
  <link type="text/css" href="css/theme/ui.css" rel="stylesheet" />  
  <link type="text/css" href="css/app.css" rel="stylesheet" />
  <script type="text/javascript" src="js/dep.js"></script>
  <script type="text/javascript" src="js/app.js"></script>
</head>
<body>
<div class="container ui-corner-all">
    <div class="header">
        <div id="header-title">Global Call Center - <c:out value="${envId}" /></div>
        <div class="ui-widget">
          <div id="header-info" class="ui-state-highlight ui-corner-all"> 
            <p>
              <span id="header-info-icon" class="ui-icon ui-icon-info"></span>
              <span id="header-info-text">Demo illustrating distributed data management in SQLFire.</span>
            </p>
          </div>
        </div>
    </div>
    <div class="content">
    
        <div class="content-left">    


			<h2>Center Details</h2>
			
			<input type="hidden" id="callId" value="" />
			<input type="hidden" id="callOn" value="" />

			<fieldset>
            	<label>Center code:</label>
            	<input type="text" value="<c:out value="${envId}" />" id="centerCode" class="full">
            </fieldset>
            
            <h2>Call Details</h2>
            
            <fieldset>
            	<label>Caller Name:</label>
            	<input type="text" value="" id="callerName" class="full">
            </fieldset>
            
            <fieldset>
            	<label>Status:</label>
            	<select id="callStatus" class="full"></select>
            </fieldset>
            
            <fieldset>
            	<textarea id="callDetail" class="long"></textarea>
            </fieldset>
            
            <fieldset>
            	<label>Created On:</label>
            	<span id="callOnLabel"></span>
            </fieldset>
            
        	<fieldset id="button-ctrl">
				<div>              
	      			<button id="deleteButton">Delete</button>
	      			<button id="saveButton">Save</button>
	      			<button id="cancelButton">Cancel</button>
	      		</div> 
      		</fieldset>     
      		
        </div> 
        
        <div class="content-right">
        
        	<h2>Call Queue</h2>
        	<div>
        		<label id="tableRefreshRateLabel">Refresh queue every...</label>
	            <div id="tableRefreshRate"></div>
        	</div>
			
			<div class="spacer-10"></div>
			    
			<div>
			
				<table id="callQueue" title="Click on individual call to edit its status">
					<thead>
						<tr>
							<th>When</th>
							<th>Where</th>
							<th>Who</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				
			</div>

        </div>

    </div> 
    
    <div class="spacer-20"></div>
    
    
</div>
</body>
</html>