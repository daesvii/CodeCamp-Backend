package com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddVersionBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampResponse;

import java.util.List;

public interface IVersionBootcampHandler {
    VersionBootcampResponse saveVersionBootcamp(AddVersionBootcampRequest request);
    List<VersionBootcampListResponse> getAllVersionsBootcamps(Integer page, Integer size, boolean ascending, String orderBy, Long bootcampId);
}
