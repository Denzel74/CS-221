package lesson;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Tests for BrowserHistory
 *
 * Fairly complete set of tests. Feel free to add additional tests.
 * 
 * @author krodgers, Denzel Balbosa
 */

public class BrowserHistoryTest {

	private final String ELEMENT = "dinosaurs.com";

	// specifies if the head or tail is the top of the stack
	// set to true if the head of your DLL is the top of the stack
	// set to false if the tail of your DLL is the top of the stack
	private final boolean HEAD_IS_TOP = true;

	
	//////////////////////////////////
	// DataProvider for Valid tests //
	//////////////////////////////////

	/**
	 * Creates an empty list, 1 element list, 2 element list, and 3
	 * element list
	 */
	@DataProvider(name = "testData")
	public Object[][] getTestData() {
		return new Object[][] {
				{ new String[0] },
				{ new String[] { "A" } },
				{ new String[] { "A", "B" } },
				{ new String[] { "A", "B", "C" } },
				{ new String[] { ELEMENT } },
				{ new String[] { "A", ELEMENT } },
				{ new String[] { "A", "B", ELEMENT } },
				{ new String[] { "A", "B", ELEMENT, "C" } },
				{ new String[] { ELEMENT, "A", ELEMENT } },
				{ new String[] { "A", "B", ELEMENT, ELEMENT } },
				{ new String[] { "A", ELEMENT, "B", ELEMENT, "C" } },
				{ new String[] { ELEMENT, ELEMENT, ELEMENT, ELEMENT } }
		};
	}

	///////////////
	// Test push //
	///////////////

	@Test(dataProvider = "testData")
	public void testPush(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		// Generate expected value
		String[] expected = new String[startContents.length + 1];
		expected[0] = ELEMENT;
		for (int i = 0; i < startContents.length; i++)
			expected[i + 1] = startContents[i];

		stack.push(ELEMENT);

		// Verify resulting stack seems correct
		assertEquals(stack.size(), expected.length);
		assertTrue(elementsInOrder(stack, expected));
		assertTrue(elementsBackward(stack, expected));
	}

	// Test pop
	@Test(dataProvider = "testData")
	public void testPop(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);

