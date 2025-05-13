package com.lmml.datafiles.UI.Sources;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.lmml.datafiles.Explorer.FileDescriptor;
import com.lmml.datafiles.Util.Logs;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FileDescriptorAdmin {

    private ArrayList<FileDescriptor> fileDescriptors = new ArrayList<FileDescriptor>();
    private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public void addChangeListener(ChangeListener _listener) {
        listeners.add(_listener);
    }//End addChangeListener

    public void removeChangeListener(ChangeListener _listener) {
        listeners.remove(_listener);
    }//End removeChangeListener
    
    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }//End for
    }//End notifyListeners
    
    public void addFileDescriptor(FileDescriptor _fileDescriptor) {
        fileDescriptors.add(_fileDescriptor);
        saveToRepository(_fileDescriptor);
        notifyListeners();
    }//End addFileDescriptor

    public void removeFileDescriptor(FileDescriptor _fileDescriptor) {
        if (fileDescriptors.contains(_fileDescriptor)) {
            deleteFromRepository(_fileDescriptor);
            fileDescriptors.remove(_fileDescriptor);
            notifyListeners();
        }//End if
    }//End removeFileDescriptor

    public void removeFileDescriptor(String _name){
        for (FileDescriptor fd : fileDescriptors) {
            if (fd.getName().equals(_name)) {
                removeFileDescriptor(fd);
            }//End if
        }//End for
   }//End removeFileDescriptor

    public FileDescriptor getFileDescriptor(String _name) {
        for (FileDescriptor fd : fileDescriptors) {
            if (fd.getName().equals(_name)) {
                return fd;
            }//End if
        }//End for
        return null;
    }//End getFileDescriptor

    public FileDescriptor[] getFileDescriptors() {
        return fileDescriptors.toArray(new FileDescriptor[fileDescriptors.size()]);
    }//End getFileDescriptors 

   private void load() {
        //Carga de descriptores de fichero desde el repositorio
        //No genera evento de cambio ya que la carga se hace al inicio
		ObjectInputStream input;
		File repository = new File("./resources/DescriptoresFichero");
		if (!repository.isDirectory()) {repository.mkdirs();}//End if
		File[] fd = repository.listFiles();
		for (File f: fd) {
			if (f.getName().endsWith(".dscfch")) {
				try {
					input = new ObjectInputStream(new FileInputStream(f));
                    FileDescriptor descriptor = (FileDescriptor) input.readObject();
                    fileDescriptors.add(descriptor);
					input.close();
				}catch (Exception e) {
					Logs.critical(Thread.currentThread().getStackTrace(), new String[]{"Error en la carga de descripores de fichero desde el repositorio",String.format("No se ha podido cargar el descriptor %s", f.getAbsolutePath())}, e);
					System.out.println(e.getMessage());
				}//End try
			}//End if
		}//End for
	}//End load

    private void saveToRepository(FileDescriptor _fileDescriptor) {
        //Guarda el descriptor de fichero en el repositorio
        File repository = new File("./resources/DescriptoresFichero");
        if (!repository.isDirectory()) {repository.mkdirs();}//End if
        File file = new File(repository, _fileDescriptor.getRepositoryName() + ".dscfch");
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(_fileDescriptor);
            output.close();
        } catch (Exception e) {
            Logs.critical(Thread.currentThread().getStackTrace(), new String[]{"Error en la carga de descripores de fichero desde el repositorio",String.format("No se ha podido cargar el descriptor %s", file.getAbsolutePath())}, e);
            System.out.println(e.getMessage());
        }//End try
    }//End saveToRepository

    private void deleteFromRepository(FileDescriptor _fileDescriptor) {
        //Elimina el descriptor de fichero del repositorio
        File repository = new File("./resources/DescriptoresFichero");
        if (!repository.isDirectory()) {repository.mkdirs();}//End if
        File file = new File(repository, _fileDescriptor.getRepositoryName() + ".dscfch");
        if (file.exists()) {
            file.delete();
        }//End if
    }//End deleteFromRepository

    public FileDescriptorAdmin() {
        load();
    }//End constructor  

}//End FileDescriptorAdmin
