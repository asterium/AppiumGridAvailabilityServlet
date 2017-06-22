This servlet is aimed to get the list of available devices, that are connected to appium grid

Example request: http://localhost:4444/grid/admin/DeviceAvailabilityServlet

Example response: 
[
   {
      "browserName": "AndroidTablet",
      "busy": {
         "count": 0,
         "deviceNames": []
      },
      "free": {
         "count": 1,
         "deviceNames": [
            "SamsungGalaxyPro"
         ]
      },
      "totalDeviceCount": 1
   },
   {
      "browserName": "AndroidPhone",
      "busy": {
         "count": 1,
         "deviceNames": [
            "Nexus6"
         ]
      },
      "free": {
         "count": 0,
         "deviceNames": []
      },
      "totalDeviceCount": 1
   }
]
