package com.github.denisura.coursera.ui.catalog;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.denisura.coursera.R;
import com.github.denisura.coursera.data.model.Catalog;
import com.github.denisura.coursera.data.model.Item;
import com.github.denisura.coursera.data.network.CourseraApi;
import com.github.denisura.coursera.data.network.CourseraService;
import com.github.denisura.coursera.ui.BaseFragment;
import com.github.denisura.coursera.ui.catalog.pagination.PaginationTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class CatalogFragment extends BaseFragment{

    // the fragment initialization parameters
    private static final String ARG_QUERY = "query";
    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    private final static int LIMIT = 7;
    private CatalogAdapter recyclerViewAdapter;
    private Subscription pagingSubscription;
    private CourseraApi _courseraService;

    private String mQuery = "";

    public CatalogFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param query Parameter 1.
     * @return A new instance of fragment CatalogFragment.
     */
    public static CatalogFragment newInstance(String query) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        setRetainInstance(true);
        init(savedInstanceState);
        return rootView;
    }


    private void init(Bundle savedInstanceState) {
        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 1);
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();
        // init adapter for the first time
        if (savedInstanceState == null) {
            recyclerViewAdapter = new CatalogAdapter();
            recyclerViewAdapter.setHasStableIds(true);
        }
        recyclerViewAdapter.setContext(getContext().getApplicationContext());
        mRecyclerView.setSaveEnabled(true);

        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        // if all items was loaded we don't need Pagination
        if (recyclerViewAdapter.isAllItemsLoaded()) {
            Timber.d("init: All Items Loaded");
            return;
        }

        _courseraService = CourseraService.createCourseraService();

        // RecyclerView pagination
        PaginationTool<Catalog> paginationTool =
                PaginationTool
                        .buildPagingObservable(mRecyclerView, offset -> _courseraService.searchCatalog(mQuery, offset, LIMIT))
                        .setLimit(LIMIT)
                        .build();

        pagingSubscription = paginationTool
                .getPagingObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Catalog>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Catalog catalog) {
                        int index = recyclerViewAdapter.getItemCount();
                        List<Item> items = new ArrayList<>();
                        for (Object entity : catalog.getEntities()) {
                            items.add(new Item(index, entity));
                            index++;
                        }
                        recyclerViewAdapter.addNewItems(items);
                        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.getItemCount() - catalog.getEntities().size());
                    }
                });
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (pagingSubscription != null && !pagingSubscription.isUnsubscribed()) {
            pagingSubscription.unsubscribe();
        }
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(null);
        }
        recyclerViewAdapter.setContext(null);
        super.onDestroyView();
    }
}
