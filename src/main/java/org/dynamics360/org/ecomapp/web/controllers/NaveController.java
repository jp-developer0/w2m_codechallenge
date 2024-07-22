package org.dynamics360.org.ecomapp.web.controllers;

import org.dynamics360.org.ecomapp.dtos.NaveDto;
import org.dynamics360.org.ecomapp.persistence.entities.Nave;
import org.dynamics360.org.ecomapp.services.NaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.time.LocalDateTime; 
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;


/**
 * NaveController is a REST controller for managing Naves in the e-commerce system.
 * It provides endpoints for creating, reading, updating, and deleting Naves.
 *
 * @author JuanGonzalez
 */

 @Slf4j
 @RestController
 @RequestMapping("/naves")
public class NaveController {

    private NaveService naveService;

    public NaveController(NaveService naveService) {
        this.naveService = naveService;
    }

    /**
     * Retrieve a Nave by its ID.
     *
     * @param naveId the ID of the Nave to retrieve
     * @return a {@code ResponseEntity} containing the requested Nave and HTTP status 200 (OK) if found,
     *         or HTTP status 404 (Not Found) if the Nave does not exist
     */
    
    @GetMapping("/{naveId}")
    public ResponseEntity<NaveDto> getNave(@PathVariable String naveId) {
        NaveDto naveDto = naveService.findNaveById(Long.valueOf(naveId));

        if (naveDto != null) {
            return ResponseEntity.ok(naveDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new Nave.
     *
     * @param nave the Nave to create
     * @return a {@code ResponseEntity} containing the created Nave and HTTP status 201 (Created)
     */
    
    @PostMapping
    public ResponseEntity<Void> createNave(@RequestBody NaveDto nave, UriComponentsBuilder ucb) {
        Nave createdNave = naveService.saveNave(nave);
        URI locationOfNewNave = ucb
                .path("naves/{id}")
                .buildAndExpand(createdNave.getId())
                .toUri();
        //return ResponseEntity.noContent().build();
        return ResponseEntity.created(locationOfNewNave).build();
    }

    /**
     * Retrieve a list of all Naves.
     *
     * @return a {@code ResponseEntity} containing a list of all Naves and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<NaveDto>> getAllNaves(Pageable pageable) {
        Page<NaveDto> naveDtoPage = naveService.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "nombre"))
                )
        );
        log.info("Listar Naves ejecutado a las "+ LocalDateTime.now());
        System.out.println("Listar Naves ejecutado a las "+ LocalDateTime.now());

        return ResponseEntity.ok(naveDtoPage.getContent());
    }

    /**
     * Retrieve a list of all Naves.
     *
     * @return a {@code ResponseEntity} containing a list of all Naves and HTTP status 200 (OK)
     */
    @GetMapping("/like/{naveLike}")
    public ResponseEntity<List<NaveDto>> getAllNaves(@PathVariable String naveLike,Pageable pageable) {
        Page<NaveDto> naveDtoPage = naveService.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "nombre"))
                )
        );

        System.out.println("LIKE: " + naveLike);
        return ResponseEntity.ok(naveDtoPage.getContent());
    }

    /**
     * Update an existing Nave.
     *
     * @param naveId the ID of the Nave to update
     * @param naveDto the Nave details to update
     * @return a {@code ResponseEntity} containing the requested Nave and HTTP status 204 (No Content) if found,
     *         or HTTP status 404 (Not Found) if the Nave does not exist
     */
    @PutMapping("/{naveId}")
    public ResponseEntity<Void> putNave(@PathVariable String naveId, @RequestBody NaveDto naveDto) {
        if (naveService.existsNaveById(Long.valueOf(naveId))) {
            naveService.saveNave(naveDto);
            return ResponseEntity.noContent().build();
        } else {
            log.info("La Nave de ID "+naveId+" no existente. ");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a Nave by its ID.
     *
     * @param naveId the ID of the Nave to delete
     * @return a {@code ResponseEntity} with HTTP status 204 (No Content) if the deletion is successful,
     *         or HTTP status 404 (Not Found) if the Nave does not exist
     */
    
    @DeleteMapping("/{naveId}")
    public ResponseEntity<Void> deleteNave(@PathVariable String naveId) {
        if (naveService.existsNaveById(Long.valueOf(naveId))) {
            naveService.deleteNave(Long.valueOf(naveId));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
