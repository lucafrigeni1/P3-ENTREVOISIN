package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourProfilActivity extends AppCompatActivity {

    private ImageView profil;
    private ImageButton bReturn;
    private FloatingActionButton bAddToFavourite;
    private TextView name1;
    private TextView name2;
    private TextView adress;
    private TextView phoneNumber;
    private TextView socialMedia;
    private TextView description;
    private Neighbour mNeighbour;
    Boolean isFavorite;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_neighbour);

        findview();
        getNeighbour();
        neighbourImage();
        neighbourInformations();
        setbReturn();
        setbAddToFavourite();
    }

    private void findview() {
        profil = findViewById(R.id.profilImage);
        bReturn = findViewById(R.id.returnButton);
        bAddToFavourite = findViewById(R.id.favouriteButton);
        name1 = findViewById(R.id.nameTitle);
        name2 = findViewById(R.id.nameDescription);
        adress = findViewById(R.id.situationText);
        phoneNumber = findViewById(R.id.phoneText);
        socialMedia = findViewById(R.id.socialText);
        description = findViewById(R.id.description);
    }

    private void getNeighbour(){
        mNeighbour = getIntent().getParcelableExtra("Neighbour");
    }

    private void neighbourImage(){
        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(profil);
    }

    private void neighbourInformations(){
        name1.setText(mNeighbour.getName());
        name2.setText(mNeighbour.getName());
        adress.setText(mNeighbour.getAddress());
        phoneNumber.setText(mNeighbour.getPhoneNumber());
        socialMedia.setText("www.facebook.fr/" + mNeighbour.getName());
        description.setText(mNeighbour.getAboutMe());
    }

    private void setbReturn(){
        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setbAddToFavourite(){
        
    }

}
