package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddVersionBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.VersionBootcampHandlerImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/version-bootcamp")
@RequiredArgsConstructor
public class VersionBootcampRestControllerAdapter {
    private final VersionBootcampHandlerImpl versionBootcampHandler;

    @PostMapping("/")
    public ResponseEntity<VersionBootcampResponse> addVersionBootcamp(@Valid @RequestBody AddVersionBootcampRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(versionBootcampHandler.saveVersionBootcamp(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<VersionBootcampListResponse>> getAllVersionBootcamps(@RequestParam(defaultValue = "0") Integer page,
                                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                                    @RequestParam (required = false) Optional<Boolean> ascending,
                                                                                    @RequestParam (required = false) Optional<String> orderBy,
                                                                                    @RequestParam (defaultValue = "0", required = false) Optional<Long> bootcampId) {
        return ResponseEntity.ok(versionBootcampHandler.getAllVersionsBootcamps(page, size, ascending.orElse(false), orderBy.orElse("bootcamp.name"), bootcampId.orElse(0L)));
    }
}
