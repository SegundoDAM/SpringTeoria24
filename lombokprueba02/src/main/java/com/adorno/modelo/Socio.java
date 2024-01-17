package com.adorno.modelo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Socio {
	@NonNull
	private String dni;
	@NonNull
	private String nombre;
	private byte edad;
}
