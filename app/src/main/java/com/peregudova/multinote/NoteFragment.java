package com.peregudova.multinote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class NoteFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.note_fragment, container, false);
        Button button = inflate.findViewById(R.id.add_access_button);
        button.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        //does smthg after button clicked
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected();
    }
}
