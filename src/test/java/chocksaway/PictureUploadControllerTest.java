package chocksaway;

import chocksaway.client.PictureUploadClient;
import chocksaway.utils.MongoDbUtils;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static io.micronaut.http.MediaType.TEXT_PLAIN_TYPE;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@MicronautTest
@TestInstance(PER_CLASS)
public class PictureUploadControllerTest implements TestPropertyProvider {
    @Inject
    PictureUploadClient pictureClient;

    @Test
    void pictureUploadEndpoint() {

        File file;
        try {
            file = File.createTempFile("data", ".zip");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MultipartBody requestBody = MultipartBody.builder()
                .addPart(
                        "file",
                        "data.zip",
                        TEXT_PLAIN_TYPE,
                        file
                ).build();

        HttpStatus status = pictureClient.upload(requestBody);

        System.out.println("tester");

//        pictureClient..toBlocking().exchange<MultipartBody, String>(request, String::class.java)
//        //then
//        assertThat(response.status).isEqualTo(OK)
//        assertThat(response.contentType.get()).isEqualTo(TEXT_PLAIN_TYPE)
//        assertThat(response.body()).isEqualTo("Uploaded")

    }

    @AfterAll
    static void cleanup() {
        MongoDbUtils.closeMongoDb();
    }

    @Override
    public Map<String, String> getProperties() {
        MongoDbUtils.startMongoDb();
        return Collections.singletonMap("mongodb.uri", MongoDbUtils.getMongoDbUri());
    }
}
