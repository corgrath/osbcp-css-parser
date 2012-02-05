OSBCP CSS Parser
========================================

A simple CSS Parser in Java.


Code examples
========================================

As direct String:

	List<Rule> rules = CSSParser.parse("div { width: 100px; -mozilla-opacity: 345; } /* a comment */ beta { height: 200px; display: blocked; } table td { }");
	
Or from a File (using [Apache Commons IO](http://commons.apache.org/io/)):

		String contents = IOUtils.toString(this.getClass().getResourceAsStream("stylesheet.css"));

		List<Rule> rules = CSSParser.parse(contents);
		
To print it  all out:

	for (Rule rule : rules) {
	
		System.out.println(rule);
		
	}
	
Or go into detail:

	for (Rule rule : rules) {
	
		// Get all the selectors (such as 'table', 'table td', 'a')
		List<Selector> selectors = rule.getSelectors();
		
		// Get all the property (such as 'width') and its value (such as '100px')	
		List<PropertyValue> propertyValues = rule.getPropertyValues();
	
	}
	
	
Download
========================================
Download the latest version here:

https://github.com/corgrath/osbcp-css-parser/downloads/

JavaDoc
========================================

To be added.

Test coverage
========================================

Version 1.0


License
========================================

OSBCP CSS Parser
Copyright 2012 Christoffer Pettersson, christoffer[at]christoffer.me.

Licensed under the Apache License, Version 2.0