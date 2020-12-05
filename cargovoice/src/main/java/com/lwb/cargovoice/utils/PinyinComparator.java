package com.lwb.cargovoice.utils;

import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SelectRegionBean> {

	public int compare(SelectRegionBean o1, SelectRegionBean o2) {
		if (o1.getLetters().equals("@")
				|| o2.getLetters().equals("#")) {
			return -1;
		} else if (o1.getLetters().equals("#")
				|| o2.getLetters().equals("@")) {
			return 1;
		} else {
			return o1.getLetters().compareTo(o2.getLetters());
		}
	}

}
