package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler.IBootcampHandler;
import com.example.microserviciobootcamp.domain.api.IBootcampServicePort;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BootcampHandlerImpl implements IBootcampHandler {
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;
    private final IBootcampServicePort bootcampServicePort;
    @Override
    public BootcampResponse saveBootcamp(AddBootcampRequest request) {
        Bootcamp bootcamp = bootcampRequestMapper.addRequestToBootcamp(request);
        Bootcamp savedBootcamp = bootcampServicePort.saveBootcamp(bootcamp);

        return bootcampResponseMapper.toBootcampResponse(savedBootcamp);
    }

    @Override
    public List<BootcampListResponse> getAllBootcamps(Integer page, Integer size, boolean ascending, String orderBy) {
        List<Bootcamp> listBootcamps = bootcampServicePort.getAllBootcamps(page,size, ascending, orderBy);
        return bootcampResponseMapper.toBootcampResponseList(listBootcamps);
    }
}
