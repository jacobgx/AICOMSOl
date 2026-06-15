/*
 * isospectral_drums.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class isospectral_drums {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("eigv", "Eigenvalue");
    model.study("std1").feature("eigv").setSolveFor("/physics/c", true);

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -3, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -3, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -3, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -3, 7, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("c").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("c").feature("dir1").selection().all();

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").feature("surf1").set("expr", "u");
    model.result("pg1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("c2", "CoefficientFormPDE", "geom2");

    model.study("std1").feature("eigv").setSolveFor("/physics/c2", false);

    model.component("comp2").physics("c2").prop("EquationForm").set("form", "Automatic");

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("eigv", "Eigenvalue");
    model.study("std2").feature("eigv").setSolveFor("/physics/c", false);
    model.study("std2").feature("eigv").setSolveFor("/physics/c2", true);

    model.component("comp2").geom("geom2").create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -3, 0, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 1, 0, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 1, 1, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 1, 1, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 1, 2, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 3, 2, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 3, 3, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 1, 3, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 1, 4, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -1, 4, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -1, 5, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -1, 5, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -1, 6, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -3, 6, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -3, 7, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -1, 7, 1);
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("c2").create("dir1", "DirichletBoundary", 1);
    model.component("comp2").physics("c2").feature("dir1").selection().all();

    model.component("comp2").mesh("mesh2").autoMeshSize(2);
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 2");
    model.result("pg2").feature("surf1").set("expr", "u2");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().set(1);
    model.result().numerical("int1").setIndex("expr", "with(1,u)*with(2,u)", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").set("data", "dset3");
    model.result().numerical("int2").selection().set(1);
    model.result().numerical("int2").setIndex("expr", "with(1,u2)*with(2,u2)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result("pg2").run();

    model.title("\u540c\u9891\u9f13");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u4e24\u4e2a\u5f62\u72b6\u4e0d\u540c\u7684\u5e73\u9762\u8584\u819c\u7684\u7279\u5f81\u503c\uff0c\u7ed3\u679c\u5176\u503c\u76f8\u540c\uff0c\u8fd9\u8868\u660e\u4e0d\u80fd\u901a\u8fc7\u542c\u58f0\u97f3\u6765\u5224\u65ad\u9f13\u7684\u5f62\u72b6\u3002");

    model.label("isospectral_drums.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
