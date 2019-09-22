package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Almacen;
import com.example.demo.model.Inventario;
import com.example.demo.model.Material;
import com.example.demo.model.Nivel;
import com.example.demo.model.Pos;
import com.example.demo.model.Zona;

@Repository
public class DaoInventarioImpl implements DaoInventario {
	
	private EntityManager em;
	
	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Integer findByMaterial(Material material, Pos pos) {
		Session ss = em.unwrap(Session.class);
		String hql = "select cast(max(idinventario) as integer) from Inventario i left join i.pos p where i.material.idmaterial = :idmaterial and p.idpos = :idpos";
		return (Integer)ss
				.createQuery(hql)
				.setParameter("idmaterial", material.getIdmaterial())
				.setParameter("idpos", pos.getIdpos())
				.uniqueResult();
	}

	@Override
	public Inventario findById(Integer idinventario) {
		Session ss = em.unwrap(Session.class);
		return (Inventario)ss.load(Inventario.class,idinventario);
	}
	
	@Override
	public void guardar(Inventario inventario) {
		Session ss = em.unwrap(Session.class);
		ss.save(inventario);
	}

	@Override
	public List<Inventario> findInventario(Almacen almacen) {
		Session ss = em.unwrap(Session.class);
		String sql = "SELECT \n" + 
				"    {i.*}, {m.*}, {p.*}, {n.*}, {z.*}, {a.*}\n" + 
				"FROM\n" + 
				"    (SELECT \n" + 
				"        MAX(idinventario) AS idinventario\n" + 
				"    FROM\n" + 
				"        logistica.inventario\n" + 
				"    GROUP BY idpos,idmaterial) AS invMax\n" + 
				"        LEFT JOIN\n" + 
				"    logistica.inventario AS i ON invMax.idinventario = i.idinventario\n" + 
				"		LEFT JOIN\n" + 
				"	logistica.material AS m ON m.idmaterial = i.idmaterial\n" + 
				"		LEFT JOIN\n" + 
				"	logistica.pos AS p ON i.idpos = p.idpos\n" + 
				"		LEFT JOIN\n" + 
				"	logistica.nivel AS n ON n.idnivel=p.idnivel\n" + 
				"		LEFT JOIN\n" + 
				"	logistica.zona AS z ON z.idzona = n.idzona\n" + 
				"		LEFT JOIN\n" + 
				"	logistica.almacen AS a ON z.idalmacen = a.idalmacen\n" + 
				"    WHERE a.idalmacen = :idalmacen ;";
		List<Object[]> objeto =  (List<Object[]>)ss.createSQLQuery(sql)
				.addEntity("i",Inventario.class)
				.addEntity("m", Material.class)
				.addEntity("p", Pos.class)
				.addEntity("n", Nivel.class)
				.addEntity("z", Zona.class)
				.addEntity("a", Almacen.class)
				.setParameter("idalmacen", almacen.getIdalmacen())
				.list();
		List<Inventario> resp = new ArrayList<Inventario>();
		for (Object[] object : objeto) {
			resp.add((Inventario)object[0]);
		}
		return resp;
	}
	
	

}
