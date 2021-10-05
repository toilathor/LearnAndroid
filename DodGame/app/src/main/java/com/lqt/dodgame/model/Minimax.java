package com.lqt.dodgame.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;

public class Minimax {

	@RequiresApi(api = Build.VERSION_CODES.N)
	public int minVal(Node node, int hight) {
		
		if(hight == 0 || node.isNodeEnd()) {
			return node.getSumPoint();
		}else {
			ArrayList<Integer> sortPoint = new ArrayList<>();

			for (Node node2 : node.getNextNodes()) {
				sortPoint.add(maxVal(node2, hight-1));
			}
			sortPoint.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 -o1;
				}
			});
			
			return sortPoint.get(0);
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	public int maxVal(Node node, int hight) {
		if(hight == 0 || node.isNodeEnd()) {
			return node.getSumPoint();
		}else {
			ArrayList<Integer> sortPoint = new ArrayList<>();
			
			for (Node node2 : node.getNextNodes()) {
				sortPoint.add(minVal(node2, hight-1));
			}
			sortPoint.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o1 - o2;
				}
			});
			
			return sortPoint.get(0);
		}
	}


	@RequiresApi(api = Build.VERSION_CODES.N)
	public Node MiniMaxVal(Node node, int hight) {
		Node chooseNode = null;

		int val = Integer.MIN_VALUE;

		if (node){

		}
		for (Node node2 : node.getNextNodes()) {
			int t = minVal(node2, hight-1);
			if(val <= t) {
				val = t;
				chooseNode = node2;
			}
		}

		return chooseNode;
	}
}
