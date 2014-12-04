package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;


public class Crime {
	
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	
	@Override
	public String toString() {
		return mTitle;
	}
	
	public Crime() {
		mId = UUID.randomUUID();
		mDate = new Date();
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}

}