package Interf;


import java.util.Map;
import java.util.Vector;

import javax.ejb.Local;
import javax.ejb.Remote;

import model3.TripSightseeing;


public interface ImgBLocal {

	Vector<Map> listItems();

	void removeItem(String ind);

	void clearItems();

	void addItem(String imgaddr, TripSightseeing tripsight);

}
