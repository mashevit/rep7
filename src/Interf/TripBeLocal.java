/*package Interf;



import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import model3.City;
import model3.Pic;
import model3.Sightseeing;
import model3.Traveler;
import model3.Trip;
import model3.TripSightseeing;


@Local
public interface TripBeLocal {

	int addSightTrip(Sightseeing sightseeing);

	int createSight(String name, City city);

	int addCity(String cityName);

	int createTraveler(String name, Date birthDate);

	List<City> getAllCities();

	int addSight(String name, City city);

	int addTraveller(String name, Date BirthD);

	List<Traveler> getTravellers();

	String removeCity(String ist);

	List<Sightseeing> sightsbycity(String cityidstr);

	int addSighti(String name, String ist);

	int createTrip(String cityi, Date date, String traveleri, int numdays, int moneyspent, String hotel, String Sightseeingi,
			int transit);

	List<Trip> Tripl();

	String getTripCityName(String idt);

	City getCitybyTrip(String idt);

	List<Sightseeing> SightsforTrip(String tri);

	List<Sightseeing> newTripCitySights(String ci, String ti);

	List<TripSightseeing> SightseeingforTrip(String tri);

	String removeSightFromTrip(String si, String ti);

	int addsighttotrip(String sigind, String tripind);

	int setpicsForSighttrip(String addr, String idtrsig);

	List<Pic> picsForSightTrip(String idSight);

	Pic picbyid(String ids);

	void deletPic(String idpic);

	List<Trip> GetTripsByCity(String cityidstr);

	List<Pic> PicbyTrip(String tripid);

	List<City> get3common();

	List<Trip> get3uniq();

	List<Pic> get3pics();

	Traveler getTravelerbyId(String id);

	void removetraveller(String travid);

	int createTripStrings(String cityi, Date date, String traveleri, String numdays, String moneyspent, String hotel,
			String transit);

	int addSightStr(String tripid, String sightname);

	Sightseeing getsightbyid(String ids);

	TripSightseeing getTrsightbyid(String ids);

	String removeTSightseeingFromCity(String trsid);

	void removeSightUndefined(String ids);

	int removeSightFromTripint(String si, String ti);

}
*/