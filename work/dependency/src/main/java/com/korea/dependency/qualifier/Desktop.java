package com.korea.dependency.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("desktop")
public class Desktop implements Computer {
	@Override
	public int getScreenWidth() {
		return 1440;
	}
}
