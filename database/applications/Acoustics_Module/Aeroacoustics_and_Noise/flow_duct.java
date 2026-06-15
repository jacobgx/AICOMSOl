/*
 * flow_duct.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class flow_duct {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Aeroacoustics_and_Noise");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cpf", "CompressiblePotentialFlow", "geom1");
    model.component("comp1").physics().create("lpff", "LinearizedPotentialFlowFrequency", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cpf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/lpff", true);

    model.baseSystem("none");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("gamma", "1.4", "\u6bd4\u70ed\u7387");
    model.param().set("M", "-0.5", "\u5e73\u5747\u6d41\u52a8\u9a6c\u8d6b\u6570");
    model.param().set("m", "10", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("omega", "16", "\u89d2\u9891\u7387");
    model.param().set("f", "omega/(2*pi)", "\u9891\u7387");
    model.param().set("rho0", "1", "\u53c2\u8003\u5bc6\u5ea6");
    model.param().set("C0", "1", "\u53c2\u8003\u5e73\u5747\u58f0\u901f");
    model.param().set("Zw", "2-i", "\u5bfc\u7ba1\u58c1\u963b\u6297");
    model.param().set("zi", "1.86393", "\u8f74\u5411\u5750\u6807\uff0c\u5165\u53e3\u5e73\u9762");
    model.param().set("ri", "0.91705", "\u5f84\u5411\u5750\u6807\uff0c\u5165\u53e3\u5e73\u9762");

    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1")
         .set("coord", new String[]{"1-0.18453*s^2+0.10158*(exp(-11*(1-s))-exp(-11))/(1-exp(-11))", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "s*zi", 1);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("pc2", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc2").set("parmax", 0.7);
    model.component("comp1").geom("geom1").feature("pc2")
         .set("coord", new String[]{"0.64212-sqrt(0.04777+0.98234*s^2)", ""});
    model.component("comp1").geom("geom1").feature("pc2").setIndex("coord", "s*zi", 1);
    model.component("comp1").geom("geom1").run("pc2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0", "zi"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ls1", "pc2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("uni1", 5);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("pc1", 1);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex1").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex2").set("pc1", 2);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("del1", "ls2", "ls3", "pc1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"ri", "1"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "zi"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1").set("Mz", "-cpf.Vz");
    model.component("comp1").variable("var1").descr("Mz", "\u8f74\u5411\u9a6c\u8d6b\u6570");
    model.component("comp1").variable("var1").set("pabsn", "abs(lpff.p)/comp1.maxop1(lpff.p)");
    model.component("comp1").variable("var1").descr("pabsn", "\u5f52\u4e00\u5316\u538b\u529b");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6e90\u5e73\u9762");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(5);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5165\u53e3\u5e73\u9762");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7ec8\u7aef\u5e73\u9762");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(4);

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().set(1);
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_ip");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel2");

    model.component("comp1").physics("cpf").prop("ReferenceValues").set("pref", "cpf.rhoref^gamma/gamma");
    model.component("comp1").physics("cpf").prop("ReferenceValues").set("rhoref", "rho0");
    model.component("comp1").physics("cpf").prop("ReferenceValues").set("vref", "M");
    model.component("comp1").physics("cpf").feature("cpf1").set("gamma_mat", "userdef");
    model.component("comp1").physics("cpf").feature("cpf1").set("gamma", "gamma");
    model.component("comp1").physics("cpf").create("nf1", "NormalFlow", 1);
    model.component("comp1").physics("cpf").feature("nf1").selection().named("sel3");
    model.component("comp1").physics("cpf").create("mf1", "MassFlow", 1);
    model.component("comp1").physics("cpf").feature("mf1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4, 7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.015);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u80cc\u666f\u6d41");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "gamma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "gamma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "M", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 -0.5", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5e73\u5747\u6d41\u901f (cpf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "cpf.normV");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(1, 2);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u4e09\u7ef4\u5e73\u5747\u6d41\u901f (cpf)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "cpf.normV");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 0.8, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.8, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "zi", 1, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e73\u5747\u6d41\uff1arho \u548c Mz");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("legendpos", "middleleft");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "rho");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("autodescr", true);
    model.result("pg3").feature("lngr1").set("legendprefix", "Ma = ");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").set("expr", "Mz");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "z");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("autodescr", true);
    model.result("pg3").feature("lngr2").set("legendprefix", "Ma = ");
    model.result("pg3").run();

    model.component("comp1").physics("lpff").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("lpff").prop("PortSweepSettings").set("ModeShapeNormalization", "Power");

    model.component("comp1").multiphysics().create("pfc1", "BackgroundPotentialFlowCoupling", -1);

    model.component("comp1").physics("lpff").create("imp1", "Impedance", 1);
    model.component("comp1").physics("lpff").feature("imp1").selection().set(6, 8);
    model.component("comp1").physics("lpff").feature("imp1").set("Zn", "Zw");

    model.nodeGroup().create("grp1", "Physics", "lpff");
    model.nodeGroup("grp1").label("\u6e90\u5e73\u9762");

    model.component("comp1").physics("lpff").create("port1", "Port", 1);

    model.nodeGroup("grp1").add("port1");

    model.component("comp1").physics("lpff").feature("port1").selection().named("sel1");
    model.component("comp1").physics("lpff").feature("port1").set("PortType", "Annular");
    model.component("comp1").physics("lpff").feature("port1").set("PortExcitation", "on");
    model.component("comp1").physics("lpff").feature("port1").set("IncidentWave", "ModeScale");
    model.component("comp1").physics("lpff").feature("port1").set("Sin", 1);
    model.component("comp1").physics("lpff").create("port2", "Port", 1);

    model.nodeGroup("grp1").add("port2");

    model.component("comp1").physics("lpff").feature("port2").selection().named("sel1");
    model.component("comp1").physics("lpff").feature("port2").set("PortType", "Annular");
    model.component("comp1").physics("lpff").feature("port2").set("n_circ", 1);

    model.nodeGroup().create("grp2", "Physics", "lpff");
    model.nodeGroup("grp2").label("\u7ec8\u7aef\u5e73\u9762");

    model.component("comp1").physics("lpff").create("port3", "Port", 1);

    model.nodeGroup("grp2").add("port3");

    model.component("comp1").physics("lpff").feature("port3").selection().named("sel3");
    model.component("comp1").physics("lpff").feature("port3").set("PortType", "Circular");
    model.component("comp1").physics("lpff").create("port4", "Port", 1);

    model.nodeGroup("grp2").add("port4");

    model.component("comp1").physics("lpff").feature("port4").selection().named("sel3");
    model.component("comp1").physics("lpff").feature("port4").set("PortType", "Circular");
    model.component("comp1").physics("lpff").feature("port4").set("n_circ", 1);

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/cpf", false);
    model.study("std2").feature("freq").setSolveFor("/physics/lpff", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pfc1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u9891\u57df\uff08M = 0\uff0c\u5e26\u886c\u57ab\uff09");
    model.study("std2").feature("freq").set("plist", "f");
    model.study("std2").feature("freq").set("usesol", true);
    model.study("std2").feature("freq").set("notsolmethod", "sol");
    model.study("std2").feature("freq").set("notstudy", "std1");
    model.study("std2").feature("freq").set("notsolnum", 1);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/cpf", false);
    model.study("std3").feature("freq").setSolveFor("/physics/lpff", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/pfc1", true);
    model.study("std3").label("\u7814\u7a76 3 - \u9891\u57df\uff08M = 0\uff0c\u786c\u58c1\uff09");
    model.study("std3").feature("freq").set("plist", "f");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"lpff/imp1"});
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std1");
    model.study("std3").feature("freq").set("notsolnum", 1);
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/cpf", false);
    model.study("std4").feature("freq").setSolveFor("/physics/lpff", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/pfc1", true);
    model.study("std4").label("\u7814\u7a76 4 - \u9891\u57df\uff08M = -0.5\uff0c\u5e26\u886c\u57ab\uff09");
    model.study("std4").feature("freq").set("plist", "f");
    model.study("std4").feature("freq").set("usesol", true);
    model.study("std4").feature("freq").set("notsolmethod", "sol");
    model.study("std4").feature("freq").set("notstudy", "std1");
    model.study("std4").feature("freq").set("notsolnum", 2);
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.study().create("std5");
    model.study("std5").create("freq", "Frequency");
    model.study("std5").feature("freq").setSolveFor("/physics/cpf", false);
    model.study("std5").feature("freq").setSolveFor("/physics/lpff", true);
    model.study("std5").feature("freq").setSolveFor("/multiphysics/pfc1", true);
    model.study("std5").label("\u7814\u7a76 5 - \u9891\u57df\uff08M = -0.5\uff0c\u786c\u58c1\uff09");
    model.study("std5").feature("freq").set("plist", "f");
    model.study("std5").feature("freq").set("useadvanceddisable", true);
    model.study("std5").feature("freq").set("disabledphysics", new String[]{"lpff/imp1"});
    model.study("std5").feature("freq").set("usesol", true);
    model.study("std5").feature("freq").set("notsolmethod", "sol");
    model.study("std5").feature("freq").set("notstudy", "std1");
    model.study("std5").feature("freq").set("notsolnum", 2);
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u5f52\u4e00\u5316\u538b\u529b\uff1aM = 0\uff0c\u5e26\u886c\u57ab");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().set(1);
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "pabsn");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1")
         .set("levels", "0.0001 0.001 0.01 0.02 0.04 0.06 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9");
    model.result("pg4").feature("con1").set("contourtype", "filled");
    model.result("pg4").feature("con1").set("colorscalemode", "logarithmic");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("con2", "con1");
    model.result("pg4").run();
    model.result("pg4").feature("con2").set("contourtype", "lines");
    model.result("pg4").feature("con2").set("coloring", "uniform");
    model.result("pg4").feature("con2").set("color", "black");
    model.result("pg4").feature("con2").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u5f52\u4e00\u5316\u538b\u529b\uff1aM = 0\uff0c\u786c\u58c1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5f52\u4e00\u5316\u538b\u529b\uff1aM = -0.5\uff0c\u5e26\u886c\u57ab");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5f52\u4e00\u5316\u538b\u529b\uff1aM = -0.5\uff0c\u786c\u58c1");
    model.result("pg7").set("data", "dset5");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("applyselectiontodatasetedges", false);
    model.result("pg8").run();
    model.result("pg8").label("\u5f3a\u5ea6\uff1aM = -0.5\uff0c\u5e26\u5185\u886c");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").selection().geom("geom1", 2);
    model.result("pg8").selection().geom("geom1", 2);
    model.result("pg8").selection().set(1);
    model.result("pg8").set("applyselectiontodatasetedges", true);
    model.result("pg8").set("titletype", "label");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "lpff.I_mag");
    model.result("pg8").feature("surf1").set("descr", "\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg8").run();
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("expr", new String[]{"lpff.Ir", "lpff.Iz"});
    model.result("pg8").feature("arws1").set("descr", "\u5f3a\u5ea6");
    model.result("pg8").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("arws1").set("color", "white");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u5f3a\u5ea6\uff1aM = -0.5\uff0c\u786c\u58f0\u573a\u58c1");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4\uff1a\u8870\u51cf");
    model.result().evaluationGroup("eg1").set("data", "none");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "10*log10(lpff.port1.P_in/intop_ip(lpff.Iz))", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "M = 0\uff0c\u5e26\u886c\u57ab", 0);
    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "10*log10(lpff.port1.P_in/intop_ip(lpff.Iz))", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "M = -0.5\uff0c\u5e26\u886c\u57ab", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset4");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 270);
    model.result().dataset("rev2").set("layermethod", "fine");
    model.result().dataset("rev2").set("hasspacevars", true);
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "dset4");
    model.result().dataset("rev3").set("layermethod", "fine");
    model.result().dataset().create("rev4", "Revolve2D");
    model.result().dataset("rev4").set("data", "dset4");
    model.result().dataset("rev4").set("startangle", -90);
    model.result().dataset("rev4").set("revangle", 270);
    model.result().dataset("rev4").set("layermethod", "fine");
    model.result().dataset("rev4").set("endcaps", false);
    model.result().dataset("rev4").selection().geom("geom1", 1);
    model.result().dataset("rev4").selection().geom("geom1", 1);
    model.result().dataset("rev4").selection().set(6, 7, 8);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u7f29\u7565\u56fe");
    model.result("pg10").set("data", "rev2");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "lpff.port1.p_in*exp(i*m*rev2phi)");
    model.result("pg10").feature("surf1").set("colortable", "Wave");
    model.result("pg10").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg10").run();
    model.result("pg10").create("surf2", "Surface");
    model.result("pg10").feature("surf2").set("expr", "lpff.port4.p_out*exp(i*m*rev2phi)");
    model.result("pg10").feature("surf2").set("colortable", "Wave");
    model.result("pg10").feature("surf2").set("colorscalemode", "linearsymmetric");
    model.result("pg10").run();
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg10").feature("str1").set("data", "rev3");
    model.result("pg10").feature("str1").set("expr", new String[]{"lpff.Ir", "lpff.Iphi", "lpff.Iz"});
    model.result("pg10").feature("str1").set("descr", "\u5f3a\u5ea6");
    model.result("pg10").feature("str1").set("startmethod", "coord");
    model.result("pg10").feature("str1").set("xcoord", 0);
    model.result("pg10").feature("str1").set("ycoord", "range(0.1,0.05,1)");
    model.result("pg10").feature("str1").set("zcoord", 1);
    model.result("pg10").feature("str1").set("linetype", "ribbon");
    model.result("pg10").feature("str1").create("col1", "Color");
    model.result("pg10").run();
    model.result("pg10").feature("str1").feature("col1").set("expr", "lpff.I_mag");
    model.result("pg10").feature("str1").feature("col1").set("descr", "\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg10").feature("str1").feature("col1").set("colorscalemode", "logarithmic");
    model.result("pg10").run();
    model.result("pg10").create("surf3", "Surface");
    model.result("pg10").feature("surf3").set("data", "rev4");
    model.result("pg10").feature("surf3").set("expr", "1");
    model.result("pg10").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg10").run();
    model.result("pg10").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg10").feature("surf3").feature("mtrl1").set("family", "chrome");

    model.view("view3").set("ssao", true);
    model.view("view3").set("environmentmap", "envmap_machineshop2");

    model.result("pg10").run();
    model.result("pg10").set("showlegends", false);
    model.result("pg10").run();

    model.title("\u6d41\u7ba1");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e00\u4e2a\u57fa\u4e8e\u6a21\u6001\u58f0\u4f20\u8f93\u3001\u5e26\u8f74\u5bf9\u79f0\u5185\u886c\u7684\u822a\u7a7a\u53d1\u52a8\u673a\u7ba1\u9053\u6a21\u578b\u4e2d\u7684\u58f0\u573a\u3002\u58f0\u6e90\u7531\u8fb9\u754c\u5904\u7684\u5355\u6a21\u6fc0\u53d1\u4ea7\u751f\uff0c\u901a\u8fc7\u4f7f\u7528\u5e26\u5185\u7f6e\u73af\u5f62\u548c\u5706\u5f62\u7aef\u53e3\u9009\u9879\u7684\u7aef\u53e3\u8fb9\u754c\u6761\u4ef6\u6765\u5e94\u7528\u58f0\u6e90\u548c\u975e\u53cd\u5c04\u6761\u4ef6\u3002\u6a21\u578b\u5206\u6790\u5206\u4e24\u6b65\u8fdb\u884c\uff1a\u9996\u5148\u8ba1\u7b97\u80cc\u666f\u5e73\u5747\u6d41\uff08\u53ef\u538b\u7f29\u65e0\u65cb\u52bf\u6d41\uff09\uff0c\u7136\u540e\u4f7f\u7528\u7ebf\u6027\u52bf\u6d41\u65b9\u7a0b\u6c42\u89e3\u6d41\u7ba1\u4e2d\u7684\u58f0\u573a\u3002\u7ed3\u679c\u5c55\u793a\u4e86\u6709/\u65e0\u80cc\u666f\u6d41\u4ee5\u53ca\u786c\u58c1\u548c\u5185\u886c\u7ba1\u58c1\u4e24\u79cd\u60c5\u51b5\u4e0b\u7684\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("flow_duct.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
