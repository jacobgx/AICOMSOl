/*
 * kdv_equation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class kdv_equation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").field("dimensionless").component(new String[]{"u1", "u2"});
    model.component("comp1").physics("g").feature("gfeq1").set("Ga", new String[][]{{"-u1x"}, {"-u2x"}});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/g", true);

    model.baseSystem("none");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", -8, 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 8, 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("g").create("pc1", "PeriodicCondition", 0);
    model.component("comp1").physics("g").feature("pc1").selection().all();
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", "u2", 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", "u1x", 1);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "6*u1*u1x", 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "u2", 1);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("da", 0, 3);
    model.component("comp1").physics("g").feature("init1").set("u1", "-6*sech(x)^2");
    model.component("comp1").physics("g").feature("init1")
         .set("u2", "-24*sech(x)^2*tanh(x)^2+12*sech(x)^2*(1-tanh(x)^2)");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.0025,2)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "3e-6");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("timemethod", "genalpha");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", "3e-7");
    model.sol("sol1").feature("t1").set("rhoinf", 0.98);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 0.1);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "u1");
    model.result("pg1").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{11}, 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("expr", "-u1");
    model.result("pg1").run();
    model.result().dataset().create("par1", "Parametric1D");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "-u1");
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();

    model.title("KdV \u65b9\u7a0b\u548c\u5b64\u5b50");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u4e24\u4e2a\u5b64\u5b50\u7684\u89e3\u4f5c\u4e3a\u521d\u59cb\u503c\u6c42\u89e3 Korteweg-de Vries (KdV) \u65b9\u7a0b\uff0c\u8fd9\u4e24\u4e2a\u5b64\u5b50\u4ee5\u4e0d\u540c\u7684\u901f\u5ea6\u4f20\u64ad\uff0c\u56e0\u6b64\u5b83\u4eec\u5728\u4f20\u64ad\u8fc7\u7a0b\u4e2d\u4f1a\u8fdb\u884c\u591a\u6b21\u5408\u5e76\u548c\u5206\u79bb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("kdv_equation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
