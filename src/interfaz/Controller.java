package interfaz;

import clases.GeneradorNumerosAletorios;
import clases.NumeroAleatorio;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    public RadioButton rb_LinealMixto;
    public RadioButton rb_Multiplicativo;
    public TextField tf_Semilla;
    public TextField tf_A_Multiplicativa;
    public TextField tf_C_Aditiva;
    public TextField tf_Modulo;
    public TextField tf_K_Valor;
    public TextField tf_G_Valor;
    public Button btGenerar;
    public Button btSiguiente;
    public Button btnSalir;
    public TableView<NumeroAleatorio> tv_NumerosGenerados;
    public TableColumn<NumeroAleatorio, Integer> tc_Iteraciones;
    public TableColumn<NumeroAleatorio, Float> tc_N_Generados;
    public ToggleGroup tg_Metodo;
    public Label lbl_c_Desc;
    public Label lbl_Semilla;
    public Label lbl_A_Multiplicativa;
    public Label lbl_C_aditiva;
    public Label lbl_X0_Desc;
    public Label lbl_a_Desc;
    public Label lbl_m_Desc;
    public Label lbl_k_g_Desc;
    public ToggleGroup tg_SubIntervalos;
    public AnchorPane ap_Base;
    public RadioButton rb_5;
    public RadioButton rb_10;
    public RadioButton rb_15;
    public RadioButton rb_20;


    private int X0;
    private int a;
    private int c;
    private int m;
    private int k;
    private int g;
    private boolean metodo = true;//true = lineal -- false = Multiplicativo

    private GeneradorNumerosAletorios lista = new GeneradorNumerosAletorios();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        tc_Iteraciones.setCellValueFactory(new PropertyValueFactory<>("iteracion"));
        tc_N_Generados.setCellValueFactory(new PropertyValueFactory<>("numRand"));
        tv_NumerosGenerados.setItems(lista.getVecNA());
    }


    public void changeMetodo()
    {
        if (metodo)
        {
            lbl_c_Desc.setText("c vale '0', no se usa");
            tf_C_Aditiva.setText("0");
            tf_C_Aditiva.setEditable(false);
            metodo = false;
       }
        else
        {
            lbl_c_Desc.setText("c es una constante aditiva");
            tf_C_Aditiva.setEditable(true);
            metodo = true;
        }
    }

    public void calcular_K()
    {
        a = Integer.parseInt(tf_A_Multiplicativa.getText());
        if (metodo)
            {
                k = (a-1)/4;
            }
        else
            k = (a-3)/8;
        tf_K_Valor.setText(String.valueOf(k));
    }

    public void calcular_G()
    {
        m = Integer.parseInt(tf_Modulo.getText());
        g = (int)(Math.log(m)/Math.log(2));
        tf_G_Valor.setText(String.valueOf(g));
    }
    public void calcular_A_multiplicativa()
    {
        k = Integer.parseInt(tf_K_Valor.getText());
        if (metodo)
        {
            a = 1+4*k;
        }
        else
            a = 3+8*k;
        tf_A_Multiplicativa.setText(String.valueOf(a));
    }

    public void calcular_Modulo()
    {
        g = Integer.parseInt(tf_G_Valor.getText());
        m = (int) Math.pow(2,g);
        tf_Modulo.setText(String.valueOf(m));
    }

    public void generarNumeros()
    {

            X0 = Integer.parseInt(tf_Semilla.getText());
            c = Integer.parseInt(tf_C_Aditiva.getText());
            lista = new GeneradorNumerosAletorios(X0, a, c, m);
            lista.generar();
            updatetable();

    }

    public void generarSiguiente(){
        lista.siguiente();
        updatetable();
    }


    public void updatetable()
    {
        tv_NumerosGenerados.setItems(lista.getVecNA());
    }

    public void close()
    {
        Stage stage = (Stage)btnSalir.getScene().getWindow();
        stage.close();
    }

    public void calcularChi(ActionEvent actionEvent) {
    }
}
