package com.mobdev.datapass_fragmenttofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    private FragmentAListener listener;
    private EditText editText;

    //interface declaration and method declaration for the interface
    public interface FragmentAListener{
        void onInputASent(CharSequence input);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a,container,false);

        editText = v.findViewById(R.id.edit_text);
        Button button = v.findViewById(R.id.button_ok);
        button.setOnClickListener(v1 -> {
            CharSequence input = editText.getText();
            listener.onInputASent(input);
        });

        return v;
    }

    public void updateEditText(CharSequence newText){
        editText.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentAListener){
            listener = (FragmentAListener) context;
        }else {
            throw new RuntimeException(context.toString().concat("Interface method not implemented"));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}