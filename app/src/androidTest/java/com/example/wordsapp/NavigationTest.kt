package com.example.wordsapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

// 3 android dependencies added in gradle:
// androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:3.0.2'
// androidTestImplementation 'androidx.navigation:navigation-testing:2.3.5'
// debugImplementation 'android.fragment:fragment-testing:1.3.6'
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Test
    fun navigate_to_words() {
        //creating instance of navigation controller
        val navController = TestNavHostController( ApplicationProvider.getApplicationContext() )
        // setting fragment equivalent of ActivityScenarioRule (isolating fragment for testing)
        val letterListScenario = launchFragmentInContainer<LetterListFragment>(themeResId = R.style.Theme_Words)
        // declaring which navigation graph to use for the fragment
        letterListScenario.onFragment {
            fragment -> navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // triggering the event that prompts the navigation
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }
}