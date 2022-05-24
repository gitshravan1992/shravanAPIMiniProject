Feature: To Validate GET response services of "https://hotels4.p.rapidapi.com" (rapidApiUrl="https://hotels4.p.rapidapi.com")

# rapidApiUrl value is coming from config properties value is "https://reqres.in/api"

#------------------------GET/locations------------------------------#

#------------------locations when key not entered---------------#

Scenario Outline: To validate GET response of locations when key is not entered
			
Given user has generated "<token>" token for Authorization
And user has entered below query params
|	query 		|	new york	|
|	locale		|	en_US		|
|	currency	|	USD			|
When user performs GET "<url>/locations/v2/search"
Then user retrieves response status code as <response>
And user validates whether jsons body "<key>" contains value "<value>"

Examples:

 |	  token		| 	   url		|  response | 	key		 |		value	 	|
 |    valid		| 	rapidApiUrl	|	 401	| message	 |	Invalid API key	|
#|   invalid	| 	rapidApiUrl	|	 401	| error		 |	UnAuthorized	|

#------------------locations with valid key---------------#

Scenario Outline: To validate GET response for locations  when key is entered
			
Given user has generated "<token>" token for Authorization
And user has entered below query params
|	query 		|	new york	|
|	locale		|	en_US		|
|	currency	|	USD			|
And user has entered below header params
|	X-RapidAPI-Host		|	hotels4.p.rapidapi.com									|
|	X-RapidAPI-Key		|	a29d5640bamsh270ea91155b90ddp1a090fjsn5f2784b9ad80		|
When user performs GET "<url>/locations/v2/search"
Then user retrieves response status code as <response>
And user validates whether jsons body "<key>" contains value "<value>"

Examples:

 |	  token		| 	   url		|  response | 	key		 |		value	 						|
 |    valid		| 	rapidApiUrl	|	 403	| message	 |	You are not subscribed to this API	|
#|   invalid	| 	rapidApiUrl	|	 401	| error		 |	UnAuthorized						|
