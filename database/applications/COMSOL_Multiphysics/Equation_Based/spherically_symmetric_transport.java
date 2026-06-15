/*
 * spherically_symmetric_transport.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class spherically_symmetric_transport {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").field("dimensionless").component(new String[]{"T"});
    model.component("comp1").physics("g").feature("gfeq1").set("Ga", new String[][]{{"-Tx"}});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/g", true);

    model.baseSystem("none");

    model.param().set("rho", "2000");
    model.param().descr("rho", "\u5bc6\u5ea6 (kg/m^3)");
    model.param().set("cp", "300");
    model.param().descr("cp", "\u70ed\u5bb9 (J/(kg*K))");
    model.param().set("k", "0.5");
    model.param().descr("k", "\u70ed\u5bfc\u7387 (W/(m*K))");
    model.param().set("Rp", "0.005");
    model.param().descr("Rp", "\u9897\u7c92\u534a\u5f84 (m)");
    model.param().set("Qs", "0");
    model.param().descr("Qs", "\u70ed\u6e90 (W/m^3)");
    model.param().set("hs", "1000");
    model.param().descr("hs", "\u4f20\u70ed\u7cfb\u6570 (W/(m^2*K))");
    model.param().set("Text", "368");
    model.param().descr("Text", "\u5916\u90e8\u6e29\u5ea6 (K)");
    model.param().set("Tinit", "298");
    model.param().descr("Tinit", "\u521d\u59cb\u503c (K)");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", "-k*x^2/Rp^2*Tx", 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "x^2*Qs", 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("da", "x^2*rho*cp", 0);
    model.component("comp1").physics("g").feature("init1").set("T", "Tinit");
    model.component("comp1").physics("g").create("flux1", "FluxBoundary", 0);
    model.component("comp1").physics("g").feature("flux1").selection().set(1);
    model.component("comp1").physics("g").create("flux2", "FluxBoundary", 0);
    model.component("comp1").physics("g").feature("flux2").selection().set(2);
    model.component("comp1").physics("g").feature("flux2").setIndex("g", "x^2/Rp*hs*(Text-T)", 0);

    model.component("comp1").mesh("mesh1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh1").feature("sca1").set("scale", 0.4);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.25,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "T");
    model.result("pg1").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6e29\u5ea6");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u65e0\u91cf\u7eb2\u534a\u5f84");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "T (K)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "t (s)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "T (K)");
    model.result("pg2").run();

    model.param().set("Rp", "0.0025");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u7403\u5bf9\u79f0\u4f20\u70ed");

    model
         .description("\u8fd9\u4e2a\u78c1\u94c1\u77ff\u9897\u7c92\u77ac\u6001\u52a0\u70ed\u793a\u4f8b\u4ecb\u7ecd\u4e86\u4e00\u79cd\u901a\u7528\u7684\u65b9\u6cd5\uff0c\u80fd\u591f\u5229\u7528\u4e00\u7ef4\u51e0\u4f55\u6a21\u578b\u5bf9\u7403\u5bf9\u79f0\u4f20\u70ed\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("spherically_symmetric_transport.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
