package hr.ikvakan.betshop.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hr.ikvakan.betshop.databinding.FragmentBottomModalBinding
import hr.ikvakan.betshop.model.BetshopLocationModel
import hr.ikvakan.betshop.utils.startActivity
import java.time.LocalTime


class BottomModalFragment(private val data : BetshopLocationModel.BetshopsModel) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomModalBinding
    companion object {
        const val TAG = "ModalBottomSheet"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBottomModalBinding.inflate(layoutInflater)
        populateModal(data)

        binding.tvRoute.setOnClickListener {
            context?.startActivity<MapsActivity>()
            dismiss()
        }
        return binding.root
    }

    private fun populateModal(data: BetshopLocationModel.BetshopsModel) {
        binding.tvName.text=data.name
        binding.tvAdress.text=data.address
        binding.tvCity.text=data.city
        if (hoursInRange(LocalTime.of(8,0), LocalTime.of(16,0), LocalTime.now())){
            binding.tvHours.text ="Open now"
        }
    }

    private fun hoursInRange(start: LocalTime, stop: LocalTime, now: LocalTime): Boolean{
        return ( ! now.isBefore( start ) ) && ! now.isAfter( stop )
    }

    override fun onDestroy() {
        dismiss()
        super.onDestroy()
    }
}