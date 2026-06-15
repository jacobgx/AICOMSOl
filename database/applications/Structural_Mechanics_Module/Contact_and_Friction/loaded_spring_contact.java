/*
 * loaded_spring_contact.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:03 by COMSOL 6.3.0.290. */
public class loaded_spring_contact {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("dh", "2[cm]");
    model.param().descr("dh", "Prescribed extension");

    model.component("comp1").geom("geom1").lengthUnit("dm");
    model.component("comp1").geom("geom1").create("hel1", "Helix");
    model.component("comp1").geom("geom1").feature("hel1").set("turns", 4.5);
    model.component("comp1").geom("geom1").feature("hel1").set("rot", 180);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", true);
    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(6);

    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "Force", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "aveop1(w)-dh", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(6);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "0", "Force"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
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
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(6);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 200);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "Force", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "aveop1(w)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Global Evaluation 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg1").run();

    model
         .title("\u8d1f\u8f7d\u5f39\u7c27 - \u4f7f\u7528\u5168\u5c40\u65b9\u7a0b\u6ee1\u8db3\u7ea6\u675f\u6761\u4ef6");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u6f14\u793a\u4e00\u4e2a\u666e\u904d\u9002\u7528\u7684\u65b9\u6cd5\uff1a\u5f39\u7c27\u7684\u7ed3\u6784\u529b\u5b66\u6a21\u578b\u901a\u8fc7\u5168\u5c40\u65b9\u7a0b\u5f97\u5230\u589e\u5f3a\uff0c\u8be5\u65b9\u7a0b\u7528\u4e8e\u6c42\u89e3\u5f39\u7c27\u8fbe\u5230\u671f\u671b\u7684\u603b\u4f38\u957f\u65f6\u6240\u9700\u7684\u8f7d\u8377\u3002");

    model.label("loaded_spring.mph");

    model.result("pg1").run();

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().all();
    model.component("comp1").pair("p1").source().set(2, 3, 4, 5);
    model.component("comp1").pair("p1").destination().all();
    model.component("comp1").pair("p1").destination().set(2, 3, 4, 5);

    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "Nitsche");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlNitsche", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_nitsche", 0.01);
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.45);

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "dh", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "dh", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "-range(0,0.5,10)", 0);
    model.study("std1").feature("stat").setIndex("punit", "cm", 0);
    model.study("std1").showAutoSequences("sol");

    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "GPa");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", 1);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u4f4d\u79fb");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayshape", "square");
    model.result("pg2").set("arrayplane", "xz");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("arraydim", "2");
    model.result("pg2").feature("vol1").set("data", "dset1");
    model.result("pg2").feature("vol1").setIndex("looplevel", 9, 0);
    model.result("pg2").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg2").feature("vol1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("vol1").feature("def1").set("scale", 1);
    model.result("pg2").feature("vol1").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("vol2", "vol1");
    model.result("pg2").feature("vol2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("vol2").setIndex("looplevel", 13, 0);
    model.result("pg2").run();
    model.result("pg2").feature("vol2").set("titletype", "none");
    model.result("pg2").feature("vol2").set("inheritplot", "vol1");
    model.result("pg2").feature().duplicate("vol3", "vol2");
    model.result("pg2").feature("vol3").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("vol3").setIndex("looplevel", 17, 0);
    model.result("pg2").feature().duplicate("vol4", "vol3");
    model.result("pg2").feature("vol4").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("vol4").setIndex("looplevel", 21, 0);
    model.result("pg2").run();
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u529b");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "-Force", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u529b", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "-dh");
    model.result("pg3").feature("glob1").set("xdataunit", "cm");
    model.result("pg3").feature("glob1").set("xdatadescractive", true);
    model.result("pg3").feature("glob1").set("xdatadescr", "\u5782\u76f4\u4f4d\u79fb");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u52a0\u8f7d\u5f39\u7c27\u7684\u81ea\u63a5\u89e6");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4e3a\u87ba\u65cb\u5f39\u7c27\u8bbe\u7f6e\u81ea\u63a5\u89e6\u3002\u5f53\u5f39\u7c27\u88ab\u65bd\u52a0\u5728\u5176\u4e00\u7aef\u7684\u5782\u76f4\u529b\u538b\u7f29\u65f6\uff0c\u5b83\u4f1a\u4e0e\u81ea\u8eab\u63a5\u89e6\u5e76\u5f00\u59cb\u65cb\u8f6c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("loaded_spring_contact.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
