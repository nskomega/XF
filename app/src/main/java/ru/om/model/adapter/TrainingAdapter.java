package ru.om.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.om.R;
import ru.om.model.pojo.Training;
import ru.om.model.utilities.Constants;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.Holder> {

    public static String TAG = TrainingAdapter.class.getSimpleName();

    private List<Training> mTrainings;

    public TrainingAdapter(List<Training> trainings) {
        mTrainings = trainings;
    }

    public void addTraining(Training training) {
        mTrainings.add(training);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Training currentTraining = mTrainings.get(position);
        holder.mTitle.setText(currentTraining.mTitle);
        holder.mText.setText(currentTraining.mText);
        holder.mUrl.setText("https://www.youtube.com/watch?v=" + currentTraining.mUrl);
        Picasso.with(holder.itemView.getContext()).load(Constants.PHOTO_URL_1 + currentTraining.mUrl + Constants.PHOTO_URL_2).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mTrainings.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView mTitle, mText, mUrl;
        public ImageView mImage;

        public Holder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.trainingTitle);
            mText = (TextView) itemView.findViewById(R.id.trainingText);
            mUrl = (TextView) itemView.findViewById(R.id.trainingUrl);
            mImage = (ImageView) itemView.findViewById(R.id.trainingImage);
        }
    }
}
