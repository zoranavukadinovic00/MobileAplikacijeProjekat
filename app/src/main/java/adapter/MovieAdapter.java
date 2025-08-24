package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilneaplikacijeprojekat.R;
import java.util.ArrayList;
import java.util.List;
import Model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movies = new ArrayList<>();
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public MovieAdapter(OnMovieClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, genreText, yearText, ratingText;
        ImageView posterImage;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            genreText = itemView.findViewById(R.id.genreText);
            yearText = itemView.findViewById(R.id.yearText);
            ratingText = itemView.findViewById(R.id.ratingText);
            posterImage = itemView.findViewById(R.id.posterImage);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onMovieClick(movies.get(pos));
                    }
                }
            });
        }

        void bind(Movie movie) {
            titleText.setText(movie.getTitle());
            genreText.setText(movie.getGenre());
            yearText.setText(String.valueOf(movie.getYear()));
            ratingText.setText(String.format("%.1f", movie.getRating()));
        }
    }
}