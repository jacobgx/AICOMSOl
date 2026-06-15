/*
 * electric_field_concentric_cylinders.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class electric_field_concentric_cylinders {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electrostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.param().set("ri", "0.1[m]");
    model.param().descr("ri", "\u5185\u5706\u67f1\u7684\u534a\u5f84");
    model.param().set("ro", "1[m]");
    model.param().descr("ro", "\u5916\u5706\u67f1\u7684\u534a\u5f84");
    model.param().set("V0", "100[V]");
    model.param().descr("V0", "\u5185\u5706\u67f1\u7684\u7535\u52bf");
    model.param().set("q0", "1e-7[C/m]");
    model.param().descr("q0", "\u5185\u5706\u67f1\u7684\u7535\u8377");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "ri", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "ro", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("es").create("gnd1", "Ground", 0);
    model.component("comp1").physics("es").feature("gnd1").selection().set(2);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 0);
    model.component("comp1").physics("es").feature("pot1").selection().set(1);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");
    model.component("comp1").physics().create("es2", "Electrostatics", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/es2", true);

    model.component("comp1").physics("es2").create("gnd1", "Ground", 0);
    model.component("comp1").physics("es2").feature("gnd1").selection().set(2);
    model.component("comp1").physics("es2").create("sfcd1", "SurfaceChargeDensity", 0);
    model.component("comp1").physics("es2").feature("sfcd1").selection().set(1);
    model.component("comp1").physics("es2").feature("sfcd1").set("rhoqs", "q0/(2*pi*ri)");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("solutionparams", "parent");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u7535\u52bf (es2)");
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("solutionparams", "parent");
    model.result("pg2").feature("lngr1").set("expr", "V2");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "r");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u52bf\u6bd4\u8f83\uff0c\u7535\u52bf");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr2").set("linewidth", "preference");
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", "V0*log(r/ro)/log(ri/ro)");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "r");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autoexpr", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autodescr", true);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u52bf\u6bd4\u8f83\uff0c\u7535\u8377\u5bc6\u5ea6");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr2").set("linewidth", "preference");
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result("pg2").feature("lngr2").set("expr", "-q0*log(r/ro)/(2*pi*epsilon0_const)");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "r");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autoexpr", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autodescr", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a\u6bd4\u8f83\uff0c\u7535\u52bf");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u573a (V/m)");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", "es.Er");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "r");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("autoexpr", true);
    model.result("pg3").feature("lngr1").set("autosolution", false);
    model.result("pg3").feature("lngr1").set("autodescr", true);
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").selection().set(1);
    model.result("pg3").feature("lngr2").set("expr", "-V0/(r*log(ri/ro))");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "r");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u573a\u6bd4\u8f83\uff0c\u7535\u8377\u5bc6\u5ea6");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u573a (V/m)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", "es2.Er");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "r");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autoexpr", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature("lngr1").set("autodescr", true);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").selection().set(1);
    model.result("pg4").feature("lngr2").set("expr", "q0/(2*pi*epsilon0_const*r)");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "r");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg4").run();

    model.title("\u540c\u5fc3\u5706\u67f1\u4e4b\u95f4\u7684\u7535\u573a");

    model
         .description("\u8fd9\u4e2a\u4ecb\u7ecd\u6027\u6a21\u578b\u5904\u7406\u6559\u79d1\u4e66\u4e2d\u5e38\u89c1\u7684\u4e24\u4e2a\u65e0\u9650\u957f\u540c\u5fc3\u5706\u67f1\u7684\u9759\u7535\u95ee\u9898\u3002\u7531\u4e8e\u6211\u4eec\u53ef\u4ee5\u4f7f\u7528\u89e3\u6790\u65b9\u6cd5\u6c42\u89e3\u8be5\u95ee\u9898\uff0c\u56e0\u6b64\u5c06\u6570\u503c\u4eff\u771f\u7ed3\u679c\u4e0e\u7406\u8bba\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u672c\u4f8b\u8003\u8651\u4e24\u79cd\u60c5\u51b5\uff1a\u4e00\u79cd\u662f\u6bcf\u4e2a\u5706\u67f1\u90fd\u5177\u6709\u56fa\u5b9a\u7535\u4f4d\uff0c\u53e6\u4e00\u79cd\u662f\u4e00\u4e2a\u5706\u67f1\u5177\u6709\u8868\u9762\u7535\u8377\u5bc6\u5ea6\uff0c\u800c\u53e6\u4e00\u4e2a\u5219\u5177\u6709\u56fa\u5b9a\u7535\u4f4d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("electric_field_concentric_cylinders.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
