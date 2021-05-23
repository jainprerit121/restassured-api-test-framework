package com.gojeck.api.assignment.utils;

public class ComparisonResultSet {

	private String endPoint1;
	private String endPoint2;
	private boolean result;
	
	public ComparisonResultSet(String endPoint1, String endPoint2, boolean result) {
		this.endPoint1 = endPoint1;
		this.endPoint2 = endPoint2;
		this.result = result;
	}
	
	public String getEndPoint1() {
		return endPoint1;
	}

	public void setEndPoint1(String endPoint1) {
		this.endPoint1 = endPoint1;
	}



	public String getEndPoint2() {
		return endPoint2;
	}



	public void setEndPoint2(String endPoint2) {
		this.endPoint2 = endPoint2;
	}



	public boolean isResult() {
		return result;
	}



	public void setResult(boolean result) {
		this.result = result;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endPoint1 == null) ? 0 : endPoint1.hashCode());
		result = prime * result + ((endPoint2 == null) ? 0 : endPoint2.hashCode());
		result = prime * result + (this.result ? 1231 : 1237);
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
		ComparisonResultSet other = (ComparisonResultSet) obj;
		if (endPoint1 == null) {
			if (other.endPoint1 != null)
				return false;
		} else if (!endPoint1.equals(other.endPoint1))
			return false;
		if (endPoint2 == null) {
			if (other.endPoint2 != null)
				return false;
		} else if (!endPoint2.equals(other.endPoint2))
			return false;
		return true;
	}
	
	
	
}
