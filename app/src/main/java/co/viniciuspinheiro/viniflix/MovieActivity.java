package co.viniciuspinheiro.viniflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.viniciuspinheiro.viniflix.model.Movie;


public class MovieActivity extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtDesc;
    private TextView txtCast;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        txtTitle = findViewById(R.id.text_view_title);
        txtDesc = findViewById(R.id.text_view_desc);
        txtCast = findViewById(R.id.text_view_cast);
        recyclerView = findViewById(R.id.recycler_view_similar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
            getSupportActionBar().setTitle(null);
        }

        txtTitle.setText("Homem Aranha 2");
        txtDesc.setText("Quando uma falha na experiência de fusão nuclear resulta em uma explosão que mata sua esposa, o Dr. Otto Octavius é transformado em Dr. Octopus, um ciborgue com tentáculos de metal. Doc Ock culpa o Homem-Aranha pelo acidente e quer vingança. Enquanto isso, o alter ego do herói, Peter Parker, perde seus poderes. Para complicar as coisas, o seu melhor amigo odeia o Homem-Aranha e sua amada fica noiva.");
        txtCast.setText(getString(R.string.cast, "Robert Pattinson, Zoe Kravits, Paul Dano, Collin Farrell"));

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Movie movie = new Movie();
            movies.add(movie);
        }

        recyclerView.setAdapter(new MovieAdapter(movies));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private static class MovieHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewCover;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCover = itemView.findViewById(R.id.image_view_cover);
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MainActivity.MovieHolder> {

        private final List<Movie> movies;

        private MovieAdapter(List<Movie> movies) {
            this.movies = movies;
        }

        @NonNull
        @Override
        public MainActivity.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainActivity.MovieHolder(getLayoutInflater().inflate(R.layout.movie_item_similar, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.MovieHolder holder, int position) {
            Movie movie = movies.get(position);
            //  holder.imageViewCover.setImageResource(movie.getCoverUrl());
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }
}
