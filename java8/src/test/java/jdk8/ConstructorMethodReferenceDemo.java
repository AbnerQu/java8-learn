/**
 * Welcome to https://waylau.com
 */
package jdk8;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JDK8：Constructor Method Reference.
 * 
 * @since 1.0.0 2019年4月21日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
class ConstructorMethodReferenceDemo {

	@Test
	public void main() {
		Supplier<Employee> sup = ()-> new Employee();
        Employee emp = sup.get();

        //构造器引用
        Supplier<Employee> sup2 = Employee::new;
        Employee emp2 = sup2.get();

        assertEquals(emp.getCompneyName(), emp2.getCompneyName());
	}

}

class Employee {
	public String getCompneyName() {
		return "waylau.com";
	}
}