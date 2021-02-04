package eu.tutorials.gingerbread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import eu.tutorials.gingerbread.databinding.FragmentPickupBinding
import eu.tutorials.gingerbread.model.GingerBreadViewModel

class PickupFragment : Fragment() {

    private var binding: FragmentPickupBinding? = null
    private val sharedViewModel: GingerBreadViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPickupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            pickupFragment = this@PickupFragment
            pickupViewModel = sharedViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun onCancel() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }

    fun onNext() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

}