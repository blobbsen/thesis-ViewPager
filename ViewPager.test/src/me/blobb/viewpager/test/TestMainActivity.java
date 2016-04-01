package me.blobb.viewpager.test;

// import static com.google.android.apps.common.testing.ui.espresso.    --- template to copy for more static imports---

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.swipeLeft;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.closeSoftKeyboard;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import me.blobb.viewpager.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

import me.blobb.viewpager.R;

/**
 *  @author andre@blobb.me
 *  
 *  @version 1.0
 *  
 *  @date 03/30/2014
 *  
 *  <b>Class to test the PageViewer.apk</b><br><br> 
 * 
 *  This class offers four methods to test the visibility of the input fields "Username", "Password" and 
 *  the "send" button. All methods are testing the "Send" button functionality as well as its feedback depending
 *  on the given text input.<br><br>
 *  
 *  <ul>
 *  <li>(1)	<i>testSendButtonWithUserAndPassword</i></li>
 *  <li>(2) <i>testSendButtonWithOnlyUser</i></li>
 *  <li>(3) <i>testSendButtonWithOnlyPassword</i></li>
 *  <li>(4) <i>testSendButtonNothingEntered</i></li></ul>
 *  
 *  @param humanReadability	Set true to wait "humanReadDelay" after every interaction.
 *  @param humanReadDelay	Delay in milliseconds after each interaction (if humanReadability = true).
 *  @param necessaryDelay	Some delays are necessary, otherwise the test will hiccup.<br><br><b>NOTE</b>: Very low values can cause errors.
 *  
 *  @param TestStrUser		Test string for the EditText login_name
 *  @param DOT				Char for the "hidden char" in fact of EditText(Password)
 *  @param TestStrPW		Test string for the EditText login_password.<br><br>NOTE: In fact of letters are hidden in a EditText(Password),<br>
 *  						you should use char DOT to be able to fire the "onView(withText(xy)).check(matches(isDisplayed()))" without errors.
 *  
 *	@param onlyUserDialog   Dialog message if only username is entered (referenced to the String in class under test).
 *	@param onlyPwDialog		Dialog message if only password is entered (referenced to the String in class under test).
 *	@param UserAndPwDialog	Dialog message if username and password are entered (referenced to the String in class under test).
 */

public class TestMainActivity extends ActivityInstrumentationTestCase2<MainActivity>
{
	// TEST STRINGS 
	private final String testStrUser = "I like Cortado!";
	private final static char DOT    = '\u2022';
	private final String testStrPw   = DOT+DOT+DOT+DOT+DOT+"!";				
	
	//DIALOG MESSAGES
	private final int UserAndPwDialog   = R.string.UserAndPwDialog;	//R.string.UserAndPwDialog;
	private final int onlyUserDialog    = R.string.onlyUserDialog;
	private final int onlyPwDialog      = R.string.onlyPwDialog;
	private final int nothingDialog 	= R.string.nothingDialog;
	
	// HumanReadability and Delays
	private final boolean humanReadability = true;
	private final int humanReadDelay = 2000;
	private final int necessaryDelay = 1500;
	
	public TestMainActivity() 
	{
		super(MainActivity.class);
	}
	
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		getActivity();
	}
	
	/**
	 * This method checks if the dialog message shows the username.
	 */
	public void testSendButtonWithUserAndPassword()
	{		
		// swipe to LogInPage fragment
		onView(withId(R.id.imageView1)).perform(swipeLeft());				
		if(humanReadability){wait(humanReadDelay);}
		
		// enter "user" string into login_name EditText
		onView(withId(R.id.login_name)).perform(typeText(testStrUser));		
		onView(withText(testStrUser)).perform(closeSoftKeyboard());			// otherwise the test will fail in horizontal view
		if(humanReadability){wait(humanReadDelay);}
		
		// enter "login" string into login_pw EditText
		onView(withId(R.id.login_pw)).perform(typeText(testStrPw));			
		onView(withText(testStrPw)).perform(closeSoftKeyboard());			// otherwise the test will fail in horizontal view
		if(humanReadability){wait(humanReadDelay);}
		else{wait(necessaryDelay);}											// necessary delay, otherwise login_button may not be found
		
		// click "send" button
		onView(withId(R.id.login_button)).perform(click());					
		if(humanReadability){wait(humanReadDelay);}
		
		// check if button fired right message
		onView(withText(UserAndPwDialog+testStrUser)).check(matches(isDisplayed()));
	}

	/**
	 * This method works like testSendButtonWithUserAndPassword(), but it only enters a username.
	 */
	public void testSendButtonWithOnlyUser()
	{		

		// swipe to LogInPage fragment
		onView(withId(R.id.imageView1)).perform(swipeLeft());
		if(humanReadability){wait(humanReadDelay);}
		
		// type text into EditText login_name
		onView(withId(R.id.login_name)).perform(typeText(testStrUser));
		onView(withText(testStrUser)).perform(closeSoftKeyboard());			// otherwise the test will fail in horizontal view
		if(humanReadability){wait(humanReadDelay);}
		else{wait(necessaryDelay);}											// necessary delay, otherwise login_button may not be found
		
		// click "send" button
		onView(withId(R.id.login_button)).perform(click());
		if(humanReadability){wait(humanReadDelay);}
		
		// check if button fired right message
		onView(withText(onlyUserDialog)).check(matches(isDisplayed()));
	}	
	
	/**
	 * This method works like testSendButtonWithUserAndPassword(), but it only enters a password. 
	 */
	public void testSendButtonWithOnlyPassword()
	{		
		// swipe to LogInPage fragment
		onView(withId(R.id.imageView1)).perform(swipeLeft());
		if(humanReadability){wait(humanReadDelay);}		
		else{wait(necessaryDelay);}											// necessary delay, otherwise testStrPw is entered in login_name
		
		// type text into EditText login_pw 
		onView(withId(R.id.login_pw)).perform(typeText(testStrPw));
		onView(withText(testStrPw)).perform(closeSoftKeyboard());			// otherwise the test will fail in horizontal view
		if(humanReadability){wait(humanReadDelay);}
		else{wait(necessaryDelay);}											// necessary delay, otherwise login_button may not be found
		
		// click "send" button
		onView(withId(R.id.login_button)).perform(click());
		if(humanReadability){wait(humanReadDelay);}
		
		// check if button fired right message
		onView(withText(onlyPwDialog)).check(matches(isDisplayed()));
	}
	
	/**
	 * This method works like testSendButtonWithUserAndPassword(), but enters nothing. 
	 */
	public void testSendButtonNothingEntered()
	{		
		// swipe to LogInPage fragment
		onView(withId(R.id.imageView1)).perform(swipeLeft());
		if(humanReadability){wait(humanReadDelay);}										
		else{wait(necessaryDelay);}											// necessary delay, otherwise login_button may not be found
		
		// click "send" button
		onView(withId(R.id.login_button)).perform(click());
		if(humanReadability){wait(humanReadDelay);}
		
		// check if button fired right message
		onView(withText(nothingDialog)).check(matches(isDisplayed()));
	}
	
	/**
	 * this methods save some writing and space.
	 * 
	 * @param pause The current Thread will sleep for "pause" milliseconds.
	 */
	private static void wait(int pause)
	{
		try 
		{
			Thread.sleep(pause);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}	
}











