package com.fluxit.microexa.template.domain.models;

import com.fluxit.microexa.template.domain.exception.IllegalCounterStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Template Test Domian")
class TemplateTest {

    private Template createInvalidTemplate(Long externalReferenceId) {
        return Template.builder()
                .id(UUID.randomUUID())
                .name("Test Template")
                .externalReferenceId(externalReferenceId)
                .build();
    }

    @Test
    @DisplayName("when Template is build ok")
    void buidOKTemplateTest() {
        assertNotNull(
                createInvalidTemplate(0L)
        );
    }
    @Test
    @DisplayName("when template does not have external reference id")
    void builderDoesNotHaveCounter() {
        assertThrows(NullPointerException.class,
                () -> Template.builder()
                        .id(UUID.randomUUID())
                        .name("Test Template")
                        .build()
        );
    }

    @Test
    @DisplayName("when template have negative external reference id")
    void builderHaveNegativeExternalReferenceId() {
        assertThrows(IllegalCounterStateException.class,
                () -> createInvalidTemplate(-1L)
        );
    }
    @Test
    @DisplayName("when Template is available")
    void templateIsAvailableTest() {
        assertTrue(
                createInvalidTemplate(1L).isAvailable()
        );
    }
    @Test
    @DisplayName("when Template is not available")
    void templateIsNotAvailableTest() {
        assertFalse(
                createInvalidTemplate(0L).isAvailable()
        );
    }
}
