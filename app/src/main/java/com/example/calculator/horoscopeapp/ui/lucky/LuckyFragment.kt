package com.example.calculator.horoscopeapp.ui.lucky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.calculator.R
import com.example.calculator.databinding.FragmentLuckyBinding
import com.example.calculator.horoscopeapp.ui.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel>()

    private var _binding: FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardsProvider: RandomCardsProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewBackContainer.viewBack.setOnClickListener {
            prepareCard()
            flipCard()
        }
    }
    private fun flipCard() {
        try{
            //visibility
            binding.viewFrontContainer.viewFront.isVisible = true
            // 3D effect
            val scale = requireContext().resources.displayMetrics.density
            val cameraDist = 8000*scale
            binding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            binding.viewBackContainer.viewBack.cameraDistance = cameraDist
            // Retrive flip_out animate
            val flipOutAnimatorSet = AnimatorInflater.loadAnimator(requireContext(),R.animator.flip_out) as AnimatorSet
            flipOutAnimatorSet.setTarget(binding.viewBackContainer.viewBack)
            // Retrive flip_in animate
            val flipInAnimatorSet = AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_in) as AnimatorSet
            flipInAnimatorSet.setTarget(binding.viewFrontContainer.viewFront )

            //Start animations
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()

            flipInAnimatorSet.doOnEnd {
                val rotation = AnimationUtils.loadAnimation(requireContext(),R.anim.rotation)
                binding.tvLuckyInfo.startAnimation(rotation)
                binding.viewBackContainer.viewBack.isVisible = false
            }


        }catch (e:java.lang.Exception) {
            Log.i("AnimationError", e.toString())
        }
    }
    private fun prepareCard() {
        val luck = randomCardsProvider.getLucky()

        binding.viewFrontContainer.ivFrontCard.setImageDrawable(ContextCompat.getDrawable(requireContext(),luck.image))
        binding.tvLuckyInfo.text = getString(luck.text)
    }
}