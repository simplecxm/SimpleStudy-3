package com.simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: Simple4H
 * @Date: 2019/03/04 11:50:39
 */
@Controller
@Slf4j
public class HTMLController {

    @GetMapping(value = "/")
    @ResponseBody
    public String index() {
        log.info("abc:{}","abc");
        return "abc";
    }


    @GetMapping(value = "/uploadHTML")
    public String uploadHTML() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/Users/simple/" + file.getOriginalFilename());
            Files.write(path, bytes);
            model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "uploadStatus";
    }
}
