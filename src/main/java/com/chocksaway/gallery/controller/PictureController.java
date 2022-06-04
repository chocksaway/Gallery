package com.chocksaway.gallery.controller;

import com.chocksaway.gallery.model.Picture;
import com.chocksaway.gallery.service.PictureService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class PictureController {
    @Autowired
    PictureService pictureService;

    @PostMapping("/picture/add")
    public Mono<Picture> addPhoto(@RequestParam("name") String name,
                                  @RequestParam("description") String description,
                                  @RequestParam("image") MultipartFile image)
            throws IOException {
        return pictureService.addPicture(Picture.builder().name(name)
                .description(description)
                .image(new Binary(BsonBinarySubType.BINARY, image.getBytes())).build());
    }

    @PostMapping("/picture/foo")
    public String addPhoto(@RequestParam("name") String name) {
        return "tester";
    }
}
