package com.koitt.board.model;

import java.sql.Date;

// JAVA Bean (VO, DTO)
public class Reservation {

	private Integer rno; // 예매 번호
	private Integer mno; // 영화 번호
	private String mscr; // 상영관
	private Date rdate; // 영화 예매 시간
	private Integer id; // 사용자 아이디
	private String mposter; // 영화 포스터
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Reservation() {}

/////////////////////////////////////////////////////////////////////////////////////////////////////
public Reservation(Integer rno, Integer mno, String mscr, Date rdate, Integer id, String mposter) {
	super();
	this.rno = rno;
	this.mno = mno;
	this.mscr = mscr;
	this.rdate = rdate;
	this.id = id;
	this.mposter = mposter;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////

public Integer getRno() {
	return rno;
}

public void setRno(Integer rno) {
	this.rno = rno;
}

public Integer getMno() {
	return mno;
}

public void setMno(Integer mno) {
	this.mno = mno;
}

public String getMscr() {
	return mscr;
}

public void setMscr(String mscr) {
	this.mscr = mscr;
}

public Date getRdate() {
	return rdate;
}

public void setRdate(Date rdate) {
	this.rdate = rdate;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getMposter() {
	return mposter;
}

public void setMposter(String mposter) {
	this.mposter = mposter;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((mno == null) ? 0 : mno.hashCode());
	result = prime * result + ((mposter == null) ? 0 : mposter.hashCode());
	result = prime * result + ((mscr == null) ? 0 : mscr.hashCode());
	result = prime * result + ((rdate == null) ? 0 : rdate.hashCode());
	result = prime * result + ((rno == null) ? 0 : rno.hashCode());
	return result;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Reservation other = (Reservation) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (mno == null) {
		if (other.mno != null)
			return false;
	} else if (!mno.equals(other.mno))
		return false;
	if (mposter == null) {
		if (other.mposter != null)
			return false;
	} else if (!mposter.equals(other.mposter))
		return false;
	if (mscr == null) {
		if (other.mscr != null)
			return false;
	} else if (!mscr.equals(other.mscr))
		return false;
	if (rdate == null) {
		if (other.rdate != null)
			return false;
	} else if (!rdate.equals(other.rdate))
		return false;
	if (rno == null) {
		if (other.rno != null)
			return false;
	} else if (!rno.equals(other.rno))
		return false;
	return true;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Reservation [rno=");
	builder.append(rno);
	builder.append(", mno=");
	builder.append(mno);
	builder.append(", mscr=");
	builder.append(mscr);
	builder.append(", rdate=");
	builder.append(rdate);
	builder.append(", id=");
	builder.append(id);
	builder.append(", mposter=");
	builder.append(mposter);
	builder.append("]");
	return builder.toString();
}

	
	
}
