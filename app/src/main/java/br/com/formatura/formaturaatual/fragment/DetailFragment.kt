package br.com.formatura.formaturaatual.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.formatura.formaturaatual.R
import br.com.formatura.formaturaatual.activity.CreateMessageActivity
import br.com.formatura.formaturaatual.activity.MainActivity
import br.com.formatura.formaturaatual.domain.Pessoa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnVoltar
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_detail, container, false)
        val pessoa = arguments?.getSerializable("detail") as Pessoa

        view.image.setImageResource(pessoa.imageResId)
        view.name.text = pessoa.name
        view.description.text = pessoa.description

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVoltar.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        btnDetailMessage.setOnClickListener {
            val intent = Intent(activity, CreateMessageActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }
}