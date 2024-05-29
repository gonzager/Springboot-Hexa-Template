package com.fluxit.microexa.template.infraestructure.adapters.out.rest;

import com.fluxit.microexa.template.domain.models.AdditionalTemplateUserInfo;
import com.fluxit.microexa.template.domain.ports.out.IExternalServicePort;
import com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions.NotFoundException;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestExternalAdapter implements IExternalServicePort {
    private final RestTemplate restTemplate;

    public RestExternalAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AdditionalTemplateUserInfo getAdditionalTemplateUserInfo(Long externalReferenceId) {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/" + externalReferenceId;
        ResponseEntity<JsonPlaceholderTodo> response = restTemplate.getForEntity(apiUrl, JsonPlaceholderTodo.class);
        JsonPlaceholderTodo todo = response.getBody();

        if (todo == null) {
            throw new NotFoundException("Not Found");
        }

        apiUrl = "https://jsonplaceholder.typicode.com/users/" + todo.getUserId();
        ResponseEntity<JsonPlaceholderUser> userResponse = restTemplate.getForEntity(apiUrl, JsonPlaceholderUser.class);
        JsonPlaceholderUser user = userResponse.getBody();

        if (user == null) {
            throw new NotFoundException("Not Found");
        }

        return new AdditionalTemplateUserInfo(user.getId(), user.getName(), user.getEmail());
    }

    @Data
    private static class JsonPlaceholderTodo {
        private Long id;
        private Long userId;
    }

    @Data
    private static class JsonPlaceholderUser {
        private Long id;
        private String name;
        private String email;
    }
}
