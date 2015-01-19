package com.sjsu.courseapp.loadbalancer;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class AntColonyUtill {

	private static Map<Integer, String> treeMap = new TreeMap<Integer, String>(
			new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}

			});

	public static Map<Integer, String> sortMapByKey(Map<Integer, String> c) {

		treeMap.putAll(c);

		return treeMap;
	}

}
