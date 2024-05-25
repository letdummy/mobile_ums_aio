package com.sekalisubmit.laundry.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sekalisubmit.laundry.R;
import com.sekalisubmit.laundry.adapter.UserListAdapter;
import com.sekalisubmit.laundry.data.room.user.User;
import com.sekalisubmit.laundry.data.room.user.UserRoomDatabase;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserRoomDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            InputFragment inputFragment = new InputFragment();

            FragmentManager fragmentManager = getFragmentManager();
            assert fragmentManager != null;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container, inputFragment);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        recyclerView = view.findViewById(R.id.rv_customers);
        db = UserRoomDatabase.getInstance(getContext());

        new GetAllUsersTask().execute();


        return view;
    }

    private class GetAllUsersTask extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {
            return db.userDao().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            UserListAdapter adapter = new UserListAdapter(users);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
    }
}