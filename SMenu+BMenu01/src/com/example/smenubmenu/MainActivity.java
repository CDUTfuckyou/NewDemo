package com.example.smenubmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.smenubmenu.fragment.MenuFragment;
import com.example.smenubmenu.menudrawer.MenuDrawer;
import com.example.smenubmenu.view.FragmentIndicator;
import com.example.smenubmenu.view.FragmentIndicator.OnIndicateListener;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String STATE_MENUDRAWER = "com.example.smenubmenu.MainActivity.menuDrawer";
    private static final String STATE_ACTIVE_VIEW_ID = "com.example.smenubmenu.MainActivity.activeViewId";

    public MenuDrawer mMenuDrawer;
    public int mActiveViewId;
    public static Fragment[] mFragments;

    
	@Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (inState != null) {
            mActiveViewId = inState.getInt(STATE_ACTIVE_VIEW_ID);
        }

        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setContentView(R.layout.activity_main);
        setFragmentIndicator(0);
        
        mMenuDrawer.setMenuView(R.layout.menu_frame);
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        Fragment mFrag = new MenuFragment();
        t.replace(R.id.menu_frame, mFrag);
        t.commit();
        
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        mMenuDrawer.setMenuSize(6 * getWindowManager().getDefaultDisplay().getWidth() / 7);

        TextView activeView = (TextView) findViewById(mActiveViewId);
        if (activeView != null) {
            mMenuDrawer.setActiveView(activeView);
        }
        mMenuDrawer.peekDrawer();
    }
	
	/**
	 * ≥ı ºªØfragment
	 */
	private void setFragmentIndicator(int whichIsDefault) {
		mFragments = new Fragment[4];
		
		mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_home);
		mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_search);
		mFragments[2] = getSupportFragmentManager().findFragmentById(R.id.fragment_settings);
		mFragments[3] = getSupportFragmentManager().findFragmentById(R.id.fragment_fourth);
		
		getSupportFragmentManager().beginTransaction().hide(mFragments[0])
				.hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).show(mFragments[whichIsDefault]).commit();

		FragmentIndicator mIndicator = (FragmentIndicator) findViewById(R.id.indicator);
		FragmentIndicator.setIndicator(whichIsDefault);
		
		mIndicator.setOnIndicateListener(new OnIndicateListener() {
			@Override
			public void onIndicate(View v, int which) {
				
				getSupportFragmentManager().beginTransaction()
						.hide(mFragments[0]).hide(mFragments[1])
						.hide(mFragments[2]).hide(mFragments[3]).show(mFragments[which]).commit();
			}
		});
	}

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        mMenuDrawer.restoreState(inState.getParcelable(STATE_MENUDRAWER));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_MENUDRAWER, mMenuDrawer.saveState());
        outState.putInt(STATE_ACTIVE_VIEW_ID, mActiveViewId);
    }

    @Override
    public void onBackPressed() {
        final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }
        super.onBackPressed();
    }
    
    @Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

    @Override
    public void onClick(View v) {
    	
    }
    
}
