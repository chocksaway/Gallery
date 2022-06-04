package com.chocksaway.gallery.service;

import com.chocksaway.gallery.model.Picture;
import com.chocksaway.gallery.repository.PictureRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;
 
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Mono<Picture> addPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

}
