/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;

/**
 *
 * @author GIANELLI
 */
public class NotasVista extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo;
    private CursadaData cursadaData;
    private MateriaData materiaData;
    private AlumnoData alumnoData;
    //private ArrayList<Cursada> listaCursada;
    //private ArrayList<Materia> listaMaterias;
    private ArrayList<Alumno> listaAlumnos;
    private Conexion con;
    /**
     * Creates new form NotasVista
     */
    public NotasVista() {
        initComponents();
        con = new Conexion("jdbc:mysql://localhost:3306/universidadg2", "root", "");
        modelo = new DefaultTableModel();
        alumnoData = new AlumnoData(con);
        listaAlumnos = (ArrayList) alumnoData.obtenerAlumnos();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboALumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tmaterias = new javax.swing.JTable();
        jGuardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("CARGA DE CALIFICACIONES");

        jLabel2.setText("ALUMNO");

        tmaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tmaterias);

        jGuardar.setText("GUARDAR");
        jGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGuardarActionPerformed(evt);
            }
        });

        jButton2.setText("SALIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(comboALumno, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboALumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jGuardar)
                    .addComponent(jButton2))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGuardarActionPerformed
        int filaSelect = tmaterias.getSelectedRow();
           if(filaSelect != -1){
               Alumno a = (Alumno)comboALumno.getSelectedItem();
               if(a!=null){
                   
                   int idMateria = (Integer)modelo.getValueAt(filaSelect, 0);
                   
                   
               
                   cursadaData = new CursadaData(con);
                   cursadaData.borrarCursadaDeUnaMateriaDeunAlumno(a.getIdAlumno(), idMateria);
                   borrarFilasTablas();
               }
           }
           JOptionPane.showMessageDialog(this, "Se Anulo Inscripcion Correctamente");
                                        
    }//GEN-LAST:event_jGuardarActionPerformed

private void cargarAlumnos() {
        for(Alumno item:listaAlumnos){
            comboALumno.addItem(item);         
        }     
    }
private void armarCabeceraTabla() {
        ArrayList<Object> columns = new ArrayList<>();
        columns.add("Id");
        columns.add("Nombre");
        columns.add("Nota");
        columns.forEach((it) -> {
            modelo.addColumn(it);
        });
        tmaterias.setModel(modelo);
    }
public void borrarFilasTablas() {
        int a = modelo.getRowCount() - 1; // -1 indice de ultima fila.
        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
private void cargarDatosInscriptas() {
        borrarFilasTablas();
        cursadaData = new CursadaData(con);
        Alumno select = (Alumno)comboALumno.getSelectedItem();
        List<Materia> lista = cursadaData.obtenerMateriasCursadas(select.getIdAlumno());
        
        for(Materia m:lista){
            modelo.addRow(new Object[]{m.getIdMateria(),m.getNombreMateria()});
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alumno> comboALumno;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tmaterias;
    // End of variables declaration//GEN-END:variables
}
