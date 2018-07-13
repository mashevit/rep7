package abc.da;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model3.City;
import model3.Pic;
import model3.Sightseeing;
import model3.Traveler;
import model3.Trip;
import model3.TripSightseeing;

public class Dac implements IDac{
	
	EntityManager em;
	public Dac() {
		// TODO Auto-generated constructor stub
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDWP");		
		 em = emf.createEntityManager();
	}
	
	
	
	public int createTrip(String cityi, Date date, String traveleri, int numdays, int moneyspent, String hotel,
			String Sightseeingi, int transit) {
		Trip trip = new Trip();
		int i = Integer.parseInt(cityi);
		City city = em.find(City.class, i);
		int i1 = Integer.parseInt(traveleri);
		Traveler traveler2 = em.find(Traveler.class, i1);

		trip.setCity(city);
		trip.setTraveler(traveler2);
		trip.setTripDate(date);
		trip.setTripHotel(hotel);
		trip.setTripNumdays(numdays);
		trip.setTripMoneyspent(moneyspent);
		trip.setTrip_numMinTransit(transit);
		em.persist(trip);
		if (Sightseeingi != null) {
			int i3 = Integer.parseInt(Sightseeingi);
			Sightseeing sight = em.find(Sightseeing.class, i3);
			TripSightseeing tsi = new TripSightseeing();
			tsi.setSightseeing(sight);
			tsi.setTrip(trip);
			em.persist(tsi);
		}
		return trip.getIdtrip();
	}

	
	public int createTripStrings(String cityi, Date date, String traveleri, String numdays, String moneyspent, String hotel, String transit) {
		Trip trip = new Trip();
		int i = Integer.parseInt(cityi);
		City city = em.find(City.class, i);
		int i1 = Integer.parseInt(traveleri);
		Traveler traveler2 = em.find(Traveler.class, i1);

		trip.setCity(city);
		trip.setTraveler(traveler2);
		trip.setTripDate(date);
		trip.setTripHotel(hotel);
		trip.setTripNumdays(Integer.parseInt(numdays));
		trip.setTripMoneyspent(Integer.parseInt(moneyspent));
		trip.setTrip_numMinTransit(Integer.parseInt(transit));
		em.persist(trip);
		return trip.getIdtrip();
	}
	
	
	public int createTraveler(String name, Date birthDate) {
		Traveler travelr = new Traveler();
		travelr.setTravelerName(name);
		travelr.setTraveler_BirthDate(birthDate);
		em.persist(travelr);
		return travelr.getIdtraveler();
	}

