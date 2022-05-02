package com.chocksaway.gallery;

import com.chocksaway.gallery.model.Picture;
import com.chocksaway.gallery.repository.PictureRepository;
import com.chocksaway.gallery.service.PictureService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// reference https://kamalhm.dev/reactive-spring-boot-application-with-r2dbc-and-postgresql

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
        Picture picture = new Picture("pic001", "description");
        StepVerifier.create(pictureService.addPicture(picture))
                .consumeNextWith(savedPicture -> {
                    assertEquals("pic001", savedPicture.getName());
                    assertEquals("description", savedPicture.getDescription());
                })
                .verifyComplete();
    }

    @Test
    public void savedUserWithMockito() {
        Picture picture = new Picture("pic001", "description");

        when(pictureRepository2.save(Mockito.any(Picture.class)))
                .thenReturn(Mono.just(picture));

        StepVerifier.create(pictureService2.addPicture(picture))
                .consumeNextWith(savedPicture -> {
                    assertEquals("pic001", savedPicture.getName());
                    assertEquals("description", savedPicture.getDescription());
                })
                .verifyComplete();
    }


}
