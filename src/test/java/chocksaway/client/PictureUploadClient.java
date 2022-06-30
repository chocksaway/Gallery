package chocksaway.client;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;

@Client("/upload")
public interface PictureUploadClient {
    @Post
    @NonNull
    HttpStatus upload(@NonNull MultipartBody file);
}