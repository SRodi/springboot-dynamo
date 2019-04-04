### DyamoDB mapper
- custom object Schema will be stored in AWS DynamoDb as Map datatype
- arraylist of custom objects is stored as List datatype

#### Functionalities:

##### Create table entry
endpoint:
`PUT http://localhost:8083/write/`

request body:
```
{
    "appName": "your_app_name",
    "schema": {
        "schemaString": "your_schema_string"
    }
}
```

##### Read table entry
endpoint:
`GET http://localhost:8083/read/{appName}`

request body:
```
{
    "appName": "your_app_name",
    "schema": {
        "schemaString": "your_schema_string"
    }
}
```

##### Update table entry
endpoint:
`POST http://localhost:8083/update/{appName}`

request body:
```
{
    "schemaString": "your_new_schema_string"
}
```

##### Delete table entry
endpoint:
`DELETE http://localhost:8083/delete/{appName}`

### Sample GET response:
response body:
```
{
    "appName": "app_name",
    "schemas": [
        {
            "version": 2,
            "schemaString": "tdddt"
        },
        {
            "version": 3,
            "schemaString": "eff334kk"
        }
    ],
    "schema": {
        "version": 1,
        "schemaString": "mosn--SCHEMA--2#@qq23"
    }
}
```