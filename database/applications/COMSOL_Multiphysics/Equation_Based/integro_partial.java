/*
 * integro_partial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class integro_partial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("epsilon", "1");
    model.param().descr("epsilon", "\u8f90\u5c04\u7387");
    model.param().set("T_cold", "300[K]");
    model.param().descr("T_cold", "\u6e29\u5ea6\uff0c\u51b7\u7aef");
    model.param().set("DT_max", "1200[K]");
    model.param().descr("DT_max", "\u6700\u5927\u6e29\u5dee");
    model.param().set("T_init", "T_cold");
    model.param().descr("T_init", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("D_i", "1[in]");
    model.param().descr("D_i", "\u5185\u5f84");
    model.param().set("D_o", "1.1*D_i");
    model.param().descr("D_o", "\u5916\u5f84");
    model.param().set("L", "0.2[m]");
    model.param().descr("L", "\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().all();
    model.component("comp1").variable("var1").set("xi", "abs(dest(x)-x)/D_i");
    model.component("comp1").variable("var1").set("k", "1-(2*xi^3+3*xi)/(2*(xi^2+1)^1.5)");
    model.component("comp1").variable("var1").descr("k", "\u79ef\u5206\u6838");
    model.component("comp1").variable("var1").set("Q_source", "4/(D_o^2-D_i^2)*epsilon*sigma_const*intop1(k*T^4)");
    model.component("comp1").variable("var1").descr("Q_source", "\u70ed\u6e90");
    model.component("comp1").variable("var1").set("Q_loss", "-4*D_i/(D_o^2-D_i^2)*epsilon*sigma_const*T^4");
    model.component("comp1").variable("var1").descr("Q_loss", "\u70ed\u635f\u8017");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"13"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8700"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"300"});

    model.component("comp1").physics("ht").create("hs1", "HeatSource", 1);
    model.component("comp1").physics("ht").feature("hs1").selection().all();
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "Q_source+Q_loss");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_init");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_cold+DT_max*tanh(t/1[min])");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp2").selection().set(2);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "T_cold");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,1[min],1[h])");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "epsilon", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "epsilon", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u79ef\u5206-\u504f\u5fae\u5206\u65b9\u7a0b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e24\u7aef\u4fdd\u6301\u4e0d\u540c\u6e29\u5ea6\u7684\u7a7a\u5fc3\u7ba1\u4e2d\u7684\u4f20\u5bfc\u4f20\u70ed\u548c\u8f90\u5c04\u4f20\u70ed\u3002\u5728\u6c42\u89e3\u65b9\u7a0b\u65f6\u4f7f\u7528\u4e86\u76ee\u6807\u7b97\u5b50\u548c\u79ef\u5206\u8026\u5408\u7b97\u5b50\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("integro_partial.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
