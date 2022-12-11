package br.com.formatura.formaturaatual.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.formatura.formaturaatual.R
import br.com.formatura.formaturaatual.domain.Pessoa
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class ListFragment : Fragment() {

    private lateinit var listener: OnListSelected

    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var imageResIds: IntArray

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val activity = activity as Context
        val recyclerView = view.recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ListAdapter()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)

        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
        if (context is OnListSelected) {
            listener = context
        } else {
            throw java.lang.ClassCastException("$context must implemented")
        }
    }

    internal inner class ListAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_list, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val pessoa = Pessoa(
                names[position],
                descriptions[position],
                imageResIds[position]
            )
            viewHolder.bind(pessoa)
            viewHolder.itemView.setOnClickListener {
                listener.onSelected(pessoa)
            }
        }
        override fun getItemCount() = names.size
    }

    internal inner class ViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(pessoa: Pessoa) {
            itemView.list_name.text = pessoa.name
            itemView.list_img.setImageResource(pessoa.imageResId)
        }
    }

    interface OnListSelected {
        fun onSelected(pessoa: Pessoa)
    }
}