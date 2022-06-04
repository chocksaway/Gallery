package com.chocksaway.gallery.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "photos")
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class  Picture {
    private String name;

    @Size(max = 4000)
    @NotBlank
    private String description;

    private Binary image;

    public Picture(String name, String description, Binary image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}