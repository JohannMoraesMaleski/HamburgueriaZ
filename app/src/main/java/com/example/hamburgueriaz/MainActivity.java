package com.example.hamburgueriaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Declaração das variáveis
    private int quantidade = 0;
    private TextView quantidadeView;
    private EditText nomeCliente;
    private TextView resumoPedido;
    private TextView valorTotal;
    private CheckBox baconCheckBox;
    private CheckBox queijoCheckBox;
    private CheckBox onionRingsCheckBox;

    private String tipoLancheSelecionado = "";

    // Map para armazenar os ingredientes de cada tipo de lanche
    private Map<String, String> ingredientesLanches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização das views
        quantidadeView = findViewById(R.id.quantidade);
        nomeCliente = findViewById(R.id.nome_cliente);
        baconCheckBox = findViewById(R.id.bacon);
        queijoCheckBox = findViewById(R.id.queijo);
        onionRingsCheckBox = findViewById(R.id.onion_rings);
        resumoPedido = findViewById(R.id.resumo_pedido);
        valorTotal = findViewById(R.id.valor_total);
        RadioGroup radioGroupLanche = findViewById(R.id.radio_group_lanche);
        RadioButton radioXBurger = findViewById(R.id.radio_x_burger);
        RadioButton radioXSalada = findViewById(R.id.radio_x_salada);
        RadioButton radioXBacon = findViewById(R.id.radio_x_bacon);

        // Inicializa o mapa de ingredientes
        ingredientesLanches = new HashMap<>();
        ingredientesLanches.put("X-Burger", "Carne, Queijo, Pão");
        ingredientesLanches.put("X-Salada", "Carne, Queijo, Alface, Tomate, Pão");
        ingredientesLanches.put("X-Bacon", "Carne, Queijo, Bacon, Pão");

        // Configuração dos listeners para os botões
        Button adicionarButton = findViewById(R.id.adicionar);
        adicionarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarQuantidade();
            }
        });

        Button subtrairButton = findViewById(R.id.subtrair);
        subtrairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtrairQuantidade();
            }
        });

        Button enviarPedidoButton = findViewById(R.id.enviar_pedido);
        enviarPedidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarPedido();
            }
        });

        // Configuração dos listeners para os checkboxes
        baconCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                atualizarResumoPedido();
                atualizarPrecoTotalView(); // Chamada para atualizar a view do preço total
            }
        });

        queijoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                atualizarResumoPedido();
                atualizarPrecoTotalView(); // Chamada para atualizar a view do preço total
            }
        });

        onionRingsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                atualizarResumoPedido();
                atualizarPrecoTotalView(); // Chamada para atualizar a view do preço total
            }
        });

        // Configuração do listener para o RadioGroup de tipos de lanche
        radioGroupLanche.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_x_burger) {
                    tipoLancheSelecionado = "X-Burger";
                } else if (checkedId == R.id.radio_x_salada) {
                    tipoLancheSelecionado = "X-Salada";
                } else if (checkedId == R.id.radio_x_bacon) {
                    tipoLancheSelecionado = "X-Bacon";
                }
                atualizarResumoPedido();
                atualizarPrecoTotalView(); // Chamada para atualizar a view do preço total
            }
        });

        // Define um tipo de lanche padrão ao iniciar
        radioXBurger.setChecked(true);
    }

    // Método para adicionar a quantidade de lanches
    private void adicionarQuantidade() {
        quantidade++;
        atualizarQuantidade();
        atualizarResumoPedido();
        atualizarPrecoTotalView(); // Chamada para atualizar a view do preço total
    }

    // Método para subtrair a quantidade de lanches
    private void subtrairQuantidade() {
        if (quantidade > 0) {
            quantidade--;
            atualizarQuantidade();
            atualizarResumoPedido();
            atualizarPrecoTotalView(); // Chamada para atualizar a view do preço total
        }
    }

    // Método para atualizar a quantidade na view
    private void atualizarQuantidade() {
        quantidadeView.setText(String.valueOf(quantidade));
    }

    // Método para atualizar o resumo do pedido na view
    private void atualizarResumoPedido() {
        StringBuilder pedidoBuilder = new StringBuilder();
        pedidoBuilder.append("Resumo do pedido").append("\n");
        pedidoBuilder.append("Nome do cliente: ").append(nomeCliente.getText().toString()).append("\n");
        pedidoBuilder.append("Tipo de Lanche: ").append(tipoLancheSelecionado).append("\n");
        pedidoBuilder.append("Ingredientes: ").append(ingredientesLanches.get(tipoLancheSelecionado)).append("\n");
        pedidoBuilder.append("Quantidade de Lanches: ").append(quantidade).append("\n\n");

        if (baconCheckBox.isChecked()) {
            pedidoBuilder.append("Com Bacon\n");
        }
        if (queijoCheckBox.isChecked()) {
            pedidoBuilder.append("Com Queijo\n");
        }
        if (onionRingsCheckBox.isChecked()) {
            pedidoBuilder.append("Com Onion Rings\n");
        }

        resumoPedido.setText(pedidoBuilder.toString());
    }

    // NOVO MÉTODO: Calcula e retorna o preço total
    private float calcularPrecoTotal() {
        float precoBaseLanche = 0.0f;
        // Preços dos itens do pedido
        float precoXBurger = 20.0f;
        float precoXSalada = 22.0f;
        float precoXBacon = 25.0f;
        switch (tipoLancheSelecionado) {
            case "X-Burger":
                precoBaseLanche = precoXBurger;
                break;
            case "X-Salada":
                precoBaseLanche = precoXSalada;
                break;
            case "X-Bacon":
                precoBaseLanche = precoXBacon;
                break;
        }

        float precoBacon = 6.0f;
        float precoQueijo = 2.0f;
        float precoOnionRings = 4.0f;
        float precoAdicionais = (baconCheckBox.isChecked() ? precoBacon : 0) +
                (queijoCheckBox.isChecked() ? precoQueijo : 0) +
                (onionRingsCheckBox.isChecked() ? precoOnionRings : 0);

        return (precoBaseLanche + precoAdicionais) * quantidade;
    }

    // MÉTODO ATUALIZADO: Atualiza a view do preço total usando o novo método calcularPrecoTotal
    private void atualizarPrecoTotalView() {
        float precoTotal = calcularPrecoTotal();
        valorTotal.setText("Valor Total: R$ " + String.format("%.2f", precoTotal));
    }

    // Método para enviar o pedido por e-mail
    private void enviarPedido() {

        // Verifica se o campo "Nome do cliente" está vazio
        if (nomeCliente.getText().toString().isEmpty()) {
            Toast.makeText(this, "Digite seu nome no pedido", Toast.LENGTH_SHORT).show();
            return; // Retorna sem enviar o pedido se o nome estiver vazio
        }

        // Verifica se a quantidade de lanches é zero
        if (quantidade == 0) {
            Toast.makeText(this, "Selecione a quantidade de lanches", Toast.LENGTH_SHORT).show();
            return; // Retorna sem enviar o pedido se a quantidade for zero
        }

        // Verifica se um tipo de lanche foi selecionado
        if (tipoLancheSelecionado.isEmpty()) {
            Toast.makeText(this, "Selecione um tipo de lanche", Toast.LENGTH_SHORT).show();
            return;
        }

        // Constrói a mensagem detalhada do pedido
        String nome = nomeCliente.getText().toString();
        float precoTotal = calcularPrecoTotal(); // Agora chama o novo método

        StringBuilder pedidoBuilder = new StringBuilder();
        pedidoBuilder.append("Nome do cliente: ").append(nome).append("\n\n");
        pedidoBuilder.append("Tipo de Lanche: ").append(tipoLancheSelecionado).append("\n");
        pedidoBuilder.append("Ingredientes: ").append(ingredientesLanches.get(tipoLancheSelecionado)).append("\n");
        pedidoBuilder.append("Quantidade de Lanches: ").append(quantidade).append("\n\n");
        pedidoBuilder.append("Adicionais:\n");
        if (baconCheckBox.isChecked()) {
            pedidoBuilder.append("- Bacon\n");
        }
        if (queijoCheckBox.isChecked()) {
            pedidoBuilder.append("- Queijo\n");
        }
        if (onionRingsCheckBox.isChecked()) {
            pedidoBuilder.append("- Onion Rings\n");
        }
        if (!baconCheckBox.isChecked() && !queijoCheckBox.isChecked() && !onionRingsCheckBox.isChecked()) {
            pedidoBuilder.append("- Nenhum\n");
        }
        pedidoBuilder.append("\nValor total do pedido: R$ ").append(String.format("%.2f", precoTotal));
        String pedido = pedidoBuilder.toString();
        enviarEmail(nome, pedido);
    }

    // Método para enviar e-mail
    private void enviarEmail(String nome, String pedido) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"estudanteads2025@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nome);
        intent.putExtra(Intent.EXTRA_TEXT, pedido);
        try {
            startActivity(Intent.createChooser(intent, "Enviar e-mail"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Cliente de e-mail não instalado. " +
                    "Por favor, instale um aplicativo de e-mail " +
                    "para enviar seu pedido.", Toast.LENGTH_SHORT).show();
        }
    }
}
