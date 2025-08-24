package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilneaplikacijeprojekat.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> reviews = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText, commentText, timestampText;
        RatingBar ratingBar;

        ViewHolder(View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.usernameText);
            commentText = itemView.findViewById(R.id.commentText);
            timestampText = itemView.findViewById(R.id.timestampText);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        void bind(Review review) {
            usernameText.setText(review.getUsername());
            ratingBar.setRating(review.getRating());
            timestampText.setText(dateFormat.format(new Date(review.getTimestamp())));

            if (review.getComment() != null && !review.getComment().trim().isEmpty()) {
                commentText.setText(review.getComment());
                commentText.setVisibility(View.VISIBLE);
            } else {
                commentText.setVisibility(View.GONE);
            }
        }
    }
}