package de.metalcon.middleware.domain.entity;

public class Band extends Entity {
    
    @Override
    public EntityType getEntityType() {
        return EntityType.BAND;
    }
    
    public Band(String name) {
        super(name);
    }
    
}
