package com.fluxit.microexa.template.domain.models;

import com.fluxit.microexa.template.domain.exception.IllegalCounterStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Template Test Domian")
class TemplateTest {

    private Template createTemplate(Long externalReferenceId) {
        return Template.builder()
                .id(UUID.randomUUID())
                .name("Test Template")
                .externalReferenceId(externalReferenceId)
                .build();
    }

    private Template createInvalidTemplate() {
        return Template.builder()
                .id(UUID.randomUUID())
                .name("Test Template")
                .build();
    }

    @Test
    @DisplayName("when Template is build ok")
    void buidOKTemplateTest() {
        assertNotNull(
                createTemplate(0L)
        );
    }
    @Test
    @DisplayName("when template does not have external reference id")
    void builderDoesNotExternalReferenceId() {
        assertThrows(NullPointerException.class,
                this::createInvalidTemplate
        );
    }

    @Test
    @DisplayName("when template have negative external reference id")
    void builderHaveNegativeExternalReferenceId() {
        assertThrows(IllegalCounterStateException.class,
                () -> createTemplate(-1L)
        );
    }
    @Test
    @DisplayName("when Template is available")
    void templateIsAvailableTest() {
        assertTrue(
                createTemplate(1L).isAvailable()
        );
    }
    @Test
    @DisplayName("when Template is not available")
    void templateIsNotAvailableTest() {
        assertFalse(
                createTemplate(0L).isAvailable()
        );
    }
}
