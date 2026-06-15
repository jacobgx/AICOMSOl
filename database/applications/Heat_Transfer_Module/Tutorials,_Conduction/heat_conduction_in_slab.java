/*
 * heat_conduction_in_slab.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:33 by COMSOL 6.3.0.290. */
public class heat_conduction_in_slab {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Conduction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").baseSystem("none");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", -1, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "1e-6");
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", 0);
    model.component("comp1").func("step1").set("smooth", "2e-6");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").physics("ht").feature("solid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("k", new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
    model.component("comp1").physics("ht").feature("solid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("rho", 1);
    model.component("comp1").physics("ht").feature("solid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("Cp", 1);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", 1);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "step1(t)");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1e-4");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.01,1)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "2e-7");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "1e-3");

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
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{2, 5, 11, 21, 41, 61}, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("linemarker", "cycle");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\\tau=0.01", 0);
    model.result("pg1").feature("lngr1").setIndex("legends", "\\tau=0.04", 1);
    model.result("pg1").feature("lngr1").setIndex("legends", "\\tau=0.1", 2);
    model.result("pg1").feature("lngr1").setIndex("legends", "\\tau=0.2", 3);
    model.result("pg1").feature("lngr1").setIndex("legends", "\\tau=0.4", 4);
    model.result("pg1").feature("lngr1").setIndex("legends", "\\tau=0.6", 5);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u5bf9 L2 \u8bef\u5dee");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().all();
    model.result("pg2").feature("lngr1")
         .set("expr", "sqrt(intop1((T-2*sum((-1)^n/((n+0.5)*pi)*exp(-(n+0.5)^2*pi^2*t)*cos((n+0.5)*pi*x),n,0,1000))^2))/sqrt(intop1(T^2))");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("descr", "\u76f8\u5bf9\u89e3\u6790\u89e3\u7684 L2 \u8bef\u5dee");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "t");
    model.result("pg2").feature("lngr1").set("linecolor", "fromtheme");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", "1e-3");
    model.result("pg2").set("xmax", 1);
    model.result("pg2").set("ymin", 0);
    model.result("pg2").set("ymax", "5e-4");
    model.result("pg2").run();

    model.title("\u6709\u9650\u677f\u4e0a\u7684\u70ed\u4f20\u5bfc");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u5728\u677f\u8fb9\u754c\u4e0a\u6e29\u5ea6\u7a81\u7136\u4e0b\u964d\u65f6\u6cbf\u677f\u7684\u6e29\u5ea6\u5206\u5e03\u3002");

    model.label("heat_conduction_in_slab.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
