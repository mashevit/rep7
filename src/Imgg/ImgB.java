package Imgg;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import Interf.ImgBLocal;
import model3.TripSightseeing;

/**
 * Session Bean implementation class ImgB
 */

public class ImgB implements ImgBLocal{
	static Vector<Map> imgdetails = new Vector<>();

	/**
	 * Default constructor.
	 */
	public ImgB() {
		// TODO Auto-generated constructor stub
	}

	public  void  addItem(String imgaddr, TripSightseeing tripsight) {
		Map tmp = new HashMap();
		tmp.put("addr", imgaddr);
		tmp.put("trs", tripsight);
		imgdetails.add(tmp);
	}

	public  void clearItems() {
		imgdetails.clear();
	}

	public  void removeItem(String ind) {
		int indi = Integer.parseInt(ind);
		imgdetails.remove(indi);
	}

	public  Vector<Map> listItems() {
		return imgdetails;
	}

}
