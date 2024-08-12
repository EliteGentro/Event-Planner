/**
 * Events
 * @author khf849 
 * Object class of the object type Events
 *
 */
package application;
import javafx.beans.property.SimpleStringProperty;

public class Events {
	/**
	 * Hour of the event
	 */
	private SimpleStringProperty hour;

	/**
	 * Place of the event
	 */
	private SimpleStringProperty place;

	/**
	 * Activity of the event
	 */
	private SimpleStringProperty activity;

	/**
	 * Physical requirements of the event
	 */
	private SimpleStringProperty physical;

	/**
	 * Classroom requirements of the event
	 */
	private SimpleStringProperty classrooms;

	/**
	 * Technology and infrastructure requirements of the event
	 */
	private SimpleStringProperty ti;

	/**
	 * Security requirements of the event
	 */
	private SimpleStringProperty security;

	/**
	 * Additional commentary for the event
	 */
	private SimpleStringProperty commentary;

	/**
	 * Name of the event
	 */
	private SimpleStringProperty name;

	/**
	 * Person responsible for the event
	 */
	private SimpleStringProperty responsible;

	/**
	 * Department responsible for the event
	 */
	private SimpleStringProperty department;

	/**
	 * Number of people expected to attend the event
	 */
	private SimpleStringProperty people;

	/**
	 * Start date of the event
	 */
	private SimpleStringProperty startDate;

	/**
	 * End date of the event
	 */
	private SimpleStringProperty endDate;

	/**
	 * Date for setting up the event
	 */
	private SimpleStringProperty setupDate;




	public Events (String name, String startDate) {
		this.name = new SimpleStringProperty(name);
		this.startDate= new SimpleStringProperty(startDate);
	}

	public Events (String name) {
		this.name = new SimpleStringProperty(name);
	}
	/**
	 * Constructor for the activity information of an event
	 * @param hour
	 * @param place
	 * @param activity
	 * @param physical
	 * @param classrooms
	 * @param ti
	 * @param security
	 * @param commentary
	 */
	public Events (String hour, String place, String activity, String physical, 
			String classrooms, String ti, String security, String commentary) {

		this.hour = new SimpleStringProperty(hour);
		this.place = new SimpleStringProperty(place);
		this.activity = new SimpleStringProperty(activity);
		this.physical = new SimpleStringProperty(physical);
		this.classrooms = new SimpleStringProperty(classrooms);
		this.ti = new SimpleStringProperty(ti);
		this.security = new SimpleStringProperty(security);
		this.commentary = new SimpleStringProperty(commentary);

	}
	/**
	 * Constructor for the main information for the event.
	 * @param name
	 * @param responsible
	 * @param department
	 * @param people
	 * @param startDate
	 * @param endDate
	 * @param setupDate
	 */
	public Events (String name, String responsible, String department, 
			String people, String startDate,String endDate, String setupDate) {


		this.name = new SimpleStringProperty(name);
		this.responsible = new SimpleStringProperty(responsible);
		this.department = new SimpleStringProperty(department);
		this.people = new SimpleStringProperty(people);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
		this.setupDate = new SimpleStringProperty(setupDate);
	}
	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour.get();
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(SimpleStringProperty hour) {
		this.hour = hour;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place.get();
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(SimpleStringProperty place) {
		this.place = place;
	}
	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity.get();
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(SimpleStringProperty activity) {
		this.activity = activity;
	}
	/**
	 * @return the physical
	 */
	public String getPhysical() {
		return physical.get();
	}
	/**
	 * @param physical the physical to set
	 */
	public void setPhysical(SimpleStringProperty physical) {
		this.physical = physical;
	}
	/**
	 * @return the classrooms
	 */
	public String getClassrooms() {
		return classrooms.get();
	}
	/**
	 * @param classrooms the classrooms to set
	 */
	public void setClassrooms(SimpleStringProperty classrooms) {
		this.classrooms = classrooms;
	}
	/**
	 * @return the ti
	 */
	public String getTi() {
		return ti.get();
	}
	/**
	 * @param ti the ti to set
	 */
	public void setTi(SimpleStringProperty ti) {
		this.ti = ti;
	}
	/**
	 * @return the security
	 */
	public String getSecurity() {
		return security.get();
	}
	/**
	 * @param security the security to set
	 */
	public void setSecurity(SimpleStringProperty security) {
		this.security = security;
	}
	/**
	 * @return the security
	 */
	public String getCommentary() {
		return commentary.get();
	}
	/**
	 * @param security the security to set
	 */
	public void setCommentary(SimpleStringProperty commentary) {
		this.commentary = commentary;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}
	/**
	 * @param name the name to set
	 */
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	/**
	 * @return the responsible
	 */
	public String getResponsible() {
		return responsible.get();
	}
	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible(SimpleStringProperty responsible) {
		this.responsible = responsible;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department.get();
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(SimpleStringProperty department) {
		this.department = department;
	}
	/**
	 * @return the people
	 */
	public String getPeople() {
		return people.get();
	}
	/**
	 * @param people the people to set
	 */
	public void setPeople(SimpleStringProperty people) {
		this.people = people;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate.get();
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(SimpleStringProperty startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate.get();
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(SimpleStringProperty endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the setupDate
	 */
	public String getSetupDate() {
		return setupDate.get();
	}
	/**
	 * @param setupDate the setupDate to set
	 */
	public void setSetupDate(SimpleStringProperty setupDate) {
		this.setupDate = setupDate;
	}


}

