package com.koitt.board.model;

public class Ticket {
	private Integer ticNo;
	private Integer id;
	private Integer schNo;
	private String ticSeatno;
	private Integer scNo;
	private String scName;
	private Integer scLine;
	private Integer scSeat;
	private Integer theNo;
	private String movietitle;
	private String movieno;
	private int ticTotalcustomer;

	public Ticket() {
	}

	public Ticket(Integer ticNo, Integer id, Integer schNo, String ticSeatno, Integer scNo, String scName,
			Integer scLine, Integer scSeat, Integer theNo, String movietitle, String movieno, int ticTotalcustomer) {
		super();
		this.ticNo = ticNo;
		this.id = id;
		this.schNo = schNo;
		this.ticSeatno = ticSeatno;
		this.scNo = scNo;
		this.scName = scName;
		this.scLine = scLine;
		this.scSeat = scSeat;
		this.theNo = theNo;
		this.movietitle = movietitle;
		this.movieno = movieno;
		this.ticTotalcustomer = ticTotalcustomer;
	}

	public Integer getTicNo() {
		return ticNo;
	}

	public void setTicNo(Integer ticNo) {
		this.ticNo = ticNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchNo() {
		return schNo;
	}

	public void setSchNo(Integer schNo) {
		this.schNo = schNo;
	}

	public String getTicSeatno() {
		return ticSeatno;
	}

	public void setTicSeatno(String ticSeatno) {
		this.ticSeatno = ticSeatno;
	}

	public Integer getScNo() {
		return scNo;
	}

	public void setScNo(Integer scNo) {
		this.scNo = scNo;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public Integer getScLine() {
		return scLine;
	}

	public void setScLine(Integer scLine) {
		this.scLine = scLine;
	}

	public Integer getScSeat() {
		return scSeat;
	}

	public void setScSeat(Integer scSeat) {
		this.scSeat = scSeat;
	}

	public Integer getTheNo() {
		return theNo;
	}

	public void setTheNo(Integer theNo) {
		this.theNo = theNo;
	}

	public String getMovietitle() {
		return movietitle;
	}

	public void setMovietitle(String movietitle) {
		this.movietitle = movietitle;
	}

	public String getMovieno() {
		return movieno;
	}

	public void setMovieno(String movieno) {
		this.movieno = movieno;
	}

	public int getTicTotalcustomer() {
		return ticTotalcustomer;
	}

	public void setTicTotalcustomer(int ticTotalcustomer) {
		this.ticTotalcustomer = ticTotalcustomer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movieno == null) ? 0 : movieno.hashCode());
		result = prime * result + ((movietitle == null) ? 0 : movietitle.hashCode());
		result = prime * result + ((scLine == null) ? 0 : scLine.hashCode());
		result = prime * result + ((scName == null) ? 0 : scName.hashCode());
		result = prime * result + ((scNo == null) ? 0 : scNo.hashCode());
		result = prime * result + ((scSeat == null) ? 0 : scSeat.hashCode());
		result = prime * result + ((schNo == null) ? 0 : schNo.hashCode());
		result = prime * result + ((theNo == null) ? 0 : theNo.hashCode());
		result = prime * result + ((ticNo == null) ? 0 : ticNo.hashCode());
		result = prime * result + ((ticSeatno == null) ? 0 : ticSeatno.hashCode());
		result = prime * result + ticTotalcustomer;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (movieno == null) {
			if (other.movieno != null)
				return false;
		} else if (!movieno.equals(other.movieno))
			return false;
		if (movietitle == null) {
			if (other.movietitle != null)
				return false;
		} else if (!movietitle.equals(other.movietitle))
			return false;
		if (scLine == null) {
			if (other.scLine != null)
				return false;
		} else if (!scLine.equals(other.scLine))
			return false;
		if (scName == null) {
			if (other.scName != null)
				return false;
		} else if (!scName.equals(other.scName))
			return false;
		if (scNo == null) {
			if (other.scNo != null)
				return false;
		} else if (!scNo.equals(other.scNo))
			return false;
		if (scSeat == null) {
			if (other.scSeat != null)
				return false;
		} else if (!scSeat.equals(other.scSeat))
			return false;
		if (schNo == null) {
			if (other.schNo != null)
				return false;
		} else if (!schNo.equals(other.schNo))
			return false;
		if (theNo == null) {
			if (other.theNo != null)
				return false;
		} else if (!theNo.equals(other.theNo))
			return false;
		if (ticNo == null) {
			if (other.ticNo != null)
				return false;
		} else if (!ticNo.equals(other.ticNo))
			return false;
		if (ticSeatno == null) {
			if (other.ticSeatno != null)
				return false;
		} else if (!ticSeatno.equals(other.ticSeatno))
			return false;
		if (ticTotalcustomer != other.ticTotalcustomer)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [ticNo=");
		builder.append(ticNo);
		builder.append(", id=");
		builder.append(id);
		builder.append(", schNo=");
		builder.append(schNo);
		builder.append(", ticSeatno=");
		builder.append(ticSeatno);
		builder.append(", scNo=");
		builder.append(scNo);
		builder.append(", scName=");
		builder.append(scName);
		builder.append(", scLine=");
		builder.append(scLine);
		builder.append(", scSeat=");
		builder.append(scSeat);
		builder.append(", theNo=");
		builder.append(theNo);
		builder.append(", movietitle=");
		builder.append(movietitle);
		builder.append(", movieno=");
		builder.append(movieno);
		builder.append(", ticTotalcustomer=");
		builder.append(ticTotalcustomer);
		builder.append("]");
		return builder.toString();
	}

}
