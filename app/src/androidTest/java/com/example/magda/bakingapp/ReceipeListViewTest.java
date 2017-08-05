package com.example.magda.bakingapp;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ReceipeListViewTest {

    @Rule public ActivityTestRule<MainActivity> mMainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkReceipeListViewLoadsData() {
        onView(withId(R.id.recycler_view_receipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.receipe_detail_fragment_container)).check(matches(isDisplayed()));
        onView(withId(R.id.receipe_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.receipe_detail_title)).check(matches(withText("Brownies")));
    }
}
