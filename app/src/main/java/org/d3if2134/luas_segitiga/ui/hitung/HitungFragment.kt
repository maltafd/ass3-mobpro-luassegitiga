package org.d3if2134.luas_segitiga.ui.hitung


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2134.luas_segitiga.R
import org.d3if2134.luas_segitiga.databinding.FragmentHitungBinding
import org.d3if2134.luas_segitiga.db.SegitigaDb
import org.d3if2134.luas_segitiga.model.Hasil

class HitungFragment :  Fragment() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_objek_segitiga -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_objekSegitigaFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = SegitigaDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this,factory)[HitungViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        //setContentView(R.layout.activity_main)

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)

        binding.buttonhitung.setOnClickListener { hitung() }
        binding.buttonreset.setOnClickListener { reset() }
        viewModel.getHasilSg().observe(requireActivity(), { showResult(it) })
        setHasOptionsMenu(true)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilSg().observe(requireActivity(), { showResult(it) })
    }
    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.alasEditText.text,
            binding.tinggiEditText.text,
            binding.hasil.text,
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
    private fun hitung() {

        //rumus luas alas segitiga = 1/2 * alas * tinggi

        val alas = binding.alasEditText.text.toString()
        if (TextUtils.isEmpty(alas)) {
            Toast.makeText(context, R.string.alas_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggi = binding.tinggiEditText.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungSegitiga(
            alas.toFloat(),
            tinggi.toFloat()
        )
    }

    private fun reset(){
        binding.alasEditText.setText("")
        binding.tinggiEditText.setText("")
        binding.hasil.setText("")
    }

    private fun showResult(result: Hasil?) {
        if (result == null) return
        binding.hasil.text = getString(R.string.hasil_x, result.luasSegitiga)
    }
}