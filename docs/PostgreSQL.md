## PostgreSQL doc 
### Advanced SQL 
+ Create views - materialized views 
    + Create view 
    + Delete and replace view 
    + Modify a view : `create or replace view... `
    + Materialized view : table that actually contains rows but behaves like a view - occupied space 
    + Materialized view : 
        + `create materialized view view_name as ...`
        + read only : `create materialized view [view_name] as ...`
        + Updatable materialized view 
        + Writeable materialized view 
+ Create cursor 
    + `Declare` : defines and opens a cursor 
    + Using cursor 
    + Close a cursor 
+ Complex topics such as subqueries and joins 
    + Aggregate function : sum, count, avg 
    + condition 
    + Join : left outer join, right outer join , full outer join , self join


### Data manipulation 



### Triggers



### Database design concept


### Transaction and locking


### Index and contraint 


### Table partitioning 



### Query tuning and optimization 



### PostgreSQL extensions and large object support

### Using Java in PostgreSQL 