package chocksaway.client;

import chocksaway.model.Picture;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Client("/picture")
public interface PictureClient {
    @Post
    @NonNull
    HttpStatus save(@NonNull @NotNull @Valid Picture picture);

    @NonNull
    @Get
    List<Picture> findAll();
}