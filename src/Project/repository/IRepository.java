package Project.repository;

import Project.domain.Entity;

import java.util.List;

public interface IRepository<TEntity extends Entity> {
    void create(TEntity entity);

    List<TEntity> read();

    TEntity read(String idEntity);

    void update(TEntity entity);

    void delete(String idEntity);

    String search(String idEntity);


}
