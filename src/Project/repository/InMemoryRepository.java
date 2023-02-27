package Project.repository;

import Project.domain.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InMemoryRepository<TEntity extends Entity> implements IRepository<TEntity> {
    private Map<String, TEntity> storage = new HashMap<>();

    public void create(TEntity entity) {
        if(storage.containsKey(entity.getId())){
            throw new RuntimeException("Exista deja o entitate cu id-ul " + entity.getId());
        }
        this.storage.put(entity.getId(), entity);
    }

    @Override
    public List<TEntity> read() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public TEntity read(String idEntity) {
        return storage.get(idEntity);
    }

    @Override
    public void update(TEntity entity) {
        if (!storage.containsKey(entity.getId())) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul: " + entity.getId());
        }
        this.storage.put(entity.getId(), entity);

    }


    @Override
    public void delete(String idEntity) {
        if (!storage.containsKey(idEntity)) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul: " + idEntity);
        }
        this.storage.remove(idEntity);
    }

    public String search(String input){
        Pattern stringPattern = Pattern.compile(input);
        String gasit = "";
        for (TEntity entityItem : storage.values()) {
            Matcher similaritate = stringPattern.matcher(entityItem.toString());
            if(similaritate.find()) {
                gasit += entityItem.toString() + "\n";
            }
        }
        return gasit;
    }

}
