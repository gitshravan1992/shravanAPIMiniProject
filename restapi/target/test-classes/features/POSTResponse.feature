Feature: To Validate POST response services of "https://reqres.in/api" (resApiUrl="https://reqres.in/api")

# resApiUrl value is coming from config properties value is "https://reqres.in/api"

#------------------------POST/login------------------------------#

#------------------login when body is not entered---------------#

Scenario Outline: To validate POST response of login when body is not entered
			
Given user has generated "<token>" token for Authorization
When user performs POST "<url>/login"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 400	| error |	Missing email or username	|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|
		
#------------------login where body contains blank/empty username and password---------------#

Scenario Outline: To validate POST response for login where body contains blank/empty username and password
			
Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	email 		|		|
|	password	|		|
When user performs POST "<url>/login"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 400	| error |	Missing email or username	|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|

#------------------login where body contains invalid email and valid password---------------#

Scenario Outline: To validate POST response for login where body contains invalid email and valid password

Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	email 		|	test.com	|
|	password	|	cityslicka	|
When user performs POST "<url>/login"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 400	| error |		user not found			|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|
		
#------------------login where body contains valid email and valid password---------------#

Scenario Outline: To validate POST response for login where body contains valid email and valid password

Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	email 		|	eve.holt@reqres.in	|
|	password	|	cityslicka			|
When user performs POST "<url>/login"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 200	| token |		QpwL5tke4Pnpja7X4		|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|

#-------------------------------------------------------------------------------------------------#


#------------------------POST/register------------------------------#

#------------------register when body is not entered---------------#

Scenario Outline: To validate POST response of register when body is not entered
			
Given user has generated "<token>" token for Authorization
When user performs POST "<url>/register"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 400	| error |	Missing email or username	|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|
		
#------------------register where body contains blank/empty username and password---------------#

Scenario Outline: To validate POST response for register where body contains blank/empty username and password
			
Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	email 		|		|
|	password	|		|
When user performs POST "<url>/register"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 400	| error |	Missing email or username	|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|

#------------------register where body contains invalid email and valid password---------------#

Scenario Outline: To validate POST response for register where body contains invalid email and valid password

Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	email 		|	test.com	|
|	password	|	cityslicka	|
When user performs POST "<url>/register"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value										|
 |    valid		| 	resApiUrl	|	 400	| error |		Note: Only defined users succeed registration	|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 								|
		
#------------------register where body contains valid email and valid password---------------#

Scenario Outline: To validate POST response for register where body contains valid email and valid password

Given user has generated "<token>" token for Authorization
And user has entered json body with below contents
|	email 		|	eve.holt@reqres.in	|
|	password	|	cityslicka			|
When user performs POST "<url>/register"
Then user retrieves response status code as <response>
And user validates whether json body "<key>" has value "<value>"

Examples:

 |	  token		| 	   url		|  response | key	|			value				|
 |    valid		| 	resApiUrl	|	 200	| token |		QpwL5tke4Pnpja7X4		|
#|   invalid	| 	resApiUrl	|	 401	| error	|			UnAuthorized 		|