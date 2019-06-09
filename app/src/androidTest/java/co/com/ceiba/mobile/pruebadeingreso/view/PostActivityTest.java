package co.com.ceiba.mobile.pruebadeingreso.view;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import co.com.ceiba.mobile.pruebadeingreso.R;

import static org.junit.Assert.*;

public class PostActivityTest {

    @Rule
    public ActivityTestRule<PostActivity> activityTestRule = new ActivityTestRule<>(PostActivity.class);

    private PostActivity postsActivity = null;

    @Before
    public void setUp(){
        postsActivity = activityTestRule.getActivity();
    }

    @Test
    public void testMainActivity(){
        RecyclerView recyclerViewPostsResults = postsActivity.findViewById(R.id.recyclerViewPostsResults);
        assertNotNull(recyclerViewPostsResults);

    }
    @After
    public void tearDown(){
        postsActivity = null;
    }
}