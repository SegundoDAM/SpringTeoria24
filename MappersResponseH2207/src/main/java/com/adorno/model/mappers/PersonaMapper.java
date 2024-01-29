package com.adorno.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.adorno.model.dtos.PersonaDTO;
import com.adorno.model.entities.Persona;
//https://www.baeldung.com/spring-data-partial-update

/**
* Este mapper viene de una dependencia mapstruct que es mas compleja
* El destino tiene que tener los valores en el mismo orden (a DTO le faltan algunos pero los que estan
* estan en el mismo orden) y un constructor con los parametros necesarios para crear el objeto
* Al reves habrá campos a null 
* Tiene que haber constructores vacios y constructores adecuados, con los paremetros en el mismo orden
* si hay propiedades que no estan en el parametro de la fuente, estas se pondran a null o 0
* tenemos que tener los getters necesarios, ya que mapper usa getters para establecer un nuevo objeto 
*/
//@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR) 
//@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.FIELD) 
@Mapper(componentModel = "spring") 
public interface PersonaMapper {
	PersonaDTO mapToDTO(Persona persona);
	
	@Mapping(target="id",ignore=true)
	Persona mapToEntity(PersonaDTO personaDTO);
}