	public int addCity(String cityName) {
		City city = new City();
		String statement = "SELECT c FROM City c WHERE c.cityName= :cn";
		try {
			Query q = em.createQuery(statement).setParameter("cn", cityName);
			@SuppressWarnings("unchecked")
			List<City> lct = q.getResultList();
			if (!lct.isEmpty())
				return lct.get(0).getIdcities();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		city.setCityName(cityName);
		em.persist(city);
		return city.getIdcities();
	}

	public int createSight(String name, City city) {
		Sightseeing sight = new Sightseeing();
		sight.setSightSeeingsName(name);
		sight.setCity(city);
		em.persist(sight);
		return sight.getIdSightSeeings();
	}
//?
	public int addSightTrip(Sightseeing sightseeing) {
		TripSightseeing tS = new TripSightseeing();
		tS.setSightseeing(sightseeing);
		em.persist(tS);
		return tS.getIdtripSightseeing();
	}

	@SuppressWarnings("unchecked")
	public List<City> getAllCities() {
		Query query = em.createNamedQuery("City.findAll");
		return query.getResultList();
	}

	public String removeCity(String ist) {
		int i = Integer.parseInt(ist);
		City city = em.find(City.class, i);
		em.remove(city);
		return i + "  city removed ";
	}

	@SuppressWarnings("unchecked")
	public List<Traveler> getTravellers() {
		Query query = em.createNamedQuery("Traveler.findAll");
		return query.getResultList();
	}

	public int addTraveller(String name, Date BirthD) {
		Traveler traveler = new Traveler();
		traveler.setTravelerName(name);
		traveler.setTraveler_BirthDate(BirthD);
		em.persist(traveler);
		return traveler.getIdtraveler();
	}

	public int addSight(String name, City city) {
		Sightseeing sight = new Sightseeing();
		sight.setCity(city);
		sight.setSightSeeingsName(name);
		em.persist(sight);
		return sight.getIdSightSeeings();
	}

	public int addSighti(String name, String ist) {
		int i = Integer.parseInt(ist);
		Sightseeing sight = new Sightseeing();
		City city = em.find(City.class, i);
		sight.setCity(city);
		sight.setSightSeeingsName(name);
		em.persist(sight);
		return sight.getIdSightSeeings();
	}

	@SuppressWarnings("unchecked")
	public List<Sightseeing> sightsbycity(String cityidstr) {
		int i = Integer.parseInt(cityidstr);
		// City city = em.find(City.class, i);
		String statement = "SELECT st FROM Sightseeing st JOIN st.city c WHERE c.idcities = :id ";
		Query query = em.createQuery(statement).setParameter("id", i);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Trip> Tripl() {
		Query query = em.createNamedQuery("Trip.findAll");
		return query.getResultList();
	}

	public String getTripCityName(String idt) {
		int id = Integer.parseInt(idt);
		String statement = "SELECT c FROM Trip t JOIN t.City c WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<City> c = q.getResultList();
		return c.get(0).getCityName();
	}

	@Override
	public City getCitybyTrip(String idt) {
		int id = Integer.parseInt(idt);
		String statement = "SELECT c FROM Trip t JOIN t.city c WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<City> c = q.getResultList();
		return c.get(0);
	}

	public List<Sightseeing> SightsforTrip(String tri) {
		int id = Integer.parseInt(tri);
		String statement = "Select s FROM Trip t JOIN t.tripSightseeings ts JOIN ts.sightseeing s WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<Sightseeing> l = q.getResultList();
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<Sightseeing> newTripCitySights(String ci, String ti) {
		int id = Integer.parseInt(ti);
		int ic = Integer.parseInt(ci);
		String statement = "SELECT s FROM Sightseeing s join s.city cq WHERE (cq.idcities=:ic) AND s NOT IN (SELECT q FROM Trip a JOIN a.tripSightseeings b JOIN b.sightseeing q JOIN q.city c WHERE ((a.idtrip = :id)))";
		Query q = em.createQuery(statement).setParameter("ic", ic).setParameter("id", id);
		return q.getResultList();
	}

	public String removeSightFromTrip(String si, String ti) {
		int id = Integer.parseInt(ti);
		int is = Integer.parseInt(si);
		Trip trip = em.find(Trip.class, id);
		TripSightseeing trs = em.find(TripSightseeing.class, is);
		trs = trip.removeTripSightseeing(trs);
		em.remove(trs);
		return "  Sightseeing " + si + " was renoved from trip" + ti + "and deleted";
	}
	public int removeSightFromTripint(String si, String ti) {
		int id = Integer.parseInt(ti);
		int is = Integer.parseInt(si);
		Trip trip = em.find(Trip.class, id);
		TripSightseeing trs = em.find(TripSightseeing.class, is);
		int i=trs.getSightseeing().getIdSightSeeings();
		trs = trip.removeTripSightseeing(trs);
		em.remove(trs);
		return i;
	}
	public List<TripSightseeing> SightseeingforTrip(String tri) {
		int id = Integer.parseInt(tri);
		String statement = "Select s FROM Trip t JOIN t.tripSightseeings s WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<TripSightseeing> l = q.getResultList();
		return l;
	}

	public int addsighttotrip(String sigind, String tripind) {
		int id = Integer.parseInt(tripind);
		int is = Integer.parseInt(sigind);
		Trip trip = em.find(Trip.class, id);
		Sightseeing sight = em.find(Sightseeing.class, is);
		TripSightseeing tsi = new TripSightseeing();
		tsi.setSightseeing(sight);
		tsi.setTrip(trip);
		em.persist(tsi);
		return tsi.getIdtripSightseeing();
	}

	@SuppressWarnings("unchecked")
	public List<Pic> picsForSightTrip(String idSight) {
		int id = Integer.parseInt(idSight);
		String statement = "Select p FROM Pic p Join p.tripSightseeing a where a.idtripSightseeing =:i";
		Query q = em.createQuery(statement).setParameter("i", id);
		return q.getResultList();
	}

	public int setpicsForSighttrip(String addr, String idtrsig) {
		int id = Integer.parseInt(idtrsig);
		TripSightseeing trs = em.find(TripSightseeing.class, id);
		Pic p = new Pic();
		p.setPicsAddr(addr);
		p.setTripSightseeing(trs);
		em.persist(p);
		return p.getIdpics();
	}

	public Pic picbyid(String ids) {
		int i = Integer.parseInt(ids);
		Pic p = em.find(Pic.class, i);
		return p;
	}

	public void deletPic(String idpic) {
		int i = Integer.parseInt(idpic);
		Pic p = em.find(Pic.class, i);
		p.setTripSightseeing(null);
		em.remove(p);
	}

	@SuppressWarnings("unchecked")
	public List<Trip> GetTripsByCity(String cityidstr) {
		int i = Integer.parseInt(cityidstr);
		// City city = em.find(City.class, i);
		String statement = "SELECT tr FROM Trip tr JOIN tr.city c WHERE c.idcities = :id ";
		Query query = em.createQuery(statement).setParameter("id", i);
		return query.getResultList();
	}

	public List<Pic> PicbyTrip(String tripid) {
		int id = Integer.parseInt(tripid);
		String statement = "Select p FROM Pic p Join p.tripSightseeing s Join s.trip t WHERE t.idtrip =:i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<Pic> l = q.getResultList();
		return l;
	}

	public List<Trip> get3uniq() {

		String q1 = "SELECT t FROM Trip t GROUP BY t.city ORDER BY COUNT(t.city) Asc";
		Query q = em.createQuery(q1).setMaxResults(3);
		@SuppressWarnings("unchecked")
		List<Trip> l = q.getResultList();
		return l;
	}

	public List<City> get3common() {

		String q1 = "SELECT a FROM Trip t Join t.city a GROUP BY a ORDER BY COUNT(a) Desc";
		Query q = em.createQuery(q1).setMaxResults(3);
		@SuppressWarnings("unchecked")
		List<City> l = q.getResultList();
		return l;
	}

	public List<Pic> get3pics() {

		String q1 = "SELECT a FROM Pic a GROUP BY a.tripSightseeing ORDER BY COUNT(a.tripSightseeing) Desc";
		Query q = em.createQuery(q1).setMaxResults(3);
		@SuppressWarnings("unchecked")
		List<Pic> l = q.getResultList();
		return l;
	}
	public Traveler getTravelerbyId(String id) {
		int i = Integer.parseInt(id);
		Traveler t = em.find(Traveler.class, i);
		return t;
		
	}
	public void removetraveller(String travid){
		int i = Integer.parseInt(travid);
		Traveler t= em.find(Traveler.class, i);
		em.remove(t);
	//	return t + " removed ";
	}
	public int addSightStr(String tripid,String sightname) {
		int i = Integer.parseInt(tripid);
		Trip t= em.find(Trip.class, i);
		Sightseeing sight = new Sightseeing();
		sight.setCity(t.getCity());
		sight.setSightSeeingsName(sightname);
		em.persist(sight);
		TripSightseeing tss = new TripSightseeing();
		tss.setSightseeing(sight);
		tss.setTrip(t);
		em.persist(tss);
		return tss.getIdtripSightseeing();}
//	public int addSightTripIndStr(String tripid,String sightid) {
//		int i = Integer.parseInt(tripid);
//		//Sightseeing sight = new Sightseeing();
//		City city = em.find(City.class, i);
//		sight.setCity(city);
//		sight.setSightSeeingsName(name);
//		em.persist(sight);
//		return sight.getIdSightSeeings();
//		
//	}
//	public Trip tripbyid{
//		
//	}
	public Sightseeing getsightbyid(String ids) {
		int i = Integer.parseInt(ids);
		Sightseeing s= em.find(Sightseeing.class, i);
		return s;
	}
	public TripSightseeing getTrsightbyid(String ids) {
		int i = Integer.parseInt(ids);
		TripSightseeing s= em.find(TripSightseeing.class, i);
		return s;
	}
	
	public String removeTSightseeingFromCity(String trsid) {
	
		int i2 = Integer.parseInt(trsid);
		TripSightseeing tmp=em.find(TripSightseeing.class, i2);
		Sightseeing todel= tmp.getSightseeing();
		tmp.setSightseeing(null);
		
		tmp.setTrip(null);
		em.remove(tmp);
		todel.setCity(null);
		todel.setSightSeeingsName(null);
		if(todel.getTripSightseeings().size()<=1) todel.setTripSightseeings(null);
		em.remove(todel);
		return "deleted sucssefully/unsucssefuly";
		// em.find(Sightseeing.class, tmp.);
		//Sightseeing todel=
	}
	
	public void removeSightUndefined(String ids) {
		int i2 = Integer.parseInt(ids);
		Sightseeing todel=em.find(Sightseeing.class, i2);
		todel.setCity(null);
		todel.setSightSeeingsName(null);
		if(todel.getTripSightseeings().size()<=1) todel.setTripSightseeings(null);
		em.remove(todel);
	}
}
