/*
 * turbomolecular_pump_quasi_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:56 by COMSOL 6.3.0.290. */
public class turbomolecular_pump_quasi_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Vacuum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pt", "MathParticle", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pt", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("S0", "1", "\u53f6\u7247\u5c55\u5f26\u6bd4");
    model.param().set("C", "1", "\u901f\u5ea6\u56e0\u5b50");
    model.param().set("alpha", "35[deg]", "\u53f6\u7247\u503e\u89d2");
    model.param().set("B", "5[cm]", "\u53f6\u7247\u957f\u5ea6");
    model.param().set("S", "B*S0", "\u53f6\u7247\u95f4\u8ddd");
    model.param().set("vb", "Vmp_H2*C", "\u6700\u53ef\u80fd\u7684\u5206\u5b50\u901f\u5ea6");
    model.param().set("T0", "300[K]", "\u6e29\u5ea6");
    model.param().set("Mp_H2", "0.002[kg/mol]", "\u6c22\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("mp_H2", "Mp_H2/N_A_const", "\u6c22\u7684\u5206\u5b50\u8d28\u91cf");
    model.param().set("Vmp_H2", "sqrt(2*R_const*T0/Mp_H2)", "\u6700\u53ef\u80fd\u7684\u901f\u5ea6");
    model.param().set("Np", "3000", "\u91ca\u653e\u7684\u7c92\u5b50\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("M12", "pt.sum(if(bndenv(pt.pcnt1.Ob)==1,1,0))/Np");
    model.component("comp1").variable("var1").descr("M12", "\u4f20\u8f93\u6982\u7387");
    model.component("comp1").variable("var1").set("m12", "pt.sum(if(bndenv(pt.pcnt1.Ob)==1,noCollision,0))/Np");
    model.component("comp1").variable("var1").descr("m12", "\u76f4\u63a5\u4f20\u8f93\u6982\u7387");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "B*sin(alpha)", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "B*cos(alpha)", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "B*sin(alpha)", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "S+B*cos(alpha)", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "S", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("pt").feature("pp1").set("mp", "mp_H2");
    model.component("comp1").physics("pt").create("aux1", "AuxiliaryField", -1);
    model.component("comp1").physics("pt").feature("aux1").set("fieldVariableName", "noCollision");
    model.component("comp1").physics("pt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("pt").feature("inl1").selection().set(1);
    model.component("comp1").physics("pt").feature("inl1").set("InitialPosition", "RandomPosition");
    model.component("comp1").physics("pt").feature("inl1").setIndex("N", "Np", 0);
    model.component("comp1").physics("pt").feature("inl1").set("VelocitySpecification", "Thermal");
    model.component("comp1").physics("pt").feature("inl1").set("T", "T0");
    model.component("comp1").physics("pt").feature("inl1").set("aux0_aux1", 1);
    model.component("comp1").physics("pt").feature("inl1").set("SubtractMovingFrameVelocity", true);
    model.component("comp1").physics("pt").feature("inl1").set("BackgroundVelocity", "UserDefined");
    model.component("comp1").physics("pt").feature("inl1").set("vb", new String[]{"0", "-vb", "0"});
    model.component("comp1").physics("pt").feature("wall1").label("\u58c1 1\uff08\u5bf9\u79f0\u8fb9\u754c\uff09");
    model.component("comp1").physics("pt").feature("wall1").set("WallCondition", "Bounce");
    model.component("comp1").physics("pt").create("tre1", "ThermalReEmission", 2);
    model.component("comp1").physics("pt").feature("tre1").selection().set(2, 5);
    model.component("comp1").physics("pt").feature("tre1").set("T", "T0");
    model.component("comp1").physics("pt").feature("tre1")
         .label("\u70ed\u518d\u53d1\u5c04 1\uff08\u53f6\u7247\u8868\u9762\uff09");
    model.component("comp1").physics("pt").feature("tre1").set("caux_aux1", true);
    model.component("comp1").physics("pt").create("out1", "Outlet", 2);
    model.component("comp1").physics("pt").feature("out1").selection().set(1, 6);
    model.component("comp1").physics("pt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("pt").feature("pcnt1").selection().set(6);

    model.component("comp1").mesh("mesh1").autoMeshSize(9);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u5bf9\u901f\u5ea6\u8fdb\u884c\u626b\u63cf");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "S0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "S0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "C", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(-10,0.5,10)", 0);
    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "0 0.2");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f20\u8f93\u6982\u7387\uff1a\u5bf9\u901f\u5ea6\u8fdb\u884c\u626b\u63cf");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4f20\u8f93\u6982\u7387 vs. \u901f\u5ea6");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u4f20\u8f93\u6982\u7387");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "M12", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "m12", 1);
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "M12", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "m12", 1);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/pt", true);
    model.study("std2").label("\u5bf9\u89d2\u5ea6\u8fdb\u884c\u626b\u63cf");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "S0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "S0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "alpha", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(5,5,55)", 0);
    model.study("std2").feature("param").setIndex("punit", "deg", 0);
    model.study("std2").feature("time").set("tunit", "ms");
    model.study("std2").feature("time").set("tlist", "0 0.2");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol45");
    model.sol("sol45").study("std2");
    model.sol("sol45").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol45");
    model.batch("p2").run("compute");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u4f20\u8f93\u6982\u7387\uff1a\u5bf9\u89d2\u5ea6\u8fdb\u884c\u626b\u63cf");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").set("title", "\u4f20\u8f93\u6982\u7387 vs. \u89d2\u5ea6");
    model.result("pg2").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/pt", true);

    model.component("comp1").geom("geom1").run();

    model.study("std3").label("\u5bf9\u957f\u5bbd\u6bd4\u8fdb\u884c\u626b\u63cf");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "S0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "S0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0.5,0.1,1.5)", 0);
    model.study("std3").feature("time").set("tunit", "ms");
    model.study("std3").feature("time").set("tlist", "0 0.2");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol58");
    model.sol("sol58").study("std3");
    model.sol("sol58").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol58");
    model.batch("p3").run("compute");

    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u4f20\u8f93\u6982\u7387\uff1a\u5bf9\u957f\u5bbd\u6bd4\u8fdb\u884c\u626b\u63cf");
    model.result("pg3").set("data", "dset6");
    model.result("pg3").set("title", "\u4f20\u8f93\u6982\u7387 vs. \u957f\u5bbd\u6bd4");
    model.result("pg3").run();

    model.title("\u6da1\u8f6e\u5206\u5b50\u6cf5\u51c6\u4e8c\u7ef4\u6a21\u578b");

    model
         .description("\u5982\u679c\u53f6\u7247\u7684\u5e73\u5747\u534a\u5f84\u8fdc\u5927\u4e8e\u53f6\u7247\u95f4\u8ddd\uff0c\u5219\u6da1\u8f6e\u5206\u5b50\u6cf5\u4e2d\u7a00\u8584\u6c14\u4f53\u6d41\u52a8\u7684\u8499\u7279\u5361\u7f57\u4eff\u771f\u53ef\u4ee5\u5927\u5927\u7b80\u5316\u3002\u5728\u8fd9\u4e9b\u6761\u4ef6\u4e0b\uff0c\u6cf5\u7684\u65cb\u8f6c\u53f6\u7247\u53ef\u4ee5\u8fd1\u4f3c\u4e3a\u4ec5\u5177\u6709\u5e73\u79fb\u901f\u5ea6\u7684\u65e0\u9650\u6392\u53f6\u7247\u3002\n\u6b64\u57fa\u51c6\u6a21\u578b\u6f14\u793a\u53f6\u7247\u7684\u901f\u5ea6\u3001\u89d2\u5ea6\u548c\u95f4\u8ddd\u5bf9\u5355\u7ea7\u6da1\u8f6e\u5206\u5b50\u6cf5\u4e2d\u5206\u5b50\u5728\u81ea\u7531\u5206\u5b50\u6d41\u6001\u4e0b\u7684\u4f20\u8f93\u6982\u7387\u7684\u5f71\u54cd\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u65e9\u671f\u53d1\u8868\u7684\u4f7f\u7528\u76f8\u540c\u7b80\u5316\u5047\u8bbe\u7684\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();

    model.label("turbomolecular_pump_quasi_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
