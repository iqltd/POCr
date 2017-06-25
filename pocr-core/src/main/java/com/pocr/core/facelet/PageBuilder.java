package com.pocr.core.facelet;

import com.pocr.core.dto.FieldDto;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;


public class PageBuilder {

	private final List<Pair<String, FieldDto>> fields = new ArrayList<>();

	public void addComponent(final String beanName, final FieldDto field) {
		fields.add(new ImmutablePair<>(beanName, field));
	}

	List<Pair<String, FieldDto>> getFields() {
		return fields;
	}
}
