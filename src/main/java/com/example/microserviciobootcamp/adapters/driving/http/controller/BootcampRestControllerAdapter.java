package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.BootcampHandlerImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {

    private final BootcampHandlerImpl bootcampHandler;

    @PostMapping("/")
    public ResponseEntity<BootcampResponse> addBootcamp(@Valid @RequestBody AddBootcampRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bootcampHandler.saveBootcamp(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<BootcampListResponse>> getAllBootcamps (@RequestParam(defaultValue = "0") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size,
                                                                       @RequestParam (required = false) Optional<Boolean> ascending,
                                                                       @RequestParam (required = false) Optional<String> orderBy){
        return ResponseEntity.ok(bootcampHandler.getAllBootcamps(page, size, ascending.orElse(false), orderBy.orElse("name")));
    }
}
