package com.example.smenubmenu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smenubmenu.MainActivity;
import com.example.smenubmenu.R;

public class MenuFragment extends Fragment implements OnClickListener{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.menu_scrollview, null);

		view.findViewById(R.id.item1).setOnClickListener(this);
		view.findViewById(R.id.item2).setOnClickListener(this);
		view.findViewById(R.id.item3).setOnClickListener(this);
		view.findViewById(R.id.item4).setOnClickListener(this);
		view.findViewById(R.id.item5).setOnClickListener(this);
		view.findViewById(R.id.item6).setOnClickListener(this);

		return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		((MainActivity)getActivity()).mMenuDrawer.setActiveView(v);
		//((MainActivity)getActivity()).mContentTextView.setText("Active item: " + ((TextView) v).getText());
        ((MainActivity)getActivity()).mMenuDrawer.closeMenu();
        ((MainActivity)getActivity()).mActiveViewId = v.getId();
		if(v.getId()==R.id.item1){
			Log.d("==================", "item1");
		}
	}

}
