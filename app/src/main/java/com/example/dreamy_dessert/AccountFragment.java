package com.example.dreamy_dessert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView editProfileImageView;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Set up onClick listeners
        editProfileImageView = view.findViewById(R.id.edit_profile_image);
        LinearLayout changePasswordLayout = view.findViewById(R.id.change_password);
        LinearLayout faqLayout = view.findViewById(R.id.faq);
        LinearLayout giveFeedbackLayout = view.findViewById(R.id.give_feedback);
        LinearLayout contactUsLayout = view.findViewById(R.id.contact_us);
        LinearLayout privacyPolicyLayout = view.findViewById(R.id.privacy_policy);
        View logOutButton = view.findViewById(R.id.log_out);

        editProfileImageView.setOnClickListener(v -> openGallery());
        changePasswordLayout.setOnClickListener(v -> onChangePasswordClicked());
        faqLayout.setOnClickListener(v -> onFAQClicked());
        giveFeedbackLayout.setOnClickListener(v -> onGiveFeedbackClicked());
        contactUsLayout.setOnClickListener(v -> onContactUsClicked());
        privacyPolicyLayout.setOnClickListener(v -> onPrivacyPolicyClicked());
        logOutButton.setOnClickListener(v -> onLogOutClicked());

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            editProfileImageView.setImageURI(selectedImage);
        }
    }

    private void onEditProfileClicked() {
        Toast.makeText(getActivity(), "Edit Profile clicked", Toast.LENGTH_SHORT).show();
    }

    private void onChangePasswordClicked() {
        Toast.makeText(getActivity(), "Change Password clicked", Toast.LENGTH_SHORT).show();
    }

    private void onFAQClicked() {
        Toast.makeText(getActivity(), "FAQ clicked", Toast.LENGTH_SHORT).show();
    }

    private void onGiveFeedbackClicked() {
        Toast.makeText(getActivity(), "Give Us Feedback clicked", Toast.LENGTH_SHORT).show();
    }

    private void onContactUsClicked() {
        Toast.makeText(getActivity(), "Contact Us clicked", Toast.LENGTH_SHORT).show();
    }

    private void onPrivacyPolicyClicked() {
        Toast.makeText(getActivity(), "Privacy Policy clicked", Toast.LENGTH_SHORT).show();
    }

    private void onLogOutClicked() {
        Toast.makeText(getActivity(), "Log Out clicked", Toast.LENGTH_SHORT).show();
    }
}
