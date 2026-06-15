/*
 * phase_change.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:25 by COMSOL 6.3.0.290. */
public class phase_change {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.01, 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("T_trans", "0[degC]");
    model.param().descr("T_trans", "\u8f6c\u53d8\u6e29\u5ea6");
    model.param().set("dT", "1[K]");
    model.param().descr("dT", "\u8f6c\u53d8\u95f4\u9694");
    model.param().set("lm", "333.5[kJ/kg]");
    model.param().descr("lm", "\u7194\u5316\u6f5c\u70ed");
    model.param().set("T_0", "-20[degC]");
    model.param().descr("T_0", "\u51b0\u67f1\u7684\u521d\u59cb\u6e29\u5ea6");
    model.param().set("T_hot", "80[degC]");
    model.param().descr("T_hot", "\u70ed\u6c34\u7684\u6e29\u5ea6");
    model.param().set("rho_ice", "918[kg/m^3]");
    model.param().descr("rho_ice", "\u51b0\u7684\u5bc6\u5ea6");
    model.param().set("rho_water", "997[kg/m^3]");
    model.param().descr("rho_water", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("rho_ratio", "rho_ice/rho_water");
    model.param().descr("rho_ratio", "\u5bc6\u5ea6\u6bd4");

    model.func().create("step1", "Step");
    model.func("step1").set("funcname", "T_right");
    model.func("step1").set("location", 0.05);
    model.func("step1").set("from", "T_0");
    model.func("step1").set("to", "T_hot");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u51b0");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2.31[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_ice"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"2052[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6c34");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.613[W/(m*K)]*rho_ratio"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("density", new String[]{"rho_water*rho_ratio"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"4179[J/(kg*K)]"});

    model.component("comp1").physics("ht").feature("fluid1").create("phc1", "PhaseChangeMaterial", 1);
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("dT_pc12", "dT");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("L_pc12", "lm");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase1", "mat1");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase2", "mat2");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_0");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_right(t[1/s])");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 120);
    model.component("comp1").mesh("mesh1").run("edg1");

    model.study("std1").feature("time").set("tlist", "range(0,15,60) range(120,60,1200)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-3");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").set("tlist", "range(0,15,60) range(120,60,1200)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-3");
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "T_trans", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "K", 0);
    model.study("std2").feature("time").setIndex("pname", "T_trans", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "K", 0);
    model.study("std2").feature("time").setIndex("pname", "lm", 0);
    model.study("std2").feature("time").setIndex("plistarr", 0, 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\uff0c\u65e0\u6f5c\u70ed");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().dataset("dset1").label("\u89e3 1\uff0c\u542b lm");
    model.result().dataset("dset2").label("\u89e3 2\uff0c\u4e0d\u542b lm");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ht", true);
    model.study("std3").feature("time").set("tlist", "range(0,15,60) range(120,60,1200)");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", "1e-3");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "T_trans", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "K", 0);
    model.study("std3").feature("param").setIndex("pname", "T_trans", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "K", 0);
    model.study("std3").feature("param").setIndex("pname", "dT", 0);
    model.study("std3").feature("param").setIndex("plistarr", "0.5 1 2", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "t = 30 \u79d2\u3001300 \u79d2\u548c 600 \u79d2\u65f6\u4e0d\u540c dT \u503c\u7684\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("data", "dset4");
    model.result("pg3").feature("lngr1").setIndex("looplevelinput", "first", 1);
    model.result("pg3").feature("lngr1").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").feature("lngr1").setIndex("interp", "30 300 600", 0);
    model.result("pg3").feature("lngr1").set("linecolor", "blue");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").feature("lngr2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg3").feature("lngr2").set("linecolor", "green");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lngr3", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr3").setIndex("looplevelinput", "last", 1);
    model.result("pg3").feature("lngr3").set("linecolor", "red");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u4e0d\u540c dT");

    model.title("\u76f8\u53d8");

    model
         .description("\u8fd9\u4e2a\u4e00\u7ef4\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u6267\u884c\u76f8\u53d8\u5efa\u6a21\uff0c\u5e76\u9884\u6d4b\u6f5c\u70ed\u5bf9\u80fd\u91cf\u4f20\u8f93\u7684\u5f71\u54cd\u3002\u6a21\u578b\u4e2d\u4f7f\u7528\u77ac\u6001\u4f20\u5bfc\u4f20\u70ed\u7684\u7269\u7406\u573a\u63a5\u53e3\u6765\u63cf\u8ff0\u6f5c\u70ed\u3002");

    model.label("phase_change.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
