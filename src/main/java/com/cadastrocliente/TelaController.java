package com.cadastrocliente;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class TelaController {

    @FXML
    private TextField Nome;
    @FXML
    private TextField CEP;
    @FXML
    private TextField Rua;
    @FXML
    private TextField Cidade;
    @FXML
    private TextField Telefone;
    @FXML
    private TextField Numero;
    @FXML
    private TextField Estado;

    private ArrayList<Cliente> Clientes;

    private Cliente cliente;

    private Endereco endereco;

    private Buscador buscador;

    @FXML
    private void initialize() {
        buscador = new Buscador();
        Clientes = new ArrayList<Cliente>();
    }

    @FXML
    private void limpar() {
        Nome.setText("");
        CEP.setText("");
        Rua.setText("");
        Cidade.setText("");
        Telefone.setText("");
        Numero.setText("");
        Estado.setText("");
    }

    @FXML
    private void gravar() {
        Cliente cliente = new Cliente();
        cliente.setNome(Nome.getText());
        cliente.setTelefone(Telefone.getText());
        cliente.setEndereco(new Endereco(CEP.getText(), Rua.getText(), Numero.getText(), Cidade.getText(), Estado.getText()));
        Clientes.add(cliente);
        MenssagemExito("Cliente cadastrado!");
        limpar();
    }

    @FXML
    private void Endereco() {
        String cep = CEP.getText();
        try {
            Endereco endereco = buscador.buscar(cep);
            Rua.setText(endereco.getRua());
            Cidade.setText(endereco.getCidade());
            Estado.setText(endereco.getEstado());
        } catch (IllegalArgumentException e) {
            MensagemErro("Formato de CEP inválido.");
        } catch (IOException e) {
            MensagemErro("CEP não existente: " + e.getMessage());
        } catch (Exception e) {
            MensagemErro("Erro desconhecido: " + e.getMessage());
        }
    }

    private void MensagemErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    private void MenssagemExito(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
