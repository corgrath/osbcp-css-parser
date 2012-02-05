package com.osbcp.cssparser;

enum Mode {
	INSIDE_SELECTOR,
	INSIDE_COMMENT,
	INSIDE_PROPERTY_NAME,
	INSIDE_VALUE;
}