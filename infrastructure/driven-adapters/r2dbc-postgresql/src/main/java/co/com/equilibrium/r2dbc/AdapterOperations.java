package co.com.equilibrium.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.function.Function;

public class AdapterOperations <E, D, I ,R extends ReactiveCrudRepository<D, I>>{
    protected R repository;
    protected Function<E,D>  toData;
    protected Function<D, E> toEntity;

    public AdapterOperations (R repository, Function<E,D>  toData, Function<D, E> toEntity){
        this.repository = repository;
        this.toData = toData;
        this.toEntity = toEntity;
    }

    protected Mono<E> doQuery(Mono<D> query){
        return query.map(this::convertToEntity);
    }

    protected Mono<D> saveData(D data){
        return repository.save(data);
    }

    protected E convertToEntity(D data){
        return toEntity.apply(data);
    }

    protected D convertToData(E entity){
        return toData.apply(entity);
    }
}