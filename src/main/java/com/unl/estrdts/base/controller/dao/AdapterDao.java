package com.unl.estrdts.base.controller.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;


public class AdapterDao <T> implements InterfaceDao <T> {

    private Class<T> clazz;
    private Gson g;
    protected static String base_path= "data" + File.separatorChar;

    public AdapterDao(Class<T> clazz) {
        this.clazz = clazz;
        this.g = new Gson();
    }

    private String readFile() throws Exception {
        File file= new File(base_path + clazz.getSimpleName() + ".json");
        if (!file.exists()) {
            saveFIle("[]");
        }
        StringBuilder sb = new StringBuilder();
        try ( Scanner in = new Scanner(new FileReader(file))) {
            while (in.hasNextLine()) {
                sb.append(in.nextLine()).append("\n");
                
            }
        }
        return sb.toString();
    }

    private void saveFIle(String data) throws Exception {
        File file= new File(base_path + clazz.getSimpleName() + ".json");
        if (!file.exists()) {
            //  
            file.createNewFile();
            
        }
        //if (!file.exists()) {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.flush();
            fw.close();
        //}
        

    }

    @Override
    public Linkendlist<T> listAll() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'listAll'");
        Linkendlist<T> lista = new Linkendlist<>();
        try {
            String data = readFile();
            T[] m = (T[]) g.fromJson(data, java.lang.reflect.Array.newInstance(clazz, 0).getClass()); 
            lista.toList(m);
            //System.out.println(lista.getLength());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lista;

    }

    @Override
    public void persist(T obj) throws Exception {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'persist'");
        Linkendlist<T> list = listAll();
        list.add(obj);
        saveFIle(g.toJson(list.toArray()));
        
    }

    @Override
    public void update(T obj, Integer pos) throws Exception {
        Linkendlist<T> list = listAll();
        list.update(obj, pos);
        saveFIle(g.toJson(list.toArray()));
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update_by_id(T obj, Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update_by_id'");
    }

    @Override
    public T get(Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    
   
    
}
