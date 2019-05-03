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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.karlo.aplikacija1.API.RESTAPIInterface;
import com.example.karlo.aplikacija1.API.RetrofitClient;
import com.example.karlo.aplikacija1.Model.BloodType;
import com.example.karlo.aplikacija1.Model.BloodUnit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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
        ScrollView view = (ScrollView) inflater.inflate(R.layout.fragment_info, container, false);
        final ListView listViewBloodTypes = (ListView) view.findViewById(R.id.bloodTypeListView);
        final ListView listViewBloodUnits = (ListView) view.findViewById(R.id.bloodUnitListView);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RESTAPIInterface.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RESTAPIInterface restapiInterface = retrofit.create(RESTAPIInterface.class);

        RESTAPIInterface api = RetrofitClient.getInstance().getAPI();

        Call<List<BloodType>> callBloodType = api.getBloodTypes();
        Call<List<BloodUnit>> callBloodUnit = api.getBloodUnits();

        callBloodType.enqueue(getBloodTypesListCallback(listViewBloodTypes));
        callBloodUnit.enqueue(getBloodUnitsListCallback(listViewBloodUnits));

        return view;
    }

    @NonNull
    private Callback<List<BloodUnit>> getBloodUnitsListCallback(final ListView listView) {
        return new Callback<List<BloodUnit>>() {
            @Override
            public void onResponse(@NonNull Call<List<BloodUnit>> call, @NonNull Response<List<BloodUnit>> response) {

                List<BloodUnit> bloodUnits = response.body();
                if (bloodUnits != null) {
                    String[] bloodUnitsString = new String[bloodUnits.size()];

                    for (BloodUnit b : bloodUnits) {
                        bloodUnitsString[bloodUnits.indexOf(b)] =
                                String.format("ID: \t%s\nNumber of blood units: \t%s",
                                        String.valueOf(b.getID()),
                                        String.valueOf(b.getNumberOfUnits())
                                                + ((b.getNumberOfUnits() == 0) ? "%" : "0%"));
                    }

                    setListItemAdapter(bloodUnitsString, listView);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BloodUnit>> call, @NonNull Throwable t) {
                Log.d("onFailure ", t.getMessage());
            }
        };
    }

    @NonNull
    private Callback<List<BloodType>> getBloodTypesListCallback(final ListView listView) {
        return new Callback<List<BloodType>>() {
            @Override
            public void onResponse(@NonNull Call<List<BloodType>> call, @NonNull Response<List<BloodType>> response) {

                List<BloodType> bloodTypes = response.body();
                if (bloodTypes != null) {
                    String[] bloodTypesString = new String[bloodTypes.size()];

                    for (BloodType b : bloodTypes) {
                        bloodTypesString[bloodTypes.indexOf(b)] =
                                String.format("Blood type:  \t%s\nBloodUnitID:  \t%s",
                                        b.getType(),
                                        String.valueOf(b.getBloodUnitID()));
                    }

                    setListItemAdapter(bloodTypesString, listView);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BloodType>> call, @NonNull Throwable t) {
                Log.d("onFailure ", t.getMessage());
            }
        };
    }

    private void setListItemAdapter(String[] bloodUnitsString, ListView listView) {
        listView.setAdapter(
                new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        bloodUnitsString
                ));

        setScrollViewListItemsHeight(listView);
    }

    private void setScrollViewListItemsHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int listViewHeight = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                if (listItem != null) {
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    listViewHeight += listItem.getMeasuredHeight();
                }
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = listViewHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
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
