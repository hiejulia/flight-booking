# Cassandra 
Distributed data store - masterless database, scalability , fault tolerance to single-master database 

### Scalability 
Horizontal scalability 
+ Add more servers to a database cluster 
+ Deployed as a cluster of instances 
+ Rebalancing the existing data 
+ Nodes within the cluster 
+ Add instances - Read and Write throughput will keep increasing linearly

High availability 
+ Has no single point of failure for reading or writing data 

Write optimization 
+ Write throughput 
+ high write throughput 

Structured record 

Secondary index 
Materialized views 
+ server side denormalization 
+ CQL row 
+ Fast lookup 
+ Secondary index and materialized views increases the disk 

Result ordering 
+ Secondary index 
+ Clustering columns 

Immediate consistency 
+ Single master 
+ Distributed system - eventual consistence 
+ Tunable consitence 
Writable collections 
+ Using JSON format 
Relational joins 
MapReduce and Spark 
+ Integrate with Hadoop to perform MapReduce across Cassandra data sets 
+ Spark for real time analysis 
+ Spark: fast,distributed, computational engine used for large-scale data processing - read data from various source : Cassandra,Hadoop, streams as Kafka 
+ Load data from Cassandra into Spark and run batch computations on the data 
Rich and flexible data model 
+ SQL like syntax 
+ CQL 
+ Collections to store multiple items in a single column 
+ Secondary index and materialized views for fast lookup 
Transactions 
+ Read queries 
+ Wrire and Read configure 
Multicenter replication 
Install Cassandra 
+ Install Cassandra using package management 
+ Install CQL shell and its dependency - Python Cassandra driver 
CQL commands : 
+ Create simple keyspace and table 
+ Create keyspace : `CREATE KEYSPACE "users"
WITH REPLICATION = {
  'class': 'SimpleStrategy', 'replication_factor': 1
};` 
+ Specify replication 
+ Select keyspace 
+ Create table 
+ Insert data 
New feature in Cassandra 
+ JSON in CQL 
+ User-defined functions 
+ User-defined aggregates 
+ Role-bases access control 
+ Materialized views 
+ Garbage collections 




The first table 
+ Configure keyspace :
    + Replication strategy - Replication factor - Durable writes 
+ Create user table 
+ Structure the table 
    + Update existing table schema 
    + Table and column option
    + Table properties : 
        + Primary key 
        + Do not support default value 
        + Data validation 
        + Data type : String, Integer, Floating point, Decimal number, Timestamp, UUID, Boolean , Blob, Collection ,Other data type
    + Insert data 
    + Partial insert 
    + Selecting data - select more than one row
    + Retrieve all the row 
    + Paginate the results 


+ Organizing related data 
    + Compound primary key 
    + 
+ Create a table with compound primary key 
+ Compound primary key 