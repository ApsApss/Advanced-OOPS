package comparator;

import java.util.Comparator;

import driver.Student;

/**
 * This is a comparator for Student elements based on lexicographical order of
 * names
 * 
 * @param <E>
 */
public class CompareNames<E> implements Comparator<E> {

	@Override
	public int compare(E element1, E element2) {
		return ((Student) element1).getName().compareTo(((Student) element2).getName());
	}
}
