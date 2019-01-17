package com.edu.edumediaplayer.fileselection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.edumediaplayer.MainActivity;
import com.edu.edumediaplayer.R;

public class FileSelectionScreen extends ListFragment {

    FileListAdapter fileListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.screen_file_selection, container, false);
        final Button streambtn = rootView.findViewById(R.id.stream_btn);
        streambtn.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                String url = "http://api.soundcloud.com/tracks/429009699/stream?client_id=xkpqYPmDf6KG7aL1xM4qfWaJQrHBLSOh";
                ((MainActivity)getActivity()).playStream(url);
            }
        });

        fileListAdapter = new FileListAdapter(getActivity());
        setListAdapter(fileListAdapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView parent, View view, int position, long id) {
        FileListItem item = (FileListItem)(parent.getItemAtPosition(position));
        if (!fileListAdapter.itemClicked(item))
            ((MainActivity)getActivity()).playSong(item.getPath());
    }

    public void refreshFileList() {
        if (fileListAdapter!=null)
            fileListAdapter.refreshFileList();
    }
}
