package com.example.demo.controller;

import com.example.demo.bean.DataObject;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class DataObjectController {
    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DataObject findById(
            @PathVariable long id, HttpServletRequest req, HttpServletResponse res)
    {
        if (req.getHeader("Test_proxy") != null) {
            res.addHeader("Test_proxy", req.getHeader("Test_proxy"));
        }
        return new DataObject(id, RandomStringUtils.randomAlphanumeric(10));
    }
}