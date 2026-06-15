/*
 * tuning_fork.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:39 by COMSOL 6.3.0.290. */
public class tuning_fork {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Structural_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.param().set("L", "7.8[cm]");
    model.param().descr("L", "\u5706\u67f1\u957f\u5ea6");
    model.param().set("R1", "7.5[mm]");
    model.param().descr("R1", "\u5e95\u5ea7\u534a\u5f84");
    model.param().set("R2", "2.5[mm]");
    model.param().descr("R2", "\u53c9\u9f7f\u534a\u5f84");

    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", "R2");
    model.component("comp1").geom("geom1").feature("cone1").set("h", "2e-2");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "angle");
    model.component("comp1").geom("geom1").feature("cone1").set("ang", 2);
    model.component("comp1").geom("geom1").feature("cone1").set("pos", new String[]{"R1", "0", "-R1"});
    model.component("comp1").geom("geom1").feature("cone1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cone1").set("ax3", new int[]{0, 0, -1});
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "4e-3");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"R1", "0", "-(R1+2.25e-2)"});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", "R1");
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", "R2");
    model.component("comp1").geom("geom1").feature("tor1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("tor1").set("pos", new String[]{"R1", "0", "0"});
    model.component("comp1").geom("geom1").feature("tor1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("tor1").set("ax3", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("tor1").set("rot", -90);
    model.component("comp1").geom("geom1").run("tor1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cone1", "sph1", "tor1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"2*R1", "0", "0"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 22, 23, 29, 32, 33, 39, 42, 43);
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(6, 24);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.078,1e-4,0.0795)", 0);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").feature("eig").set("shift", "440");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("rtol", "1e-3");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 16, 1);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("tablecols", "inner");
    model.result().numerical("gev1").set("expr", new String[]{"solid.freq"});
    model.result().numerical("gev1").set("descr", new String[]{"\u9891\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"Hz"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 12});
    model.result("pg1").run();

    model.title("\u97f3\u53c9");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u97f3\u53c9\u7684\u57fa\u672c\u7279\u5f81\u9891\u7387\u548c\u7279\u5f81\u6a21\u6001\u3002\u5f53\u8bbe\u8ba1\u5f97\u5f53\u65f6\uff0c\u97f3\u53c9\u4f1a\u4ee5 440\u00a0Hz \u8fd9\u4e00\u6807\u51c6\u97f3\u4e50\u4f1a\u97f3\u9ad8\u7684\u9891\u7387\u632f\u52a8\u3002");

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

    model.label("tuning_fork.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
