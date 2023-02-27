package Project.domain;

public abstract class Entity {
    protected String id;
    public Entity(String id) {
        this.id = id;
    }

    protected Entity() {
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                '}';
    }
}
