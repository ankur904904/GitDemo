Feature: Validating Place API's

@AddPlace1
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
          Given Add Place PayLoad with "<name>" "<language>" "<address>"
          When user calls "AddPlaceAPI" with "POST" http request
          Then the API call is success with status code "200"
          And "status" in response body is "OK"
          And "scope" in response body is "APP"
          And verify place_id created maps to "<name>" using "GetPlaceAPI"
          
 Examples:
 	|name   |language |address				  |
 	|Amaira |English  |#1165 Sector 80-Mohali |
# 	|Kritika |English  |#1165 Sector 80-Mohali|

@DeletePlace
Scenario: Verify if Delete Place functionality is working

		Given DeletePlace PayLoad
		When user calls "DeletePlaceAPI" with "POST" http request
		Then the API call is success with status code "200"
		 And "status" in response body is "OK"