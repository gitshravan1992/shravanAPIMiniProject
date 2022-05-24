Feature: To Validate PUT response services of "https://reqres.in/api" (resApiUrl="https://reqres.in/api")

# resApiUrl value is coming from config properties value is "https://reqres.in/api"

#------------------------PUT/users------------------------------#

#------------------users when body is not entered---------------#

Scenario Outline: To validate PUT response of users when body is not entered
			
Given user has generated "<token>" token for Authorization
When user performs PUT "<url>/users/2"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" contains value "<value>"

Examples:

 |	  token		| 	   url		|  response | 	key		 |		value	 |
 |    valid		| 	resApiUrl	|	 200	| updatedAt	 |	curdate	 	 |
#|   invalid	| 	resApiUrl	|	 401	| error		 |	UnAuthorized |
		
#------------------users where body contains name and job---------------#

Scenario Outline: To validate PUT response for users  when body contains name and job
			
Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	name |	morpheus	|
|	job	 |   zion	    |
When user performs PUT "<url>/users/2"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" contains value "<value>"

Examples:

 |	  token		| 	   url		|  response | key		 |    value	 	 |
 |    valid		| 	resApiUrl	|	 200	| updatedAt	 |	curdate	 	 |
 |    valid		| 	resApiUrl	|	 200	| name	 	 |	morpheus     |
 |    valid		| 	resApiUrl	|	 200	| job		 |	zion	     |
#|   invalid	| 	resApiUrl	|	 401	| error		 |	UnAuthorized |
