import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;

public class InterfazGrafos extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panelPrincipal;
    private JButton quemarButton;
    private JTextField textField1;
    private JButton insertarButton;
    private JTextField textField2;
    private JButton insertarButton1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton mostrarGrafoButton;
    private JComboBox comboBox3;
    private JButton DFSButton;
    private JButton BFSButton;
    private JButton dijsktraButton;
    private JTextArea textArea1;
    private JCheckBox direccionadoCheckBox;
    private JCheckBox conPesosCheckBox;
    private JButton crearButton;
    Grafo grafoNew;
    Vertice verticeNew;
    Grafo grafoA=new Grafo(false,true);

public InterfazGrafos() {
    add(panelPrincipal);
    setSize(500, 500);
    setLocationRelativeTo(null);

    setTitle("Interfaz Grafos");
    quemarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText(" ");
            grafoA.addVertice("1");
            grafoA.addVertice("2");
            grafoA.addVertice("3");
            grafoA.addVertice("4");
            grafoA.addEdge("1","2",5);
            grafoA.addEdge("1","3",3);
            grafoA.addEdge("3","4",2);
            for (Vertice recorrerVertice:grafoA.getVertices()){
                textArea1.append("\n"+recorrerVertice.print(grafoA.isWeighted()));
            }

        }
    });
    crearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (conPesosCheckBox.isSelected()){
                Grafo grafoNuevoWeight=new Grafo(false,true);
                grafoNew=grafoNuevoWeight;
                textArea1.setText("Grafo con peso creado");
            } else if (conPesosCheckBox.isSelected() && direccionadoCheckBox.isSelected()) {
                Grafo grafoNuevoWeightDire=new Grafo(true,true);
                grafoNew=grafoNuevoWeightDire;
                textArea1.setText("Grafo con peso y direccionado creado");
            }else if (direccionadoCheckBox.isSelected()) {
                Grafo grafoNuevoWeightDire=new Grafo(true,false);
                grafoNew=grafoNuevoWeightDire;
                textArea1.setText("Grafo direccionado creado");
            }else{
                Grafo grafoSencillo=new Grafo(false,false);
                grafoNew=grafoSencillo;
                textArea1.setText("Grafo sencillo creado");
            }
        }
    });
    insertarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vertice verticeNuevo=new Vertice(textField1.getText());
            verticeNew=verticeNuevo;
            grafoNew.addVertice(verticeNew.getData());
            textArea1.setText(" ");
            textField1.setText("");
        }
    });

    mostrarGrafoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Vertice recorrerVertice:grafoNew.getVertices()){
                textArea1.append("\n"+recorrerVertice.print(grafoNew.isWeighted()));
            }
        }
    });
    insertarButton1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (grafoNew.isWeighted()) {
                grafoNew.addEdge(comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), Integer.valueOf(textField2.getText()));
            }else if (!grafoNew.isWeighted()){
                grafoNew.addEdge(comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(),1);
            }
        }
    });
    insertarButton1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText(" ");
        }
    });
    DFSButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*ArrayList<Vertice> visitedVeritce=grafoA.depthFirstSearch(grafoA.getVertexByValue(comboBox3.getSelectedItem().toString()));
            textArea1.setText(" ");
            for (Vertice recorrerVertice:visitedVeritce){
                textArea1.append("\n"+recorrerVertice.getData());

            }*/
            ArrayList<Vertice> visitedVeritce=grafoNew.depthFirstSearch(grafoNew.getVertexByValue(comboBox3.getSelectedItem().toString()));
            textArea1.setText(" ");
            for (Vertice recorrerVertice:visitedVeritce){
                textArea1.append("\n"+recorrerVertice.getData());

            }

        }
    });
    BFSButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Vertice> visitedVeritce=grafoNew.breadthFirstSearch(grafoNew.getVertexByValue(comboBox3.getSelectedItem().toString()));
            textArea1.setText(" ");
            for (Vertice recorrerVertice:visitedVeritce){
                textArea1.append("\n"+recorrerVertice.getData());

            }
        }
    });
    dijsktraButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vertice startVertex=grafoNew.getVertexByValue(comboBox3.getSelectedItem().toString());
            Dictionary[] dijsktraResult=grafoNew.Dijsktra(startVertex);

            String result =grafoNew.dijkstraResultPrinter(dijsktraResult);
            textArea1.setText(result);
        }
    });
}
}
