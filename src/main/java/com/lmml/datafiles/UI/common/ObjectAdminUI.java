package com.lmml.datafiles.UI.common;

public interface ObjectAdminUI<G> {

    /*
     * Define las propiedades que debe tener un elemento de la interfaz de usuario para la administración de un objeto (modificación de propiedades y valores)
     */

    public void clearUI(); //Este método se utiliza para limpiar la interfaz de usuario.
    public void setObject(G _administratedObject); //Este método se utiliza para establecer el objeto que se va a administrar en la interfaz de usuario.
    public G getNew();
    public G acceptChanges(G _administratedObject);
    //public Boolean allowCancelChanges(); //Propuesta futuro: Este método se utiliza para determinar si se permiten cambios en la interfaz de usuario.
    
}//End interface ObjectAdmin
