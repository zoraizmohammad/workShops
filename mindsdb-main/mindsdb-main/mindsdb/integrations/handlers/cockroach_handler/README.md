# CockroachDB Handler

This is the implementation of the CockroachDB handler for MindsDB.

## CockroachDB

CockroachDB was architected for complex, high performant distributed writes and delivers scale-out read capability. CockroachDB delivers simple relational SQL transactions and obscures complexity away from developers. CockroachDB is wire-compatible with PostgreSQL and provides a familiar, easy interface for developers. For more info check https://www.cockroachlabs.com/docs/

## Implementation

Since, CockroachDB is wire-compatible with PostgreSQL this implementation was pretty straight-forward by just extending PostgreSQL handler.

The required arguments to establish a connection are:

* `host`: the host name or IP address of the CockroachDB
* `database`: the name of the database to connect to
* `user`: the user to authenticate with
* `port`: the port to use when connecting 
* `password`: the password to authenticate the user

## Usage

In order to make use of this handler and connect to a CockroachDB server in MindsDB, the following syntax can be used,

```sql
CREATE DATABASE cockroachdb
WITH
engine='cockroachdb',
parameters={
    "host": "localhost",
    "database": "dbname",
    "user": "admin",
    "password": "password",
    "port": "5432"
};
```

Now, you can use this established connection to query your database as follows:

```sql
SELECT * FROM cockroachdb.public.db;
```
