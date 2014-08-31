package com.gazelle.sumrize.ranker;


public class MyPair {
	private String _first;
	private double _second;

	public MyPair(String first, double second)
	  {
	    this._first = first;
	    this._second = second;
	  }

	  public String getFirst()
	  {
	    return _first;
	  }


	  public double getSecond()
	  {
	    return _second;
	  }

	  public void setFirst(String toSet)
	  {
	    _first = toSet;
	  }

	  public void setSecond(double toSet)
	  {
	    _second = toSet;
	  }
	  
	     

}
