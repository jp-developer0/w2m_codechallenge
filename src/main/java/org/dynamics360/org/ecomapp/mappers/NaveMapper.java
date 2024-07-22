package org.dynamics360.org.ecomapp.mappers;

import org.dynamics360.org.ecomapp.dtos.NaveDto;
import org.dynamics360.org.ecomapp.persistence.entities.Nave;

/**
 * The NaveMapper class handles the mapping between Nave entity and the Nave DTO
 * @author andyserrato
 */
public class NaveMapper {

    private NaveMapper() {
    }

    /**
     * Converts a Nave entity into a Nave DTO.
     * @param Nave the Nave entity.
     * @return NaveDTO the Nave DTO.
     */
    public static NaveDto toDto(Nave nave) {
        if (nave == null) {
            return null;
        }

        return new NaveDto(
                nave.getId(),
                nave.getNombre(),
                nave.getPelicula()
        );
    }

    /**
     * Converts a NaveDTO to a Nave entity.
     *
     * @param NaveDto the NaveDTO to convert
     * @return the converted Nave entity
     */
    public static Nave toEntity(NaveDto naveDto) {
        if (naveDto == null) {
            return null;
        }

        return new Nave(
                naveDto.id(),
                naveDto.nombre(),
                naveDto.pelicula()
        );
    }
}
