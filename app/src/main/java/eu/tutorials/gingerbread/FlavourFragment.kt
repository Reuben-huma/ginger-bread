package eu.tutorials.gingerbread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import eu.tutorials.gingerbread.databinding.FragmentFlavourBinding
import eu.tutorials.gingerbread.model.GingerBreadViewModel

class FlavourFragment : Fragment() {

    private var binding: FragmentFlavourBinding? = null
    private val sharedViewModel: GingerBreadViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlavourBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            flavourFragment = this@FlavourFragment
            flavourViewModel = sharedViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun onCancel() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavourFragment_to_startFragment)
    }

    fun onNext() {
        findNavController().navigate(R.id.action_flavourFragment_to_pickupFragment)
    }

}