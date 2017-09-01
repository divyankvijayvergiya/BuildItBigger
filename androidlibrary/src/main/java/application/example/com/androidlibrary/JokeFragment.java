package application.example.com.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Dell on 31-08-2017.
 */

public class JokeFragment extends Fragment {
    public JokeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.joke_fragment , container , false);
        Intent intent=getActivity().getIntent();
        String joke=intent.getStringExtra(JokeActivity.JOKE_KEY);
        TextView jokeTextView= (TextView) root.findViewById(R.id.joke_text);
        if(joke!=null && joke.length() !=0){
            jokeTextView.setText(joke);
        }
        return root;



    }
}

