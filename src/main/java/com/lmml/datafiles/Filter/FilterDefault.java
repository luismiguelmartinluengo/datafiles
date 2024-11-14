package com.lmml.datafiles.Filter;

public class FilterDefault implements IFilter{
    /*Se usa en los casos de que no se proporcione ningún filtro para la exploración
     * Este filtro permite al explorador validar ok todas las lineas del fichero
     */

    @Override
    public boolean getEvaluation(String[] _values) {
        return true;
    }//End getEvaluation

    @Override
    public int getMaxIndexField() {
        return -1;
    }//End getMaxIndexField

}
