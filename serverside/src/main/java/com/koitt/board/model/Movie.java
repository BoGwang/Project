package com.koitt.board.model;

// JAVA Bean (VO, DTO)
public class Movie {
	private Integer movieNo;
	private String movietitle;
	private String moviecontent;
	private String movieposter;
	private Integer movieruntime;
	private String moviedirector;

	public Movie() {
	}

	public Movie(Integer movieNo, String movietitle, String moviecontent, String movieposter, Integer movieruntime,
			String moviedirector) {
		super();
		this.movieNo = movieNo;
		this.movietitle = movietitle;
		this.moviecontent = moviecontent;
		this.movieposter = movieposter;
		this.movieruntime = movieruntime;
		this.moviedirector = moviedirector;
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

	public String getMovieposter() {
		return movieposter;
	}

	public void setMovieposter(String movieposter) {
		this.movieposter = movieposter;
	}

	public Integer getMovieruntime() {
		return movieruntime;
	}

	public void setMovieruntime(Integer movieruntime) {
		this.movieruntime = movieruntime;
	}

	public String getMoviedirector() {
		return moviedirector;
	}

	public void setMoviedirector(String moviedirector) {
		this.moviedirector = moviedirector;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieNo == null) ? 0 : movieNo.hashCode());
		result = prime * result + ((moviecontent == null) ? 0 : moviecontent.hashCode());
		result = prime * result + ((moviedirector == null) ? 0 : moviedirector.hashCode());
		result = prime * result + ((movieposter == null) ? 0 : movieposter.hashCode());
		result = prime * result + ((movieruntime == null) ? 0 : movieruntime.hashCode());
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
		if (movieruntime == null) {
			if (other.movieruntime != null)
				return false;
		} else if (!movieruntime.equals(other.movieruntime))
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
		builder.append(", movieposter=");
		builder.append(movieposter);
		builder.append(", movieruntime=");
		builder.append(movieruntime);
		builder.append(", moviedirector=");
		builder.append(moviedirector);
		builder.append("]");
		return builder.toString();
	}

}
