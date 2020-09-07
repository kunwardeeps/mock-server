# Mock-server
Create and use custom responses. No need to wait for the backend!

## What is it?
- Spring boot application to mock API requests quickly. 
- Integrated UI makes designing mock requests easier.
- All mock requests are persisted in MongoDB. 
- Can be used to mock both REST and SOAP APIs.

## Installation notes for creating your own server
1. Install MongoDB
1. Run the app using spring boot or create an uber jar to deploy

## Usage
1. Design your API response, customize headers and status
1. Add network delay, if needed
1. Hit submit, your mock link http://localhost:8080/mock/{mockUUID} is ready

## Screenshot
![Screenshot](https://github.com/kunwardeeps/mock-server/raw/master/mock-server/src/images/screenshot.png)
