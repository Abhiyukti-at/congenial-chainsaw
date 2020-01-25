package com.robomq.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person> {

	public Person mapRow(ResultSet resultSet, int i) throws SQLException {

		Person person = new Person();
		person.setId(resultSet.getInt("id"));
		person.setFirstName(resultSet.getString("fname"));
		person.setLastName(resultSet.getString("lname"));
		person.setAge(resultSet.getInt("age"));
		return person;
	}
}
