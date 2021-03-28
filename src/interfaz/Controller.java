package interfaz;

import clases.Chi_Cuadrado;
import clases.logicaTP1;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    public TextField tf_Semilla;
    public TextField tf_A_Multiplicativa;
    public TextField tf_C_Aditiva;
    public TextField tf_Modulo;
    public TextField tf_K_Valor;
    public TextField tf_G_Valor;
    public Button btGenerar;
    public Button btSiguiente;
    public Button btnSalir;
    public TableView<logicaTP1.randomNum> tv_NumerosGenerados;
    public TableColumn<logicaTP1.randomNum, Integer> tc_Iteraciones;
    public TableColumn<logicaTP1.randomNum, Float> tc_N_Generados;
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
    public RadioButton rb_LinealMixto;
    public RadioButton rb_Multiplicativo;
    public RadioButton rb_5;
    public RadioButton rb_10;
    public RadioButton rb_15;
    public RadioButton rb_20;
    public RadioButton rb_Lineal_Testhi;
    public RadioButton rb_Sistema;
    public ToggleGroup tg_MetodoChi;
    public TextField tf_Muestra;
    public TableView<Chi_Cuadrado.Intervalo> tv_Test_Chi;
    public TableColumn<Chi_Cuadrado, Integer> tc_Nr_intervalo;
    public TableColumn<Chi_Cuadrado, Float> tc_menor;
    public TableColumn<Chi_Cuadrado, Float> tc_Max;
    public TableColumn<Chi_Cuadrado, Integer> tc_F_Observada;
    public TableColumn<Chi_Cuadrado, Integer> tc_F_Esperada;
    public TableColumn<Chi_Cuadrado, Float> tc_Chi;
    public Button btn_Calcular_Chi;


    private int X0;
    private int a;
    private int c;
    private int m;
    private int k;
    private int g;
    private boolean metodo = true;//true = lineal -- false = Multiplicativo
    private int metocoChi = 1;
    private logicaTP1 lista = new logicaTP1();
    private Chi_Cuadrado chi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tc_Iteraciones.setCellValueFactory(new PropertyValueFactory<>("iteracion"));
        tc_N_Generados.setCellValueFactory(new PropertyValueFactory<>("numRand"));

        tc_Nr_intervalo.setCellValueFactory(new PropertyValueFactory<>("numIt"));
        tc_menor.setCellValueFactory(new PropertyValueFactory<>("inferior"));
        tc_Max.setCellValueFactory(new PropertyValueFactory<>("superior"));
        tc_F_Observada.setCellValueFactory(new PropertyValueFactory<>("f_Obs"));
        tc_F_Esperada.setCellValueFactory(new PropertyValueFactory<>("f_Esp"));
        tc_Chi.setCellValueFactory(new PropertyValueFactory<>("chi"));
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
            lista = new logicaTP1(X0, a, c, m);
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

    public void calcularChi()
    {
        int muestra = Integer.parseInt( tf_Muestra.getText());
        int numInter= Integer.parseInt(((RadioButton) tg_SubIntervalos.getSelectedToggle()).getText());
        String met = ((RadioButton)tg_MetodoChi.getSelectedToggle()).getText();


        chi = new Chi_Cuadrado(muestra, numInter,met);
        tv_Test_Chi.setItems(chi.getIntervalos());
        chi.mostrar();
    }
}
