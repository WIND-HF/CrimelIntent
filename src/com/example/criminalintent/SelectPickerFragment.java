package com.example.criminalintent;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class SelectPickerFragment extends DialogFragment {
	public static final String EXTRA_SELECT =
			"com.example.android.criminalintent.select";
	
	public static final String DIALOG_DATE = "date";
	public static final String DIALOG_TIME = "time";
	private static final int REQUEST_DATE = 0;
	private static final int REQUEST_TIME = 1;
	
	private Date mDate;
	private Button mDateButton;
	private Button mTimeButton;
	
	public static SelectPickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SELECT, date);
		
		SelectPickerFragment fragment = new SelectPickerFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setRetainInstance(true);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_SELECT);
		
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.fragment_select_picker, null);
		mDateButton = (Button)v.findViewById(R.id.select_date);
		mDateButton.setText("Date");
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getActivity()
						.getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment
						.newInstance(mDate);
				dialog.setTargetFragment(SelectPickerFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});

		mTimeButton = (Button)v.findViewById(R.id.select_time);
		mTimeButton.setText("Time"); // TODO : update time display
		mTimeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getActivity()
						.getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment
						.newInstance(mDate);
				dialog.setTargetFragment(SelectPickerFragment.this, REQUEST_TIME);
				dialog.show(fm, DIALOG_TIME);				
			}
		});
		
		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle(R.string.select_picker_title)
		.setPositiveButton(
				android.R.string.ok,
				new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);						
					}
				})
		.create();	
	}
	
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		Intent intent = new Intent();
		intent.putExtra(EXTRA_SELECT, mDate);
		
		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_DATE) {
			mDate = (Date)data
					.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
		}
		if (requestCode == REQUEST_TIME) {
			mDate = (Date)data
					.getSerializableExtra(TimePickerFragment.EXTRA_TIME);

		}
	}
	
	@Override
	 public void onDestroyView() {
	     if (getDialog() != null && getRetainInstance())
	         getDialog().setDismissMessage(null);
	     super.onDestroyView();
	 }

}
