package com.bochuan.pinke.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bochuan.pinke.R
import com.bochuan.pinke.adapter.SearchPartnerAdapter
import com.gome.work.common.divider.CustomNewsDivider
import com.gome.work.core.model.SearchPartnerItem
import com.gome.work.core.net.IResponseListener
import com.gome.work.core.net.WebApi
import kotlinx.android.synthetic.main.fragment_common_refresh_listview.*

class MySearchPartnerListFragment : BaseFragment() {

    var mAdapter: SearchPartnerAdapter? = null

    var index = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (mAdapter == null) {
            mAdapter = SearchPartnerAdapter(mActivity!!)
            recyclerView.adapter = mAdapter
            recyclerView.layoutManager = LinearLayoutManager(mActivity!!)
            recyclerView.addItemDecoration(
                CustomNewsDivider(context, DividerItemDecoration.HORIZONTAL, 2, R.color.divider_color)
            )
            getData(index, "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        smart_refresh_layout.setEnableLoadMore(false)
        smart_refresh_layout.autoRefresh(200)
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_common_refresh_listview
    }

    override fun refreshData() {

    }


    private fun getData(pageIndex: Int, courseCode: String) {
        WebApi.getInstance()
            .getMySearchPartnerList(pageIndex,
                20, object : IResponseListener<SearchPartnerItem.ResponseWrapper> {
                    override fun onError(code: String?, message: String?) {

                    }

                    override fun onSuccess(result: SearchPartnerItem.ResponseWrapper?) {
                        mAdapter!!.setItemList(result?.dataItems)

                    }

                })
    }


}
