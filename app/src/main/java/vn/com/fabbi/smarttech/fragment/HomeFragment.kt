package vn.com.fabbi.smarttech.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.jaredrummler.materialspinner.MaterialSpinner

import vn.com.fabbi.smarttech.R
import vn.com.fabbi.smarttech.util.NetworkUtil

class HomeFragment : Fragment() {
    private var mContext: Context? = null
    private var mRoot: View? = null
    private var mNoContractView: View? = null
    private var monthPickerText: TextView? = null

    private val mButtonClickListener = View.OnClickListener { v ->
        if (NetworkUtil.isNetworkConnected(mContext)) {
            when (v.id) {
                R.id.register_contract_btn -> Snackbar.make(v, "register_contract_btn", Snackbar.LENGTH_SHORT).show()
                R.id.view_month_picker_left -> Snackbar.make(v, "view_month_picker_left", Snackbar.LENGTH_SHORT).show()
                R.id.view_month_picker_right -> Snackbar.make(v, "view_month_picker_right", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private val mSpinnerItemSelected = MaterialSpinner.OnItemSelectedListener<MaterialSpinner>
    { materialSpinner: MaterialSpinner, i: Int, l: Long, view: View ->
        fun onItemSelected(v: MaterialSpinner, position: Int, id: Long, item: Any) {
            if (v.id == R.id.spinner1) {
                Snackbar.make(v, "Spinner 1 -- $position", Snackbar.LENGTH_SHORT).show()

            } else if (v.id == R.id.spinner2) {
                Snackbar.make(v, "Spinner 2 -- $position", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * @return true if no contract was registered
     */
    private val isNoContract: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.fragment_home, container, false)
        mNoContractView = mRoot!!.findViewById(R.id.view_no_registered_contract)
        if (isNoContract) {
            mNoContractView!!.visibility = View.VISIBLE
            mNoContractView!!.findViewById<View>(R.id.register_contract_btn).setOnClickListener(mButtonClickListener)
        } else {
            mRoot!!.findViewById<View>(R.id.view_month_picker_left).setOnClickListener(mButtonClickListener)
            mRoot!!.findViewById<View>(R.id.view_month_picker_right).setOnClickListener(mButtonClickListener)
            monthPickerText = mRoot!!.findViewById(R.id.view_month_picker_txt)
            val spinner = mRoot!!.findViewById<MaterialSpinner>(R.id.spinner1)
            val spinner2 = mRoot!!.findViewById<MaterialSpinner>(R.id.spinner2)
            spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow")
            spinner2.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow")
            spinner.setOnItemSelectedListener(mSpinnerItemSelected)
            spinner2.setOnItemSelectedListener(mSpinnerItemSelected)
        }
        return mRoot
    }

    companion object {
        val TAG = "HomeFragment"
    }

}
