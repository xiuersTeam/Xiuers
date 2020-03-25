package ts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="clientinfo")
@XmlRootElement(name="ClientInfo")

public class ClientInfo implements Serializable{

	private static final long serialVersionUID = -206715202377867497L;
	
	@Column(name="cid", nullable=false)	
	@Id	
	@GeneratedValue(generator="MODEL_CLIENTINFO_CID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MODEL_CLIENTINFO_CID_GENERATOR", strategy="native")	
	private int cid;
	
	@OneToMany( targetEntity=CustomerInfo.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<CustomerInfo> customerInfo = new java.util.HashSet<CustomerInfo>();
	
	@OneToMany(targetEntity=Appointment.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Appointment> appointment = new java.util.HashSet<Appointment>();
	
	@Column(name="password", nullable=false, length=50)	
	private String password;
	
	@Column(name="telphone", nullable=true, length=11)	
	private String telphone;
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	public void setCustomerInfo(java.util.Set<CustomerInfo> value) {
		this.customerInfo = value;
	}
	
	public java.util.Set<CustomerInfo> getCustomerInfo() {
		return customerInfo;
	}
	
	
	public void setAppointment(java.util.Set<Appointment> value) {
		this.appointment = value;
	}
	
	public java.util.Set<Appointment> getAppointment() {
		return appointment;
	}
	
	
	@Override
	public String toString() {
		return "ClientInfo [cid=" + cid +  " password=" + password + " telphone="
				+ telphone + "]";
	}
	
	
	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
}
