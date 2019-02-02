## Node Tree Exercise - Implementation Notes

This project contains the implementation for the node tree exercise.

The chosen implementation makes use of an in-memory H2 database as a quick way to introduce persistency. 

Also, the relational model selected is believed to be a good fit for the requirements presented.

Regarding load or stress testing mentioned in the exercise specification, one could write some JMeter (or similar) integration tests to actually fire a high level of requests at the API over HTTP and then collect some metrics. For this to be meaningfull, the presistence store should probably be moved to disk first.

Nonetheless, both API calls trigger one DB transaction each (1 per HTTP request) with fairly simple queries involved. This way I have tried to make the API performant by design.

The API responses could potentially be enriched with meta-data (control messages, HATEOS data, etc), but they were kept simple for the purpose of the exercise.

## Node API Documentation

### Resource Information

##### Base URL: http://localhost:9090/nodeapi

#### Node - resource
* id - long
* parent - Node 
* root - Node
* height - integer

#### API Operations
	GET: /nodes/[id]/children
	
		Response List of Node resources 
		Response format JSON


	PUT: /nodes/[id]
		
		Request Body Node resource
		Request format JSON
		Response List of Node resources 
		Response format JSON




