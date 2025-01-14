package com.youngzy.groovy.book.gina2.ch13

import com.youngzy.groovy.book.gina2.ch13.util.DbUtil

def sql = DbUtil.create()

sql.execute '''
  INSERT INTO Athlete (firstname, lastname, dateOfBirth)
  VALUES ('Paul', 'Tergat', '1969-06-17')
'''                                                                   //#A

def data = [first: 'Khalid', last: 'Khannouchi', birth: '1971-12-22']
def keys = sql.executeInsert """
  INSERT INTO Athlete (firstname, lastname, dateOfBirth)
  VALUES (${data.first}, ${data.last}, ${data.birth})
"""                                                                   //#B
// 只有ID啊，本以为返回了整个记录
//println(keys)
assert keys[0] == [1]                                                 //#1

def insertSql = '''
  INSERT INTO Athlete (firstname, lastname, dateOfBirth)
  VALUES (?,?,?)
'''
def params = ['Ronaldo', 'da Costa', '1970-06-07']
def keyColumnNames = ['ATHLETEID']
keys = sql.executeInsert insertSql, params, keyColumnNames            //#C
println(keys)
assert keys[0] == [ATHLETEID: 2]                                      //#2

sql.close()
//#A Insert using plain statement
//#B GString variant
//#C List of params variant
//#1 Checking generated keys for second row
//#2 Checking generated athleteId key for third row
