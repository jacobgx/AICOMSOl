/*
 * shallow_water_equations.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class shallow_water_equations {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").field("dimensionless").component(new String[]{"Z", "V"});
    model.component("comp1").physics("g").feature("gfeq1").set("Ga", new String[][]{{"-Zx"}, {"-Vx"}});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/g", true);

    model.baseSystem("none");

    model.param().set("nu1", "1e-6");
    model.param().descr("nu1", "\u8fd0\u52a8\u9ecf\u5ea6 (m^2/s)");
    model.param().set("x0", "6");
    model.param().descr("x0", "\u6d77\u5e95\u5c71\u810a\u4f4d\u7f6e (m)");
    model.param().set("a", "0.005");
    model.param().descr("a", "\u6d77\u5e95\u5c71\u810a\u9ad8\u5ea6 (m)");
    model.param().set("k1", "0.0015");
    model.param().descr("k1", "\u6d77\u5e95\u659c\u7387\u53c2\u6570");
    model.param().set("tune", "0.1");
    model.param().descr("tune", "\u8c03\u8282\u53c2\u6570");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 10, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Zf", "a*exp(-(x-x0)^2)+k1*x");
    model.component("comp1").variable("var1").descr("Zf", "\u6d77\u5e95\u8f6e\u5ed3");
    model.component("comp1").variable("var1").set("dZfdx", "d(Zf,x)");
    model.component("comp1").variable("var1").descr("dZfdx", "\u6d77\u5e8a\u8f6e\u5ed3\uff0cx \u5bfc\u6570");
    model.component("comp1").variable("var1").set("Zs", "Z+Zf");
    model.component("comp1").variable("var1").descr("Zs", "\u6d77\u5e73\u9762");
    model.component("comp1").variable("var1").set("Z0", "0.02-Zf+0.005*exp(-(x-3)^2)");
    model.component("comp1").variable("var1").descr("Z0", "\u521d\u59cb\u6c34\u6df1\u8f6e\u5ed3");
    model.component("comp1").variable("var1").set("vphase", "abs(V)+sqrt(g_const*Z)");
    model.component("comp1").variable("var1").descr("vphase", "\u6700\u5927\u6ce2\u4f20\u64ad\u901f\u5ea6");
    model.component("comp1").variable("var1").set("nu", "nu1+vphase*h*tune");
    model.component("comp1").variable("var1").descr("nu", "\u7b49\u6548\u8fd0\u52a8\u9ecf\u5ea6");

    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", "V*Z", 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", "-nu*Vx", 1);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "-g_const*(Zx+dZfdx)-V*Vx+nu*Vx*Zx/Z", 1);
    model.component("comp1").physics("g").feature("init1").set("Z", "Z0");
    model.component("comp1").physics("g").create("cons1", "Constraint", 0);
    model.component("comp1").physics("g").feature("cons1").selection().set(1, 2);
    model.component("comp1").physics("g").feature("cons1").setIndex("R", "-V", 1);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,60)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-5");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", "1e-7");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "Z");
    model.result("pg1").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("data", "dset1");
    model.result("pg1").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").feature("lngr1").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg1").feature("lngr1").set("expr", "Zf");
    model.result("pg1").feature("lngr1").set("descr", "\u6d77\u5e95\u8f6e\u5ed3");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "Zf", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("expr", "Zs");
    model.result("pg1").feature("lngr2").set("descr", "\u6d77\u5e73\u9762");
    model.result("pg1").feature("lngr2").setIndex("legends", "Zs (t = 0)", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").setIndex("looplevel", new int[]{4}, 0);
    model.result("pg1").feature("lngr2").setIndex("legends", "Zs (t = 3 s)", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").setIndex("looplevel", new int[]{7}, 0);
    model.result("pg1").feature("lngr2").setIndex("legends", "Zs (t = 6 s)", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").setIndex("looplevel", new int[]{10}, 0);
    model.result("pg1").feature("lngr2").setIndex("legends", "Zs (t = 9 s)", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").setIndex("looplevel", new int[]{13}, 0);
    model.result("pg1").feature("lngr2").setIndex("legends", "Zs (t = 12 s)", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").setIndex("looplevel", new int[]{16}, 0);
    model.result("pg1").feature("lngr2").setIndex("legends", "Zs (t = 15 s)", 0);
    model.result("pg1").run();

    model.title("\u6d45\u6c34\u65b9\u7a0b");

    model
         .description("\u8fd9\u4e2a\u4e00\u7ef4\u6a21\u578b\u4f7f\u7528\u6d45\u6c34\u65b9\u7a0b\u7814\u7a76\u53d8\u5316\u6d77\u5e8a\u4e0a\u6ce2\u968f\u65f6\u95f4\u7684\u6c89\u964d\u3002\u521d\u59cb\u6ce2\u548c\u6d77\u5e8a\u5f62\u72b6\u7528\u6570\u5b66\u5173\u7cfb\u8868\u793a\uff0c\u4ece\u800c\u53ef\u4ee5\u8f7b\u677e\u5730\u66f4\u6539\u6ce2\u5e45\u6216\u6d77\u5e8a\u5f62\u72b6\u7b49\u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("shallow_water_equations.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
