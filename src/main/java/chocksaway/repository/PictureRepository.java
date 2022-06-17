package chocksaway.repository;

import chocksaway.model.Picture;
import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface PictureRepository {
    @NonNull
    Publisher<Picture> list();

    Mono<Boolean> save(@NonNull @NotNull @Valid Picture picture);
}
