This project is abandoned
========================================
I am sorry to say but this project is abandoned, and I stopped maintaining it back in 2012, as my focus has shifted from Java.

However, there might be an active fork with improvements. Please check the fork tree below.
https://github.com/corgrath/osbcp-css-parser/network

Again, I am so sorry but I highly appreciate all the stars and forks this project has gotten :-)

-- Christoffer


OSBCP CSS Parser
========================================

A simple CSS parser in Java.

An oh, it is also very strict. If it finds an unexpected character in your CSS it self-destructs, taking you with you - making it a great tool to validate strict CSS.


Code examples
========================================

As String directly:
```java
	List<Rule> rules = CSSParser.parse("div { width: 100px; -mozilla-opacity: 345; }");
```	
Or from a file (using [Apache Commons IO](http://commons.apache.org/io/)):
```java
		String contents = IOUtils.toString(this.getClass().getResourceAsStream("stylesheet.css"));

		List<Rule> rules = CSSParser.parse(contents);
```		
To print it  all out:
```java
	for (Rule rule : rules) {
	
		System.out.println(rule);
		
	}
```	
Or go into details:
```java
	for (Rule rule : rules) {
	
		// Get all the selectors (such as 'table', 'table td', 'a')
		List<Selector> selectors = rule.getSelectors();
		
		// Get all the property (such as 'width') and its value (such as '100px')	
		List<PropertyValue> propertyValues = rule.getPropertyValues();
	
	}
```	
Please view the JavaDoc for more informaton.
	
	
Download
========================================
Download the latest version here:

https://github.com/corgrath/osbcp-css-parser/downloads/

JavaDoc
========================================

http://dl.dropbox.com/u/8183146/persistent/projects/java_osbcp_css_parser/javadoc/index.html

Release to bintray
========================================

```
mvn release:prepare release:perform -Prelease -DskipTests=true -Darguments="-DskipTests=true -Prelease"
```

Reporting bugs
========================================

Please create an issue here:

https://github.com/corgrath/osbcp-css-parser/issues

Test coverage
========================================

Version 1.2

![Code coverage](http://dl.dropbox.com/u/8183146/persistent/projects/java_osbcp_css_parser/code_coverage12.png "Code coverage")


Code is also checked with [PMD](http://pmd.sourceforge.net/) and [Checkstyle](http://checkstyle.sourceforge.net/).


Changes
========================================

v1.5 2012-04-28 Christoffer Pettersson

* The parser can now handle nested comments. 
  Thanks to Justin Marsan (HammHetfield) for reporting the bug.


v1.4 2012-03-11 Christoffer Pettersson

* The parser now gives an error if it founds another colon : while reading the value.
	

v1.3 2012-02-08 Christoffer Pettersson

* It is now possible to have duplicate property names in a rule


v1.2 2012-06-07 Christoffer Pettersson

* Added IncorrectFormatException.ErrorCode for more specific exceptions
* Added support for values with data URIs


v1.1 2012-02-06 Christoffer Pettersson

* While inside the INSIDE_PROPERTY_NAME and a colon : is read, a IncorrectFormatException is thrown 


v1.0 2012-02-05 Christoffer Pettersson
 
* Initial release


License
========================================

OSBCP CSS Parser
Copyright 2012 Christoffer Pettersson, christoffer[at]christoffer.me.

Licensed under the Apache License, Version 2.0
