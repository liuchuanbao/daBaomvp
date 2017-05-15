
package cn.efarm360.com.dabaomvp.activity.CoordinatorLayoutTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.efarm360.com.dabaomvp.R;

public class FakePageFragment extends Fragment {
	private RecyclerView mRootView;

	// 填充布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = (RecyclerView) inflater.inflate(R.layout.fragment_page, container, false);
		return mRootView;
	}

	//初始化数据
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecyclerView();
	}
//	RecyclerView 设置adapter
	private void initRecyclerView() {
		mRootView.setAdapter(new FakePageAdapter(20));
	}

	public static Fragment newInstance() {
		return new FakePageFragment();
	}


	//RecyclerView 的 adapter
	private static class FakePageAdapter extends RecyclerView.Adapter<FakePageVH> {
		private final int numItems;

		//初始化
		FakePageAdapter(int numItems) {
			this.numItems = numItems;
		}

		// 绑定view
		@Override
		public FakePageVH onCreateViewHolder(ViewGroup viewGroup, int i) {
			View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.list_item_card, viewGroup, false);

			return new FakePageVH(itemView);
		}

		//view的操作，数据的填充
		@Override
		public void onBindViewHolder(FakePageVH fakePageVH, int i) {
			// do nothing
		}

		//获取view的总数
		@Override
		public int getItemCount() {
			return numItems;
		}
	}

	// ViewHolder
	private static class FakePageVH extends RecyclerView.ViewHolder {
		FakePageVH(View itemView) {
			super(itemView);
		}
	}
}
