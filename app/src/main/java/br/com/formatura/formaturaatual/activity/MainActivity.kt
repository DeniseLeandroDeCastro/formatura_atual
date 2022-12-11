package br.com.formatura.formaturaatual.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.formatura.formaturaatual.R
import br.com.formatura.formaturaatual.domain.Pessoa
import br.com.formatura.formaturaatual.fragment.DetailFragment
import br.com.formatura.formaturaatual.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.OnListSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_root, ListFragment.newInstance(), "pessoaList")
                .commit()
        }

        btnVoltar.setOnClickListener {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onSelected(pessoa: Pessoa) {
        val args = Bundle()
        args.putSerializable("detail", pessoa)

        val fragment = DetailFragment.newInstance()
        fragment.arguments = args

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_root, fragment, "fragmentDetail")
            .addToBackStack(null)
            .commit()
    }
}