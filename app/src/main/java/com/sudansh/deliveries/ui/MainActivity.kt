package com.sudansh.deliveries.ui

import android.app.ActivityOptions
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sudansh.deliveries.R
import com.sudansh.deliveries.data.Resource
import com.sudansh.deliveries.data.Status
import com.sudansh.deliveries.databinding.ActivityMainBinding
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem
import com.sudansh.deliveries.util.action
import com.sudansh.deliveries.util.observeNonNull
import com.sudansh.deliveries.util.snack
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), OnItemClickListener {
    private val viewModel by viewModel<MainViewModel>()
    private val deliverAdapter by lazy { DeliverAdapter(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        val divider = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.item_divider)!!)
        }

        with(recyclerView) {
            adapter = deliverAdapter
            addItemDecoration(divider)
            itemAnimator = SlideInUpAnimator()
        }
        swipe.setOnRefreshListener(viewModel::refresh)

        viewModel.deliveries.observeNonNull(this) {
            updateUI(it)
        }
    }

    private fun updateUI(resource: Resource<List<DeliveryItem>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                if (swipe.isRefreshing) swipe.isRefreshing = false
                deliverAdapter.updateItems(resource.data.orEmpty())
                viewModel.isLoading.set(false)
            }
            Status.LOADING -> viewModel.isLoading.set(true)
            Status.ERROR -> {
                if (swipe.isRefreshing) swipe.isRefreshing = false
                viewModel.isLoading.set(false)
                showError()
            }
        }

    }

    private fun showError() {
        mainContainer.snack(getString(R.string.error_message), Snackbar.LENGTH_INDEFINITE) {
            action(getString(R.string.yes)) { viewModel.refresh() }
        }
    }

    override fun onItemClick(delivery: DeliveryItem, image: ImageView, description: TextView) {
        val options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create<View, String>(image, getString(R.string.transitionImage)),
                Pair.create<View, String>(description, getString(R.string.transitionDescription)))
        Intent(this, MapsActivity::class.java).apply {
            putExtra(MapsActivity.KEY_DELIVERY, delivery)
        }.also { startActivity(it, options.toBundle()) }

    }
}