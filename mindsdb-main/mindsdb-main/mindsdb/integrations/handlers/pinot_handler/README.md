# Apache Pinot Handler

This is the implementation of the Apache Pinot handler for MindsDB.

## Apache Pinot
Apache Pinot is a real-time distributed OLAP database designed for low-latency query execution even at extremely high throughput. Apache Pinot can ingest directly from streaming sources like Apache Kafka and make events available for querying immediately.
<br>
https://www.startree.ai/what-is-apache-pinot
## Implementation
This handler was implemented using the `pinotdb` library, the Python DB-API and SQLAlchemy dialect for Pinot.

The required arguments to establish a connection are,
* `host`: the host name or IP address of the Apache Pinot cluster
* `broker_port`: the port that the Broker of the Apache Pinot cluster is running on
* `controller_port`: the port that the Controller of the Apache Pinot cluster is running on
* `path`: the query path

## Usage
In order to make use of this handler and connect to a Apache Pinot cluster in MindsDB, the following syntax can be used,
~~~~sql
CREATE DATABASE pinot_datasource
WITH
engine='pinot',
parameters={
    "host":"localhost",
    "broker_port": 8000,
    "controller_port": 9000,
    "path": "/query/sql",
    "scheme": "http"
};
~~~~

Now, you can use this established connection to query your database as follows,
~~~~sql
SELECT * FROM pinot_datasource.example_tbl
~~~~

## Quickstart
To quickly spin up Apache Pinot in Docker, run the following command,
~~~~bash
docker run --name pinot-quickstart -p 2123:2123 -p 9000:9000 -p 8000:8000 -d apachepinot/pinot:latest QuickStart -type batch
~~~~

Install MindsDB on your local Python environment,
~~~~bash
pip install mindsdb
~~~~

Launch the MindsDB SQL Editor,
~~~~bash
python -m mindsdb 
~~~~

Execute the following commands to create a data source and query the `baseballStats` table as explained under the Usage section,

~~~~sql
CREATE DATABASE pinot_datasource
WITH
engine='pinot',
parameters={
    "host":"localhost",
    "broker_port": 8000,
    "controller_port": 9000,
    "path": "/query/sql",
    "scheme": "http"
};

SELECT * FROM pinot_datasource.baseballStats
~~~~