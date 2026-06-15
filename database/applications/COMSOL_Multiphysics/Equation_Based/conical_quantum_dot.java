/*
 * conical_quantum_dot.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class conical_quantum_dot {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("eigv", "Eigenvalue");
    model.study("std1").feature("eigv").setSolveFor("/physics/c", true);

    model.param().set("m", "me_const[1/kg]/e_const[1/C]");
    model.param().descr("m", "\u7535\u5b50\u8d28\u91cf (eV/c^2)");
    model.param().set("hbar", "hbar_const[1/(J*s)]/e_const[1/C]");
    model.param().descr("hbar", "\u7ea6\u5316\u666e\u6717\u514b\u5e38\u6570 (eV*s)");
    model.param().set("V_In", "0");
    model.param().descr("V_In", "\u52bf\u5792\uff0c\u7837\u5316\u94df (eV)");
    model.param().set("V_Ga", "0.697");
    model.param().descr("V_Ga", "\u52bf\u5792\uff0c\u7837\u5316\u9553 (eV)");
    model.param().set("c_In", "hbar^2/(2*0.023*m)");
    model.param().descr("c_In", "c \u7cfb\u6570\uff0c\u7837\u5316\u94df");
    model.param().set("c_Ga", "hbar^2/(2*0.067*m)");
    model.param().descr("c_Ga", "c \u7cfb\u6570\uff0c\u7837\u5316\u9553");
    model.param().set("l", "0");
    model.param().descr("l", "\u4e3b\u91cf\u5b50\u6570");

    model.component("comp1").geom("geom1").lengthUnit("nm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{25, 100});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{12.5, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{25, 2});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{12.5, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 12, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3.6, 2, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("pol1", "r2");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "r2+pol1");
    model.component("comp1").geom("geom1").feature("co1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").create("co2", "Compose");
    model.component("comp1").geom("geom1").feature("co2").selection("input").set("co1", "r1");
    model.component("comp1").geom("geom1").feature("co2").set("formula", "r1+co1");
    model.component("comp1").geom("geom1").run("co2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", new String[]{"c_In", "0", "0", "c_In"}, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("a", "c_In*(l/r)^2+V_In", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("be", new String[]{"-c_In/r", "0"}, 0);
    model.component("comp1").physics("c").create("cfeq2", "CoefficientFormPDE", 2);
    model.component("comp1").physics("c").feature("cfeq2").selection().set(1, 3);
    model.component("comp1").physics("c").feature("cfeq2").setIndex("c", new String[]{"c_Ga", "0", "0", "c_Ga"}, 0);
    model.component("comp1").physics("c").feature("cfeq2").setIndex("a", "c_Ga*(l/r)^2+V_Ga", 0);
    model.component("comp1").physics("c").feature("cfeq2").setIndex("be", new String[]{"-c_Ga/r", "0"}, 0);
    model.component("comp1").physics("c").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("c").feature("dir1").selection().set(2, 9);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eigv").set("neigsactive", true);
    model.study("std1").feature("eigv").set("neigs", 4);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").feature("surf1").set("expr", "u");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 1");
    model.result("pg2").feature("surf1").set("expr", "u");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{3});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4});
    model.result("pg1").run();

    model.title("\u9525\u5f62\u91cf\u5b50\u70b9");

    model
         .description("\u91cf\u5b50\u70b9\u7531\u81ea\u7531\u7535\u5b50\u5c40\u9650\u5728\u534a\u5bfc\u4f53\u8868\u9762\u4ea7\u751f\u3002\u672c\u4f8b\u662f\u4e00\u4e2a\u751f\u957f\u5728\u7837\u5316\u9553\u57fa\u677f\u4e0a\u7684\u5706\u9525\u5f62\u7837\u5316\u94df\u91cf\u5b50\u70b9\u7684\u7535\u5b50\u72b6\u6001\u3002");

    model.label("conical_quantum_dot.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
