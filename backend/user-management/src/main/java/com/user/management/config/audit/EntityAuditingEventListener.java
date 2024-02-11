package com.user.management.config.audit;

import jakarta.persistence.PrePersist;

import java.lang.reflect.Field;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.aspectj.ConfigurableObject;

import com.user.management.exception.EntityIdAuditingException;

@Configurable
public class EntityAuditingEventListener implements ConfigurableObject {

    @PrePersist
    public void onPrePersist(Object target) throws NoSuchFieldException,
                    IllegalAccessException, BadRequestException {
        Class<?> entityClass = target.getClass();
        Field idField = null;

        idField = entityClass.getDeclaredField("id");

        if (idField == null) {
            return;
        }
        idField.setAccessible(true);
        Long entityId = (Long) idField.get(target);
        idField.setAccessible(false);

        if (entityId != null) {
            throw new EntityIdAuditingException("Entity_id_001", "entity_id");
        }
    }
}
