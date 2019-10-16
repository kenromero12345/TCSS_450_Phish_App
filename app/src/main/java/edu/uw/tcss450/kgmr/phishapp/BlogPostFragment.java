package edu.uw.tcss450.kgmr.phishapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.uw.tcss450.kgmr.phishapp.blog.BlogPost;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostFragment extends Fragment {


    public BlogPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_post, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {

            BlogPost blogPost = (BlogPost) getArguments().get(getString(R.string.key_blog_post_view));
            //Log.d("Click Student", student.name);
            TextView tvId = getActivity().findViewById(R.id.tv_blogPostTitle);
            tvId.setText(blogPost.getTitle());
            TextView tvName = getActivity().findViewById(R.id.tv_blogPostDate);
            tvName.setText(blogPost.getPubDate());
            TextView tvDetails = getActivity().findViewById(R.id.tv_blogPostTeaser);
            tvDetails.setText(HtmlCompat.fromHtml((blogPost.getTeaser()), HtmlCompat.FROM_HTML_MODE_LEGACY).toString());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button b = (Button) view.findViewById(R.id.button_fullPost);
        b.setOnClickListener(v -> {
            gotoURL();
        });
    }

    private void gotoURL() {
        BlogPost blogPost = (BlogPost) getArguments().get(getString(R.string.key_blog_post_view));
        Intent gotoURL = new Intent(Intent.ACTION_VIEW, Uri.parse(blogPost.getUrl()));
        startActivity(gotoURL);
    }
}
