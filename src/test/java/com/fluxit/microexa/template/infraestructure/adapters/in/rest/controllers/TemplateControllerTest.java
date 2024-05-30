package com.fluxit.microexa.template.infraestructure.adapters.in.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluxit.microexa.template.application.services.IExternalService;
import com.fluxit.microexa.template.application.services.ITemplateService;
import com.fluxit.microexa.template.domain.models.AdditionalTemplateUserInfo;
import com.fluxit.microexa.template.domain.models.Template;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos.TemplateRequestDTO;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Template Controller Test")
class TemplateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ITemplateService templateService;

    @MockBean
    IExternalService externalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTemplatesIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/template"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getTemplatesIsNotEmpty() throws Exception {
        Mockito.doReturn(Collections.singletonList(
                        Template.builder()
                                .id(UUID.randomUUID())
                                .name("Test")
                                .externalReferenceId(1L)
                                .build()
        )).when(templateService).getAllTemplates();
        mockMvc.perform(MockMvcRequestBuilders.get("/template"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getTemplate() throws Exception {
        var id = UUID.randomUUID();
        Mockito.doReturn(Optional.of(Template.builder()
                .id(id)
                .name("Test Template")
                .externalReferenceId(1L)
                .build())).when(templateService).getTemplateById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/template/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void getTemplateAvailable() throws Exception {
        var id = UUID.randomUUID();
        Mockito.doReturn(Optional.of(Template.builder()
                .id(id)
                .name("Test Template")
                .externalReferenceId(1L)
                .build())).when(templateService).getTemplateById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/template/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void getTemplateNotAvailable() throws Exception {
        var id = UUID.randomUUID();
        Mockito.doReturn(Optional.of(Template.builder()
                .id(id)
                .name("Test Template")
                .externalReferenceId(0L)
                .build())).when(templateService).getTemplateById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/template/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void getTemplateNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/template/" + UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getAdditionalTemplateUserInfo() throws Exception {
        var id = UUID.randomUUID();

        Mockito.doReturn(Optional.of(Template.builder()
                .id(id)
                .name("Test Template")
                .externalReferenceId(123L)
                .build())).when(templateService).getTemplateById(id);

        Mockito.when(externalService.getAdditionalTemplateUserInfo(123L))
                .thenReturn(new AdditionalTemplateUserInfo(0L,"Test", "test@mail.com"));

        mockMvc.perform(MockMvcRequestBuilders.get("/template/" + id + "/additionalTemplateUserInfo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.userName").value("Test"))
                .andExpect(jsonPath("$.userEmail").value("test@mail.com"));
    }

    @Test
    void getAdditionalTemplateUserInfoNotFoundTemplate() throws Exception {
        var id = UUID.randomUUID();

        Mockito.when(externalService.getAdditionalTemplateUserInfo(1L)).thenThrow(NotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/template/" + id + "/additionalTemplateUserInfo"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void createTemplate() throws Exception {
        var id = UUID.randomUUID();
        var template = Template.builder()
                .id(id)
                .name("Test Template")
                .externalReferenceId(1L)
                .build();

        Mockito.doReturn(template).when(templateService).createTemplate(
                Template.builder()
                .name("Test Template")
                .externalReferenceId(1L)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/template")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                TemplateRequestDTO.builder().name("Test Template").externalReferenceId(1L).build()))
                        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void createTemplateBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/template")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TemplateRequestDTO.builder().build())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void deleteTemplate() throws Exception {
        var id = UUID.randomUUID();
        Mockito.doReturn(Optional.of(Template.builder()
                .id(id)
                .name("Test Template")
                .externalReferenceId(1L)
                .build())).when(templateService).getTemplateById(id);

        Mockito.doNothing().when(templateService).deleteTemplateById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/template/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void deleteTemplateNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/template/" + UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}