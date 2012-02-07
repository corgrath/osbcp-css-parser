OSBCP CSS Parser
========================================

A simple CSS parser in Java.


Code examples
========================================

As String directly:

	List<Rule> rules = CSSParser.parse("div { width: 100px; -mozilla-opacity: 345; }");
	
Or from a file (using [Apache Commons IO](http://commons.apache.org/io/)):

		String contents = IOUtils.toString(this.getClass().getResourceAsStream("stylesheet.css"));

		List<Rule> rules = CSSParser.parse(contents);
		
To print it  all out:

	for (Rule rule : rules) {
	
		System.out.println(rule);
		
	}
	
Or go into details:

	for (Rule rule : rules) {
	
		// Get all the selectors (such as 'table', 'table td', 'a')
		List<Selector> selectors = rule.getSelectors();
		
		// Get all the property (such as 'width') and its value (such as '100px')	
		List<PropertyValue> propertyValues = rule.getPropertyValues();
	
	}
	
Please view the JavaDoc for more informaton.
	
	
Download
========================================
Download the latest version here:

https://github.com/corgrath/osbcp-css-parser/downloads/

JavaDoc
========================================

http://dl.dropbox.com/u/8183146/persistent/projects/java_osbcp_css_parser/javadoc/index.html

Reporting bugs
========================================

Please create an issue here:

https://github.com/corgrath/osbcp-css-parser/issues

Test coverage
========================================

Version 1.2

![Code coverage](http://dl.dropbox.com/u/8183146/persistent/projects/java_osbcp_css_parser/code_coverage.png "Code coverage")


Code is also checked with [PMD](http://pmd.sourceforge.net/) and [Checkstyle](http://checkstyle.sourceforge.net/).

License
========================================

OSBCP CSS Parser
Copyright 2012 Christoffer Pettersson, christoffer[at]christoffer.me.

Licensed under the Apache License, Version 2.0