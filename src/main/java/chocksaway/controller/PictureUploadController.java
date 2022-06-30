package chocksaway.controller;

import com.mongodb.reactivestreams.client.gridfs.GridFSBucket;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.multipart.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;


@Controller("/upload")
class PictureUploadController {

    private static final Logger logger = LoggerFactory.getLogger(PictureUploadController.class);

    private final GridFSBucket bucket;

    PictureUploadController(GridFSBucket bucket) {
        this.bucket = bucket;
    }

    @Post(uri = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA})
    public Mono<HttpStatus> upload(MultipartBody file) {
        System.out.println("tester");
//        String filename = file.getFilename();
//        String  name = file.getName();
//        Optional<MediaType> contentType = file.getContentType();
//        long size = file.getSize();
//        logger.debug("uploading file...\n filename:{},\n name:{},\n contentType: {},\n size: {} ", filename, name, contentType, size);
//        GridFSUploadOptions options = new GridFSUploadOptions();
//        contentType.ifPresent(c -> options.metadata(new Document("contentType", c)));
//        return Mono.from(this.bucket.uploadFromPublisher(
//                                filename,
//                                Mono.from(file).mapNotNull(partData -> {
//                                    try {
//                                        return partData.getByteBuffer();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                    return null;
//                                }),
//                                options
//                        )
//                );
        return Mono.just(HttpStatus.CREATED);
    }
}
