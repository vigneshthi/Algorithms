package test.java;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import main.java.SeparateChainingLiteHashST;

public class SeparateChainingLiteHashSTTest {

	@Test
	public void test1() {
		SeparateChainingLiteHashST<Integer, Boolean> st = new SeparateChainingLiteHashST<>(4);
		st.put(4, true);
		st.put(8, false);
		st.put(12, true);
		st.put(16, false);
		st.put(20, true);
		st.put(24, false);
		st.put(28, true);
		st.put(32, false);
		st.put(2, true);
		st.put(3, true);
		st.put(997, true);
		st.put(997, false);
		st.put(997, true);
		st.put(997, false);
		st.put(6, true);
		for (Map.Entry<Integer, Integer> bin : st.binsCount().entrySet()) {
			if (bin.getKey() == 0)
				assertEquals("Bin = 0", bin.getValue().intValue(), 8);
			if (bin.getKey() == 1)
				assertEquals("Bin = 1", bin.getValue().intValue(), 1);
			if (bin.getKey() == 2)
				assertEquals("Bin = 2", bin.getValue().intValue(), 2);
			if (bin.getKey() == 3)
				assertEquals("Bin = 3", bin.getValue().intValue(), 1);
		}
		// System.out.println(st.get(997));
		// Assert.assertEquals("test hash count", length , 1);

	}
	@Test
	public void test2() {
		SeparateChainingLiteHashST<Integer, Boolean> st = new SeparateChainingLiteHashST<>(4);
		st.put(4, true);
		st.put(8, false);
		st.put(2, true);
		st.put(3, true);
		st.put(997, true);
		//for()
		//Assert.assertTrue(st.hash(4) == st.hash(8));
		
		Assert.assertFalse(st.hash(4) == st.hash(2));
		Assert.assertFalse(st.hash(4) == st.hash(3));
		Assert.assertFalse(st.hash(4) == st.hash(997));
	}
	
	
}
