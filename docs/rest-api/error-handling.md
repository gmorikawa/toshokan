# REST API - Error Handling

When a exception is raised and catched by the system, it should return to the client a object that describes the error without exposing sensitive information.

The returned object have the following information:

* Name of the exception.
* Message.
* HTTP Status Code.
* Exception raise time and date.
