package com.youngzy.groovy.book.gina2.ch13

import com.youngzy.groovy.book.gina2.ch13.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

sql.execute '''
    DROP   VIEW AthleteRecord IF EXISTS;
    CREATE VIEW AthleteRecord AS
      SELECT * FROM Athlete LEFT OUTER JOIN Record
        ON fkAthlete=athleteId;
'''

def records = sql.dataSet('AthleteRecord').findAll {
  it.firstname == 'Khalid'
}
//def rows = records.rows()
println(records.dump())
def result = records.rows().collect { "$it.lastname $it.venue" }
assert ['Khannouchi London', 'Khannouchi Chicago'] == result
println('done')