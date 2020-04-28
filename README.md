Playback Validation
===================

This example demonstrates a Validation API to accept Member, Device, and Viewable information as inputs, to evaluate a set of rules to decide whether or not playback should be allowed, and provide a response to the client device.

Contents
--------

The mapping of the URI path space is presented in the following table:

URI path                                 | Resource class      | HTTP methods                                          | Notes
---------------------------------------- | ------------------- | ----------------------------------------------------- | --------------------------------------------------------
**_/playbackresource_**                  | UsersResource       | POST                                                  | Checks to see if playback is possible with the member, device and viewable information provided.
**_/playbackresource/log_**              | UsersResource       | GET                                                   | Returns a list of all the logs for playback validation performed.

API definition can be found in swagger.json

Running the Example
-------------------

This example utilizes Jersey RESTful WebServices. The project is packaged through maven.

#### Building And Testing The Bookmark Service
    
    mvn clean test
    
#### Building And Starting The Bookmark Service

    mvn exec:java
    
#### Sample Request to post for playback validation:

    curl -i -X POST http://localhost:8080/myapp/playbackresource -H 'content-type:application/json' -d '{"userId":4,"deviceType":"tv","viewableId":"starwars"}'
    
    (Output):
    Playback not allowed!
    
#### Sample Request to get log trace:

    curl GET http://localhost:8080/myapp/playbackresource/log 

    (Output):
    6571588115743126 User: steve failed playback becuase of inactive user.
    1588115724529 User: ychen successful playback allowed for viewable: starwars.
    
    
#### Dummy data populated in DB:

    User:
        1    ychen    premium    USA
        2    emily    basic      China
        3    jane     standard   USA
        4    steve    standard   India (inactive)
        5    mark     premium    Spain
        
    Viewable:
        1    walkingdead    basic                 USA, China    
        2    gameofthrone   basic, HD, UltraHD    USA Spain
        3    starwars       HD, UltraHD           USA, China, India, Spain
        4    residentevil   Basic, HD             USA, China
        5    themandalorian UltraHD               USA
