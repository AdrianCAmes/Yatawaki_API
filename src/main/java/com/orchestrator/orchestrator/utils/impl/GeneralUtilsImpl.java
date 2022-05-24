package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.utils.GeneralUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class GeneralUtilsImpl implements GeneralUtils {
    @Override
    public void mapFields(Object fromObject, Object toObject) throws IllegalAccessException {
        List<Field> fromFields = getAllFields(fromObject);
        List<Field> toFields = getAllFields(toObject);

        for (Field fromField : fromFields) {
            Optional<Field> optionalToField = toFields.stream().filter(field -> field.getName().equalsIgnoreCase(fromField.getName())).findFirst();
            if (optionalToField.isEmpty()) continue;
            fromField.setAccessible(true);
            Object valueFrom = fromField.get(fromObject);
            if (valueFrom == null) continue;
            Field toField = optionalToField.get();
            Class<?> type = toField.getType();
            toField.setAccessible(true);
            toField.set(toObject, type.cast(valueFrom));
        }
    }

    private List<Field> getAllFields(Object object) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, object.getClass().getDeclaredFields());
        Collections.addAll(fields, object.getClass().getSuperclass().getDeclaredFields());
        return fields;
    }
}
