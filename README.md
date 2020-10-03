# Mock-server
Create and use custom responses. No need to wait for the backend!

**Description** : Create mock response to test your backend or downstream REST calls for any service.
It can be used to integration with any service without calling actul service.


- Spring boot application to mock API requests quickly. 
- All mock response are persisted in MongoDB. 
- Can be used to mock  REST APIs
- Can mock request to be sent to any service.
- Mock response to any request from other service.
- Test Service as a whole with endpoints by mocking request and response for a service.
- Uses ReactiveMongo for db connection.

	- **Technology stack** : Standalone Springboot appliation providing RESTful service written in JAVA 8 with Maven as Build tool. 


## Screenshot
![Screenshot](https://github.com/kunwardeeps/mock-server/raw/master/mock-server/src/images/screenshot.png)

## Dependencies
1. Language: Java 8
2. Database: MongoDb 
3. Framework: SpringBoot
4. Build tool: Maven

## Installation Notes:
1. Install MongoDB or use any cloud service for mongodb.
2. Run the app using spring boot or create an jar to deploy.

## Configuration:
1. Configure mongoDb instance/uri in application.yml

## Usage
1. Design your API response, customize headers and status
2. Add network delay, if needed
3. Hit submit, your mock link http://localhost:8080/mock/{mockUUID} is ready


