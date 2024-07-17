# Trade Enrichment Service Documentation
## Overview
The Trade Enrichment Service processes trade data from a CSV file, enriches it with product names, and returns the enriched data in CSV format. This document provides instructions on how to run and use the service, details on the API, and possible areas for improvement.

## How to Run the Service
### Build the Project
To build the project, run the following command:

```shell
mvn clean install
```
### Run the Service
To start the service, execute:

```shell
mvn spring-boot:run
```
By default, the service will be accessible at http://localhost:8080.

## How to Use the API
### Enrich Trade Data Endpoint
#### URL: `POST /api/v1/enrich`

**Description**: This endpoint accepts a CSV file containing trade data and returns the enriched trade data with product names in CSV format.

### Request Example
**Sample Trade Data File**

Create a CSV file named `trade.csv` with the following content:

```csv
date,product_id,currency,price
20160101,1,EUR,10.0
20160101,2,EUR,20.1
20160101,3,EUR,30.34
20160101,11,EUR,35.34
```
### CURL Command
To send a request with the CSV file, use the following CURL command:

```shell
curl -F "file=@src/test/resources/trade.csv" http://localhost:8080/api/v1/enrich
```
### Response Example
The response will be a CSV file containing the enriched trade data with product names.

## Limitations of the Code
- **Synchronous implementation**: may lead to increased execution time during I/O operations.
- **Large Datasets**: The current implementation cannot handle large datasets effectively and may run into memory issues.
- **OutOfMemory Errors**: Loading too many rows from the database at once can cause OutOfMemory errors.
- **Error Handling & Logging**: The current error handling and logging mechanisms can be improved to provide more detailed messages.

## Possible Improvements
### Asynchronous Processing
Implementing asynchronous processing will enhance the performance and responsiveness of the service, especially during I/O operations.

### Pagination & Batching
To handle large datasets more efficiently and avoid memory leaks, implement pagination and batching techniques.

### Caching
Introducing a caching layer can reduce the number of database calls and improve response time. 
For applications using multiple instances to handle large amounts of data consider using distributed caching.

### Indexing
Creating an index on the productId field can improve the performance of read operations.

### API Documentation
Add Swagger or OpenAPI for interactive API documentation and to improve the developer experience.

### Testing
Increase the test coverage of the project by adding more JUnit tests and integration tests.

## Additional Information
See the [TASK.md](TASK.md) file for specific instructions related to tasks and requirements.