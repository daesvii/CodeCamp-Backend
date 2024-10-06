package com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampResponse;

import java.util.List;

public interface IBootcampHandler {
    BootcampResponse saveBootcamp(AddBootcampRequest request);
    List<BootcampListResponse> getAllBootcamps(Integer page, Integer size, boolean ascending, String orderBy);
}
