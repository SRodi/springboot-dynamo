package com.mapper.dynamo.controller;

import com.mapper.dynamo.dynamo.Dynamo;
import com.mapper.dynamo.model.AppToSchema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class Controller {

    private Dynamo db;

    @PostConstruct
    protected void init() {
        db = new Dynamo();
    }

    @RequestMapping(value = "/read/{appName}", method = RequestMethod.GET)
    public AppToSchema read(@PathVariable String appName) {

        return db.read(appName);
    }

    @RequestMapping(value = "/write", method = RequestMethod.PUT)
    public AppToSchema write(@RequestBody AppToSchema appToSchema) {

        return db.write(appToSchema);
    }

    @RequestMapping(value = "/update/{appName}", method = RequestMethod.POST)
    public AppToSchema update(@PathVariable String appName, @RequestBody AppToSchema.Schema newSchema) {

        return db.update(appName, newSchema);
    }
}
