# Databend Handler

This is the implementation of the Databend handler for MindsDB.

## Databend
Databend is a modern cloud data warehouse that empowers your object storage for real-time analytics.
<br>
https://databend.rs/

## Implementation
This handler was implemented using `databend-sqlalchemy` library, the Databend dialect for SQLAlchemy.

The required arguments to establish a connection are,
* `host`: the host name or IP address of the Databend warehouse. NOTE: use \'127.0.0.1\' instead of \'localhost\' to connect to local server.
* `port`: the TCP/IP port of the Databend warehouse.
* `user`: the username used to authenticate with the Databend warehouse.
* `password`: the password to authenticate the user with the Databend warehouse.
* `database`: the database name to use when connecting with the Databend warehouse.

## Usage
In order to make use of this handler and connect to Databend in MindsDB, the following syntax can be used,
~~~~sql
CREATE DATABASE databend_datasource
WITH
engine='databend',
parameters={
    "user": "root",
    "port": 443,
    "password": "password",
    "host": "some-url.aws-us-east-2.default.databend.com",
    "database": "test_db"
};
~~~~

Now, you can use this established connection to query your data warehouse as follows,
~~~~sql
SELECT * FROM databend_datasource.example_tbl
~~~~