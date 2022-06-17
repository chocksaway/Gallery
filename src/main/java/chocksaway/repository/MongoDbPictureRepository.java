package chocksaway.repository;

import chocksaway.config.MongoDbConfiguration;
import chocksaway.model.Picture;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public class MongoDbPictureRepository implements PictureRepository{
    private final MongoDbConfiguration mongoConf;
    private final MongoClient mongoClient;

    public MongoDbPictureRepository(MongoDbConfiguration mongoConf,
                                  MongoClient mongoClient) {
        this.mongoConf = mongoConf;
        this.mongoClient = mongoClient;
    }

    @Override
    public Mono<Boolean> save(@NonNull @NotNull @Valid Picture picture) {
        return Mono.from(getCollection().insertOne(picture))
                .map(insertOneResult -> true)
                .onErrorReturn(false);
    }

    @Override
    @NonNull
    public Publisher<Picture> list() {
        return getCollection().find();
    }

    @NonNull
    private MongoCollection<Picture> getCollection() {
        return mongoClient.getDatabase(mongoConf.getName())
                .getCollection(mongoConf.getCollection(), Picture.class);
    }
}
