package com.sjsu.courseapp.cloudwatch;

import java.util.Comparator;

import com.amazonaws.services.cloudwatch.model.Datapoint;

class CompData implements Comparator<Datapoint> {
	@Override
	public int compare(Datapoint d1, Datapoint d2) {
		if (d1.getTimestamp().after(d2.getTimestamp())) {
			return 1;
		} else {
			return -1;
		}

	}
}
