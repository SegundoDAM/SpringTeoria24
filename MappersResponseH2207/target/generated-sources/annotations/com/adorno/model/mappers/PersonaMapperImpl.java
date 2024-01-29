package com.adorno.model.mappers;

import com.adorno.model.dtos.PersonaDTO;
import com.adorno.model.entities.Persona;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T10:15:55+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaDTO mapToDTO(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        String nombre = null;
        int edad = 0;

        nombre = persona.getNombre();
        edad = persona.getEdad();

        PersonaDTO personaDTO = new PersonaDTO( nombre, edad );

        return personaDTO;
    }

    @Override
    public Persona mapToEntity(PersonaDTO personaDTO) {
        if ( personaDTO == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setNombre( personaDTO.getNombre() );
        persona.setEdad( personaDTO.getEdad() );

        return persona;
    }
}
