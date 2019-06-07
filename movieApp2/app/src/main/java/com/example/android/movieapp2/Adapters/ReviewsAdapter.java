package com.example.android.movieapp2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.movieapp2.Models.Reviews;
import com.example.android.movieapp2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MovieReviewViewHolder> {

    private Context context;
    private List<Reviews> reviewList;

    public ReviewsAdapter(Context context, List<Reviews> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_reviews_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieReviewViewHolder holder, int position) {
        final Reviews review = reviewList.get(position);
        holder.bindReview(reviewList.get(position));

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = review.isExpanded();
            review.setExpanded(!expanded);

            notifyItemChanged(position);
        });

    }

    @Override
    public int getItemCount() {
        return reviewList == null ? 0 : reviewList.size();
    }

    class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author)
        TextView author;
        @BindView(R.id.tv_content)
        TextView content;
        @BindView(R.id.sub_item)
        LinearLayout subItem;

        MovieReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindReview(Reviews review) {
            boolean expanded = review.isExpanded();
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            author.setText(review.getAuthor());
            content.setText(review.getContent());
        }
    }
}
