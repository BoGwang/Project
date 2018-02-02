package com.koitt.board.model;

// JAVA Bean (VO, DTO)
public class Movie {
	private Integer movieNo;
	private String movietitle;
	private String moviecontent;
	private Integer id;
	private String moviedirector;
	private String movieposter;

	public Movie() {
	}

	public Movie(Integer movieNo, String movietitle, String moviecontent, Integer id, String moviedirector,
			String movieposter) {
		this.movieNo = movieNo;
		this.movietitle = movietitle;
		this.moviecontent = moviecontent;
		this.id = id;
		this.moviedirector = moviedirector;
		this.movieposter = movieposter;
	}

	public Integer getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(Integer movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovietitle() {
		return movietitle;
	}

	public void setMovietitle(String movietitle) {
		this.movietitle = movietitle;
	}

	public String getMoviecontent() {
		return moviecontent;
	}

	public void setMoviecontent(String moviecontent) {
		this.moviecontent = moviecontent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoviedirector() {
		return moviedirector;
	}

	public void setMoviedirector(String moviedirector) {
		this.moviedirector = moviedirector;
	}

	public String getMovieposter() {
		return movieposter;
	}

	public void setMovieposter(String movieposter) {
		this.movieposter = movieposter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movieNo == null) ? 0 : movieNo.hashCode());
		result = prime * result + ((moviecontent == null) ? 0 : moviecontent.hashCode());
		result = prime * result + ((moviedirector == null) ? 0 : moviedirector.hashCode());
		result = prime * result + ((movieposter == null) ? 0 : movieposter.hashCode());
		result = prime * result + ((movietitle == null) ? 0 : movietitle.hashCode());
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
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (movieNo == null) {
			if (other.movieNo != null)
				return false;
		} else if (!movieNo.equals(other.movieNo))
			return false;
		if (moviecontent == null) {
			if (other.moviecontent != null)
				return false;
		} else if (!moviecontent.equals(other.moviecontent))
			return false;
		if (moviedirector == null) {
			if (other.moviedirector != null)
				return false;
		} else if (!moviedirector.equals(other.moviedirector))
			return false;
		if (movieposter == null) {
			if (other.movieposter != null)
				return false;
		} else if (!movieposter.equals(other.movieposter))
			return false;
		if (movietitle == null) {
			if (other.movietitle != null)
				return false;
		} else if (!movietitle.equals(other.movietitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Movie [movieNo=");
		builder.append(movieNo);
		builder.append(", movietitle=");
		builder.append(movietitle);
		builder.append(", moviecontent=");
		builder.append(moviecontent);
		builder.append(", id=");
		builder.append(id);
		builder.append(", moviedirector=");
		builder.append(moviedirector);
		builder.append(", movieposter=");
		builder.append(movieposter);
		builder.append("]");
		return builder.toString();
	}

}
