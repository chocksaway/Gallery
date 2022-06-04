package com.chocksaway.gallery;

import com.chocksaway.gallery.model.Picture;
import com.chocksaway.gallery.repository.PictureRepository;
import com.chocksaway.gallery.service.PictureService;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PictureTest {
    @Autowired
    private PictureRepository pictureRepository;

    @Mock
    private PictureRepository pictureRepository2 = Mockito.mock(PictureRepository.class);

    private PictureService pictureService;
    private PictureService pictureService2;

    @BeforeEach
    void initUseCase() {
        pictureService = new PictureService(pictureRepository);
        pictureService2 = new PictureService(pictureRepository2);
    }

    @Test
    public void savedPicture() {
        Picture picture = new Picture("pic001", "description", new Binary("picture".getBytes(StandardCharsets.UTF_8)));
        StepVerifier.create(pictureService.addPicture(picture))
                .consumeNextWith(savedPicture -> {
                    assertEquals("pic001", savedPicture.getName());
                    assertEquals("description", savedPicture.getDescription());
                })
                .verifyComplete();
    }

    @Test
    public void savedUserWithMockito() {
        Picture picture = new Picture("pic001", "description", new Binary("picture".getBytes(StandardCharsets.UTF_8)));

        when(pictureRepository2.save(Mockito.any(Picture.class)))
                .thenReturn(Mono.just(picture));

        StepVerifier.create(pictureService2.addPicture(picture))
                .consumeNextWith(savedPicture -> {
                    assertEquals("pic001", savedPicture.getName());
                    assertEquals("description", savedPicture.getDescription());
                })
                .verifyComplete();
    }

    @Test
    public void testSavePictureInRepository() {
        Picture picture = new Picture("pic21", "description", new Binary("testPicture".getBytes(StandardCharsets.UTF_8)));
        Mono<Picture> pictureMono = pictureRepository.save(picture);

        StepVerifier
                .create(pictureMono)
                .assertNext(account -> {
                    assertEquals("pic21", picture.getName());
                    assertEquals("description" , picture.getDescription());
                })
                .expectComplete()
                .verify();
    }


}
