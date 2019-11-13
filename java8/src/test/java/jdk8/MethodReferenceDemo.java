/**
 * Welcome to https://waylau.com
 */
package jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JDK8:Method Reference
 * 
 * @since 1.0.0 2019年4月21日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
class MethodReferenceDemo {

	/**
	 * @param args
	 */
	@Test
	public void main() {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0);

		// 方法引用
		Integer maxInteger = Collections.max(list, MethodReferenceDemo::compareInteger);

		assertEquals(3, maxInteger.intValue());
	}

	public static int compareInteger(Integer a, Integer b) {
		return a.compareTo(b);
	}
}
