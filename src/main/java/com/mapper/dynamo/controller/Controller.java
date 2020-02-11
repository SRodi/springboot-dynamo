package com.mapper.dynamo.controller;

import com.mapper.dynamo.dynamo.Dynamo;
import com.mapper.dynamo.model.TableSchema;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;

@RestController
public class Controller {

    private Dynamo db;

    @PostConstruct
    protected void init() {

        db = new Dynamo();
    }

    @RequestMapping(value = "/read/{name}", method = RequestMethod.GET)
    public TableSchema read(@PathVariable String name) {

        return db.read(name);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String emptyRequest() {

        return db.emptyRequest();
    }

    @RequestMapping(value = "/write", method = RequestMethod.PUT)
    public TableSchema write(@RequestBody TableSchema tableSchema) {

        return db.write(tableSchema);
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String name) {

        return db.delete(name);
    }
}
