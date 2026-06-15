/*
 * jet_pipe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class jet_pipe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Aeroacoustics_and_Noise");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("lpff", "LinearizedPotentialFlowFrequency", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/lpff", true);

    model.baseSystem("none");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{1.45, 1.9});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0.75, -0.7});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.2, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.25, 1.9});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0.75, -0.7});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", 0.7, 0);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("M0", "0.25");
    model.param().set("M1", "0.45");
    model.param().set("f", "30/(2*pi)");
    model.param().set("m", "4");
    model.param().set("n", "0");
    model.param("default").paramCase().create("case1");
    model.param("default").paramCase().create("case2");
    model.param("default").paramCase("case2").set("m", "17");
    model.param("default").paramCase("case2").set("n", "1");
    model.param("default").paramCase().create("case3");
    model.param("default").paramCase("case3").set("m", "24");
    model.param("default").paramCase("case3").set("n", "1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7aef\u53e3");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"1"});

    model.component("comp1").physics("lpff").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("lpff").prop("PortSweepSettings").set("ModeShapeNormalization", "Intensity");
    model.component("comp1").physics("lpff").feature("lpfm1").set("minput_velocity", new String[]{"0", "0", "M0"});
    model.component("comp1").physics("lpff").create("vs1", "VortexSheet", 1);
    model.component("comp1").physics("lpff").feature("vs1").selection().set(12, 13);
    model.component("comp1").physics("lpff").create("ishb1", "InteriorSoundHard", 1);
    model.component("comp1").physics("lpff").feature("ishb1").selection().set(10);
    model.component("comp1").physics("lpff").create("lpfm2", "LinearizedPotentialFlowModel", 2);
    model.component("comp1").physics("lpff").feature("lpfm2").selection().set(1, 2, 3);
    model.component("comp1").physics("lpff").feature("lpfm2").set("minput_velocity", new String[]{"0", "0", "M1"});
    model.component("comp1").physics("lpff").create("port1", "Port", 1);
    model.component("comp1").physics("lpff").feature("port1").selection().named("sel1");
    model.component("comp1").physics("lpff").feature("port1").set("PortType", "Annular");
    model.component("comp1").physics("lpff").feature("port1").set("n_circ", "n");
    model.component("comp1").physics("lpff").feature("port1").set("PortExcitation", "on");
    model.component("comp1").physics("lpff").feature("port1").set("IncidentWave", "ModeScale");
    model.component("comp1").physics("lpff").feature("port1").set("Sin", 1);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(3, 4, 6, 7, 8, 9);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2, 5);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "(1-M1)/f/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "(1-M1)/f/6");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(16, 19, 20, 21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "f");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,3)", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,3)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("layermethod", "custom");
    model.result().dataset("rev1").set("revlayers", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (lpff)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("expr", "lpff.p");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u58f0\u538b (lpff)");
    model.result("pg1").run();
    model.result("pg1").label("\u8fd1\u573a\u538b\u529b");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8fd1\u573a\u538b\u529b\uff1a(m,n)=(eval(m),eval(n))");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().set(1, 2, 5);
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u538b\u7ea7 (lpff)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("expr", "lpff.Lp");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").label("\u58f0\u538b\u7ea7 (lpff)");
    model.result("pg2").run();
    model.result("pg2").label("\u8fd1\u573a\u58f0\u538b\u7ea7");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u8fd1\u573a\u58f0\u538b\u7ea7\uff1a(m,n)=(eval(m),eval(n))");
    model.result("pg2").set("paramindicator", "");
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().set(1, 2, 5);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset("rev2")
         .set("defaultPlotIDs", new String[]{"linearizedpotentialflow/LinearizedPotentialFlowPhysicsInterfaceComponents/icom4/pdef1/pcond2/pcond3/pg1|lpff", "linearizedpotentialflow/LinearizedPotentialFlowPhysicsInterfaceComponents/icom4/pdef1/pcond2/pcond3/pg2|lpff", "linearizedpotentialflow/LinearizedPotentialFlowPhysicsInterfaceComponents/icom4/pdef1/pcond2/pcond3/pg2|lpff|surf1"});
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58f0\u538b\uff0c\u4e09\u7ef4 (lpff)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("expr", "lpff.p");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").label("\u58f0\u538b\uff0c\u4e09\u7ef4 (lpff)");
    model.result("pg3").run();
    model.result("pg3").label("\u8fd1\u573a\u538b\u529b\uff0c\u4e8c\u7ef4\u65cb\u8f6c");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u8fd1\u573a\u538b\u529b\uff0c\u4e8c\u7ef4\u65cb\u8f6c\uff1a(m,n)=(eval(m),eval(n))");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "lpff.p*exp(-i*m*rev1phi)");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1, 2, 5);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "lpff.p_in_1*exp(-i*m*rev1phi)");
    model.result("pg4").run();
    model.result("pg4").label("\u5165\u5c04\u6e90\u6a21\u5f0f\u538b\u529b");
    model.result("pg4").set("title", "\u5165\u5c04\u6e90\u6a21\u5f0f\u538b\u529b\uff1a(m,n)=(eval(m),eval(n))");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1 - \u6a21\u5f0f\u622a\u6b62\u9891\u7387");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lpff.port1.fc"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u6a21\u5f0f\u622a\u6b62\u9891\u7387"});
    model.result().evaluationGroup("eg1").feature("gev1").set("unit", new String[]{""});
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u8ba1\u7b97\u7ec4 2 - \u6563\u5c04\u7cfb\u6570");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"lpff.S11"});
    model.result().evaluationGroup("eg2").feature("gev1").set("descr", new String[]{"S11"});
    model.result().evaluationGroup("eg2").feature("gev1").set("unit", new String[]{""});
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "abs(lpff.S11)", 0);
    model.result().evaluationGroup("eg2").run();
    model.result("pg3").run();

    model.title("\u55b7\u5c04\u7ba1");

    model
         .description("\u672c\u6a21\u578b\u8ba1\u7b97\u822a\u7a7a\u53d1\u52a8\u673a\u6da1\u8f6e\u98ce\u6247\u566a\u58f0\u8f90\u5c04\u3002\u5728\u9ad8\u901f\u55b7\u5c04\u6c14\u6d41\u548c\u5468\u56f4\u4f4e\u901f\u7a7a\u6c14\u4e4b\u95f4\u5f62\u6210\u4e86\u6da1\u65cb\u5c42\uff0c\u5e76\u4e14\u8be5\u6da1\u65cb\u5c42\u4f1a\u5f71\u54cd\u58f0\u573a\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("jet_pipe.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
