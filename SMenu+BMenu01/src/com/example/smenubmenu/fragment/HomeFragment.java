package com.example.smenubmenu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smenubmenu.HelpActivity;
import com.example.smenubmenu.R;
import com.example.smenubmenu.view.TitleView;
import com.example.smenubmenu.view.TitleView.OnLeftButtonClickListener;
import com.example.smenubmenu.view.TitleView.OnRightButtonClickListener;

/**
 * @author yangyu
 *	������������ҳfragmentҳ��
 */
public class HomeFragment extends Fragment {

	private View mParent;
	
	private FragmentActivity mActivity;
	
	private TitleView mTitle;
	
	//private TextView mText;
	
	private ListView home_listView;
	
	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static HomeFragment newInstance(int index) {
		HomeFragment f = new HomeFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		mParent = getView();

		mTitle = (TitleView) mParent.findViewById(R.id.title);
		mTitle.setTitle(R.string.title_home);
		mTitle.setLeftButton(R.string.exit, new OnLeftButtonClickListener(){

			@Override
			public void onClick(View button) {
				mActivity.finish();
			}
			
		});
		mTitle.setRightButton(R.string.help, new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				goHelpActivity();
			}
		});
		
		//mText = (TextView) mParent.findViewById(R.id.fragment_home_text);
		home_listView = (ListView) mParent.findViewById(R.id.home_listView);
		
		String n[]={"xx","aa","xx","aa","xx","aa","xx","aa","xx","aa","xx","aa","xx","aa","xx","aa","xx","aa"};
		ArrayAdapter<String> aa = new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,n);
		home_listView.setAdapter(aa);

	}
	
	private void goHelpActivity() {
		Intent intent = new Intent(mActivity, HelpActivity.class);
		startActivity(intent);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
