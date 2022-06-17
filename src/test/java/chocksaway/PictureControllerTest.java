package chocksaway;

import chocksaway.client.PictureClient;
import chocksaway.model.Picture;
import chocksaway.utils.MongoDbUtils;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.micronaut.http.HttpStatus.CREATED;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@MicronautTest
@TestInstance(PER_CLASS)
public class PictureControllerTest implements TestPropertyProvider {
    @Inject
    PictureClient pictureClient;

    @Test
    void picturesEndpointInteractsWithMongo() {
        List<Picture> pictures = pictureClient.findAll();
        assertTrue(pictures.isEmpty());

        HttpStatus status = pictureClient.save(new Picture("picture001", "picture 001 description"));
        assertEquals(CREATED, status);

        pictures = pictureClient.findAll();
        assertFalse(pictures.isEmpty());
        assertEquals("picture001", pictures.get(0).getName());
        assertEquals("picture 001 description", pictures.get(0).getDescription());

        status = pictureClient.save(new Picture("picture002", "picture 002 description"));
        assertEquals(CREATED, status);

        pictures = pictureClient.findAll();
        assertTrue(pictures.stream().anyMatch(f -> "picture 002 description".equals(f.getDescription())));
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
