package com.apk.editor.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.editor.R;
import com.apk.editor.utils.APKEditorUtils;
import com.google.android.material.textview.MaterialTextView;

import java.io.File;
import java.util.List;

/*
 * Created by APK Explorer & Editor <apkeditor@protonmail.com> on March 05, 2021
 */
public class RecycleViewFilePickerAdapter extends RecyclerView.Adapter<RecycleViewFilePickerAdapter.ViewHolder> {

    private static ClickListener clickListener;

    private List<String> data;

    public RecycleViewFilePickerAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecycleViewFilePickerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_filepicker, parent, false);
        return new RecycleViewFilePickerAdapter.ViewHolder(rowItem);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewFilePickerAdapter.ViewHolder holder, int position) {
        if (new File(this.data.get(position)).isDirectory()) {
            holder.mIcon.setImageDrawable(holder.mTitle.getContext().getResources().getDrawable(R.drawable.ic_folder));
            holder.mIcon.setColorFilter(APKEditorUtils.getThemeAccentColor(holder.mTitle.getContext()));
        } else {
            holder.mIcon.setImageDrawable(holder.mIcon.getContext().getResources().getDrawable(R.drawable.ic_file));
            holder.mIcon.setColorFilter(APKEditorUtils.isDarkTheme(holder.mIcon.getContext()) ? holder.mIcon.getContext()
                    .getResources().getColor(R.color.colorWhite) : holder.mIcon.getContext().getResources().getColor(R.color.colorBlack));
        }
        holder.mTitle.setText(new File(this.data.get(position)).getName());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageButton mIcon;
        private MaterialTextView mTitle;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.mIcon = view.findViewById(R.id.icon);
            this.mTitle = view.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecycleViewFilePickerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

}