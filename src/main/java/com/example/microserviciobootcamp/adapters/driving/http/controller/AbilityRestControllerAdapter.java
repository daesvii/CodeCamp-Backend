package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddAbilityRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.AbilityHandlerImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ability")
@RequiredArgsConstructor
public class AbilityRestControllerAdapter {
    private final AbilityHandlerImpl abilityHandler;

    @PostMapping("/")
    public ResponseEntity<AbilityResponse> addAbility(@Valid @RequestBody AddAbilityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(abilityHandler.saveAbility(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<AbilityListResponse>> getAllAbilities (@RequestParam(defaultValue = "0") Integer page,
                                                                      @RequestParam(defaultValue = "10") Integer size,
                                                                      @RequestParam (required = false) Optional<Boolean> ascending,
                                                                      @RequestParam (required = false) Optional<String> orderBy){
        return ResponseEntity.ok(abilityHandler.getAllAbilities(page, size, ascending.orElse(false), orderBy.orElse("name")));
    }
}
