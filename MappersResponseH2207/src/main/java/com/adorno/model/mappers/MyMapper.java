package com.adorno.model.mappers;

public interface MyMapper<T, S> {
	public T map(S s);
}
