package classes;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ClienteGui extends JFrame {
    private HashMap<String, Cliente> clientes;
    private JTextField txtCPF;
    private JTextField txtNome;
    private JTextField txtCelular;
    private JTextField txtEmail;
    private JTextArea areaDeTexto;

    public ClienteGui() {
        super("Sistema de Gestão de Clientes");
        clientes = new HashMap<>();

        // Configuração da janela
        setSize(700, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel de entrada
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new GridLayout(5, 2));
        add(painelEntrada, BorderLayout.NORTH);

        // Campos de entrada
        painelEntrada.add(new JLabel("CPF:"));
        txtCPF = new JTextField();
        painelEntrada.add(txtCPF);

        painelEntrada.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelEntrada.add(txtNome);

        painelEntrada.add(new JLabel("Celular:"));
        txtCelular = new JTextField();
        painelEntrada.add(txtCelular);

        painelEntrada.add(new JLabel("E-Mail:"));
        txtEmail = new JTextField();
        painelEntrada.add(txtEmail);

        // Botões
        JButton btnAdicionar = new JButton("Adicionar Cliente");
        painelEntrada.add(btnAdicionar);

        JButton btnBuscar = new JButton("Buscar por CPF");
        painelEntrada.add(btnBuscar);

        // Área de texto
        areaDeTexto = new JTextArea();
        add(new JScrollPane(areaDeTexto), BorderLayout.CENTER);

        // Ações do botoes
        btnAdicionar.addActionListener(e -> adicionarCliente());
        btnBuscar.addActionListener(e -> buscarCliente());
    }

    private void adicionarCliente() {
        String cpf = txtCPF.getText();
        String nome = txtNome.getText();
        String celular = txtCelular.getText();
        String email = txtEmail.getText();

        // Verifica se os campos estão preenchidos e se o CPF tem 11 dígitos
        if (!cpf.isEmpty() && cpf.length() == 11 && !nome.isEmpty() && !celular.isEmpty()) {
            // Verifica se o cliente já existe no Hash
            if (clientes.containsKey(cpf)) {
                JOptionPane.showMessageDialog(this, "Cliente já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Adiciona um novo cliente
                Cliente novoCliente = new Cliente(nome, cpf, celular, email);
                clientes.put(cpf, novoCliente);
                areaDeTexto.append("Cliente adicionado: " + novoCliente.getNome() + " (CPF: " + cpf + ")\n");
            }
        } else {
            JOptionPane.showMessageDialog(this, "CPF deve ter 11 dígitos e os campos não podem estar vazios.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        txtCPF.setText("");
        txtNome.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
    }

    private void buscarCliente() {
        String cpf = txtCPF.getText();
        if (!cpf.isEmpty()) {
            // Busca o cliente no Hash
            Cliente cliente = clientes.get(cpf);
            if (cliente != null) {
                areaDeTexto.append("Cliente encontrado: \n");
                areaDeTexto.append("Nome: " + cliente.getNome() + "\n");
                areaDeTexto.append("CPF: " + cliente.getCpf() + "\n");
                areaDeTexto.append("Celular: " + cliente.getCelular() + "\n");
                areaDeTexto.append("E-mail: " + cliente.getEmail() + "\n");
            } else {
                areaDeTexto.append("Cliente não encontrado para o CPF: " + cpf + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(this, "CPF não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteGui frame = new ClienteGui();
            frame.setVisible(true);
        });
    }
}
