package chocksaway.model;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;

import javax.validation.constraints.NotBlank;

@Introspected
public class Picture {

    @NonNull
    @NotBlank
    @BsonProperty("name")
    private final String name;

    @Nullable
    @BsonProperty("description")
    private final String description;

    @BsonProperty("image")
    private Binary image;

    @Creator
    @BsonCreator
    public Picture(@NonNull @BsonProperty("name") String name,
                   @Nullable @BsonProperty("description") String description,
                   @Nullable @BsonProperty("image") Binary image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Picture(@NonNull @BsonProperty("name") String name,
                   @Nullable @BsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }


    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public Binary getImage() {
        return image;
    }
}