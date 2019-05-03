package com.example.karlo.aplikacija1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.karlo.aplikacija1.API.RESTAPIInterface;
import com.example.karlo.aplikacija1.API.RetrofitClient;
import com.example.karlo.aplikacija1.Model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         /* HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);*/


        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_map, container, false);
        final ListView listViewMap = (ListView) view.findViewById(R.id.locationListView);

//
//        Gson gsonSetDateFormatFromJSON = new GsonBuilder()
//                .setDateFormat("dd/MM/yyyy HH:mm")
//                .create();
//
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RESTAPIInterface.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gsonSetDateFormatFromJSON))
//                /*.client(httpClient.build())*/
//                .build();
//        final RESTAPIInterface restapiInterface = retrofit.create(RESTAPIInterface.class);

        RESTAPIInterface api = RetrofitClient.getInstance().getAPI();

        Call<List<Location>> callLocations = api.getLocations();
        callLocations.enqueue(getLocationsListViewCallback(listViewMap));

        return view;
    }

    @NonNull
    private Callback<List<Location>> getLocationsListViewCallback(final ListView listViewMap) {
        return new Callback<List<Location>>() {
            @Override
            public void onResponse(@NonNull Call<List<Location>> call, @NonNull Response<List<Location>> response) {

                List<Location> locations = response.body();

                if (locations != null) {
                    String[] locationString = new String[locations.size()];
                    for (Location l : locations) {
                        locationString[locations.indexOf(l)] = String.format("%s\n%s\n%s\n%s",
                                l.getAddress(),
                                l.getNameOfFoundation(),
                                l.getDateOfAction(),
                                l.getShortTimesOfAction());
                    }

                    setListItemAdapter(locationString, listViewMap);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Location>> call, @NonNull Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        };
    }

    private void setListItemAdapter(String[] locationString, ListView listViewMap) {
        listViewMap.setAdapter(
                new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        locationString
                )
        );
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
