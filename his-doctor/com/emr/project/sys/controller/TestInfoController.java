package com.emr.project.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emr/test"})
public class TestInfoController {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
}
