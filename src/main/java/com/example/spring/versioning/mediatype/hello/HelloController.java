package com.example.spring.versioning.mediatype.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    @GetMapping(path = "hello",
                // headers = "Accept=application/vnd.hello.v1+json",
                produces = "application/vnd.hello.v1+json")
    public HelloV1 getV1()
    {
        return new HelloV1("Version 1");
    }

    @GetMapping(path = "hello",
                // headers = "Accept=application/vnd.hello.v2+json",
                produces = "application/vnd.hello.v2+json")
    public HelloV2 getV2()
    {
        return new HelloV2("Version 2", 2);
    }

    @PostMapping(path = "hello",
                 consumes = "application/vnd.hello.v1+json",
                 produces = "application/vnd.hello.v1+json")
    public HelloV1 postV1(@RequestBody HelloV1 hello)
    {
        return new HelloV1("New Version 1");
    }

    @PostMapping(path = "hello",
                 consumes = "application/vnd.hello.v2+json",
                 produces = "application/vnd.hello.v2+json")
    public HelloV2 postV2(@RequestBody HelloV1 hello)
    {
        return new HelloV2("New Version 2", 22);
    }
}
