package com.sekalisubmit.laundry.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sekalisubmit.laundry.R;
import com.sekalisubmit.laundry.data.room.user.User;
import com.sekalisubmit.laundry.data.room.user.UserDao;
import com.sekalisubmit.laundry.data.room.user.UserRoomDatabase;

public class InputFragment extends Fragment {

    private EditText etName, etAddress, etPhone;
    private UserRoomDatabase db;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        etName = view.findViewById(R.id.etName);
        etAddress = view.findViewById(R.id.etAddress);
        etPhone = view.findViewById(R.id.etPhone);
        Button btnAddCustomer = view.findViewById(R.id.btnAddCustomer);
        ImageButton btnBack = view.findViewById(R.id.btnBack);

        db = UserRoomDatabase.getInstance(getContext());

        btnAddCustomer.setOnClickListener(v -> {
            if (validateInputs()) {
                String phone = transformPhone(etPhone.getText().toString());
                if (phone != null) {

                    Toast.makeText(getContext(), etName.getText() + " added successfully", Toast.LENGTH_SHORT).show();
                    User user = new User(etName.getText().toString(), etAddress.getText().toString(), phone);

                    new InsertUserTask().execute(user);
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                } else {
                    etPhone.setError("Invalid phone number");
                }
            }
        });

        btnBack.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }


    @SuppressLint("StaticFieldLeak")
    private class InsertUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            db.userDao().insert(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        }
    }

    private boolean validateInputs() {
        return isNotEmpty(etName, "Name cannot be blank") &&
                isNotEmpty(etAddress, "Address cannot be blank") &&
                isNotEmpty(etPhone, "Phone cannot be blank");
    }

    private boolean isNotEmpty(EditText editText, String errorMessage) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(errorMessage);
            return false;
        }
        return true;
    }

    private String transformPhone(String phone) {
        if (phone.startsWith("0")) {
            return "62" + phone.substring(1);
        } else if (phone.startsWith("62")) {
            return phone;
        } else {
            return null;
        }
    }
}