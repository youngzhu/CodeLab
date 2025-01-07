package com.youngzy.groovy.book.gina2.ch12

mailReminder =                                               //#A
'''
Dear ${salutation?salutation+' ':''}$lastname,
another month has passed and it's time for these
<%=tasks.size()%> tasks:
<% tasks.each { %>- $it 
<% } %> 
your collaboration is very much appreciated
'''

//def engine   = new groovy.text.SimpleTemplateEngine()
// 可以清晰地看到转换后的脚本，方便排错
def engine   = new groovy.text.SimpleTemplateEngine(true)   //#B)Listing_12_16_GroovletExample.groovy
def template = engine.createTemplate(mailReminder)
def binding  = [                                            
    salutation: 'Mrs.',                                      //|#B
    lastname  : 'Davis',                                     //|#B
    tasks     : ['visit the Groovy in Action (GinA) page',   //|#B
                 'chat with GinA readers']                   //|#B
]

assert template.make(binding).toString() ==                   //#C
'''
Dear Mrs. Davis,
another month has passed and it's time for these
2 tasks:
- visit the Groovy in Action (GinA) page 
- chat with GinA readers 
 
your collaboration is very much appreciated
'''
//#A Text of template containing placeholders
//#B Variables to substitute in the template
//#C Evaluate the template against the binding
