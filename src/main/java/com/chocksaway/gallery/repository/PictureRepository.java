package com.chocksaway.gallery.repository;

import com.chocksaway.gallery.model.Picture;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends ReactiveCrudRepository<Picture, Long> {
}
