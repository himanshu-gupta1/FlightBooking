package e.wolfsoft1.tickets;

import android.app.DatePickerDialog;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.DateFormat;
import java.util.Calendar;

import e.wolfsoft1.tickets.Fragments.HomeFragment;
import e.wolfsoft1.tickets.Fragments.ProfileFragment;
import e.wolfsoft1.tickets.Fragments.SearchFragment;

public class LiveItemsAndTickets extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private BottomBar mBottomBar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_items_and_tickets);

        replace_fragment(new HomeFragment());
        setupBottomBar();
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        mBottomBar = (BottomBar) findViewById(R.id.bottombar);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {

                Fragment fragment = null;
                switch (tabId) {
                    case R.id.search:

                        replace_fragment(new SearchFragment());
                        break;
//                    case R.id.tickets:
//
//                        replace_fragment(new TicketFragment());
//                        break;

                    case R.id.home:

                        replace_fragment(new HomeFragment());
                        break;

                    case R.id.profilee:
                        replace_fragment(new ProfileFragment());
                        break;

                }


            }
        });
    }

            private void setupBottomBar() {
                mBottomBar = (BottomBar) findViewById(R.id.bottombar);

                for (int i = 0; i < mBottomBar.getTabCount(); i++) {
                    BottomBarTab tab = mBottomBar.getTabAtPosition(i);
                    tab.setGravity(Gravity.CENTER);

                    View icon = tab.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_icon);
                    // the paddingTop will be modified when select/deselect,
                    // so, in order to make the icon always center in tab,
                    // we need set the paddingBottom equals paddingTop
                    icon.setPadding(0, icon.getPaddingTop(), 0, icon.getPaddingTop());

                    View title = tab.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_title);
                    title.setVisibility(View.GONE);
                }
            }
    public void replace_fragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        SearchFragment.date=currentDateString;
    }
}
