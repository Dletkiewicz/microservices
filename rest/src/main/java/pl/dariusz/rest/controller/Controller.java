package pl.dariusz.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import pl.dariusz.rest.entity.UserDto;

import java.util.Optional;

@RestController
@RequestMapping("/rest/v1")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public Page<UserDto> getUsers() {
        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<UserDto>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/users/{id}")
    public Optional<UserDto> getUser(@RequestParam Long id){
        ResponseEntity<Optional<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Optional<UserDto>>() {}
        );
        return response.getBody();
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://db/db/v1/users",
                HttpMethod.POST,
                new HttpEntity<>(userDto),
                new ParameterizedTypeReference<String>() {
                }
        );
        return ResponseEntity.status(response.getStatusCode()).headers(response.getHeaders()).body(response.getBody());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> addUser(@PathVariable Long id) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://db/db/v1/users/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {},
                id
        );
        return response;
    }

}
