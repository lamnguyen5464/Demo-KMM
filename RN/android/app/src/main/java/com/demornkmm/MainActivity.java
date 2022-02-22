package com.demornkmm;

import android.os.Bundle;
import android.util.Log;

import com.demo.kmmshared.Greeting;
import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "DemoRnKmm";
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Greeting greeting = new Greeting();

    Log.d("@@@", greeting.greeting());
  }
}
