package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.common.OnClickListener;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.CeibaDataBase;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(PostActivity.class.getName(), null, false);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void testMainActivity(){
        RecyclerView recyclerViewSearchResults = mainActivity.findViewById(R.id.recyclerViewSearchResults);
        assertNotNull(recyclerViewSearchResults);
    }

    @Test
    public void dbExixts(){
        CeibaDataBase appDatabase = CeibaDataBase.getDatabase(mainActivity.getApplicationContext());
        assertNotNull(appDatabase);
    }

    @Test
    public void goToPosts(){
        RecyclerView recyclerViewSearchResults = mainActivity.findViewById(R.id.recyclerViewSearchResults);
        UsersAdapter adapter = (UsersAdapter)recyclerViewSearchResults.getAdapter();
        assertNotNull(adapter);
        OnClickListener listener = adapter.getClickListener();
        assertNotNull(listener);
        listener.onClick(null, 1);

        Activity postActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(postActivity);
    }

    @After
    public void tearDown(){
        mainActivity = null;
    }
}