		// empty stack will throw an exception
		if(startContents.length == 0){
			assertThrows(NoSuchElementException.class, ()->stack.pop());
		} else {
			// Generate expected stack
			String[] expected = Arrays.copyOfRange(startContents, 1, startContents.length);

			String retVal = stack.pop();

			// Verify return value is correct
			assertEquals(retVal, startContents[0]);

			// Verify resulting stack seems correct
			assertEquals(stack.size(), expected.length);
			assertTrue(elementsInOrder(stack, expected));
			assertTrue(elementsBackward(stack, expected));
		}
	}

	// Test firstVisited
	@Test(dataProvider = "testData")
	public void testFirstVisited(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		
		// empty stack will throw an exception
		if(startContents.length == 0){
			assertThrows(NoSuchElementException.class, ()->stack.firstVisited());
		} else {
			// Generate expected value
			String expectedRetVal = startContents[startContents.length - 1];

			// Call method
			String retVal = stack.firstVisited();

			// Verify return value is correct
			assertEquals(retVal, expectedRetVal);

			// Verify resulting stack seems correct
			assertEquals(stack.size(), startContents.length);
			assertTrue(elementsInOrder(stack, startContents));
			assertTrue(elementsBackward(stack, startContents));
		}
	}

	// Test lastVisited
	@Test(dataProvider = "testData")
	public void testLastVisited(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		
		if(startContents.length == 0){
			assertThrows(NoSuchElementException.class, ()->stack.lastVisited());
		} else {
			
			// Generate expected value
			String expectedRetVal = startContents[0];

			// Call method
			String retVal = stack.lastVisited();

			// Verify return value is correct
			assertEquals(retVal, expectedRetVal);

			// Verify resulting stack seems correct
			assertEquals(stack.size(), startContents.length);
			assertTrue(elementsInOrder(stack, startContents));
			assertTrue(elementsBackward(stack, startContents));
		}
	}

	// Test getHistory
	@Test(dataProvider = "testData")
	public void testGetHistoryOldestFirst(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		// Generate expected value
		String expectedRetVal = "";
		for (String elem : startContents) {
			expectedRetVal = elem + ":" + expectedRetVal;
		}

		// Call method
		String actual = stack.getHistory(true);

		// Verify return value is correct
		assertEquals(actual, expectedRetVal);

		// Verify resulting stack seems correct
		assertEquals(stack.size(), startContents.length);
		assertTrue(elementsInOrder(stack, startContents));
	}

	@Test(dataProvider = "testData")
	public void testGetHistoryNewestFirst(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		// Generate expected value
		String expectedRetVal = "";
		for (String elem : startContents) {
			expectedRetVal += elem + ":";
		}

		// Call method
		String actual = stack.getHistory(false);

		// Verify return value is correct
		assertEquals(actual, expectedRetVal);

		// Verify resulting stack seems correct
		assertEquals(stack.size(), startContents.length);
		assertTrue(elementsInOrder(stack, startContents));
	}

	// Test removeFromHistory
	@Test(dataProvider = "testData")
	public void testRemoveFromHistory(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		// Generate expected value
		String[] expected = removeValues(startContents);

		// Call method
		boolean actual = stack.removeFromHistory(ELEMENT);

		// Verify return value is correct
		assertEquals(actual, startContents.length != expected.length);

		// Verify resulting stack seems correct
		assertEquals(stack.size(), expected.length);
		assertTrue(elementsInOrder(stack, expected));
		assertTrue(elementsBackward(stack, expected));
	}

	// Test size
	@Test(dataProvider = "testData")
	public void testSize(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		// Call method
		int actual = stack.size();

		// Verify return value is correct
		assertEquals(actual, startContents.length);

		// Verify resulting stack seems correct
		assertTrue(elementsInOrder(stack, startContents));
		assertTrue(elementsBackward(stack, startContents));
	}

	// Test toString
	@Test(dataProvider = "testData")
	public void testToString(String[] startContents) {
		BrowserHistory<String> stack = getStack(startContents);
		// Generate Expected
		String expected = "[History(" + startContents.length + "): "
				+ (startContents.length == 0 ? "" : startContents[0]) + "]";

		// Call method
		String actual = stack.toString();

		// Verify return value is correct
		assertEquals(actual, expected);

		// Verify resulting stack seems correct
		assertEquals(stack.size(), startContents.length);
		assertTrue(elementsInOrder(stack, startContents));
		assertTrue(elementsBackward(stack, startContents));
	}

	////////////////////////////////////////
	// Test Methods Generating Exceptions //
	////////////////////////////////////////

	// Invalid dataProvider

	// Test invalid method calls

	/////////////////////
	// Utility Methods //
	/////////////////////
	/**
	 * Creates BrowserHistory object containing elems
	 * 
	 * @param elems - elements to be added in top to bottom order
	 * @return stack containing elems
	 */
	private BrowserHistory<String> getStack(String[] elems) {
		BrowserHistory<String> stack = new BrowserHistory<String>();

		for (int i = elems.length - 1; i >= 0; i--) {
			stack.push(elems[i]);
		}
		return stack;
	}

	/**
	 * Removes all instances of ELEMENT from values
	 *
	 * @param values - array from which to remove ELEMENT
	 * @return - new array with all instances of ELEMENT removed
	 */
	private String[] removeValues(String[] values) {
		String[] temp = new String[values.length];
		int j = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i].equals(ELEMENT)) {
			} else {
				temp[j++] = values[i];
			}
		}

		return Arrays.copyOfRange(temp, 0, j);
	}

	/**
	 * Verifies that the stack can be traversed forward
	 * 
	 * @param stack            - the stack of which to check the contents
	 * @param expectedContents - expected contents of the stack in top to bottom
	 *                         order
	 *
	 * @return true if the stack can be traversed forward and contents match
	 *         expectedContents
	 */
	private boolean elementsInOrder(BrowserHistory<String> stack, String[] expectedContents) {

		// verify number of elements is correct
		if (stack.size() != expectedContents.length)
			return false;

		DLLNode<String> current = (HEAD_IS_TOP ? stack.getHead() : stack.getTail());

		// verify elements in the list are correct
		int i = 0;
		boolean matches = true;
		while (matches && i < expectedContents.length) {
			if (current == null) {
				matches = false;
			} else {
				String stackElem = current.getElement();

				if (!stackElem.equals(expectedContents[i]))
					matches = false;

				i++;
				current = (HEAD_IS_TOP ? current.getNext() : current.getPrev());
			}
		}

		return matches;
	}

	/**
	 * Verifies that the stack can be traversed backwards
	 * 
	 * @param stack            - the stack of which to check the contents
	 * @param expectedContents - expected contents of the stack in top to bottom
	 *                         order
	 *
	 * @return true if the stack can be traversed backward and contents match
	 *         expectedContents
	 */
	private boolean elementsBackward(BrowserHistory<String> stack, String[] expectedContents) {

		// verify number of elements is correct
		if (stack.size() != expectedContents.length)
			return false;

		DLLNode<String> current = (HEAD_IS_TOP ? stack.getTail() : stack.getHead());

		// verify elements in the list are correct
		int i = expectedContents.length - 1;
		boolean matches = true;
		while (matches && i >= 0) {
			if (current == null) {
				matches = false;
			} else {
				String stackElem = current.getElement();

				if (!stackElem.equals(expectedContents[i]))
					matches = false;

				i--;
				current = (HEAD_IS_TOP ? current.getPrev() : current.getNext());
			}
		}
		return matches;
	}

}
