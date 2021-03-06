/**
 * 
 */
package com.jjc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.jjc.entity.base.AbstractEntity;

/**
 * @author jjc
 *
 */
@Table
@Entity
public class Teacher extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private String name;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Course> courses = new ArrayList<>();
	
	public Teacher() {
		super();
	}

	public Teacher(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		courses.add(course);
		course.getTeachers().add(this);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
		course.getTeachers().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Teacher teacher = (Teacher) o;
		return Objects.equals(getId(), teacher.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
