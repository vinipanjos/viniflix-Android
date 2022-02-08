package co.viniciuspinheiro.viniflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.viniciuspinheiro.viniflix.model.Movie;
import co.viniciuspinheiro.viniflix.model.Category;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_main);

        List<Category> categories = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            Category category = new Category();
            category.setName("Categoria " + j);


            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                Movie movie = new Movie();
                //book.setCoverUrl(R.drawable.book);
                movies.add(movie);
            }

            category.setMovies(movies);
            categories.add(category);
        }
        mainAdapter = new MainAdapter(categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(mainAdapter);
    }

    static class MovieHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewCover;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCover = itemView.findViewById(R.id.image_view_cover);
        }
    }

    private static class CategoryHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        RecyclerView recyclerViewTitle;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            recyclerViewTitle = itemView.findViewById(R.id.recycler_view_book);
        }
    }

    private class MainAdapter extends RecyclerView.Adapter<CategoryHolder> {

        private final List<Category> categories;

        private MainAdapter(List<Category> categories) {
            this.categories = categories;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CategoryHolder(getLayoutInflater().inflate(R.layout.category_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
            Category category = categories.get(position);
            holder.textViewTitle.setText(category.getName());
            holder.recyclerViewTitle.setAdapter(new MovieAdapter(category.getMovies()));
            holder.recyclerViewTitle.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.HORIZONTAL, false));

        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
    }
    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

        private final List<Movie> movies;

        private MovieAdapter(List<Movie> movies) {
            this.movies = movies;
        }

        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MovieHolder(getLayoutInflater().inflate(R.layout.movie_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
            Movie movie = movies.get(position);
            //holder.imageViewCover.setImageResource(book.getCoverUrl());
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }
}