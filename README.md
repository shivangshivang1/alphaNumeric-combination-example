# Alphanumeric keyboard combination
 
This app allows user to enter any 7 or 10 digit phone number and on form submission it fetches data uses Spring Boot API 
and display its data with an Angular UI. 
This app is using pagination to display records in multiple page. Number of results on a page is limited to 25 but 
can be changed.

## Getting Started

To install this example application, run the following commands:

```bash
cd alphaNumeric-combination-example
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, 
follow the instructions below.

To run the server, cd into the `server` folder and run:
 
```bash
./mvnw spring-boot:run
```

To run the client, cd into the `client` folder and run:
 
```bash
npm install && npm start
```

## Help
This project except any 7 or 10 digits valid phone number and calculate all possible alpha numeric combination for input.

## License

Apache 2.0, see [LICENSE](LICENSE).
