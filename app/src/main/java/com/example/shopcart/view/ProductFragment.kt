package com.example.shopcart.view

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopcart.Presenter.ProductDetailMVP
import com.example.shopcart.Presenter.ProductDetailsPresenter
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentProductBinding
import com.example.shopcart.model.CommunicatorAddToCart
import com.example.shopcart.model.ProductDetailImageViewPagerAdapter
import com.example.shopcart.model.ProductVolleyHandler
import com.example.shopcart.model.ReviewAdapter
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.Cart
import com.example.shopcart.model.local.CartDao
import com.example.shopcart.model.productdetails.Image
import com.example.shopcart.model.productdetails.ProductD
import com.example.shopcart.model.productdetails.ProductDetailResponse
import com.example.shopcart.model.productdetails.Review
import com.example.shopcart.model.productdetails.Specification


class ProductFragment : Fragment(),ProductDetailMVP.ProductDetailView {
    private lateinit var binding:FragmentProductBinding
    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var productDetail: ProductD
    private lateinit var home:HomeFragment
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductBinding.inflate(layoutInflater,container,false)
        presenter = ProductDetailsPresenter(ProductVolleyHandler(this.requireContext()), this)
        val productId = arguments?.getString("ProductId").toString()
        home= HomeFragment()

        getProductDetail(productId)
        initDatabase()
        setUpEvents()

        return binding.root
    }

    private fun initDatabase() {
        appDatabase = AppDatabase.getAppDatabase(this.requireContext())!!
        cartDao=appDatabase.getCartDao()
    }

    private fun setUpEvents() {
        binding.btnAddToCart.setOnClickListener {
           /* addCartItemLocally(encryptedSharedPreferences,
                CartItem(
                    productDetail.product_id,
                    productDetail.product_name,
                    productDetail.description,
                    productDetail.price,
                    productDetail.product_image_url,
                    binding.numberPicker.value
                )
            )*/
            cartDao.insertCart(Cart(productDetail.images[1].toString(),productDetail.product_name,productDetail.price.toString()))

            openCartDialog()
        }
    }

    private fun openCartDialog() {
        val builder = AlertDialog.Builder(this.requireContext())
            .setTitle("Thank you!")
            .setMessage("Successfully added to cart")
            .setIcon(R.drawable.shopping_cart)
            .setNeutralButton("Keep Shopping"){ _,_ ->moveToCategories()}
            .setPositiveButton("Go to Cart") { _, _ -> moveToCart() }
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun moveToCategories() {
        val homeFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

        homeFragment?.changeHomeFragment(CategoryFragment())

        }

    private fun moveToCart() {
        val homeFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

        homeFragment?.changeHomeFragment(CartFragment())
    }

    private fun getProductDetail(productId: String) {
        presenter.getProductDetail(productId)
    }

    override fun setResult(data: Any, successed: Boolean) {
        if (successed) {
            productDetail = (data as ProductDetailResponse).product
            setUpViews(productDetail)
        }
    }

    private fun setUpViews(productDetail: ProductD) {
        binding.apply {
            textProductName.text = productDetail.product_name
            val price = "$ " + productDetail.price
            textProductPrice.text = price
            textProductDescription.text = productDetail.description
            ratingBarProduct.rating = productDetail.average_rating.toFloat()
            setUpImageViewPager(ArrayList(productDetail.images))
            setUpReviewRecyclerView(ArrayList(productDetail.reviews))
            setUpSpecificationTable(ArrayList(productDetail.specifications))
        }
    }

    private fun setUpSpecificationTable(specifications: ArrayList<Specification>) {
        if (specifications.isEmpty()) {
            binding.noSpecification.visibility = View.VISIBLE
        } else {
            binding.noSpecification.visibility = View.GONE
        }
        for (specification in specifications) {
            val row = TableRow(context)
            val title = TextView(context)
            val detail = TextView(context)

            val layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            layoutParams.setMargins(0,0,0,15)

            title.layoutParams = layoutParams
            detail.layoutParams = layoutParams

            title.text = specification.title
            title.textSize = 15F
            detail.text = specification.specification
            detail.textSize = 15F
            detail.setTextColor(getResources().getColor(R.color.cyan))

            row.addView(title)
            row.addView(detail)
            binding.tableLayoutSpecification.addView(row)
        }

    }

    private fun setUpReviewRecyclerView(reviews: ArrayList<Review>) {
        val adapter = ReviewAdapter(reviews)
        binding.recyclerViewReview.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerViewReview.adapter = adapter
        if (reviews.isEmpty()) {
            binding.noReviews.visibility = View.VISIBLE
        }
    }

    private fun setUpImageViewPager(images: ArrayList<Image>) {
        val adapter = ProductDetailImageViewPagerAdapter(images)
        binding.viewPagerImageProduct.adapter = adapter
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }

}