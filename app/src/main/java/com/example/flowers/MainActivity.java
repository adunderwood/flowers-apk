package com.example.flowers;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> loadDrawables(Class<?> clz) {
        // This is pretty much directly copied from StackOverflow
        // I only have a vague idea of what it does...
        // But at least I'm classy enough to put in the URL
        // https://stackoverflow.com/questions/3221603/retrieving-all-drawable-resources-from-resources-object

        final Field[] fields = clz.getDeclaredFields();
        ArrayList<Integer> imageList = new ArrayList<Integer>();

        for (Field field : fields) {
            final int drawableId; // tf is a final?
            try {
                // really this is the part that I'm fuzzy on
                drawableId = field.getInt(clz);

                // The word of the day is "launcher"
                // Don't say "launcher"
                if (field.getName().contains("launcher")) {
                    // AHHH! You said "launcher"
                } else {
                    // Add it to the list
                    imageList.add(drawableId);
                }

            } catch (Exception e) {
                // Life goes on
                continue;
            }
        }

        // return the whole thing
        return imageList;
    }

    private int getFlowerImage() {
        // first we create an array list to hold all the resources ids
        ArrayList<Integer> imageList = loadDrawables(R.drawable.class);

        // create a new random number
        Random random = new Random(); // god I hate Java lol

        // get a random number that represents an item in
        // the list, limited by the size of the list
        int randomIndex = random.nextInt(imageList.size());

        // get the *actual* item from the list
        int randomImage = imageList.get(randomIndex);

        // return it
        return randomImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup window properties

        // hide title
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        // hide status bar (clock, etc)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // hide action bar
        getSupportActionBar().hide();

        // show view
        setContentView(R.layout.activity_main);

        // change flower image
        ImageView iv = (ImageView) findViewById(R.id.mainImage);
        iv.setImageResource(getFlowerImage());

        // change flower image again on click
        iv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                iv.setImageResource(getFlowerImage());
            }
        });
    }
}