package austinlentzmobileapp.pickupi399;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class HomeScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

    }

    public void toCreate(View view) {
        Intent myIntent = new Intent(this, createPage.class);

        startActivity(myIntent);
    }//goes to create page

    public void toExplore(View view) {
        Intent myIntent = new Intent(this, explorePage.class);
        startActivity(myIntent);
    } //goes to explore page

    public void onTempPage(View view) {
        Intent myIntent = new Intent(this, austinlentzmobileapp.pickupi399.tempPage.class);
        startActivity(myIntent);
    } //goes to temp page

}