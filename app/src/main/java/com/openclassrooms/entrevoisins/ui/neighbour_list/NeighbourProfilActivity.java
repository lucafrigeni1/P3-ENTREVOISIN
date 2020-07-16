package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourProfilActivity extends AppCompatActivity {

    private ImageView profil;
    private ImageButton bReturn;
    private FloatingActionButton bAddToFavorite;
    private TextView name1;
    private TextView name2;
    private TextView adress;
    private TextView phoneNumber;
    private TextView socialMedia;
    private TextView description;

    NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_neighbour);

        mApiService = DI.getNeighbourApiService();

        findview();
        getNeighbour();
        neighbourImage();
        neighbourInformations();
        setbReturn();
        neighbourState();
        setbAddToFavorite();
    }

    private void findview() {
        profil = findViewById(R.id.profilImage);
        bReturn = findViewById(R.id.returnButton);
        bAddToFavorite = findViewById(R.id.favouriteButton);
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
                .apply(RequestOptions.centerCropTransform())
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
        bReturn.setOnClickListener(view -> finish());
    }

    private void neighbourState(){
        if (mNeighbour.getFavorite() == false){
            bAddToFavorite.setImageResource(R.drawable.ic_star_border_white_24dp);
        }else {
            bAddToFavorite.setImageResource(R.drawable.ic_star_white_24dp);
        }
    }

    private void setbAddToFavorite(){
        bAddToFavorite.setOnClickListener(view -> setFavoriteNeighbour());
    }

    private void setFavoriteNeighbour(){
        if (mNeighbour.getFavorite() == false){
            mNeighbour.setFavorite(true);
            Toast.makeText(this,"Ajouté aux favoris",Toast.LENGTH_SHORT).show();
            bAddToFavorite.setImageResource(R.drawable.ic_star_white_24dp);
            mApiService.addFavoriteNeighbour(mNeighbour);
        }else {
            mNeighbour.setFavorite(false);
            Toast.makeText(this,"Supprimé des favoris",Toast.LENGTH_SHORT).show();
            bAddToFavorite.setImageResource(R.drawable.ic_star_border_white_24dp);
            mApiService.deleteFavoriteNeighbour(mNeighbour);
        }
    }
}
