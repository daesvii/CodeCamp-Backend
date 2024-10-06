package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddAbilityRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IAbilityRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IAbilityResponseMapper;
import com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler.IAbilityHandler;
import com.example.microserviciobootcamp.domain.api.IAbilityServicePort;
import com.example.microserviciobootcamp.domain.model.Ability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AbilityHandlerImpl  implements IAbilityHandler {
    private final IAbilityRequestMapper abilityRequestMapper;
    private final IAbilityResponseMapper abilityResponseMapper;
    private final IAbilityServicePort abilityServicePort;

    @Override
    public AbilityResponse saveAbility(AddAbilityRequest request) {
        Ability ability = abilityRequestMapper.addRequestToAbility(request);
        Ability savedAbility = abilityServicePort.saveAbility(ability);

        return abilityResponseMapper.toAbilityResponse(savedAbility);
    }

    @Override
    public List<AbilityListResponse> getAllAbilities(Integer page, Integer size, boolean ascending, String orderBy) {
        List<Ability> listAbilities = abilityServicePort.getAllAbilities(page,size, ascending, orderBy);
        return abilityResponseMapper.toAbilityResponseList(listAbilities);
    }
}
