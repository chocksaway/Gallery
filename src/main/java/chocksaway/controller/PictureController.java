package chocksaway.controller;

import chocksaway.model.Picture;
import chocksaway.repository.PictureRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static io.micronaut.http.HttpStatus.CONFLICT;
import static io.micronaut.http.HttpStatus.CREATED;

@Controller("/picture")
class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureRepository pictureRepository;

    PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Get
    Publisher<Picture> list() {
        return pictureRepository.list();
    }

    @Post
    Mono<HttpStatus> save(@NonNull @NotNull @Valid Picture picture) {
        return pictureRepository.save(picture)
                .map(added -> added ? CREATED : CONFLICT);
    }
}
