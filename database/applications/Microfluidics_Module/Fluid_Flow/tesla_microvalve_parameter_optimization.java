/*
 * tesla_microvalve_parameter_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:08 by COMSOL 6.3.0.290. */
public class tesla_microvalve_parameter_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("spf2", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf2", true);

    model.component("comp1").geom("geom1")
         .insertFile("tesla_microvalve_parameter_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").set("Re", "100");
    model.param("par2").descr("Re", "\u96f7\u8bfa\u6570");
    model.param("par2").set("mu0", "1e-3[Pa*s]");
    model.param("par2").descr("mu0", "\u52a8\u529b\u9ecf\u5ea6");
    model.param("par2").set("rho0", "1e3[kg/m^3]");
    model.param("par2").descr("rho0", "\u5bc6\u5ea6");
    model.param("par2").set("Uin", "Re*mu0/(rho0*D)");
    model.param("par2").descr("Uin", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param("par2").set("meshsz", "0.01[mm]");
    model.param("par2").descr("meshsz", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("geom1_boxsel2");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("geom1_boxsel1");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("dP_forward", "aveop1(p)-aveop2(p)");
    model.component("comp1").variable("var1").descr("dP_forward", "\u538b\u5dee\uff0c\u6b63\u5411");
    model.component("comp1").variable("var1").set("dP_backward", "aveop2(p2)-aveop1(p2)");
    model.component("comp1").variable("var1").descr("dP_backward", "\u538b\u5dee\uff0c\u53cd\u5411");
    model.component("comp1").variable("var1").set("Di", "dP_backward/dP_forward");
    model.component("comp1").variable("var1").descr("Di", "\u538b\u5dee\u6bd4");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf2").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf2").feature("wallbc2").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf2").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf2").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf2").feature("inl1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf2").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf2").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf2").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf2").feature("out1").selection().named("geom1_boxsel2");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf2.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf2)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p2");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "Di", 0);
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u521d\u59cb\u8bbe\u8ba1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "bobyqa");
    model.study("std2").feature("opt").set("optobj", new String[]{"comp1.Di"});
    model.study("std2").feature("opt").set("descr", new String[]{"\u538b\u5dee\u6bd4"});
    model.study("std2").feature("opt").set("objectivetype", "maximization");
    model.study("std2").feature("opt").setIndex("pname", "aLT", 0);
    model.study("std2").feature("opt").setIndex("initval", 0.4, 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "aLT", 0);
    model.study("std2").feature("opt").setIndex("initval", 0.4, 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "aR", 1);
    model.study("std2").feature("opt").setIndex("initval", 0.4, 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "aR", 1);
    model.study("std2").feature("opt").setIndex("initval", 0.4, 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "aRC", 2);
    model.study("std2").feature("opt").setIndex("initval", 1, 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "aRC", 2);
    model.study("std2").feature("opt").setIndex("initval", 1, 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("lbound", 0.01, 0);
    model.study("std2").feature("opt").setIndex("ubound", 0.99, 0);
    model.study("std2").feature("opt").setIndex("lbound", 0.01, 1);
    model.study("std2").feature("opt").setIndex("ubound", 0.99, 1);
    model.study("std2").feature("opt").setIndex("pname", "aXT", 2);
    model.study("std2").feature("opt").setIndex("lbound", 0.01, 2);
    model.study("std2").feature("opt").setIndex("ubound", 0.99, 2);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "aLT", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "aLT", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "Re", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "50 100", 0);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "aLT", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "aLT", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "error", 0);
    model.study("std2").feature("param").setIndex("plistarr", "-tol 0 tol", 0);
    model.study("std2").label("\u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("o1").getInitialValue();

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u901f\u5ea6 (spf) 1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u538b\u529b (spf) 1");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("con1", "Contour");
    model.result("pg6").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("expr", "p");
    model.result("pg6").feature("con1").set("number", 40);
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("smooth", "internal");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("data", "parent");
    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u901f\u5ea6 (spf2) 1");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "spf2.U");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u538b\u529b (spf2) 1");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("expr", "p2");
    model.result("pg8").feature("con1").set("number", 40);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("smooth", "internal");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result("pg5").run();

    model.study("std2").feature("opt").set("objectivesolution", "last");
    model.study("std2").feature("opt").set("outersolution", "min");
    model.study("std2").feature("opt").set("plot", true);
    model.study("std2").feature("opt").set("plotgroup", "pg7");
    model.study("std2").createAutoSequences("all");

    model.batch("o1").run("compute");

    model.result("pg5").run();

    model.study("std2").feature("opt").set("probewindow", "");

    model.result().numerical("gev1").set("data", "dset3");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();
    model.result("pg7").run();
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").label("\u5df2\u4f18\u5316");

    model.title("Tesla \u5fae\u9600\u7684\u53c2\u6570\u4f18\u5316");

    model
         .description("Tesla \u5fae\u9600\u7684\u62d3\u6251\u4f18\u5316\u662f\u6211\u4eec\u5efa\u7acb\u53c2\u6570\u5316\u51e0\u4f55\u6a21\u578b\u7684\u7075\u611f\u6765\u6e90\u3002\u672c\u4f8b\u901a\u8fc7\u4f18\u5316\u53c2\u6570\u5316\u626b\u63cf\u6765\u5305\u542b\u51e0\u4f55\u7684\u524a\u51cf\u548c\u6269\u5f20\uff0c\u4ece\u800c\u5bf9\u51e0\u4f55\u8fdb\u884c\u4f18\u5316\u3002\u6bcf\u4e2a\u53c2\u6570\u5316\u626b\u63cf\u5b9e\u73b0\u4e86 3 \u4e2a\u51e0\u4f55\uff0c\u6211\u4eec\u5bf9\u6700\u5dee\u7684\u4e00\u4e2a\u8fdb\u884c\u4f18\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("tesla_microvalve_parameter_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
