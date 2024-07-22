package org.dynamics360.org.ecomapp.services;

import org.dynamics360.org.ecomapp.dtos.NaveDto;
import org.dynamics360.org.ecomapp.persistence.entities.Nave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * NaveService is an interface that provides business logic for managing Naves in the e-commerce system.
 * It defines methods for creating, retrieving, updating, and deleting Naves.
 * @author juanpablogonzalez
 */
public interface NaveService {

    /**
     * Retrieves a Nave by its ID.
     *
     * @param naveId the ID of the Nave to retrieve
     * @return the Nave with the given ID, or {@code null} if no such Nave exists
     */
    NaveDto findNaveById(Long naveId);

    /**
     * Creates a new Nave.
     *
     * @param naveDTO the Nave data transfer object containing the details of the Nave to create
     * @return the created Nave
     */
    Nave saveNave(NaveDto naveDTO);

    /**
     * Retrieves a list of all Naves.
     *
     * @return a list of all Naves paged and sorted.
     */
    Page<NaveDto> findAll(PageRequest page);

    /**
     * Checks if a Nave exists by its ID.
     *
     * @param naveId the ID of the Nave to check
     * @return {@code true} if a Nave with the given ID exists, {@code false} otherwise
     */
    boolean existsNaveById(Long naveId);

    /**
     * Deletes a Nave by its ID.
     *
     * @param naveId the ID of the Nave to delete
     */
    void deleteNave(Long naveId);
}
