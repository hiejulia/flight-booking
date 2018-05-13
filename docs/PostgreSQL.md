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
    + Join : left outer join, right outer join , full outer join , self join, union join
    + Sub query
### Data manipulation 
+ Data type 
    + convert datatype : cast 
+ Manage and use arrays 
    + Array constructors 
    + Array 
    + Multi dimensional array
    + String_to_array()
    + Array_dims()
    + Array_agg() : concatenate values 
    + Array_upper()
    + Array_length()
    + Array slicing and splicing 
    + Unnesting arrays to row 
+ Manage XML and JSON data 
    + JSON : json and jsonb 
    + Insert JSON data in PostgreSQL 
    + Query JSON 
    + Containment 
    + Key/ element existence 
    + Output JSON 
+ Composite datatypes 




### Triggers



### Database design concept


### Transaction and locking


### Index and contraint 


### Table partitioning 



### Query tuning and optimization 



### PostgreSQL extensions and large object support

### Using Java in PostgreSQL 