# Bank ecommerce 

This application exposes a single endpoint to perform a checkout operation, very much in line with a checkout operation for an ecommerce.

# How to run it

To run the tests execute:
```
./gradlew test
```

To build the application which starts the server type the following command:
```
./gradlew run
```

This will start the application which will be accessible at **http://localhost:8080/health**

Health endpoint is an endpoint provided for smoke tests as well as to make sure that gradlew run started the application correctly.

# Using the application

The application comes preloaded with 5 watches as per PDF instructions. You can test the application by command line with curl, for instance:
```
curl -d '["1","2","1","004","3"]' -H "Content-Type: application/json" -X POST http://localhost:8080/checkout
```
This will return as expected:
```
{"price":360} 
```

# Design choices / Future improvements

We don't make choices on what is the best price for the user. This means that, if possible, the discount will always be applied even though may mean that the final price is higher than without the discount.

Currency is stored in their minor unit form. So to store 10, I save it as an integer in the database as 1000 and internally all operations are done in the minor unit form. 
I have not used BigDecimal because there are no divisions nor rounding problems with the given dataset. Future refactorings would be to use BigDecimal instead to store currencies but for this exercise and to keep things simple I have 
 used their minor unit form instead.

I have used hexagonal architecture to develop the application having as benefits to easily test the business logic and to defer technical choices. 

Given that the application is just a simple endpoint I didn't want to start defining aggregates but this should be easy to refactor when we start adding new use cases.
The idea should be to provide a layout following the screaming architecture with domain, application and infrastructure per each aggregate.

Most of the code has been covered by unit tests. There are some functional tests which cover almost the entire application without starting up a server.
I have additionally added a smoke test that starts up the server and checks that everything is correct

There are quite a few corner cases covered though there are some that are not covered, and for production use should be. Namely, I have identified the following improvements:

1.- Limit the total body payload sent by the post operation, maybe up to 100 items?

2.- Improve logging and error handling. If the operation fails only returns status code but there should come along an ErrorDto message containing information about what went wrong

3.- The database is an in-memory database already prepopulated. Further improvement would be to adapt ORM such as exposed or jooQ and use Liquibase or Flyway for database migrations.

4.- The database could be a dockerized MySQL or PostgreSQL to resemble better the database used in production

5.- The integration/smoke tests should be separated from gradlew test command and moved to integration test phase. This will be helpful in a CI pipeline where we want to test integration tests in a latter phase previous to application deployment.

6.- I could have provided more value objects for the watch object, e.g. for the price and for the id so that these values are already checked at creation time









