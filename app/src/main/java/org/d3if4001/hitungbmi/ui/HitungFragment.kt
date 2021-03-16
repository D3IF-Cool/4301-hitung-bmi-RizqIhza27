package org.d3if4001.hitungbmi.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if4001.hitungbmi.R
import org.d3if4001.hitungbmi.databinding.FragmenHitungBinding

class HitungFragment : Fragment() {
    private lateinit var binding: FragmenHitungBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmenHitungBinding.inflate(
                layoutInflater, container, false)
        binding.button.setOnClickListener { hitungBmi() }
        return binding.root
    }

    private fun hitungBmi() {
        val berat = binding.beratEditText.text.toString().toFloat()
        val tinggi = binding.tinggiEditText.text.toString().toFloat() / 100
        val bmi = berat / (tinggi * tinggi)
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val isMale = selectedId == R.id.priaRadioButton
        val kategori = getKategori(bmi, isMale)
        binding.bmiTextView.text = getString(R.string.bmi_x, bmi)
        binding.kategoriTextView.text = getString(R.string.kategori_x, kategori)
    }
    private fun getKategori(bmi: Float, isMale: Boolean): String {
        val stringRes = if (isMale) {
            when {
                bmi < 20.5 -> R.string.kurus
                bmi >= 27.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        } else {
            when {
                bmi < 18.5 -> R.string.kurus
                bmi >= 25.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        }
        return getString(stringRes)
    }
}
