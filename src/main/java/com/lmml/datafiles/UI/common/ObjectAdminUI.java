package com.lmml.datafiles.UI.common;
import javax.swing.JPanel;

public interface ObjectAdminUI {

    /*
     * Define las propiedades que debe tener un elemento de la interfaz de usuario para la administración de un objeto (modificación de propiedades y valores)
     */

    public void setObject(Object _object); //Este método se utiliza para establecer el objeto que se va a administrar en la interfaz de usuario.
    public Boolean acceptChanges();
    public Boolean cancelChanges();
    //public Boolean allowCancelChanges(); //Propuesta futuro: Este método se utiliza para determinar si se permiten cambios en la interfaz de usuario.
    
}//End interface ObjectAdmin
