package Imgg;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import model3.TripSightseeing;

/**
 * Session Bean implementation class bla
 */
@Stateful
@LocalBean
public class Bla11 {
	Vector<Map> imgdetails = new Vector<>();

    /**
     * Default constructor. 
     */
    public Bla11() {
        // TODO Auto-generated constructor stub
    }
    

	public  void  addItem(String imgaddr, TripSightseeing tripsight) {
		Map<String, Serializable> tmp = new HashMap<String, Serializable>();
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
