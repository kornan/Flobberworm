package com.flobberworm.framework.views.layout;




/**
 * flobberworm team
 * Created by Kornan on 2017/12/7.
 */

public class ELRManager {

//    //方案1
//    public static void setLoadingView(Context context, View oldView) {
//        View view = (View) oldView;
//        ViewGroup contentParent = (ViewGroup) (view.getParent());
//
//        View newView = LayoutInflater.from(context).inflate(R.layout.fw_layout_loading, contentParent, false);
//        newView.setLayoutParams(oldView.getLayoutParams());
//
//        contentParent.removeView(oldView);
//        contentParent.addView(newView);
//    }
//
//    //方案2
//    public static void setLoadingView2(Context context, final RecyclerView recyclerView) {
//        final View emptyView = LayoutInflater.from(context).inflate(R.layout.fw_layout_loading, null);
//        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        ViewGroup contentParent = (ViewGroup) (recyclerView.getParent());
//        contentParent.addView(emptyView);
//
//        RecyclerView.AdapterDataObserver emptyObserver = new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeChanged(int positionStart, int itemCount) {
//                RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
//                if (adapter != null && emptyView != null) {
//                    if (adapter.getItemCount() == 0) {
//                        emptyView.setVisibility(View.VISIBLE);
//                        recyclerView.setVisibility(View.GONE);
//                    } else {
//                        emptyView.setVisibility(View.GONE);
//                        recyclerView.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//        };
//        recyclerView.getAdapter().registerAdapterDataObserver(emptyObserver);
//        emptyObserver.onChanged();
//    }
}
