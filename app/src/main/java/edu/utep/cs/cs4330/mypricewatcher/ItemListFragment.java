package edu.utep.cs.cs4330.mypricewatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class ItemListFragment extends ListFragment {
    public interface Listener {
        void itemClicked(String name);
    }

    private Listener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<String> data = new ArrayList<>();
        data.add("maly");
        data.add("prd");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked((String) listView.getItemAtPosition(position));
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
