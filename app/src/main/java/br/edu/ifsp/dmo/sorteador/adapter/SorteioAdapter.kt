package br.edu.ifsp.dmo.sorteador.adapter

import br.edu.ifsp.dmo.sorteador.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SorteioAdapter(context: Context, datasource: List<Int>) :
    ArrayAdapter<Int>(context, R.layout.numero_sorteio, datasource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView;

        if (itemView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
            itemView = inflater.inflate(R.layout.numero_sorteio, parent, false);
        }

        val number: Int? = getItem(position);

        if (itemView != null && number != null) {
            itemView.findViewById<TextView>(R.id.posicao_numero).text = (position + 1).toString() + "° Sorteio: ";
            itemView.findViewById<TextView>(R.id.numero).text = number.toString() + ".";
        }

        return itemView!!
    }
}