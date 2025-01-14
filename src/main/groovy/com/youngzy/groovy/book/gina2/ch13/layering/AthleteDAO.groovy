package com.youngzy.groovy.book.gina2.ch13.layering

class AthleteDAO extends DataAccessObject {
  List getFields() {
    return [
        'firstname',   'VARCHAR(64)',
        'lastname',    'VARCHAR(64)',
        'dateOfBirth', 'DATE'
    ]
  }
}
