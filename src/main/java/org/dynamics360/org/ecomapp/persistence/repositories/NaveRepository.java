package org.dynamics360.org.ecomapp.persistence.repositories;

import org.dynamics360.org.ecomapp.persistence.entities.Nave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * NaveRepository is an interface for performing CRUD operations on the Nave entity.
 * It extends CrudRepository and PagingAndSortingRepository to provide standard methods for interacting with the database.
 *
 * @author juanGonzalez
 */
 public interface NaveRepository extends CrudRepository<Nave, Long>, PagingAndSortingRepository<Nave, Long> {

}
