package com.fluxit.microexa.template.infraestructure.adapters.in.rest.controllers;

import com.fluxit.microexa.template.application.services.IExternalService;
import com.fluxit.microexa.template.application.services.ITemplateService;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos.AdditionalTemplateUserInfoDTO;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos.TemplateRequestDTO;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos.TemplateResponseDTO;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions.BadRequestErrors;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private static final String NOFOUNDMESSAGE=  "Not Found";
    private final ITemplateService templateService;
    private final IExternalService externalService;

    public TemplateController(ITemplateService templateService, IExternalService externalService) {
        this.templateService = templateService;
        this.externalService = externalService;
    }

    @GetMapping
    @Operation(summary = "Get All Templates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of Template",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TemplateResponseDTO.class)
                    )}
            )
    })
    public List<TemplateResponseDTO> getTemplates() {
        return templateService.getAllTemplates().stream().map(TemplateResponseDTO::from).toList();
    }

    @Operation(summary = "Get Template by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Template",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TemplateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = NOFOUNDMESSAGE,
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)) })
    })
    @GetMapping("/{id}")
    public TemplateResponseDTO getTemplate(@PathVariable UUID id) {
        var template = templateService.getTemplateById(id).orElseThrow(()-> new NotFoundException(NOFOUNDMESSAGE));
        return TemplateResponseDTO.from(template);
    }

    @Operation(summary = "Get Additional Template user Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Additional Template user Info",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AdditionalTemplateUserInfoDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)) })
    })
    @GetMapping("/{id}/additionalTemplateUserInfo")
    public AdditionalTemplateUserInfoDTO getAdditionalTemplateUserInfo(@PathVariable UUID id) {
        var template = templateService.getTemplateById(id).orElseThrow(()-> new NotFoundException(NOFOUNDMESSAGE));
        return AdditionalTemplateUserInfoDTO.from(
                externalService.getAdditionalTemplateUserInfo(template.getExternalReferenceId())
        );
    }

    @Operation(summary = "Create a Template")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Template created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TemplateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BadRequestErrors.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TemplateResponseDTO createTemplate(@Valid @RequestBody TemplateRequestDTO templateRequestDTO) {
        return TemplateResponseDTO.from(templateService.createTemplate(templateRequestDTO.toTemplate()));
    }

    @DeleteMapping("/{id}")
    public TemplateResponseDTO deleteTemplate(@PathVariable UUID id) {
        var template = templateService.getTemplateById(id).orElseThrow(()-> new NotFoundException("Not Found"));
        templateService.deleteTemplateById(id);
        return TemplateResponseDTO.from(template);
    }
}
