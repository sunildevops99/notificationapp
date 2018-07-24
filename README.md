# notificationapp
Testing for webhook concept
notificationapp is a Spring REST Application, which as POST,GET,DELETE,UPDATE APIS

APIS Of Devices : 
     Represents Device details.
   Device attributes:
      deviceId
      deviceName
      
    Create Device
      URL : http://{IP ADDRESS}/devices
      Headers: Content-Type  application/json
      RequestBody:  {
                       "deviceId" : 1,
                       "deviceName" : "test"
                    }                  
      ResponseCode : 200 OK
    
    Get All Device
      URL : http://{IP ADDRESS}/devices
      Headers: Content-Type  application/json
      RequestBody:  [{
                       "deviceId" : 1,
                       "deviceName" : "test"
                    }                  
      ResponseCode : 200 OK   
    
    Update Device By Id
      URL : http://{IP ADDRESS}/devices/{1}
      Headers: Content-Type  application/json
      RequestBody:  {
                       "deviceId" : 1,
                       "deviceName" : "test1223"
                    }                  
      ResponseCode : 200 OK  
      
    Delete Device By Id
      URL : http://{IP ADDRESS}/devices/{1}
      Headers: Content-Type  application/json
      RequestBody:  {
                       "deviceId" : 1,
                       "deviceName" : "test1223"
                    }                  
      ResponseCode : 200 OK      
