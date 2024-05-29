package com.fluxit.microexa.template.domain.models;

import com.fluxit.microexa.template.domain.exception.IllegalCounterStateException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder(builderClassName = "Builder")
public class Template {
    private final UUID id;
    private String name;
    @NonNull
    private Long externalReferenceId;

    public Boolean isAvailable() {
        return externalReferenceId > 0;
    }

    public static class Builder {
        public Template build() {
            if (externalReferenceId < 0)
                throw new IllegalCounterStateException("External Reference Id cannot be negative");

            return new Template(id, name, externalReferenceId);
        }
    }

}
