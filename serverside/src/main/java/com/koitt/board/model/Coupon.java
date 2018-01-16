package com.koitt.board.model;

// Java Bean (VO, DTO)
public class Coupon {

	private Integer cno;
	private Integer id;
	private String email;
	private String sale;
	
	//////////////////////////////////////////////////////////////////////
	public Coupon() {}
	
	///	/////////////////////////////////////////////////////////////////////////////
	public Coupon(Integer cno, Integer id, String email, String sale) {
		this.cno = cno;
		this.id = id;
		this.email = email;
		this.sale = sale;
	}

	///	/////////////////////////////////////////////////////////////////////////////
	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	///	/////////////////////////////////////////////////////////////////////////////
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cno == null) ? 0 : cno.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sale == null) ? 0 : sale.hashCode());
		return result;
	}

	///	/////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (cno == null) {
			if (other.cno != null)
				return false;
		} else if (!cno.equals(other.cno))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sale == null) {
			if (other.sale != null)
				return false;
		} else if (!sale.equals(other.sale))
			return false;
		return true;
	}
	
///	/////////////////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coupon [cno=");
		builder.append(cno);
		builder.append(", id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", sale=");
		builder.append(sale);
		builder.append("]");
		return builder.toString();
	}


	
	
}
