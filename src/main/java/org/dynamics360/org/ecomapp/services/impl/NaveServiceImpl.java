package org.dynamics360.org.ecomapp.services.impl;

import org.dynamics360.org.ecomapp.dtos.NaveDto;
import org.dynamics360.org.ecomapp.mappers.NaveMapper;
import org.dynamics360.org.ecomapp.persistence.entities.Nave;
import org.dynamics360.org.ecomapp.persistence.repositories.NaveRepository;
import org.dynamics360.org.ecomapp.services.NaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public class NaveServiceImpl implements NaveService {

    public static final String CACHE_NAME = "naves";

    private final NaveRepository naveRepository;

    public NaveServiceImpl(NaveRepository naveRepository) {
        this.naveRepository = naveRepository;
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "#naveId")
    @Override
    public NaveDto findNaveById(Long naveId) {
        Optional<Nave> nave = naveRepository.findById(naveId);
        
        return nave.map(NaveMapper::toDto).orElse(null);
    }

    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    @Override
    public Nave saveNave(NaveDto naveDTO) {
        Nave nave = NaveMapper.toEntity(naveDTO);
        return naveRepository.save(nave);
    }

    @Override
    public Page<NaveDto> findAll(PageRequest page) {
        return naveRepository
                .findAll(page)
                .map(NaveMapper::toDto);
    }

    @Override
    public boolean existsNaveById(Long id) {
        return naveRepository.existsById(id);

    }

    @CacheEvict(cacheNames = CACHE_NAME, key = "#id")
    @Override
    public void deleteNave(Long naveId) {
        naveRepository.deleteById(naveId);
    }
}
