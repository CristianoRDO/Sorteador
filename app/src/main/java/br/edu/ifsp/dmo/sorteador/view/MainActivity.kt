package br.edu.ifsp.dmo.sorteador.view

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.sorteador.R
import br.edu.ifsp.dmo.sorteador.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.sorteador.model.Draw
import br.edu.ifsp.dmo.sorteador.model.SorteioAdapter
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private var draw = Draw();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        setClickListener();
    }

    override fun onClick(v: View) {
        when(v)
        {
            binding.buttonUseLimit ->{
                val limit: Int = try{
                    binding.editLimit.text.toString().toInt();
                }
                catch (e: NumberFormatException) {
                    -1;
                }
                draw = if(limit > 1)
                    Draw(limit)
                else
                    Draw()
                updateUI()
            }

            binding.buttonDraw -> {

                val number = draw.getNumber();

                if(number != -1)
                {
                    binding.textviewExit.text = number.toString();
                }
                else {
                    Toast.makeText(
                        this,
                        "Aviso: Todos os Números já Foram Sorteados.",
                        Toast.LENGTH_SHORT).show()
                }

                updateListView();
            }
        }
    }

    private fun setClickListener()
    {
        binding.buttonDraw.setOnClickListener(this);
        binding.buttonUseLimit.setOnClickListener(this);
    }

    private fun updateUI()
    {
        val str = String.format("Intervalo de 1 à %,d.", draw.getHighBorder());
        binding.textviewInterval.text = str;
        binding.editLimit.text.clear();
        binding.textviewExit.text = getString(R.string.inicie_o_sorteio);
        updateListView();
    }

    private fun updateListView()
    {
        val adapter = SorteioAdapter(
            this,
            draw.getHistory()
        )

        binding.listviewDraw.adapter = adapter;
    }


}