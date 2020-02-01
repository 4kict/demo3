package com.example.demo.controller;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    final Sardine sardine;

    public static void main(String[] args) throws IOException {
        new HelloController().hello("");
    }

    public HelloController() {
        sardine = SardineFactory.begin("vasia.kozha", "4MashGrish");
    }

    @GetMapping("local/folder/{path}")
    public String[] getFiles(@PathVariable(required = false) String path) {
        final File file = new File("" + path);
        return file.list();
    }

    @PostMapping("/file/{name}")
    public String hello(@PathVariable String name) throws IOException {
        InputStream fis = new FileInputStream(new File("\\" + name));
        sardine.put("https://webdav.yandex.ru/" + name, fis);

        //        final List<DavResource> davResources = sardine.list("https://webdav.yandex.ru/");
//        System.out.println(davResources.size());
        return "File " + name + " saved";
    }
}
