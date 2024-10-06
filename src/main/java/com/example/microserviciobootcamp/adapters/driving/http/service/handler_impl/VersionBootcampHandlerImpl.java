package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddVersionBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IVersionBootcampRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IVersionBootcampResponseMapper;
import com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler.IVersionBootcampHandler;
import com.example.microserviciobootcamp.domain.api.IVersionBootcampServicePort;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VersionBootcampHandlerImpl implements IVersionBootcampHandler {
    private final IVersionBootcampRequestMapper versionBootcampRequestMapper;
    private final IVersionBootcampResponseMapper versionBootcampResponseMapper;
    private final IVersionBootcampServicePort versionBootcampServicePort;

    @Override
    public VersionBootcampResponse saveVersionBootcamp(AddVersionBootcampRequest request) {
        VersionBootcamp bootcamp = versionBootcampRequestMapper.toVersionBootcamp(request);
        VersionBootcamp savedBootcamp = versionBootcampServicePort.saveVersionBootcamp(bootcamp);

        return versionBootcampResponseMapper.toVersionBootcampResponse(savedBootcamp);
    }

    @Override
    public List<VersionBootcampListResponse> getAllVersionsBootcamps(Integer page, Integer size, boolean ascending, String orderBy, Long bootcampId) {
        List<VersionBootcamp> listVersionsBootcamps = versionBootcampServicePort.getAllVersionsBootcamps(page, size, ascending, orderBy, bootcampId);

        return versionBootcampResponseMapper.toVersionBootcampResponseList(listVersionsBootcamps);
    }
}
