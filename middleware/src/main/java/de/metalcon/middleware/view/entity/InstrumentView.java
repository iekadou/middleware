package de.metalcon.middleware.view.entity;

import de.metalcon.middleware.domain.entity.EntityType;

public class InstrumentView extends EntityView {
    
    @Override
    public EntityType getEntityType() {
        return EntityType.INSTRUMENT;
    }
    
}